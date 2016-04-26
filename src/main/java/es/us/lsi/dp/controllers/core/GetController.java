package es.us.lsi.dp.controllers.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.controllers.core.contracts.AddCustomFormat;
import es.us.lsi.dp.controllers.core.contracts.Authorizable;
import es.us.lsi.dp.formats.CustomCurrencyFormat;
import es.us.lsi.dp.formats.CustomDateFormat;
import es.us.lsi.dp.formats.CustomDecimalFormat;
import es.us.lsi.dp.formats.CustomFormat;
import es.us.lsi.dp.security.SecurityHandler;
import es.us.lsi.dp.utilities.ContextParser;
import es.us.lsi.dp.validation.contracts.Validable;
import formatters.DefaultFormats;

public abstract class GetController<D extends Validable, E extends Validable> extends BaseController implements Authorizable<E> {

	// Authorization -----------------------------------------------------------

	@Autowired
	protected SecurityHandler<E> security;

	// Initialisation ----------------------------------------------------------

	@PostConstruct
	protected final void setAuthorizable() {
		security.setAuthorizable(this);
	}

	// Responses ---------------------------------------------------------------

	@SuppressWarnings("unchecked")
	protected ModelAndView get(final Map<String, String> pathVariables, final E entity) {
		ModelAndView result;
		D domainObject;
		E entityDomainObject;

		domainObject = getObject(pathVariables, entity, ContextParser.parse(pathVariables));

		Assert.notNull(domainObject);

		entityDomainObject = entity;

		if (entity == null) {
			entityDomainObject = (E) domainObject;
			beforeAuthorization(entityDomainObject, ContextParser.parse(pathVariables));
		}

		security.authorize(entityDomainObject);

		result = currentView(domainObject, ContextParser.parse(pathVariables));

		return result;
	}

	// Alternative invocations -------------------------------------------------

	protected ModelAndView get(final Map<String, String> pathVariables) {
		return get(pathVariables, null);
	}

	// Helpers -----------------------------------------------------------------

	protected Integer entityId(final Map<String, String> pathVariables) {
		String pathVariable;
		Integer result;

		pathVariable = Collections.max(pathVariables.keySet());

		result = Integer.valueOf(pathVariables.get(pathVariable));

		return result;
	}

	// Binders ----------------------------------------------------------------

	// public abstract List<PropertyEditor> registerCustomEditors();

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		List<CustomFormat> formats = new ArrayList<>();

		if (this instanceof AddCustomFormat) {
			AddCustomFormat addCustomInternationalization = (AddCustomFormat) this;
			addCustomInternationalization.addCustomFormats(formats);
		}

		formats.addAll(DefaultFormats.getDefaultFormats());

		for (CustomFormat format : formats) {
			format.setMessageSource(messageSource);
			if (format instanceof CustomDateFormat) {
				CustomDateFormat dateFormat = (CustomDateFormat) format;
				if (dateFormat.getField().equals(""))
					binder.registerCustomEditor(dateFormat.getType(), new CustomDateEditor(dateFormat.getFormat(), true));
				else
					binder.registerCustomEditor(dateFormat.getType(), dateFormat.getField(), new CustomDateEditor(dateFormat.getFormat(), true));
			}

			if (format instanceof CustomCurrencyFormat) {
				CustomCurrencyFormat currencyFormat = (CustomCurrencyFormat) format;
				if (currencyFormat.getField().equals(""))
					binder.registerCustomEditor(currencyFormat.getType(), new CustomNumberEditor(currencyFormat.getType(), currencyFormat.getFormat(), true));
				else
					binder.registerCustomEditor(currencyFormat.getType(), currencyFormat.getField(), new CustomNumberEditor(currencyFormat.getType(),
							currencyFormat.getFormat(), true));
			}

			if (format instanceof CustomDecimalFormat) {
				CustomDecimalFormat decimalFormat = (CustomDecimalFormat) format;
				if (decimalFormat.getField().equals(""))
					binder.registerCustomEditor(decimalFormat.getType(), new CustomNumberEditor(decimalFormat.getType(), decimalFormat.getFormat(), true));
				else
					binder.registerCustomEditor(decimalFormat.getType(), decimalFormat.getField(), new CustomNumberEditor(decimalFormat.getType(),
							decimalFormat.getFormat(), true));
			}

		}
	}

	protected D getCurrentVersion(final HttpServletRequest request) {
		return getObject(getPathVariables(request), null, ContextParser.parse(getPathVariables(request)));
	}

	// Template methods -------------------------------------------------------

	public abstract D getObject(Map<String, String> pathVariables, E entity, List<String> context);

}