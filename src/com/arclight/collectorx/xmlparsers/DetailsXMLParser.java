package com.arclight.collectorx.xmlparsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.arclight.collectorx.moviecontainers.AudienceRating;
import com.arclight.collectorx.moviecontainers.BoxSet;
import com.arclight.collectorx.moviecontainers.Country;
import com.arclight.collectorx.moviecontainers.Language;
import com.arclight.collectorx.moviecontainers.Link;
import com.arclight.collectorx.moviecontainers.MovieDetails;
import com.arclight.collectorx.moviecontainers.Person;
import com.arclight.collectorx.moviecontainers.Ratings;
import com.arclight.collectorx.moviecontainers.Region;
import com.arclight.collectorx.moviecontainers.Role;
import com.arclight.collectorx.moviecontainers.SoundFormat;

public class DetailsXMLParser {
	// We don't use namespaces
	private static final String ns = null;

	public static MovieDetails parse(InputStream inputFile, Activity activity)
			throws XmlPullParserException, IOException {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser parser = factory.newPullParser();

		parser.setInput(inputFile, "utf8");

		MovieDetails movieDetails = new MovieDetails();

		parser.nextTag();
		parser.require(XmlPullParser.START_TAG, ns, "movie");
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("barcode")) {
				movieDetails.editionDetails.barcode = parseBarcode(parser);
			} else if (name.equals("title")) {
				movieDetails.topInfo.title = parseTitle(parser);
			} else if (name.equals("year")) {
				movieDetails.topInfo.year = parseYear(parser);
			} else if (name.equals("poster")) {
				movieDetails.topInfo.poster = parsePoster(parser);
			} else if (name.equals("studios")) {
				movieDetails.technicalDetails.studios = parseStudios(parser);
			} else if (name.equals("genres")) {
				movieDetails.genres = parseGenres(parser);
			} else if (name.equals("country")) {
				movieDetails.technicalDetails.country = parseCountry(parser);
			} else if (name.equals("language")) {
				movieDetails.technicalDetails.language = parseLanguage(parser);
			} else if (name.equals("runtime")) {
				movieDetails.topInfo.runtime = parseRuntime(parser);
			} else if (name.equals("crew")) {
				movieDetails.crew = parseCrew(parser, activity);
			} else if (name.equals("cast")) {
				movieDetails.cast = parseCast(parser, activity);
			} else if (name.equals("plot")) {
				movieDetails.plot = parsePlot(parser);
			} else if (name.equals("ratings")) {
				movieDetails.topInfo.ratings = parseRatings(parser);
			} else if (name.equals("medium")) {
				movieDetails.topInfo.medium = parseMedium(parser);
			} else if (name.equals("editionreleaseyear")) {
				movieDetails.editionDetails.editionReleaseYear = parseEditionReleaseYear(parser);
			} else if (name.equals("owner")) {
				movieDetails.editionDetails.owner = parseOwner(parser);
			} else if (name.equals("condition")) {
				movieDetails.editionDetails.condition = parseCondition(parser);
			} else if (name.equals("boxset")) {
				movieDetails.editionDetails.boxSet = parseBoxSet(parser);
			} else if (name.equals("viewerrating")) {
				movieDetails.topInfo.audienceRating = parseAudienceRating(parser);
			} else if (name.equals("extrafeatures")) {
				movieDetails.editionDetails.extraFeatures = parseExtraFeatures(parser);
			} else if (name.equals("picture_formats")) {
				movieDetails.technicalDetails.pictureFormats = parsePictureFormats(parser);
			} else if (name.equals("subtitles")) {
				movieDetails.technicalDetails.subtitles = parseSubtitles(parser);
			} else if (name.equals("regions")) {
				movieDetails.topInfo.regions = parseRegions(parser);
			} else if (name.equals("sound_formats")) {
				movieDetails.technicalDetails.soundFormats = parseSoundFormats(parser);
			} else if (name.equals("links")) {
				movieDetails.links = parseLinks(parser, activity);
			} else {
				skip(parser);
			}
		}
		return movieDetails;
	}

	private static Bitmap parseMedium(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "medium");
		Bitmap image = readImage(parser);
		parser.require(XmlPullParser.END_TAG, ns, "medium");
		return image;
	}

	private static String parseBarcode(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "barcode");
		String barcode = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "barcode");
		return barcode;
	}

	private static String parseTitle(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "title");
		String title = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "title");
		return title;
	}

	private static String parseOwner(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "owner");
		String owner = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "owner");
		return owner;
	}

	private static String parseCondition(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "condition");
		String condition = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "condition");
		return condition;
	}

	private static int parseYear(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "year");
		int year = readInteger(parser);
		parser.require(XmlPullParser.END_TAG, ns, "year");
		return year;
	}

	private static int parseEditionReleaseYear(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "editionreleaseyear");
		int year = readInteger(parser);
		parser.require(XmlPullParser.END_TAG, ns, "editionreleaseyear");
		return year;
	}

	private static Bitmap parsePoster(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "poster");
		Bitmap poster = readImage(parser);
		parser.require(XmlPullParser.END_TAG, ns, "poster");
		return poster;
	}

	private static String[] parseStudios(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "studios");
		ArrayList<String> studios = new ArrayList<String>();
		parser.nextTag();
		while (parser.getName().equals("studio")) {
			studios.add(parseStudio(parser));
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, "studios");
		return studios.toArray(new String[studios.size()]);
	}

	private static String parseStudio(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "studio");
		String studio = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "studio");
		return studio;
	}

	private static String[] parseGenres(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "genres");
		ArrayList<String> genres = new ArrayList<String>();
		parser.nextTag();
		while (parser.getName().equals("genre")) {
			genres.add(parseGenre(parser));
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, "genres");
		return genres.toArray(new String[genres.size()]);
	}

	private static String parseGenre(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "genre");
		String genre = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "genre");
		return genre;
	}

	private static Country parseCountry(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "country");
		parser.nextTag();
		String name = parseName(parser);
		parser.nextTag();
		Bitmap image = parseImage(parser);
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, ns, "country");
		return new Country(name, image);
	}

	private static Language parseLanguage(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "language");
		parser.nextTag();
		String name = parseName(parser);
		parser.nextTag();
		Bitmap image = parseImage(parser);
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, ns, "language");
		return new Language(name, image);
	}

	private static int parseRuntime(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "runtime");
		int runtime = readInteger(parser);
		parser.require(XmlPullParser.END_TAG, ns, "runtime");
		return runtime;
	}

	private static HashMap<String, Person[]> parseCrew(XmlPullParser parser,
			Activity activity) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "crew");
		HashMap<String, Person[]> crew = new HashMap<String, Person[]>();
		parser.nextTag();
		while (parser.getName().equals("job")) {
			parseJob(parser, crew, activity);
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, "crew");
		return crew;
	}

	private static void parseJob(XmlPullParser parser,
			HashMap<String, Person[]> crew, Activity activity)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "job");
		parser.nextTag();
		String jobName = parseJobName(parser);
		parser.nextTag();
		ArrayList<Person> workers = new ArrayList<Person>();
		while (parser.getName().equals("worker")) {
			workers.add(parsePerson(parser, "worker", activity));
			parser.nextTag();
		}
		crew.put(jobName, workers.toArray(new Person[workers.size()]));
		parser.require(XmlPullParser.END_TAG, ns, "job");
	}

	private static String parseJobName(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "jobname");
		String jobName = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "jobname");
		return jobName;
	}

	private static Person parsePerson(XmlPullParser parser, String xmlTag,
			Activity activity) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, xmlTag);
		parser.nextTag();
		String name = parseName(parser);
		parser.nextTag();
		parser.require(XmlPullParser.START_TAG, ns, "imdburl");
		String imdbUrl = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "imdburl");
		parser.nextTag();
		Bitmap image = parseImage(parser);
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, ns, xmlTag);
		return new Person(name, imdbUrl, image, activity);
	}

	private static Role[] parseCast(XmlPullParser parser, Activity activity)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "cast");
		ArrayList<Role> cast = new ArrayList<Role>();
		parser.nextTag();
		while (parser.getName().equals("role")) {
			cast.add(parseRole(parser, activity));
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, "cast");
		return cast.toArray(new Role[cast.size()]);
	}

	private static Role parseRole(XmlPullParser parser, Activity activity)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "role");
		parser.nextTag();
		Person actor = parsePerson(parser, "actor", activity);
		parser.nextTag();
		String roleName = parseRoleName(parser);
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, ns, "role");
		return new Role(actor, roleName);
	}

	private static String parseRoleName(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "rolename");
		String roleName = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "rolename");
		return roleName;
	}

	private static String parsePlot(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "plot");
		String plot = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "plot");
		return plot;
	}

	private static BoxSet parseBoxSet(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		BoxSet boxSet = new BoxSet();
		parser.require(XmlPullParser.START_TAG, ns, "boxset");
		parser.nextTag();
		boxSet.name = parseName(parser);
		parser.nextTag();
		boxSet.image = parseImage(parser);
		parser.nextTag();
		boxSet.barcode = parseBarcode(parser);
		parser.nextTag();
		boxSet.year = parseYear(parser);
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, ns, "boxset");
		return boxSet;
	}

	private static AudienceRating parseAudienceRating(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		AudienceRating ar = new AudienceRating();
		parser.require(XmlPullParser.START_TAG, ns, "viewerrating");
		parser.nextTag();
		parser.require(XmlPullParser.START_TAG, ns, "rating");
		ar.rating = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "rating");
		parser.nextTag();
		ar.image = parseImage(parser);
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, ns, "viewerrating");
		return ar;
	}

	private static String parseExtraFeatures(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "extrafeatures");
		String ef = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "extrafeatures");
		return ef;
	}

	private static Ratings parseRatings(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		Ratings out = new Ratings();
		parser.require(XmlPullParser.START_TAG, ns, "ratings");
		parser.nextTag();
		parser.require(XmlPullParser.START_TAG, ns, "imdbrating");
		out.ratingIMDB = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "imdbrating");
		parser.nextTag();
		parser.require(XmlPullParser.START_TAG, ns, "metascore");
		out.ratingMetaScore = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "metascore");
		parser.nextTag();
		parser.require(XmlPullParser.START_TAG, ns, "tomatometer");
		out.ratingTomatoMeter = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "tomatometer");
		parser.nextTag();
		parser.require(XmlPullParser.START_TAG, ns, "tomatousermeter");
		out.ratingTomatoUserMeter = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "tomatousermeter");
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, ns, "ratings");
		return out;
	}

	private static ArrayList<String> parsePictureFormats(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		ArrayList<String> picture_formats = new ArrayList<String>();
		parser.require(XmlPullParser.START_TAG, ns, "picture_formats");
		parser.nextTag();
		while (parser.getName().equals("picture_format")) {
			picture_formats.add(parsePictureFormat(parser));
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, "picture_formats");
		return picture_formats;
	}

	private static String parsePictureFormat(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "picture_format");
		String format = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "picture_format");
		return format;
	}

	private static ArrayList<String> parseSubtitles(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		ArrayList<String> subtitles = new ArrayList<String>();
		parser.require(XmlPullParser.START_TAG, ns, "subtitles");
		parser.nextTag();
		while (parser.getName().equals("subtitle")) {
			subtitles.add(parseSubtitle(parser));
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, "subtitles");
		return subtitles;
	}

	private static String parseSubtitle(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "subtitle");
		String subtitle = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "subtitle");
		return subtitle;
	}

	private static ArrayList<Region> parseRegions(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		ArrayList<Region> regions = new ArrayList<Region>();
		parser.require(XmlPullParser.START_TAG, ns, "regions");
		parser.nextTag();
		while (parser.getName().equals("region")) {
			regions.add(parseRegion(parser));
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, "regions");
		return regions;
	}

	private static Region parseRegion(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		Region region = new Region();
		parser.require(XmlPullParser.START_TAG, ns, "region");
		parser.nextTag();
		region.name = parseName(parser);
		parser.nextTag();
		region.image = parseImage(parser);
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, ns, "region");
		return region;
	}

	private static ArrayList<SoundFormat> parseSoundFormats(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		ArrayList<SoundFormat> soundFormats = new ArrayList<SoundFormat>();
		parser.require(XmlPullParser.START_TAG, ns, "sound_formats");
		parser.nextTag();
		while (parser.getName().equals("sound_format")) {
			soundFormats.add(parseSoundFormat(parser));
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, "sound_formats");
		return soundFormats;
	}

	private static SoundFormat parseSoundFormat(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		SoundFormat soundFormat = new SoundFormat();
		parser.require(XmlPullParser.START_TAG, ns, "sound_format");
		parser.nextTag();
		soundFormat.name = parseName(parser);
		parser.nextTag();
		soundFormat.image = parseImage(parser);
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, ns, "sound_format");
		return soundFormat;
	}

	private static ArrayList<Link> parseLinks(XmlPullParser parser,
			Activity activity) throws XmlPullParserException, IOException {
		ArrayList<Link> links = new ArrayList<Link>();
		parser.require(XmlPullParser.START_TAG, ns, "links");
		parser.nextTag();
		while (parser.getName().equals("link")) {
			links.add(parseLink(parser, activity));
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, "links");
		return links;
	}

	private static Link parseLink(XmlPullParser parser, Activity activity)
			throws XmlPullParserException, IOException {
		Link link = new Link(activity);
		parser.require(XmlPullParser.START_TAG, ns, "link");
		parser.nextTag();
		link.title = parseTitle(parser);
		parser.nextTag();
		parser.require(XmlPullParser.START_TAG, ns, "url");
		link.url = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "url");
		parser.nextTag();
		parser.require(XmlPullParser.START_TAG, ns, "type");
		link.type = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "type");
		parser.nextTag();
		parser.require(XmlPullParser.END_TAG, ns, "link");
		return link;
	}

	private static Bitmap parseImage(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "image");
		Bitmap image = readImage(parser);
		parser.require(XmlPullParser.END_TAG, ns, "image");
		return image;
	}

	private static String parseName(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "name");
		String name = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "name");
		return name;
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

	private static Bitmap readImage(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		String image = readText(parser);
		if (!image.equals("")) {
			byte[] bytes = Base64.decode(image, Base64.DEFAULT);
			return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		}
		return null;
	}

	private static void skip(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		if (parser.getEventType() != XmlPullParser.START_TAG) {
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0) {
			switch (parser.next()) {
			case XmlPullParser.END_TAG:
				depth--;
				break;
			case XmlPullParser.START_TAG:
				depth++;
				break;
			}
		}
	}
}
