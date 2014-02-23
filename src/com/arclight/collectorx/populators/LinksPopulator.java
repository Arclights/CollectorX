package com.arclight.collectorx.populators;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
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
			TextView tv = ((TextView) ((LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.link_list_item, null));
			tv.setText(link.title);
			tv.setOnClickListener(link);
			ll.addView(tv);
			if (it.hasNext()) {
				ll.addView(Separators.getTableSeparator(activity));
			}
		}
	}
}
