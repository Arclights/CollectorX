package com.arclight.collectorx.populators;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arclight.collectorx.ImageHandler;
import com.arclight.collectorx.R;
import com.arclight.collectorx.moviecontainers.Region;
import com.arclight.collectorx.moviecontainers.TopInfo;

public class TopInfoPopulator {

	public static void populate(TopInfo topInfo, Activity activity) {
		// Image
		ImageView movieImage = ((ImageView) activity
				.findViewById(R.id.movie_image));

		movieImage.setImageBitmap(ImageHandler.resizePoster(topInfo.poster,
				activity));
		// Title
		((TextView) activity.findViewById(R.id.movie_title))
				.setText(topInfo.title);
		activity.getActionBar().setTitle(topInfo.title);

		// Year
		((TextView) activity.findViewById(R.id.movie_year)).setText("("
				+ topInfo.year + ")");

		// Runtime
		((TextView) activity.findViewById(R.id.movie_runtime)).setText(topInfo
				.getFormattedRuntime());

		// Medium
		((TextView) activity.findViewById(R.id.movie_medium))
				.setCompoundDrawablesWithIntrinsicBounds(
						ImageHandler.getIcon(topInfo.medium,
								activity.getResources()), null, null, null);

		// Regions
		LinearLayout regions = ((LinearLayout) activity
				.findViewById(R.id.movie_regions));
		regions.removeAllViews();
		for (Region region : topInfo.regions) {
			TextView tv = new TextView(activity);
			if (region.image != null) {
				tv.setCompoundDrawablesWithIntrinsicBounds(
						ImageHandler.getIcon(region.image,
								activity.getResources()), null, null, null);
			} else {
				tv.setText(region.name);
			}
			regions.addView(tv);
		}

		// Audience Rating
		if (topInfo.audienceRating.image != null) {
			((TextView) activity.findViewById(R.id.movie_audience_rating))
					.setCompoundDrawablesWithIntrinsicBounds(ImageHandler
							.getIcon(topInfo.audienceRating.image,
									activity.getResources()), null, null, null);
		} else {
			((TextView) activity.findViewById(R.id.movie_audience_rating))
					.setText(topInfo.audienceRating.rating);
		}

		// Ratings
		// IMDB
		((TextView) activity.findViewById(R.id.movie_rating_imdb))
				.setText(topInfo.ratings.ratingIMDB);
		// Metacritic
		((TextView) activity.findViewById(R.id.movie_rating_metascore))
				.setText(topInfo.ratings.ratingMetaScore + "/100");
		// Rotten Tomatoes - Tomato Meter
		TextView rottenT = ((TextView) activity
				.findViewById(R.id.movie_rating_tomatoes));
		rottenT.setText(topInfo.ratings.ratingTomatoMeter + "%");
		if (!topInfo.ratings.ratingTomatoMeter.equals("N/A")
				&& Integer.parseInt(topInfo.ratings.ratingTomatoMeter) < 60) {
			rottenT.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.ic_tomatoes_rotten, 0, 0, 0);
		}
		// Rotten Tomatoes - Tomato User Meter
		((TextView) activity.findViewById(R.id.movie_rating_tomatoes_users))
				.setText(topInfo.ratings.ratingTomatoUserMeter + "%");
	}

}
