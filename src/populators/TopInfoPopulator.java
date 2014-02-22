package populators;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arclight.collectorx.ImageHandler;
import com.arclight.collectorx.R;
import com.arclight.collectorx.moviecontainers.Region;
import com.arclight.collectorx.moviecontainers.TopInfo;

public class TopInfoPopulator {
	private static double movieImageRatio = 1.5;
	private static double movieImageToDispWithRatio = 0.3;

	public static void populate(TopInfo topInfo, Activity activity) {
		// Image
		ImageView movieImage = ((ImageView) activity
				.findViewById(R.id.movie_image));
		WindowManager wm = (WindowManager) activity
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = (int) (display.getWidth() * movieImageToDispWithRatio);
		int height = (int) (display.getWidth() * movieImageToDispWithRatio * movieImageRatio);
		movieImage.setImageBitmap(Bitmap.createScaledBitmap(topInfo.poster,
				width, height, false));
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
		if (topInfo.ratings.ratingTomatoMeter.compareTo("60") < 0) {
			rottenT.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.ic_tomatoes_rotten, 0, 0, 0);
		}
		// Rotten Tomatoes - Tomato User Meter
		((TextView) activity.findViewById(R.id.movie_rating_tomatoes_users))
				.setText(topInfo.ratings.ratingTomatoUserMeter + "%");
	}

}
