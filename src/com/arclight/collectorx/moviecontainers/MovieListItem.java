package com.arclight.collectorx.moviecontainers;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class MovieListItem implements Parcelable {
	public String id;
	public String title;
	public int year;
	public Bitmap thumbnail;

	public MovieListItem(String id, String title, int year, Bitmap thumbnail) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.thumbnail = thumbnail;
	}

	public MovieListItem(Parcel in) {
		id = in.readString();
		title = in.readString();
		year = in.readInt();
		thumbnail = in.readParcelable(Bitmap.class.getClassLoader());
	}

	public String getTitleYear() {
		return title + " (" + year + ")";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(title);
		dest.writeInt(year);
		dest.writeParcelable(thumbnail, flags);
	}

	public static final Parcelable.Creator<MovieListItem> CREATOR = new Parcelable.Creator<MovieListItem>() {
		public MovieListItem createFromParcel(Parcel in) {
			return new MovieListItem(in);
		}

		public MovieListItem[] newArray(int size) {
			return new MovieListItem[size];
		}
	};

}
