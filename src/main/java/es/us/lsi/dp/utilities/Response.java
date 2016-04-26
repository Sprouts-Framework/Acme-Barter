package es.us.lsi.dp.utilities;

import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

public class Response {

	public static ModelAndView create(final String viewName, final Object... arguments) {
		Assert.hasLength(viewName);

		ModelAndView result;

		result = new ModelAndView(viewName);

		if (arguments.length > 0)
			addObjects(result, arguments);

		return result;
	}

	public static ModelAndView redirect(final String url, final Object... arguments) {
		Assert.hasLength(url);

		String redirection;

		redirection = "redirect:" + url;

		return create(redirection, arguments);
	}

	public static void addObjects(final ModelAndView modelAndView, final Object... arguments) {
		Assert.notNull(modelAndView);
		Assert.notEmpty(arguments);
		Assert.isTrue(arguments.length % 2 == 0);

		for (int i = 0; i < arguments.length; i += 2) {
			String key;
			Object value;

			key = (String) arguments[i];
			Assert.isInstanceOf(String.class, arguments[i]);
			value = arguments[i + 1];

			modelAndView.addObject(key, value);
		}
	}

}
