package sample.com.samplelistapplication.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import sample.com.samplelistapplication.R;
import sample.com.samplelistapplication.datamodels.SampleList;
import sample.com.samplelistapplication.datamodels.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    public static final String TAG=MainActivity.class.getName();
    BottomNavigationView bottomNavigationView;
    ArrayList<SampleList> sampleLists = new ArrayList<>();
    android.support.v4.app.Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        getContactsData();
        init();


    }
    public void getContactsData() {
        ArrayList<SampleList> sampleListArrayList = new ArrayList<>();
        String jsonFileContent = loadAssestsData();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonFileContent);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String firstName = jsonObj.getString("first_name");
                String companyName = jsonObj.getString("company_name");
                String country = jsonObj.getString("county");
                String phone = jsonObj.getString("phone1");
                sampleListArrayList.add(new SampleList(firstName, companyName, country, null, phone));
            }
            Utils.getInstance().setSampleListArrayList(sampleListArrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private String loadAssestsData() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("data.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;

    }

    private void init() {
        Log.d(TAG, "init: ");
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.mainActivity_bottomNav);


        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(new ContactListFragment());




    }

    private void loadFragment(android.support.v4.app.Fragment fragment) {
        Log.d(TAG, "loadFragment: ");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.container_frag, fragment);
        fragmentTransaction.commit();

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 101:
                if (data != null) {
                    if (resultCode == RESULT_OK) {
                        String name = data.getStringExtra("name");
                        String company = data.getStringExtra("company");
                        sampleLists.add(new SampleList(name, company, null, null, null));

                    }
                }
                break;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.notification_button:
                addNotification();
                break;*/
        }

    }

    private void addNotification() {
        int numMessages = 0;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).
                setContentText("this is small notifiction")
                .setSmallIcon(R.drawable.whatshot)
                .setTicker("notification ticker example ").setNumber(++numMessages)
                .setContentTitle("New Message Arrived");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);


        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle(builder);
        inboxStyle.setBigContentTitle("Big Notification title");
        String[] events = new String[6];
        events[0] = new String("first");
        events[1] = new String("second");
        events[2] = new String("third");
        events[3] = new String("fourth");
        events[4] = new String("fifth");
        for (int i = 0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }

        builder.setStyle(inboxStyle);


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

        /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(this, NotificationViewActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationViewActivity.class);

        /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(resultPendingIntent);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        /* notificationID allows you to update the notification later on. */
        int notificationID = 0;
        manager.notify(notificationID, builder.build());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.men_add:
                getSupportActionBar().setTitle("Contacts List Fragment");
                fragment = new ContactListFragment();
                loadFragment(fragment);
                return true;
            case R.id.men_con:


                break;
            case R.id.men_fav:
                getSupportActionBar().setTitle("Favorites List");
                fragment = new FavListFragment();
                loadFragment(fragment);
                break;

        }
        return false;
    }
}
