package com.arclight.collectorx.populators;

import android.app.Activity;
import android.widget.TextView;

import com.arclight.collectorx.R;
import com.arclight.collectorx.moviecontainers.EditionDetails;

public class EditionDetailsPopulator {
	public static void populate(EditionDetails editionDetails, Activity activity) {
		// Barcode
		((TextView) activity.findViewById(R.id.movie_barcode))
				.setText(editionDetails.barcode);
	}

}
