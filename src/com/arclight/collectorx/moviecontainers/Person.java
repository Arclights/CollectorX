package com.arclight.collectorx.moviecontainers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class Person implements android.view.View.OnClickListener, Parcelable {

	public String name;
	public String imdbUrl;
	public Bitmap image;

	public Person(String name, String imdbUrl, Bitmap image, Activity activity) {
		this.name = name;
		this.imdbUrl = imdbUrl;
		this.image = image;
	}

	public Person() {

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(imdbUrl);
		dest.writeParcelable(image, flags);
	}

	public Person(Parcel in) {
		name = in.readString();
		imdbUrl = in.readString();
		image = in.readParcelable(Bitmap.class.getClassLoader());
	}

	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
		public Person createFromParcel(Parcel in) {
			return new Person(in);
		}

		public Person[] newArray(int size) {
			return new Person[size];
		}
	};

	@Override
	public void onClick(View v) {
		Uri webpage = Uri.parse(imdbUrl);
		Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
		if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
			v.getContext().startActivity(intent);
		}
	}

}
