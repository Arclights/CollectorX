package com.arclight.collectorx.moviecontainers;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {

	public String name;
	public Bitmap image;

	public Country(String name, Bitmap image) {
		this.name = name;
		this.image = image;
	}

	public Country() {

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
	}

	public Country(Parcel in) {
		name = in.readString();
		image = in.readParcelable(Bitmap.class.getClassLoader());
	}

	public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
		public Country createFromParcel(Parcel in) {
			return new Country(in);
		}

		public Country[] newArray(int size) {
			return new Country[size];
		}
	};

}
