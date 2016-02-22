package com.fanyafeng.nested.ExpandListView;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by fanyafeng on 2016/2/22,0022.
 */
public class ExpandBean implements Comparable,Parcelable{
    private String Group;
    private List<String> Child;

    public ExpandBean(String group, List<String> child) {
        Group = group;
        Child = child;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public List<String> getChild() {
        return Child;
    }

    public void setChild(List<String> child) {
        Child = child;
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
        dest.writeString(Group);
        dest.writeStringList(Child);
    }

    protected ExpandBean(Parcel in) {
        Group = in.readString();
        Child = in.createStringArrayList();
    }

    public static final Creator<ExpandBean> CREATOR = new Creator<ExpandBean>() {
        @Override
        public ExpandBean createFromParcel(Parcel in) {
            return new ExpandBean(in);
        }

        @Override
        public ExpandBean[] newArray(int size) {
            return new ExpandBean[size];
        }
    };
}
