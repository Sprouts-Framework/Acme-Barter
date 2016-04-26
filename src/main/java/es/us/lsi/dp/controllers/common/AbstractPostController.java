package es.us.lsi.dp.controllers.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.controllers.core.PostController;
import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.contracts.Service;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Controller
public abstract class AbstractPostController<S extends Service<? extends DomainObject>> extends PostController<DomainObject, DomainObject> {

	// Service -----------------------------------------------------------------

	protected S service;

	@Autowired
	public void setService(final S service) {
		this.service = service;
	}

	// Actions -----------------------------------------------------------------

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get(final HttpServletRequest request) {
		setRequest(request);
		return get(getPathVariables(request));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView post(final HttpServletRequest request) {
		setRequest(request);
		return attempt(getPathVariables(request));
	}

	// Adaptation --------------------------------------------------------------

	@Override
	public void beforeAuthorization(DomainObject object, List<String> context) {
	}

	@Override
	public void postAction(final DomainObject object, final DomainObject entity, final Map<String, String> pathVariables) {
		action(pathVariables);
	}

	@Override
	public void businessRules(final List<BusinessRule<DomainObject>> rules, final List<Validator> validators) {
	}

	@Override
	public boolean authorize(final DomainObject domainObject, final UserAccount principal) {
		return true;
	}

	@Override
	public DomainObject getObject(final Map<String, String> pathVariables, final DomainObject entity, List<String> context) {
		return null;
	}

	// Template methods --------------------------------------------------------

	protected abstract void action(Map<String, String> pathVariables);

	@Override
	protected abstract String onSuccess();
}
