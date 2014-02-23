package com.arclight.collectorx.moviecontainers;

import android.os.Parcel;
import android.os.Parcelable;

public class Ratings implements Parcelable {

	public String ratingIMDB = "N/A";
	public String ratingTomatoMeter = "N/A";
	public String ratingTomatoUserMeter = "N/A";
	public String ratingMetaScore = "N/A";

	public Ratings(String ratingIMDB, String ratingTomatoMeter,
			String ratingTomatoUserMeter, String ratingMetaScore) {
		this.ratingIMDB = ratingIMDB;
		this.ratingTomatoMeter = ratingTomatoMeter;
		this.ratingTomatoUserMeter = ratingTomatoUserMeter;
		this.ratingMetaScore = ratingMetaScore;
	}

	public Ratings() {

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(ratingIMDB);
		dest.writeString(ratingMetaScore);
		dest.writeString(ratingTomatoMeter);
		dest.writeString(ratingTomatoUserMeter);
	}

	public Ratings(Parcel in) {
		ratingIMDB = in.readString();
		ratingMetaScore = in.readString();
		ratingTomatoMeter = in.readString();
		ratingTomatoUserMeter = in.readString();
	}

	public static final Parcelable.Creator<Ratings> CREATOR = new Parcelable.Creator<Ratings>() {
		public Ratings createFromParcel(Parcel in) {
			return new Ratings(in);
		}

		public Ratings[] newArray(int size) {
			return new Ratings[size];
		}
	};

}
