package org.sagarmala.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidation {
	private static final String EMAIL_PATTERN = "^(.+)@(.+)$";
	private static final String MOBILE_PATTERN = "^[6-9]{1}[0-9]{9}$";
	private static final String INTEGER_PATTERN = "[0-9]+";
	private static final String ALPHABET_PATTERN = "^[\\p{L} .'-]+$";

	public static boolean validateMobile(String mobileNumber) {
		Pattern pattern = Pattern.compile(MOBILE_PATTERN);
		Matcher matcher = pattern.matcher(String.valueOf(mobileNumber));
		return matcher.matches();
	}

	public static boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isIntegerInput(String input) {
		Pattern pattern = Pattern.compile(INTEGER_PATTERN);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	public static boolean isAlphabetInput(String input) {
		Pattern pattern = Pattern.compile(ALPHABET_PATTERN);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}
