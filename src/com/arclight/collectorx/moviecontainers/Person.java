package com.arclight.collectorx.moviecontainers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

public class Person implements android.view.View.OnClickListener {

	public String name;
	public String imdbUrl;
	public Bitmap image;
	private Activity activity;

	public Person(String name, String imdbUrl, Bitmap image, Activity activity) {
		this.name = name;
		this.imdbUrl = imdbUrl;
		this.image = image;
		this.activity = activity;
	}

	public Person() {

	}

	@Override
	public void onClick(View v) {
		Uri webpage = Uri.parse(imdbUrl);
		Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
		if (intent.resolveActivity(activity.getPackageManager()) != null) {
			activity.startActivity(intent);
		}
	}

}
