package com.fanyafeng.nested.ExpandListView;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fanyafeng on 2016/2/23,0023.
 */
public class GroupItemBean implements Parcelable,Comparable{
    private boolean groupIsChecked;
    private String groupImage;
    private String groupName;
    private boolean groupIsCoupon;
    private boolean groupIsEdit;

    public GroupItemBean(boolean groupIsChecked, String groupImage, String groupName, boolean groupIsCoupon, boolean groupIsEdit) {
        this.groupIsChecked = groupIsChecked;
        this.groupImage = groupImage;
        this.groupName = groupName;
        this.groupIsCoupon = groupIsCoupon;
        this.groupIsEdit = groupIsEdit;
    }

    public boolean isGroupIsChecked() {
        return groupIsChecked;
    }

    public void setGroupIsChecked(boolean groupIsChecked) {
        this.groupIsChecked = groupIsChecked;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isGroupIsCoupon() {
        return groupIsCoupon;
    }

    public void setGroupIsCoupon(boolean groupIsCoupon) {
        this.groupIsCoupon = groupIsCoupon;
    }

    public boolean isGroupIsEdit() {
        return groupIsEdit;
    }

    public void setGroupIsEdit(boolean groupIsEdit) {
        this.groupIsEdit = groupIsEdit;
    }

    @Override
    public String toString() {
        return "GroupItemBean{" +
                "groupIsChecked=" + groupIsChecked +
                ", groupImage='" + groupImage + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupIsCoupon=" + groupIsCoupon +
                ", groupIsEdit=" + groupIsEdit +
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
        dest.writeByte((byte) (groupIsChecked ? 1 : 0));
        dest.writeString(groupImage);
        dest.writeString(groupName);
        dest.writeByte((byte) (groupIsCoupon ? 1 : 0));
        dest.writeByte((byte) (groupIsEdit ? 1 : 0));
    }

    protected GroupItemBean(Parcel in) {
        groupIsChecked = in.readByte() != 0;
        groupImage = in.readString();
        groupName = in.readString();
        groupIsCoupon = in.readByte() != 0;
        groupIsEdit = in.readByte() != 0;
    }

    public static final Creator<GroupItemBean> CREATOR = new Creator<GroupItemBean>() {
        @Override
        public GroupItemBean createFromParcel(Parcel in) {
            return new GroupItemBean(in);
        }

        @Override
        public GroupItemBean[] newArray(int size) {
            return new GroupItemBean[size];
        }
    };

}
