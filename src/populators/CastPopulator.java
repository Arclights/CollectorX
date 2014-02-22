package populators;

import java.util.Arrays;
import java.util.Iterator;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.arclight.collectorx.ImageHandler;
import com.arclight.collectorx.R;
import com.arclight.collectorx.Separators;
import com.arclight.collectorx.moviecontainers.Role;

public class CastPopulator {

	public static void populate(Context context, TableLayout tabLay,
			Role[] cast, boolean showFullCast, Resources res) {

		Iterator<Role> it = Arrays.asList(cast).iterator();
		Role role;
		int rows = 1;
		while (it.hasNext()) {
			role = it.next();
			tabLay.addView(getRow(context, role, res));
			if (!showFullCast && rows == 2) {
				break;
			}
			rows++;
			if (it.hasNext()) {
				tabLay.addView(Separators.getTableSeparator(context));
			}
		}
	}

	private static TableRow getRow(Context context, Role role, Resources res) {
		TableRow out = (TableRow) ((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.cast_table_row, null);
		Bitmap profileImage = ImageHandler.getProfileImage(role.actor.image,
				res);
		if (profileImage != null) {
			((ImageView) out.findViewById(R.id.cast_list_item_image))
					.setImageBitmap(profileImage);
		}
		((TextView) out.findViewById(R.id.cast_list_item_actor))
				.setText(role.actor.name);
		((TextView) out.findViewById(R.id.cast_list_item_role))
				.setText(role.roleName);
		out.setOnClickListener(role.actor);
		return out;
	}
}
