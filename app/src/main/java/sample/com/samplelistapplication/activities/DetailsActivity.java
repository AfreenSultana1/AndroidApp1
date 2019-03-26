package sample.com.samplelistapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import sample.com.samplelistapplication.R;
import sample.com.samplelistapplication.datamodels.SampleList;

public class DetailsActivity extends AppCompatActivity  {
TextView contactName;
TextView companyName;


SampleList sampleList;
ArrayList<SampleList> sampleListArrayList;
    public static final String NAME = "Name";
    public static final String CONTACT = "Contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Contact Details");
        init();



    }

    private void init() {
        contactName=(TextView) findViewById(R.id.contact_name);
      companyName=  (TextView) findViewById(R.id.company_name);
      sampleList=new SampleList();
//using Bundle
      Bundle bundle=getIntent().getExtras();
      sampleList=bundle.getParcelable("listData");
      contactName.setText(sampleList.fullName);
      companyName.setText(sampleList.companyName);


       //using intent
    /* Intent intent=getIntent();
     String name=intent.getStringExtra("name");
     String company=intent.getStringExtra("company");*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }







}
