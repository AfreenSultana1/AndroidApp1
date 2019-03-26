package sample.com.samplelistapplication.activities;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sample.com.samplelistapplication.R;
import sample.com.samplelistapplication.datamodels.SampleList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends android.support.v4.app.DialogFragment {
    ListView listView;
    MainActivity mainActivity;
     ArrayList<String> list;
    //CheckedTextView checkedTextView;

    public DialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      listView = view.findViewById(R.id.options_list);
        //checkedTextView=view.findViewById(R.id.options_list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
   }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("My Title");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        String s[] = new String[]{"a", "b", "c", "a", "b", "c"};
        list = new ArrayList<String>();
        for (int i = 0; i < s.length; ++i) {
            list.add(s[i]);
        }
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        builder.setView(view);
        ListView listView=view.findViewById(R.id.options_list);

        if(getActivity() instanceof MainActivity){
            mainActivity= (MainActivity) getActivity();
        }
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(mainActivity, android.R.layout.select_dialog_item, list);
            listView.setAdapter(itemsAdapter);

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }
}


