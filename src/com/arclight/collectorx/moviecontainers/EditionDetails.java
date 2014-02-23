package com.arclight.collectorx.moviecontainers;

import android.os.Parcel;
import android.os.Parcelable;

public class EditionDetails implements Parcelable {
	public String barcode;
	public int editionReleaseYear;
	public String owner;
	public BoxSet boxSet;
	public String extraFeatures;
	public String condition;

	public EditionDetails() {

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(barcode);
		dest.writeInt(editionReleaseYear);
		dest.writeString(owner);
		dest.writeParcelable(boxSet, flags);
		dest.writeString(extraFeatures);
		dest.writeString(condition);
	}

	public EditionDetails(Parcel in) {
		barcode = in.readString();
		editionReleaseYear = in.readInt();
		owner = in.readString();
		boxSet = in.readParcelable(BoxSet.class.getClassLoader());
		extraFeatures = in.readString();
		condition = in.readString();
	}

	public static final Parcelable.Creator<EditionDetails> CREATOR = new Parcelable.Creator<EditionDetails>() {
		public EditionDetails createFromParcel(Parcel in) {
			return new EditionDetails(in);
		}

		public EditionDetails[] newArray(int size) {
			return new EditionDetails[size];
		}
	};
}
