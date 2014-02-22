package com.arclight.collectorx.moviecontainers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class MovieDetails implements Serializable {
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

	// public String getTechnicalInfo() {
	// return country + " / " + language + " / " + color + " / "
	// + getFormattedRuntime();
	// }

	// @Override
	// public String toString() {
	// return title;
	// }

}
