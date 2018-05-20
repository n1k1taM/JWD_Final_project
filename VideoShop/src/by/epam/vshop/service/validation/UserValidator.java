package by.epam.vshop.service.validation;

import by.epam.vshop.service.DataValidater;

public final class UserValidator {

	private UserValidator() {
	}

	public static boolean validUser(String login, String firstname, String lastname, String email, String password,
			String confirmPassword) {

		if (validString(login) && validString(login) && validString(login) && validString(login)
				&& validString(password) && DataValidater.comparisonLines(password, confirmPassword)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validString(String line) {
		if (line == null || line.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
