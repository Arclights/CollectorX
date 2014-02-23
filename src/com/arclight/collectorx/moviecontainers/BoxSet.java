package com.arclight.collectorx.moviecontainers;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class BoxSet implements Parcelable {
	public String name;
	public Bitmap image;
	public String barcode;
	public int year;

	public BoxSet() {

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeParcelable(image, flags);
		dest.writeString(barcode);
		dest.writeInt(year);
	}

	public BoxSet(Parcel in) {
		name = in.readString();
		image = in.readParcelable(Bitmap.class.getClassLoader());
		barcode = in.readString();
		year = in.readInt();
	}

	public static final Parcelable.Creator<BoxSet> CREATOR = new Parcelable.Creator<BoxSet>() {
		public BoxSet createFromParcel(Parcel in) {
			return new BoxSet(in);
		}

		public BoxSet[] newArray(int size) {
			return new BoxSet[size];
		}
	};

}
