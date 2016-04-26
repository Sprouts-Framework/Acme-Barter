package es.us.lsi.dp.controllers.datatypes;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.controllers.Codes;
import es.us.lsi.dp.controllers.core.PostController;
import es.us.lsi.dp.datatypes.Datatype;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.contracts.Service;

@Controller
public abstract class AbstractPostController<D extends Datatype, E extends DomainEntity, S extends Service<E>> extends PostController<D, E> {

	// Service -----------------------------------------------------------------

	protected S service;

	@Autowired
	public void setService(final S service) {
		this.service = service;
	}

	// Actions -----------------------------------------------------------------

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get(final HttpServletRequest request) {
		Map<String, String> pathVariables;
		E entity;

		pathVariables = getPathVariables(request);

		entity = service.findById(entityId(pathVariables));

		return get(pathVariables, entity);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView post(@ModelAttribute(Codes.MODEL_OBJECT_NAME) final D datatype, final BindingResult bindingResult, final HttpServletRequest request,
			final HttpServletResponse response) {
		Map<String, String> pathVariables;
		E entity;

		pathVariables = getPathVariables(request);

		entity = service.findById(entityId(pathVariables));

		setRequestAndResponse(request, response);

		return post(datatype, entity, bindingResult, pathVariables);
	}

	// Adaptation --------------------------------------------------------------

	@Override
	public void beforeAuthorization(E object, List<String> context) {
	}

	@Override
	public void postAction(final D object, final E entity, final Map<String, String> pathVariables) {
		action(object, entity, pathVariables);
	}

	@Override
	public void beforeCommiting(E entity) {
	}

	// Template methods --------------------------------------------------------

	@Override
	protected abstract String successCode();

	protected abstract void action(D object, E entity, Map<String, String> pathVariables);

	public abstract void beforeCommiting(D entityOrDatatype, E entity);
}
