package es.us.lsi.dp.utilities;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordEncoder {

	public static String encode(final String input) {

		Md5PasswordEncoder encoder;
		String result;

		encoder = new Md5PasswordEncoder();

		result = encoder.encodePassword(input, null);

		return result;

	}

}
