package com.fanyafeng.nested.ExpandListView;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fanyafeng on 2016/2/23,0023.
 */
public class ChildItemBean implements Parcelable,Comparable{
    private int count;
    private String name;

    public ChildItemBean(int count, String name) {
        this.count = count;
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChildItemBean{" +
                "count=" + count +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object another) {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeString(name);
    }

    protected ChildItemBean(Parcel in) {
        count = in.readInt();
        name = in.readString();
    }

    public static final Creator<ChildItemBean> CREATOR = new Creator<ChildItemBean>() {
        @Override
        public ChildItemBean createFromParcel(Parcel in) {
            return new ChildItemBean(in);
        }

        @Override
        public ChildItemBean[] newArray(int size) {
            return new ChildItemBean[size];
        }
    };
}
