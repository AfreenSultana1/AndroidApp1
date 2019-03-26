package sample.com.samplelistapplication.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sample.com.samplelistapplication.R;
import sample.com.samplelistapplication.adapters.SampleRecyclerAdapter;
import sample.com.samplelistapplication.datamodels.SampleList;
import sample.com.samplelistapplication.datamodels.Utils;

public class FavListFragment extends Fragment {
    static final String TAG = FavListFragment.class.getName();
    RecyclerView recyclerView;
    SampleRecyclerAdapter sampleRecyclerAdapter;
    CoordinatorLayout coordinatorLayout;
    ArrayList<SampleList> favList = new ArrayList<>();
    MainActivity mainActivity;

    SampleList sampleList=new SampleList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_fav_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
        mainActivity = (MainActivity) getActivity();
        ArrayList<SampleList> sampleListArrayList = Utils.getInstance().getSampleListArrayList();
        for (SampleList sampleList : sampleListArrayList) {
            if (sampleList.isFav) {
                sampleList.isChecked=true;
                favList.add(sampleList);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity, OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        sampleRecyclerAdapter = new SampleRecyclerAdapter(mainActivity, favList);
        recyclerView.setAdapter(sampleRecyclerAdapter);
        sampleRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.mainActivity_recyclerView_list);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_nav, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_deleteContact:

                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
