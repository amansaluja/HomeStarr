package com.showorld.housestar.model;

/**
 * Created by Aman on 13-07-2015.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ProcedureList implements Parcelable {

    private int id;

    private String itemList;
    private String SelectProcedure;
    private String Comment;
    private String TimeHours;
    private String TimeMinutes;


    public ProcedureList() {
        super();
    }

    private ProcedureList(Parcel in) {
        super();
        this.id = in.readInt();
        this.itemList = in.readString();
        this.SelectProcedure = in.readString();
        this.Comment = in.readString();
        this.TimeHours = in.readString();
        this.TimeMinutes = in.readString();
    }

    @Override
    public String toString() {
        return "ProcedureList [id=" + id + ", itemList=" + itemList + "," +
                " SelectProcedure=" + SelectProcedure + "," + " Comment="  + Comment + "," +
                " TimeHours=" + TimeHours + "," + " TimeMinutes" + TimeMinutes + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProcedureList other = (ProcedureList) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getItemList());
        parcel.writeString(getSelectProcedure());
        parcel.writeString(getComment());
        parcel.writeString(getTimeHours());
        parcel.writeString(getTimeMinutes());
    }

    public static final Creator<ProcedureList> CREATOR = new Creator<ProcedureList>() {
        public ProcedureList createFromParcel(Parcel in) {
            return new ProcedureList(in);
        }

        public ProcedureList[] newArray(int size) {
            return new ProcedureList[size];
        }
    };

}