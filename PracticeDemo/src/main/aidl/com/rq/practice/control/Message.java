package com.rq.practice.control;

import android.os.Parcel;
import android.os.Parcelable;

// https://www.jianshu.com/p/a8e43ad5d7d2
// 实现Parcelable可以使用alt+enter
public class Message implements Parcelable {

    private String mID;

    private int mCommand;

    public Message(){
        // Empty Method
    }

    protected Message(Parcel in) {
        mID = in.readString();
        mCommand = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mID);
        dest.writeInt(mCommand);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public String getID() {
        return mID;
    }

    public void setID(String id) {
        this.mID = id;
    }

    public int getCommand() {
        return mCommand;
    }

    public void setCommand(int command) {
        this.mCommand = command;
    }
}
