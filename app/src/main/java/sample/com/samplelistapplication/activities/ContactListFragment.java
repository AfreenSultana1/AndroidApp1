package sample.com.samplelistapplication.activities;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import sample.com.samplelistapplication.R;
import sample.com.samplelistapplication.adapters.OnRecyclerItemClickListener;
import sample.com.samplelistapplication.adapters.RecyclerItemTouchHelper;
import sample.com.samplelistapplication.adapters.RecyclerItemTouchHelperListener;
import sample.com.samplelistapplication.adapters.SampleRecyclerAdapter;
import sample.com.samplelistapplication.datamodels.SampleList;
import sample.com.samplelistapplication.datamodels.Utils;
import sharedPreferences.SharedValues;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment implements RecyclerItemTouchHelperListener, SearchView.OnQueryTextListener, OnRecyclerItemClickListener {
    public static final String TAG = ContactListFragment.class.getName();
    RecyclerView recyclerView;
    SampleRecyclerAdapter sampleRecyclerAdapter;
    CoordinatorLayout coordinatorLayout;
    CoordinatorLayout snakbarLayout;
    // SearchView searchView;
    MainActivity mainActivity;
    ArrayList<SampleList> sampleListArrayList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    ArrayList<SampleList> favLists = new ArrayList<>();
    ImageView button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.mainActivity_recyclerView_list);
        //  searchView = view.findViewById(R.id.mainActivity_searchView);

//        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator_layout);
        snakbarLayout = (CoordinatorLayout) view.findViewById(R.id.contact_coordinator);
        button = view.findViewById(R.id.fav_contact);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
        mainActivity = (MainActivity) getActivity();


        init();


        Collections.sort(sampleListArrayList, new CustomComparator());



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);

    }


    private void init() {
        Log.d(TAG, "init: ");
        LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity, OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        sampleListArrayList = Utils.getInstance().getSampleListArrayList();

//        SampleList sampleList = new SampleList();
//        try {
//            JSONArray jsonArray = new JSONArray();
//            for (int i=0;i<sampleListArrayList.size();i++) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("company_name", sampleList.companyName);
//                jsonObject.put("full_name", sampleList.fullName);
//                jsonObject.put("is_Fav", sampleList.isFav);
//                jsonArray.put(jsonObject);
//            }
//            for(int i=0;i<sampleListArrayList.size();i++){
//                if (sampleList.isFav) {
//                    SharedValues.getInstance(getActivity()).setFavContacts(jsonArray);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        sampleRecyclerAdapter = new SampleRecyclerAdapter(mainActivity, sampleListArrayList);
        recyclerView.setAdapter(sampleRecyclerAdapter);
        sampleRecyclerAdapter.notifyDataSetChanged();
        sampleRecyclerAdapter.setOnRecyclerItemClickListener(this);


        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, (RecyclerItemTouchHelperListener) this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (sampleRecyclerAdapter != null) {
            sampleRecyclerAdapter.getFilter().filter(newText);
        }
        return false;
    }

    @Override
    public void onItemClick(int position) {
       /* DialogFragment departmentFragment = new DialogFragment();
        departmentFragment.show(mainActivity.getSupportFragmentManager(), "departmentFragment");
*/

        Intent intent = new Intent(mainActivity, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("listData", sampleListArrayList.get(position));
        intent.putExtras(bundle);
        intent.putExtra("name", sampleListArrayList.get(position).fullName);
        intent.putExtra("company", sampleListArrayList.get(position).companyName);
        startActivity(intent);


    }

    @Override
    public void onItemLongClick(int position) {


    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof SampleRecyclerAdapter.SampleViewHolder) {
            String name = sampleListArrayList.get(viewHolder.getAdapterPosition()).fullName;

            final SampleList deletedItem = sampleListArrayList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();


            sampleRecyclerAdapter.removeItem(viewHolder.getAdapterPosition());


            Snackbar snackbar = Snackbar.make(snakbarLayout, name, Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sampleRecyclerAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();


        }
    }


    /*This class is to sort the list in alphabetical order*/
    public class CustomComparator implements Comparator<SampleList> {
        @Override
        public int compare(SampleList sampleList, SampleList t1) {
            return sampleList.fullName.compareTo(t1.fullName);
        }
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
             mainActivity.finish();
                return true;
        }
        return false;
    }*/


}
