package com.arclight.collectorx.populators;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arclight.collectorx.R;
import com.arclight.collectorx.Separators;
import com.arclight.collectorx.moviecontainers.Link;

public class LinksPopulator {
	public static void populate(ArrayList<Link> links, Activity activity) {
		LinearLayout ll = ((LinearLayout) activity
				.findViewById(R.id.movie_links));
		Iterator<Link> it = links.iterator();
		while (it.hasNext()) {
			Link link = it.next();
			View v = ((LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(android.R.layout.simple_list_item_1, null);
			TextView tv = (TextView) v.findViewById(android.R.id.text1);
			System.out.println(link.title);
			tv.setText(link.title);
			tv.setOnClickListener(link);
			ll.addView(v);
			if (it.hasNext()) {
				ll.addView(Separators.getTableSeparator(activity));
			}
		}
	}
}
