package com.arclight.collectorx.moviecontainers;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class TopInfo {
	public String title;
	public int year;
	public Bitmap poster;
	public int runtime;
	public Ratings ratings;
	public AudienceRating audienceRating;
	public Bitmap medium;
	public ArrayList<Region> regions;
	
	public String getTitleYear() {
		return title + " (" + year + ")";
	}
	
	public String getFormattedRuntime() {
		return runtime / 60 + "h " + runtime % 60 + "m";
	}
}
