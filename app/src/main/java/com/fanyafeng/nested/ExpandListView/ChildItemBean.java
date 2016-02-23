package com.fanyafeng.nested.ExpandListView;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fanyafeng on 2016/2/23,0023.
 */
public class ChildItemBean implements Parcelable,Comparable{
    private boolean childIsChecked;
    private String childImage;
    private String name;
    private int count;

    public ChildItemBean(boolean childIsChecked, String childImage, String name, int count) {
        this.childIsChecked = childIsChecked;
        this.childImage = childImage;
        this.name = name;
        this.count = count;
    }

    public boolean isChildIsChecked() {
        return childIsChecked;
    }

    public void setChildIsChecked(boolean childIsChecked) {
        this.childIsChecked = childIsChecked;
    }

    public String getChildImage() {
        return childImage;
    }

    public void setChildImage(String childImage) {
        this.childImage = childImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ChildItemBean{" +
                "childIsChecked=" + childIsChecked +
                ", childImage='" + childImage + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
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
        dest.writeByte((byte) (childIsChecked ? 1 : 0));
        dest.writeString(childImage);
        dest.writeString(name);
        dest.writeInt(count);
    }

    protected ChildItemBean(Parcel in) {
        childIsChecked = in.readByte() != 0;
        childImage = in.readString();
        name = in.readString();
        count = in.readInt();
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
