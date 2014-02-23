package com.arclight.collectorx.moviecontainers;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class AudienceRating implements Parcelable {
	public String rating;
	public Bitmap image;

	public AudienceRating() {

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(rating);
		dest.writeParcelable(image, flags);
	}

	public AudienceRating(Parcel in) {
		rating = in.readString();
		image = in.readParcelable(Bitmap.class.getClassLoader());
	}

	public static final Parcelable.Creator<AudienceRating> CREATOR = new Parcelable.Creator<AudienceRating>() {
		public AudienceRating createFromParcel(Parcel in) {
			return new AudienceRating(in);
		}

		public AudienceRating[] newArray(int size) {
			return new AudienceRating[size];
		}
	};
}
