package com.showorld.housestar.model;

/**
 * Created by Aman on 13-07-2015.
 */

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class BasketItem implements Parcelable {

    private int id;
    private String itemName;
    private String quantity;

    public BasketItem() {
        super();
    }

    private BasketItem(Parcel in) {
        super();
        this.id = in.readInt();
        this.itemName = in.readString();
        this.quantity = in.readString();
    }

    @Override
    public String toString() {
        return "BasketComplaint [id=" + id + ", itemName=" + itemName + "," +
                " quantity=" + quantity + "]";
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
        BasketItem other = (BasketItem) obj;
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
        parcel.writeString(getItemName());
        parcel.writeString(getQuantity());
    }

    public static final Parcelable.Creator<BasketItem> CREATOR = new Parcelable.Creator<BasketItem>() {
        public BasketItem createFromParcel(Parcel in) {
            return new BasketItem(in);
        }

        public BasketItem[] newArray(int size) {
            return new BasketItem[size];
        }
    };

}