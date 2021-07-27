package com.example.hpc.utils.validation;

class RegexConstants {
	private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
	private static final String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)+";
	private static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

	static final String EMAIL_PATTERN =
			"^" + ATOM + "+(\\." + ATOM + "+)*@"
					+ DOMAIN
					+ "|"
					+ IP_DOMAIN
					+ ")$";

	static final String PHONE_NUMBER_PATTERN = "^(\\+98|0)?9\\d{9}$";

	static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";

	static final String URL_PATTERN = "(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?";

}
