package com.arclight.collectorx.moviecontainers;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class SoundFormat implements Parcelable{
	public String name;
	public Bitmap image;
	
	public SoundFormat(){
		
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

	public SoundFormat(Parcel in) {
		name = in.readString();
		image = in.readParcelable(Bitmap.class.getClassLoader());
	}

	public static final Parcelable.Creator<SoundFormat> CREATOR = new Parcelable.Creator<SoundFormat>() {
		public SoundFormat createFromParcel(Parcel in) {
			return new SoundFormat(in);
		}

		public SoundFormat[] newArray(int size) {
			return new SoundFormat[size];
		}
	};

}
