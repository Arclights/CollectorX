package com.arclight.collectorx.moviecontainers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import android.os.Parcel;
import android.os.Parcelable;

public class TechnicalDetails implements Parcelable {
	public String[] studios;
	public Country country;
	public Language language;
	public ArrayList<String> pictureFormats;
	public ArrayList<String> subtitles;
	public ArrayList<SoundFormat> soundFormats;

	public TechnicalDetails() {

	}

	public String getStudios() {
		StringBuilder out = new StringBuilder();
		Iterator<String> it = Arrays.asList(studios).iterator();
		while (it.hasNext()) {
			out.append(it.next());
			if (it.hasNext()) {
				out.append("/");
			}
		}
		return out.toString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeArray(studios);
		dest.writeParcelable(country, flags);
		dest.writeParcelable(language, flags);
		dest.writeList(pictureFormats);
		dest.writeList(subtitles);
		dest.writeList(soundFormats);
	}

	public TechnicalDetails(Parcel in) {
		studios = (String[]) in.readArray(String.class.getClassLoader());
		country = in.readParcelable(Country.class.getClassLoader());
		language = in.readParcelable(Language.class.getClassLoader());
		pictureFormats = in.readArrayList(String.class.getClassLoader());
		subtitles = in.readArrayList(String.class.getClassLoader());
		soundFormats = in.readArrayList(SoundFormat.class.getClassLoader());
	}

	public static final Parcelable.Creator<TechnicalDetails> CREATOR = new Parcelable.Creator<TechnicalDetails>() {
		public TechnicalDetails createFromParcel(Parcel in) {
			return new TechnicalDetails(in);
		}

		public TechnicalDetails[] newArray(int size) {
			return new TechnicalDetails[size];
		}
	};
}
