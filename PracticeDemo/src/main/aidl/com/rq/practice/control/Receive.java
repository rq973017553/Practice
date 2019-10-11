package com.rq.practice.control;

import android.os.Parcel;
import android.os.Parcelable;

public class Receive implements Parcelable {
    private String mID;

    private int mReceive;

    public Receive(){
        // Empty Method
    }

    protected Receive(Parcel in) {
        mID = in.readString();
        mReceive = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mID);
        dest.writeInt(mReceive);
    }

    public void readFromParcel(Parcel in){
        mID = in.readString();
        mReceive = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Receive> CREATOR = new Creator<Receive>() {
        @Override
        public Receive createFromParcel(Parcel in) {
            return new Receive(in);
        }

        @Override
        public Receive[] newArray(int size) {
            return new Receive[size];
        }
    };

    public String getID() {
        return mID;
    }

    public void setID(String id) {
        this.mID = id;
    }

    public int getReceive() {
        return mReceive;
    }

    public void setReceive(int receive) {
        this.mReceive = receive;
    }
}
