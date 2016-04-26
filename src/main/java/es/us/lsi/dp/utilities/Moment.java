package es.us.lsi.dp.utilities;

import java.util.Date;

public class Moment {

	public static Date now() {
		return new Date(System.currentTimeMillis() - 1);
	}

}
