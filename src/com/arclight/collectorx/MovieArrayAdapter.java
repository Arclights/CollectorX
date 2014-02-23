package com.arclight.collectorx;

import java.util.ArrayList;
import java.util.Comparator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.arclight.collectorx.moviecontainers.MovieListItem;

public class MovieArrayAdapter extends ArrayAdapter<MovieListItem> implements
		SectionIndexer {

	private static String sections = "abcdefghilmnopqrstuvz";

	public MovieArrayAdapter(Context context, int resource,
			int textViewResourceId, ArrayList<MovieListItem> objects) {
		super(context, resource, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View out = convertView;
		if (out == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			out = vi.inflate(R.layout.movie_list_item_layout, null);
		}
		MovieListItem movie = getItem(position);
		ImageView iv = (ImageView) out.findViewById(R.id.movie_list_image);
		iv.setImageBitmap(movie.thumbnail);

		((TextView) out.findViewById(R.id.movie_list_name)).setText(movie
				.getTitleYear());

		return out;
	}

	@Override
	public int getPositionForSection(int section) {
		Log.d("ListView", "Get position for section");
		for (int i = 0; i < this.getCount(); i++) {
			String item = this.getItem(i).title.toLowerCase(getContext().getResources().getConfiguration().locale);
			if (item.charAt(0) == sections.charAt(section))
				return i;
		}
		return 0;
	}

	@Override
	public int getSectionForPosition(int arg0) {
		Log.d("ListView", "Get section");
		return 0;
	}

	@Override
	public Object[] getSections() {
		Log.d("ListView", "Get sections");
		String[] sectionsArr = new String[sections.length()];
		for (int i = 0; i < sections.length(); i++)
			sectionsArr[i] = "" + sections.charAt(i);

		return sectionsArr;

	}

	public void update(ArrayList<MovieListItem> list) {
		addAll(list);
		sort(getComparator());
		notifyDataSetChanged();
	}

	private Comparator<MovieListItem> getComparator() {
		return new Comparator<MovieListItem>() {

			@Override
			public int compare(MovieListItem lhs, MovieListItem rhs) {
				return lhs.title.compareTo(rhs.title);
			}
		};
	}
}
