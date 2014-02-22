package com.arclight.collectorx.moviecontainers;

import android.graphics.Bitmap;

public class MovieListItem {
	public String id;
	public String title;
	public int year;
	public Bitmap thumbnail;

	public MovieListItem(String id, String title, int year,
			Bitmap thumbnail) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.thumbnail = thumbnail;
	}

	public String getTitleYear() {
		return title + " (" + year + ")";
	}

}
