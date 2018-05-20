package by.epam.vshop.service.validation;

import java.util.regex.Pattern;

public final class FilmValidator {
	private FilmValidator() {
	}

	public static boolean validFilm(String title, String cover, String url, String trailerUrl, String year,
			String price, String shortDescription, String longDescription, String[] genres) {

		if (validString(title) && validString(url) && validString(trailerUrl) && validNumber(year) && validNumber(price)
				&& validString(shortDescription) && validString(longDescription) && validGenre(genres)
				&& validString(cover)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validFilm(String title, String cover, String url, String trailerUrl, String year,
			String price, String shortDescription, String longDescription, String[] genres, String id) {

		if (validString(title) && validString(url) && validString(trailerUrl) && validNumber(year) && validNumber(price)
				&& validString(shortDescription) && validString(longDescription) && validGenre(genres)
				&& validString(cover) && validNumber(id)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean validString(String line) {
		if (line == null || line.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean validGenre(String[] genres) {
		boolean result = true;
		if (genres != null) {
			for (String genre : genres) {
				if (!validNumber(genre)) {
					result = false;
					break;
				}
			}
		}

		return result;
	}

	private static boolean validNumber(String number) {
		if ((number.length() == 0)||(number.equals("0"))) {
			return false;
		}
		return Pattern.matches(RegularExpression.POSITIVE_FLOAT_PATTERN, number);
	}

}
