package com.arclight.collectorx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class Separators {

	public static View getTableSeparator(Context context) {
		return ((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.table_separator_gradient, null);
	}

}
