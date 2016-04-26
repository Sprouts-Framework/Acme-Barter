package es.us.lsi.dp.utilities;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;
import org.apache.tiles.request.servlet.ServletUtil;

public class TilesUtils {

	private static final String VIEWS_FOLDER = "views";
	private static final String VIEW_PATH_ATTRIBUTE = "body";

	public static boolean isReadOnly(final Definition definition) {
		boolean result;
		Attribute readOnlyAttr;
		String readOnly;

		readOnlyAttr = definition.getAttribute("readOnly");

		readOnly = "false";

		if (null != readOnlyAttr)
			readOnly = (String) readOnlyAttr.getValue();

		result = "true".equals(readOnly);

		return result;
	}

	public static Definition getViewDefinition(final String viewName, final HttpServletRequest request, final HttpServletResponse response) {
		Definition result;
		ApplicationContext appContext;
		TilesContainer container;
		ServletRequest servletRequest;

		appContext = ServletUtil.getApplicationContext(request.getServletContext());
		container = TilesAccess.getContainer(appContext);
		servletRequest = new ServletRequest(appContext, request, response);

		result = container.getDefinition(viewName, servletRequest);

		return result;
	}

	public static String getJspPath(final String viewName, final HttpServletRequest request, final HttpServletResponse response) {
		Definition definition;
		String result;

		definition = TilesUtils.getTemplateDefinition(viewName, request, response);
		result = TilesUtils.getJspPath(definition, request, response);

		return result;
	}

	public static String getJspPath(final Definition definition, final HttpServletRequest request, final HttpServletResponse response) {
		String result;
		ClassLoader classLoader;
		String body;
		String partialPath;
		String fullPath;
		Attribute bodyAttr;

		result = null;
		fullPath = null;
		classLoader = TilesUtils.class.getClassLoader();

		bodyAttr = definition.getAttribute(VIEW_PATH_ATTRIBUTE);

		if (null != bodyAttr)
			body = bodyAttr.toString();
		else {
			bodyAttr = definition.getTemplateAttribute();
			body = bodyAttr.toString();
		}

		// Get rid of ../ prefix
		body = body.substring(3);

		partialPath = VIEWS_FOLDER + "/" + body;

		fullPath = classLoader.getResource(partialPath).getPath();

		// Get rid of leading /
		fullPath = fullPath.substring(1);

		try {
			result = URLDecoder.decode(fullPath, "UTF-8");
		} catch (Throwable e) {
			throw new RuntimeException();
		}

		return result;
	}

	public static Definition getTemplateDefinition(final String viewName, final HttpServletRequest request, final HttpServletResponse response) {
		Definition result;
		String body;
		Attribute bodyAttr;

		result = getViewDefinition(viewName, request, response);

		bodyAttr = result.getAttribute(VIEW_PATH_ATTRIBUTE);
		body = bodyAttr.getValue().toString();

		if (!body.endsWith(".jsp"))
			result = getViewDefinition(body, request, response);

		return result;
	}

}
