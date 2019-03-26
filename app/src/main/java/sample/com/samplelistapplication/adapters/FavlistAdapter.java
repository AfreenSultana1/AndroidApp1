package sample.com.samplelistapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sample.com.samplelistapplication.R;
import sample.com.samplelistapplication.datamodels.SampleList;

public class FavlistAdapter extends BaseAdapter {
    ArrayList<SampleList> sampleListArrayList = new ArrayList<>();

    public FavlistAdapter(ArrayList<SampleList> favArrayList) {
        this.sampleListArrayList = favArrayList;
    }

    @Override
    public int getCount() {
        return sampleListArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return sampleListArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        View view = null;
        if (viewHolder == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlist, null);
            convertView.setTag(view);
        } else {
            view = (View) convertView.getTag();
        }
        viewHolder.updateObj(sampleListArrayList.get(position));

        return view;
    }

    public class ViewHolder {
        TextView companyName;
        TextView contactName;

        public ViewHolder(View view) {
            companyName = view.findViewById(R.id.company_name);
            contactName = view.findViewById(R.id.name);

        }

        public void updateObj(SampleList sampleList) {
            contactName.setText(sampleList.fullName);
            companyName.setText(sampleList.companyName);
        }
    }
}
