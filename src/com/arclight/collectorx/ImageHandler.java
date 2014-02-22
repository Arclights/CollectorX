package com.arclight.collectorx;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class ImageHandler {

	public static BitmapDrawable getIcon(Bitmap icon, Resources res) {
		return new BitmapDrawable(res, resizeIcon(icon, 18, res));
	}

	private static Bitmap resizeIcon(Bitmap icon, int iconBaseSize,
			Resources res) {
		// For downloaded icons
		if (icon != null) {
			float density = res.getDisplayMetrics().density;
			int imageWidth = icon.getWidth();
			int imageHeight = icon.getHeight();
			int imageDimen;
			if (imageWidth > imageHeight) {
				imageDimen = imageWidth;
			} else {
				imageDimen = imageHeight;
			}
			double scaling = imageDimen / (iconBaseSize * density);
//			System.out.println("Density: " + density);
//			System.out.println("Original: " + imageHeight + "x" + imageWidth);
//			System.out.println("Scaled: " + imageHeight / scaling + "x"
//					+ imageWidth / scaling);
			return Bitmap.createScaledBitmap(icon,
					(int) (imageWidth / scaling),
					(int) (imageHeight / scaling), false);
		} else {
			return null;
		}
	}

	public static Bitmap getProfileImage(Bitmap profilePicture, Resources res) {
		return resizeProfilePicture(profilePicture, 48, res);
	}

	private static Bitmap resizeProfilePicture(Bitmap profilePicture,
			int iconBaseSize, Resources res) {
		// For downloaded icons
		if (profilePicture != null) {
			float density = res.getDisplayMetrics().density;
			int imageWidth = profilePicture.getWidth();
			int imageHeight = profilePicture.getHeight();
			double scaling;
			if (imageWidth > imageHeight) {
				scaling = imageWidth / (48 * density);
			} else {
				scaling = imageHeight / (69 * density);
			}
//			System.out.println("Density: " + density);
//			System.out.println("Original: " + imageHeight + "x" + imageWidth);
//			System.out.println("Scaled: " + imageHeight / scaling + "x"
//					+ imageWidth / scaling);
			return cropProfleImage(Bitmap.createScaledBitmap(profilePicture,
					(int) (imageWidth / scaling),
					(int) (imageHeight / scaling), false), density);
		} else {
			return null;
		}
	}

	private static Bitmap cropProfleImage(Bitmap profilePicture, float density) {
		int newHeight = (int) (69 * density);
		int newWidth = (int) (48 * density);
		if (newWidth > profilePicture.getWidth()) {
			newWidth = profilePicture.getWidth();
		}
		if (newHeight > profilePicture.getHeight()) {
			newHeight = profilePicture.getHeight();
		}
		return Bitmap.createBitmap(profilePicture,
				(profilePicture.getWidth() - newWidth) / 2,
				(profilePicture.getHeight() - newHeight) / 2, newWidth,
				newHeight);
	}

}
