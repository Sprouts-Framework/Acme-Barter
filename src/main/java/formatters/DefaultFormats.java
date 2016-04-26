package formatters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.us.lsi.dp.formats.CustomDateFormat;
import es.us.lsi.dp.formats.CustomDecimalFormat;
import es.us.lsi.dp.formats.CustomFormat;

public class DefaultFormats {

	public static List<CustomFormat> getDefaultFormats() {
		List<CustomFormat> result = new ArrayList<>();
		result.add(new CustomDateFormat("", Date.class));
		result.add(new CustomDecimalFormat("", Double.class));
		return result;
	}

}
