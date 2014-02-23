package com.arclight.collectorx.moviecontainers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;

public class Link implements OnClickListener, Parcelable {
	public String title;
	public String url;
	public String type;

	public Link(Activity activity) {
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(url);
		dest.writeString(type);
	}

	public Link(Parcel in) {
		title = in.readString();
		url = in.readString();
		type = in.readString();
	}

	public static final Parcelable.Creator<Link> CREATOR = new Parcelable.Creator<Link>() {
		public Link createFromParcel(Parcel in) {
			return new Link(in);
		}

		public Link[] newArray(int size) {
			return new Link[size];
		}
	};

	@Override
	public void onClick(View v) {
		Uri webpage = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
		if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
			v.getContext().startActivity(intent);
		}
	}
}
