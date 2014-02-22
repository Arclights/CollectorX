package com.arclight.collectorx.moviecontainers;

public class Ratings {

	public String ratingIMDB = "N/A";
	public String ratingTomatoMeter = "N/A";
	public String ratingTomatoUserMeter = "N/A";
	public String ratingMetaScore = "N/A";

	public Ratings(String ratingIMDB, String ratingTomatoMeter,
			String ratingTomatoUserMeter, String ratingMetaScore) {
		this.ratingIMDB = ratingIMDB;
		this.ratingTomatoMeter = ratingTomatoMeter;
		this.ratingTomatoUserMeter = ratingTomatoUserMeter;
		this.ratingMetaScore = ratingMetaScore;
	}

	public Ratings() {

	}

}
