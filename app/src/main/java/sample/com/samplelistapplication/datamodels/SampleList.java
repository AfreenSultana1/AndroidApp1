package sample.com.samplelistapplication.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

public class SampleList implements Parcelable {
    public String fullName;
    public String companyName;
    public String country;
    public String state;
    public String phone;
    public boolean isFav;
    public boolean isChecked;

    public SampleList() {

    }

    public SampleList(String fullName, String companyName, String country, String state,String phone) {
        this.fullName = fullName;
        this.companyName = companyName;
        this.country = country;
        this.state = state;
        this.phone=phone;
    }

    public SampleList(Parcel in) {
        fullName = in.readString();
        companyName = in.readString();
        country = in.readString();
        state = in.readString();
        phone=in.readString();
    }

    public static final Creator<SampleList> CREATOR = new Creator<SampleList>() {
        @Override
        public SampleList createFromParcel(Parcel in) {
            return new SampleList(in);
        }

        @Override
        public SampleList[] newArray(int size) {
            return new SampleList[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fullName);
        parcel.writeString(companyName);
        parcel.writeString(country);
        parcel.writeString(state);
        parcel.writeString(phone);
    }
}
