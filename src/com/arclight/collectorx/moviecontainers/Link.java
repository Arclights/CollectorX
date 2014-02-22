package com.arclight.collectorx.moviecontainers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

public class Link implements OnClickListener {
	public String title;
	public String url;
	public String type;
	private Activity activity;

	public Link(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		Uri webpage = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
		if (intent.resolveActivity(activity.getPackageManager()) != null) {
			activity.startActivity(intent);
		}
	}
}
