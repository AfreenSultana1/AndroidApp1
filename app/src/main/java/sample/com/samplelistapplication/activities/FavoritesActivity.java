package sample.com.samplelistapplication.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import sample.com.samplelistapplication.R;
import sample.com.samplelistapplication.adapters.SampleRecyclerAdapter;
import sample.com.samplelistapplication.datamodels.SampleList;

public class FavoritesActivity extends AppCompatActivity {
RecyclerView recyclerView;
SampleRecyclerAdapter sampleRecyclerAdapter;
ArrayList<SampleList> sampleListArrayList=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_favorite);
       recyclerView= (RecyclerView) findViewById(R.id.favActivity_recyclerView_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, OrientationHelper.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        sampleRecyclerAdapter = new SampleRecyclerAdapter(this, sampleListArrayList);
        recyclerView.setAdapter(sampleRecyclerAdapter);
    }
}
