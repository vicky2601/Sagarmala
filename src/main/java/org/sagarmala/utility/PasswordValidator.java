package org.sagarmala.utility;

public class PasswordValidator {
	public boolean isLegalPassword(String pass) {

		if (!pass.matches(".*[A-Z].*"))
			return false;

		if (!pass.matches(".*[a-z].*"))
			return false;

		if (!pass.matches(".*\\d.*"))
			return false;

		if (!pass.matches(".*[^A-Za-z0-9].*"))
			return false;

		return true;
	}

	public static void main(String[] args) {
		PasswordValidator obj = new PasswordValidator();
		boolean b = obj.isLegalPassword("Utl123acv");
		System.out.println("is password valid " + b);
	}
}
