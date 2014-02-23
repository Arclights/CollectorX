package com.arclight.collectorx.moviecontainers;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Language implements Parcelable {

	public String name;
	public Bitmap image;

	public Language(String name, Bitmap image) {
		this.name = name;
		this.image = image;
	}

	public Language() {

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

	public Language(Parcel in) {
		name = in.readString();
		image = in.readParcelable(Bitmap.class.getClassLoader());
	}

	public static final Parcelable.Creator<Language> CREATOR = new Parcelable.Creator<Language>() {
		public Language createFromParcel(Parcel in) {
			return new Language(in);
		}

		public Language[] newArray(int size) {
			return new Language[size];
		}
	};

}
