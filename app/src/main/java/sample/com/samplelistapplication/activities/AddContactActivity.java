package sample.com.samplelistapplication.activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import sample.com.samplelistapplication.R;
import sample.com.samplelistapplication.datamodels.SampleList;

import static sample.com.samplelistapplication.activities.DetailsActivity.CONTACT;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText company;
    Button save;
    Button clear;
    ArrayList<SampleList> sampleListArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(" Add Contact Details");

        save = (Button) findViewById(R.id.save);
        clear = (Button) findViewById(R.id.clear);

        name = (EditText) findViewById(R.id.edit_name);
        company = (EditText) findViewById(R.id.edit_company);

        save.setOnClickListener(this);
        clear.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                String editedName = name.getText().toString();
                String editedCompany = company.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("name", editedName);
                intent.putExtra("company", editedCompany);

                setResult(RESULT_OK, intent);

                finish();
                break;
            case R.id.clear:

                name.setText("");
                company.setText("");

                break;
        }

    }
}
