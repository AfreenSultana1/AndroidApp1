package sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sample.com.samplelistapplication.datamodels.SampleList;

public class SharedValues {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static SharedValues instance;
    private Context context;


    public SharedValues(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
    }

    public synchronized static SharedValues getInstance(Context context) {
        if (instance == null) {
            instance = new SharedValues(context);
        }
        return instance;

    }

    public String getFavContacts() {
        return sharedPreferences.getString("favContacts", new JSONArray().toString());

    }

    public void setFavContacts(JSONArray jsonArray) {
        editor = sharedPreferences.edit();
        editor.putString("favContacts", jsonArray.toString());
        editor.commit();
    }


}
