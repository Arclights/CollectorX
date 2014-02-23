package com.arclight.collectorx.moviecontainers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieDetails implements Serializable, Parcelable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4489835256201586022L;

	public String[] genres;
	public HashMap<String, Person[]> crew;
	public Role[] cast;
	public String plot;
	public ArrayList<Link> links;
	public TopInfo topInfo;
	public EditionDetails editionDetails;
	public TechnicalDetails technicalDetails;

	public MovieDetails() {
		topInfo = new TopInfo();
		editionDetails = new EditionDetails();
		technicalDetails = new TechnicalDetails();
	}

	public String getGenres() {
		StringBuilder out = new StringBuilder();
		Iterator<String> it = Arrays.asList(genres).iterator();
		while (it.hasNext()) {
			out.append(it.next());
			if (it.hasNext()) {
				out.append(", ");
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
	public void writeToParcel(Parcel out, int flags) {
		out.writeArray(genres);
		out.writeMap(crew);
		out.writeArray(cast);
		out.writeString(plot);
		out.writeList(links);
		out.writeParcelable(topInfo, flags);
		out.writeParcelable(editionDetails, flags);
		out.writeParcelable(technicalDetails, flags);
	}

	public MovieDetails(Parcel in) {
		genres = (String[]) in.readArray(String.class.getClassLoader());
		in.readMap(crew, HashMap.class.getClassLoader());
		cast = (Role[]) in.readArray(Role.class.getClassLoader());
		plot = in.readString();
		links = in.readArrayList(Link.class.getClassLoader());
		topInfo = in.readParcelable(TopInfo.class.getClassLoader());
		editionDetails = in.readParcelable(EditionDetails.class
				.getClassLoader());
		technicalDetails = in.readParcelable(TechnicalDetails.class
				.getClassLoader());
	}

	public static final Parcelable.Creator<MovieDetails> CREATOR = new Parcelable.Creator<MovieDetails>() {
		public MovieDetails createFromParcel(Parcel in) {
			return new MovieDetails(in);
		}

		public MovieDetails[] newArray(int size) {
			return new MovieDetails[size];
		}
	};

	// public String getTechnicalInfo() {
	// return country + " / " + language + " / " + color + " / "
	// + getFormattedRuntime();
	// }

	// @Override
	// public String toString() {
	// return title;
	// }

}
