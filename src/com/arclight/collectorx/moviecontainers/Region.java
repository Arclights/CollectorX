package com.arclight.collectorx.moviecontainers;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Region implements Parcelable{
	public String name;
	public Bitmap image;
	
	public Region(){
		
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

	public Region(Parcel in) {
		name = in.readString();
		image = in.readParcelable(Bitmap.class.getClassLoader());
	}

	public static final Parcelable.Creator<Region> CREATOR = new Parcelable.Creator<Region>() {
		public Region createFromParcel(Parcel in) {
			return new Region(in);
		}

		public Region[] newArray(int size) {
			return new Region[size];
		}
	};
}
