package com.arclight.collectorx.moviecontainers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TechnicalDetails {
	public String[] studios;
	public Country country;
	public Language language;
	public ArrayList<String> pictureFormats;
	public ArrayList<String> subtitles;
	public ArrayList<SoundFormat> soundFormats;
	
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
}
