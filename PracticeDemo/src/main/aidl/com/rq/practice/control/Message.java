package com.rq.practice.control;

import android.os.Parcel;
import android.os.Parcelable;

// https://www.jianshu.com/p/a8e43ad5d7d2
// 实现Parcelable可以使用alt+enter
public class Message implements Parcelable {

    private String mCommand;

    private int mInitialValue;

    public String getCommand() {
        return mCommand;
    }

    public void setCommand(String command) {
        this.mCommand = command;
    }

    public int getInitialValue() {
        return mInitialValue;
    }

    public void setInitialValue(int initialValue) {
        this.mInitialValue = initialValue;
    }

    protected Message(Parcel parcel) {
        mInitialValue = parcel.readInt();
        mCommand = parcel.readString();
    }

    public Message() {
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel parcel) {
            return new Message(parcel);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mInitialValue);
        parcel.writeString(mCommand);
    }
}
