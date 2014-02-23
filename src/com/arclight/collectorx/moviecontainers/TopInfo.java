package com.arclight.collectorx.moviecontainers;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class TopInfo implements Parcelable {
	public String title;
	public int year;
	public Bitmap poster;
	public int runtime;
	public Ratings ratings;
	public AudienceRating audienceRating;
	public Bitmap medium;
	public ArrayList<Region> regions;
	
	public TopInfo(){
		
	}

	public String getTitleYear() {
		return title + " (" + year + ")";
	}

	public String getFormattedRuntime() {
		return runtime / 60 + "h " + runtime % 60 + "m";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeInt(year);
		dest.writeParcelable(poster, flags);
		dest.writeInt(runtime);
		dest.writeParcelable(ratings, flags);
		dest.writeParcelable(audienceRating, flags);
		dest.writeParcelable(medium, flags);
		dest.writeList(regions);
	}

	public TopInfo(Parcel in) {
		title = in.readString();
		year = in.readInt();
		poster = in.readParcelable(Bitmap.class.getClassLoader());
		runtime = in.readInt();
		ratings = in.readParcelable(Ratings.class.getClassLoader());
		audienceRating = in.readParcelable(AudienceRating.class
				.getClassLoader());
		medium = in.readParcelable(Bitmap.class.getClassLoader());
		regions = in.readArrayList(Region.class.getClassLoader());
	}

	public static final Parcelable.Creator<TopInfo> CREATOR = new Parcelable.Creator<TopInfo>() {
		public TopInfo createFromParcel(Parcel in) {
			return new TopInfo(in);
		}

		public TopInfo[] newArray(int size) {
			return new TopInfo[size];
		}
	};
}
