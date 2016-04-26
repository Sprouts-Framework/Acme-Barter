package es.us.lsi.dp.utilities;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.tiles.Definition;
import org.springframework.util.FileCopyUtils;

public class ViewParser {

	private static final String CHARSET = "UTF-8";

	public static Set<String> getDeclaredFields(final String viewName, final HttpServletRequest request, final HttpServletResponse response) {
		Definition definition;
		String jspPath;
		boolean isReadOnly;
		Set<String> result;

		result = new HashSet<>();

		definition = TilesUtils.getTemplateDefinition(viewName, request, response);
		jspPath = TilesUtils.getJspPath(definition, request, response);
		isReadOnly = TilesUtils.isReadOnly(definition);

		if (!isReadOnly)
			result = getFieldsByTags(jspPath, "path=\"", "\"");

		return result;
	}

	public static Set<String> getProtectedFields(final String viewName, final HttpServletRequest request, final HttpServletResponse response) {
		String jspPath;
		Set<String> result;

		jspPath = TilesUtils.getJspPath(viewName, request, response);
		result = getFieldsByTags(jspPath, "protected path=\"", "\"");

		return result;
	}

	protected static Set<String> getFieldsByTags(final String path, final String openingTag, final String closingTag) {
		try {
			String[] fields = {};
			Set<String> result;

			byte[] rawJsp;
			String stringJsp;
			FileInputStream fileInputStream;

			fileInputStream = new FileInputStream(path);
			rawJsp = FileCopyUtils.copyToByteArray(fileInputStream);
			stringJsp = new String(rawJsp, CHARSET);
			fields = StringUtils.substringsBetween(stringJsp, openingTag, closingTag);

			result = new HashSet<String>();

			if (fields != null)
				result.addAll(Arrays.asList(fields));

			return result;
		} catch (final Throwable oops) {
			throw new RuntimeException(oops);
		}
	}

}
