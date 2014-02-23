package com.arclight.collectorx.moviecontainers;

import android.os.Parcel;
import android.os.Parcelable;

public class Role implements Parcelable{

	public String roleName;
	public Person actor;

	public Role(Person actor, String roleName) {
		this.roleName = roleName;
		this.actor = actor;
	}

	public Role() {

	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(roleName);
		dest.writeParcelable(actor, flags);
	}

	public Role(Parcel in) {
		roleName = in.readString();
		actor = in.readParcelable(Person.class.getClassLoader());
	}

	public static final Parcelable.Creator<Role> CREATOR = new Parcelable.Creator<Role>() {
		public Role createFromParcel(Parcel in) {
			return new Role(in);
		}

		public Role[] newArray(int size) {
			return new Role[size];
		}
	};

}
