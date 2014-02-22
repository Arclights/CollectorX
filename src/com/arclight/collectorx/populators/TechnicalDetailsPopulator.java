package com.arclight.collectorx.populators;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arclight.collectorx.ImageHandler;
import com.arclight.collectorx.R;
import com.arclight.collectorx.moviecontainers.SoundFormat;
import com.arclight.collectorx.moviecontainers.TechnicalDetails;

public class TechnicalDetailsPopulator {
	public static void populate(TechnicalDetails technicalDetails,
			Activity activity) {
		TextView tv;
		// Production Company
		((TextView) activity.findViewById(R.id.movie_production_co))
				.setText(technicalDetails.getStudios());
		// Country
		tv = ((TextView) activity.findViewById(R.id.movie_country));
		tv.setText(technicalDetails.country.name);
		tv.setCompoundDrawablesWithIntrinsicBounds(
				ImageHandler.getIcon(technicalDetails.country.image,
						activity.getResources()), null, null, null);
		// Language
		tv = ((TextView) activity.findViewById(R.id.movie_language));
		tv.setText(technicalDetails.language.name);
		tv.setCompoundDrawablesWithIntrinsicBounds(
				ImageHandler.getIcon(technicalDetails.language.image,
						activity.getResources()), null, null, null);

		// Picture Formats
		LinearLayout pictureFormats = ((LinearLayout) activity
				.findViewById(R.id.movie_picture_formats));
		for (String pictureFormat : technicalDetails.pictureFormats) {
			tv = new TextView(activity);
			tv.setText(pictureFormat);
			pictureFormats.addView(tv);
		}

		// Subtitles
		LinearLayout subtitles = ((LinearLayout) activity
				.findViewById(R.id.movie_subtitles));
		for (String subtitle : technicalDetails.subtitles) {
			tv = new TextView(activity);
			tv.setText(subtitle);
			subtitles.addView(tv);
		}

		// Picture Formats
		LinearLayout soundFormats = ((LinearLayout) activity
				.findViewById(R.id.movie_sound_formats));
		for (SoundFormat soundFormat : technicalDetails.soundFormats) {
			tv = new TextView(activity);
			if (soundFormat.image != null) {
				tv.setCompoundDrawablesWithIntrinsicBounds(
						ImageHandler.getIcon(soundFormat.image,
								activity.getResources()), null, null, null);
			}
			tv.setText(soundFormat.name);
			soundFormats.addView(tv);
		}
	}
}
