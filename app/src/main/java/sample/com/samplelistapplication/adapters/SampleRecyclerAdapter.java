package sample.com.samplelistapplication.adapters;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import sample.com.samplelistapplication.R;
import sample.com.samplelistapplication.activities.FavListFragment;
import sample.com.samplelistapplication.datamodels.SampleList;
import sharedPreferences.SharedValues;

public class SampleRecyclerAdapter extends RecyclerView.Adapter<SampleRecyclerAdapter.SampleViewHolder> implements Filterable {

    Context context;

    ArrayList<SampleList> sampleListArrayList;
    ArrayList<SampleList> tempList;
    ArrayList<SampleList> favList;

    public OnRecyclerItemClickListener itemClickListener;


    public SampleRecyclerAdapter(Context context, ArrayList<SampleList> sampleListArrayList) {
        this.context = context;
        this.sampleListArrayList = sampleListArrayList;
        this.tempList = sampleListArrayList;


    }

    public SampleRecyclerAdapter(Context context, ArrayList<SampleList> favList, boolean r) {
        this.context = context;
        this.favList = favList;
    }


    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.itemClickListener = onRecyclerItemClickListener;
    }


    @NonNull
    @Override
    public SampleRecyclerAdapter.SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlist, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleRecyclerAdapter.SampleViewHolder holder, final int position) {
        final SampleList list = sampleListArrayList.get(position);
        holder.updateObject(list, position);
    }

    @Override
    public int getItemCount() {
        if (sampleListArrayList != null) {
            return sampleListArrayList.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new SearchSampleList();
    }

    public class SampleViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView companyName;
        ImageView favContact;
        private ImageView deleteIcon;
        RelativeLayout backgroundView, foreGroundView;
        CheckBox selectContact;

        private SampleViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            companyName = (TextView) itemView.findViewById(R.id.company_name);
            favContact = (ImageView) itemView.findViewById(R.id.fav_contact);
            favContact.setTag(R.drawable.star);
            foreGroundView = (RelativeLayout) itemView.findViewById(R.id.descriptionLayout_foreGround);
            backgroundView = (RelativeLayout) itemView.findViewById(R.id.view_backGround);
            deleteIcon = (ImageView) itemView.findViewById(R.id.delete_icon);
            selectContact = itemView.findViewById(R.id.select_contact_checkBox);
        }

        private void updateObject(final SampleList sampleList, final int position) {

            name.setText(sampleList.fullName);
            companyName.setText(sampleList.phone);

            if (sampleList.isFav) {
                favContact.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp));
            } else {
                favContact.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.star));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(position);
                    }
                }
            });


            favContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if ((int) favContact.getTag() == R.drawable.star) {
                        sampleList.isFav = true;
                        favContact.setTag(R.drawable.ic_star_black_24dp);
                        favContact.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp));

                    } else {
                        sampleList.isFav = false;
                        favContact.setTag(R.drawable.star);
                        favContact.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.star));
                        notifyDataSetChanged();
                    }

                }
            });

            selectContact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {

                    }
                }
            });
        }
    }

    public void removeItem(int position) {

        sampleListArrayList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(SampleList item, int position) {
        sampleListArrayList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }


    public class SearchSampleList extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            ArrayList<SampleList> filterList = new ArrayList<>();

            if (charSequence != null || charSequence.length() >= 1) {
                // String filterString = charSequence.toString();
                for (int i = 0; i < tempList.size(); i++) {
                    if (tempList.get(i).fullName.toLowerCase().contains(charSequence.toString())) {
                        filterList.add(tempList.get(i));
                    }
                }
                filterResults.count = filterList.size();
                filterResults.values = filterList;

            } else {
                filterResults.count = filterList.size();
                filterResults.values = filterList;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            sampleListArrayList = (ArrayList<SampleList>) filterResults.values;
            notifyDataSetChanged();
            if (sampleListArrayList != null) {
                Toast.makeText(context, "Found " + " " + filterResults.count + " " + "Records", Toast.LENGTH_SHORT).show();
            }

        }

    }


}

