package by.epam.vshop.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DataValidater {

	private DataValidater() {
		super();
	}

	static public boolean validatePositiveInteger(String intVar) {
		if (intVar == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\d+$");
		Matcher matcher = pattern.matcher(intVar);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	static public boolean validatePositiveIntegers(String intVar1, String intVar2) {
		return validatePositiveInteger(intVar1) && validatePositiveInteger(intVar2);
	}

	static public boolean validatePositiveIntegers(String intVar1, String intVar2, String intVar3) {
		return validatePositiveInteger(intVar1) && validatePositiveInteger(intVar2) && validatePositiveInteger(intVar3);
	}

	public static boolean comparisonLines(String line1, String line2) {
		return line1.equals(line2);
	}
	
	public static boolean isStringEmpty(String line){
		if (line.length() == 0) {
			return true;
		}
		return false;
	}
	
	public static final boolean validatePositiveFloat(String floatVar){
		if ((floatVar == null)|| (floatVar.length() == 0)) {
			return false;
		}
		Pattern pattern = Pattern.compile(RegularExpression.POSITIVE_FLOAT_PATTERN);
		Matcher matcher = pattern.matcher(floatVar);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	public static boolean validatePositiveInteger(int var) {
		if (var < 0) {
			return false;
		}
		return true;
	}

	public static boolean validateBoolean(String strUserStatus) {
		if (strUserStatus.equals("true")) {
			return true;
		}
		if (strUserStatus.equals("false")) {
			return true;
		}
		return false;
	}
}
