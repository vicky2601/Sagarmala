package org.sagarmala.utility;

public class UserNameMasking {
	public static void main(String[] args) throws Exception {

		// String username = "SLU8765433456";
		// String username="29080102915";
		/* String username = "SCLU6757788678"; */
		String username = "MOS001";
		System.out.println(UserNameMasking.getMaskUser(username));
	}

	/*
	 * public static String getMaskUser(String username) throws Exception { String
	 * user = null; if (username.length() == 11) { user = maskString(username,
	 * username.length() - 10, username.length() - 1, 'X'); } else if
	 * (username.length() == 13) { user = maskString(username, username.length() -
	 * 11, username.length() - 2, 'X'); } else if (username.length() == 14) { user =
	 * maskString(username, username.length() - 12, username.length() - 2, 'X'); }
	 * return user; }
	 */

	public static String getMaskUser(String username) {
		StringBuilder user = new StringBuilder();
		user.append(username.charAt(0));
		for (int i = 1; i < username.length() - 1; i++) {
			user.append('X');
		}
		user.append(username.charAt(username.length() - 1));
		return user.toString();
	}

	public static String maskString(String strText, int start, int end, char maskChar) throws Exception {

		if (strText == null || strText.equals(""))
			return "";

		if (start < 0)
			start = 0;

		if (end > strText.length())
			end = strText.length();

		if (start > end)
			throw new Exception("End index cannot be greater than start index");

		int maskLength = end - start;

		if (maskLength == 0)
			return strText;

		StringBuilder sbMaskString = new StringBuilder(maskLength);

		for (int i = 0; i < maskLength; i++) {
			sbMaskString.append(maskChar);
		}

		return strText.substring(0, start) + sbMaskString.toString() + strText.substring(start + maskLength);
	}
}
