package sample.com.samplelistapplication.datamodels;

import android.content.Context;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import sharedPreferences.SharedValues;

public class Utils {
    private static final Utils UTILS = new Utils();

    public synchronized static Utils getInstance() {
        return UTILS;
    }


    public ArrayList<SampleList> getSampleListArrayList() {
        return sampleListArrayList;
    }

    public void setSampleListArrayList(ArrayList<SampleList> sampleListArrayList) {
        this.sampleListArrayList = sampleListArrayList;
    }

    ArrayList<SampleList> sampleListArrayList = new ArrayList<>();

    public static ArrayList<SampleList> getSelectedFavContacts(Context context) {
        ArrayList<SampleList> list = new ArrayList<>();
        try {
            String favContacts = SharedValues.getInstance(context).getFavContacts();
            JSONArray jsonArray = new JSONArray(favContacts);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SampleList sampleList = new SampleList();
                sampleList.companyName = jsonObject.getString("company_name");
                sampleList.fullName = jsonObject.getString("full_name");

                list.add(sampleList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

}
