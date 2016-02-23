package com.fanyafeng.nested.ExpandListView;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by fanyafeng on 2016/2/22,0022.
 */
public class ExpandBean implements Comparable,Parcelable{
    private GroupItemBean Group;
    private List<ChildItemBean> Child;

    public ExpandBean(GroupItemBean group, List<ChildItemBean> child) {
        Group = group;
        Child = child;
    }

    public GroupItemBean getGroup() {
        return Group;
    }

    public void setGroup(GroupItemBean group) {
        Group = group;
    }

    public List<ChildItemBean> getChild() {
        return Child;
    }

    public void setChild(List<ChildItemBean> child) {
        Child = child;
    }

    @Override
    public String toString() {
        return "ExpandBean{" +
                "Group=" + Group +
                ", Child=" + Child +
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
        dest.writeParcelable(Group, flags);
        dest.writeTypedList(Child);
    }

    protected ExpandBean(Parcel in) {
        Group = in.readParcelable(GroupItemBean.class.getClassLoader());
        Child = in.createTypedArrayList(ChildItemBean.CREATOR);
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
