package com.arclight.collectorx.xmlparsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.arclight.collectorx.moviecontainers.MovieListItem;

public class ListXMLParser {
	// We don't use namespaces
	private static final String ns = null;

	public static ArrayList<MovieListItem> parse(InputStream input)
			throws XmlPullParserException, IOException {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser parser = factory.newPullParser();

		parser.setInput(input, "utf8");
		ArrayList<MovieListItem> mli = new ArrayList<MovieListItem>();
		parser.nextTag();
		parser.require(XmlPullParser.START_TAG, ns, "movielist");

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			mli.add(parseMovieListItem(parser));
		}
		parser.require(XmlPullParser.END_TAG, ns, "movielist");
		return mli;
	}

	private static MovieListItem parseMovieListItem(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "movielistitem");
		String id = "";
		String title = "";
		int year = 0;
		Bitmap thumbnail = null;

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("id")) {
				id = parseid(parser);
			} else if (name.equals("title")) {
				title = parseTitle(parser);
			} else if (name.equals("year")) {
				year = parseYear(parser);
			} else if (name.equals("thumbnail")) {
				thumbnail = parseThumbnail(parser);
			}
		}
		parser.require(XmlPullParser.END_TAG, ns, "movielistitem");
		return new MovieListItem(id, title, year, thumbnail);
	}

	private static String parseid(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "id");
		String id = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "id");
		return id;
	}

	private static String parseTitle(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "title");
		String title = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "title");
		return title;
	}

	private static int parseYear(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "year");
		int year = readInteger(parser);
		parser.require(XmlPullParser.END_TAG, ns, "year");
		return year;
	}

	private static Bitmap parseThumbnail(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "thumbnail");
		String thumbnail = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "thumbnail");
		byte[] bytes = Base64.decode(thumbnail, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	private static String readText(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText().replaceAll("\\s\\s+", " ");
			parser.nextTag();
		}
		return result;
	}

	private static int readInteger(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		int result = 0;
		if (parser.next() == XmlPullParser.TEXT) {
			result = Integer.parseInt(parser.getText());
			parser.nextTag();
		}
		return result;
	}
}
