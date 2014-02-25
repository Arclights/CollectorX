package com.arclight.collectorx.populators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.arclight.collectorx.ImageHandler;
import com.arclight.collectorx.R;
import com.arclight.collectorx.Separators;
import com.arclight.collectorx.moviecontainers.Person;

public class CrewPopulator {

	private static ArrayList<String> jobs;

	static {
		jobs = new ArrayList<String>();
		jobs.add("Director");
		jobs.add("Producer");
		jobs.add("Writer");
		jobs.add("Musician");
		jobs.add("Cinematographer");
	}

	public static void populate(Context context, TableLayout tabLay,
			HashMap<String, Person[]> crew, boolean showFullCrew) {
		tabLay.removeAllViews();
		Iterator<String> it = jobs.iterator();
		String job;
		while (it.hasNext()) {
			job = it.next();
			tabLay.addView(getRow(context, job, crew.get(job), showFullCrew));
			if (it.hasNext()) {
				tabLay.addView(Separators.getTableSeparator(context));
			}
		}
	}

	private static TableRow getRow(Context context, String job,
			Person[] workers, boolean showFullCrew) {
		TableRow out = (TableRow) ((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.crew_table_row, null);
		((TextView) out.findViewById(R.id.crew_table_item_job)).setText(job);
		populateWorkers(context,
				(TableLayout) out.findViewById(R.id.crew_table_item_workers),
				workers, showFullCrew);
		return out;
	}

	private static void populateWorkers(Context context,
			TableLayout workersLayout, Person[] workers, boolean showFullCrew) {
		int rows = 1;
		Iterator<Person> it = Arrays.asList(workers).iterator();
		Person worker;
		while (it.hasNext()) {
			worker = it.next();
			TableRow tr = (TableRow) ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.crew_table_row_worker, null);
			Bitmap profileImage = ImageHandler.getProfileImage(worker.image,
					context.getResources());
			if (profileImage != null) {
				((ImageView) tr.findViewById(R.id.crew_table_row_worker_image))
						.setImageBitmap(profileImage);
			}
			((TextView) tr.findViewById(R.id.crew_table_row_worker_name))
					.setText(worker.name);
			tr.setOnClickListener(worker);
			workersLayout.addView(tr);
			if (!showFullCrew && rows == 2) {
				break;
			}
			rows++;
		}
	}
}
