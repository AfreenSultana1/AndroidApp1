package sample.com.samplelistapplication.activities;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.Toast;

import java.util.ArrayList;

import sample.com.samplelistapplication.R;
import sample.com.samplelistapplication.datamodels.SampleList;

public class DialogAdapter extends BaseAdapter {
   ArrayList<String> stringArrayList=new ArrayList<>();
    Context context;
    LayoutInflater inflter;
    String value;

    public DialogAdapter(ArrayList<String> names, Context context) {
        this.context = context;
        this.stringArrayList = names;
        inflter = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return stringArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.dialog_item, null);
        final CheckedTextView simpleCheckedTextView = (CheckedTextView) view.findViewById(R.id.text1);
        simpleCheckedTextView.setText(stringArrayList.get(position));
// perform on Click Event Listener on CheckedTextView
        simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleCheckedTextView.isChecked()) {
// set cheek mark drawable and set checked property to false
                    value = "un-Checked";

                    simpleCheckedTextView.setChecked(false);
                } else {
// set cheek mark drawable and set checked property to true
                    value = "Checked";

                    simpleCheckedTextView.setChecked(true);
                }
                Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
