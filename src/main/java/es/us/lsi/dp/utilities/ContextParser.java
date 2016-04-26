package es.us.lsi.dp.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ContextParser {

	public static List<String> parse(Map<String, String> pathVariables) {
		List<String> result = new ArrayList<>();
		String toParse = pathVariables.get("context");
		if (toParse != null) {
			String[] parsedArr = toParse.split(",");
			result = Arrays.asList(parsedArr);
		}
		return result;
	}

}
