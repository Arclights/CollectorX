package com.arclight.collectorx;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.arclight.collectorx.moviecontainers.MovieDetails;
import com.arclight.collectorx.populators.CastPopulator;
import com.arclight.collectorx.populators.CrewPopulator;
import com.arclight.collectorx.populators.EditionDetailsPopulator;
import com.arclight.collectorx.populators.LinksPopulator;
import com.arclight.collectorx.populators.TechnicalDetailsPopulator;
import com.arclight.collectorx.populators.TopInfoPopulator;
import com.arclight.collectorx.xmlparsers.DetailsXMLParser;

/**
 * A fragment representing a single Movie detail screen. This fragment is either
 * contained in a {@link MovieListActivity} in two-pane mode (on tablets) or a
 * {@link MovieDetailActivity} on handsets.
 */
public class MovieDetailFragment extends Fragment implements OnClickListener {
	private boolean showFullCast = false;
	private boolean showFullCrew = false;
	TableLayout castTabLay;
	Button showCast;
	TableLayout crewTabLay;
	Button showCrew;

	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	// public static final String ARG_ITEM_ID = "item_id";
	public static final String ARG_ID = "movie_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private MovieDetails mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public MovieDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
			mItem = savedInstanceState.getParcelable("movie_details");
		} else {
			if (getArguments().containsKey(ARG_ID)) {
				new MovieDetailRetriever().execute(getArguments().getString(
						ARG_ID));
			}
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable("movie_details", mItem);
	}

	private void updateContent() {
		if (mItem != null) {

			TopInfoPopulator.populate(mItem.topInfo, getActivity());

			// Genres
			((TextView) getActivity().findViewById(R.id.movie_genres))
					.setText(mItem.getGenres());

			// Plot
			((TextView) getActivity().findViewById(R.id.movie_plot))
					.setText(mItem.plot);

			// Cast
			castTabLay = (TableLayout) getActivity().findViewById(
					R.id.movie_cast);
			CastPopulator.populate((Context) getActivity(), castTabLay,
					mItem.cast, showFullCast, getResources());
			castTabLay.setColumnShrinkable(1, true);

			showCast = (Button) getActivity()
					.findViewById(R.id.movie_show_cast);
			showCast.setOnClickListener(this);

			// Crew
			crewTabLay = (TableLayout) getActivity().findViewById(
					R.id.movie_crew);
			CrewPopulator.populate((Context) getActivity(), crewTabLay,
					mItem.crew, showFullCrew);
			crewTabLay.setColumnShrinkable(1, true);

			showCrew = (Button) getActivity()
					.findViewById(R.id.movie_show_crew);
			showCrew.setOnClickListener(this);

			// Technical info
			TechnicalDetailsPopulator.populate(mItem.technicalDetails,
					getActivity());

			// Edition info
			EditionDetailsPopulator.populate(mItem.editionDetails,
					getActivity());

			// Links
			LinksPopulator.populate(mItem.links, getActivity());
		}
	}

	@Override
	public void onClick(View view) {
		if (view == showCast) {
			showFullCast = !showFullCast;
			if (showFullCast) {
				showCast.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.ic_action_collapse, 0, 0);
			} else {
				showCast.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.ic_action_expand, 0, 0);
			}
			castTabLay.removeAllViews();
			CastPopulator.populate((Context) getActivity(), castTabLay,
					mItem.cast, showFullCast, getResources());
			castTabLay.invalidate();
		} else if (view == showCrew) {
			showFullCrew = !showFullCrew;
			if (showFullCrew) {
				showCrew.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.ic_action_collapse, 0, 0);
			} else {
				showCrew.setCompoundDrawablesWithIntrinsicBounds(0,
						R.drawable.ic_action_expand, 0, 0);
			}
			crewTabLay.removeAllViews();
			CrewPopulator.populate((Context) getActivity(), crewTabLay,
					mItem.crew, showFullCrew);
			crewTabLay.invalidate();
		}
	}

	public class MovieDetailRetriever extends
			AsyncTask<String, Void, MovieDetails> {
		private String data_get_movie_details = "MOVIE_DETAILS";

		@Override
		protected MovieDetails doInBackground(String... params) {
			try {
				Socket kkSocket = new Socket(
						InetAddress.getByName("192.168.0.7"), 9999);
				PrintWriter out = new PrintWriter(kkSocket.getOutputStream(),
						true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						kkSocket.getInputStream()));
				out.println(data_get_movie_details + "\t" + params[0]);
				StringBuilder movieDetails = new StringBuilder();
				String inString;
				while ((inString = in.readLine()) != null) {
					movieDetails.append(inString);
					movieDetails.append("\n");
				}
				kkSocket.close();
				System.out.println("Downloaded details");

				MovieDetails movie = DetailsXMLParser.parse(
						new ByteArrayInputStream(movieDetails.toString()
								.getBytes()), getActivity());
				// RatingRetriever.getRatings(movie);
				return movie;
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(MovieDetails result) {
			mItem = result;
			updateContent();
			((ProgressBar) getActivity().findViewById(R.id.progBar))
					.setVisibility(ProgressBar.INVISIBLE);
			((ViewGroup) getActivity().findViewById(R.id.movie_detail_fragment))
					.setVisibility(ViewGroup.VISIBLE);
			((ViewGroup) getActivity().findViewById(R.id.movie_top_info))
					.invalidate();
		}
	}
}
