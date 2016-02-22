package com.fanyafeng.nested.ChangeData;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fanyafeng on 2016/2/19,0019.
 */
public class ChangeDataBean implements Parcelable,Comparable{
    private int number;
    private String password;

    public ChangeDataBean(int number, String password) {
        this.number = number;
        this.password = password;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(Object another) {
        return 0;
    }

    protected ChangeDataBean(Parcel in) {
        number = in.readInt();
        password = in.readString();
    }

    public static final Creator<ChangeDataBean> CREATOR = new Creator<ChangeDataBean>() {
        @Override
        public ChangeDataBean createFromParcel(Parcel in) {
            return new ChangeDataBean(in);
        }

        @Override
        public ChangeDataBean[] newArray(int size) {
            return new ChangeDataBean[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(password);
    }
}
