package es.us.lsi.dp.controllers.entities.crud;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.controllers.Codes;
import es.us.lsi.dp.controllers.core.PostController;
import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Controller
public abstract class AbstractUpdateController<D extends DomainObject, S extends UpdateService<D>> extends PostController<D, D> {

	// Attributes --------------------------------------------------------------

	protected S service;

	@Autowired
	public void setService(final S service) {
		this.service = service;
	}

	// Action methods ----------------------------------------------------------

	@RequestMapping(value = {
			Codes.UPDATE_MAPPING_VALUE, Codes.UPDATE_MAPPING_VALUE_PARAMS
	}, method = RequestMethod.GET)
	public ModelAndView updateGet(final HttpServletRequest request) {
		return get(getPathVariables(request));
	}

	@RequestMapping(value = {
			Codes.UPDATE_MAPPING_VALUE, Codes.UPDATE_MAPPING_VALUE_PARAMS
	}, method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute(Codes.MODEL_OBJECT_NAME) final D domainObject, final BindingResult bindingResult,
			final HttpServletRequest request, final HttpServletResponse response) {
		setRequestAndResponse(request, response);
		return post(domainObject, bindingResult, getPathVariables(request));
	}

	// Gettable ----------------------------------------------------------------

	@Override
	public D getObject(final Map<String, String> pathVariables, final D entity, List<String> context) {
		return service.findById(entityId(pathVariables));
	}

	// Postable ----------------------------------------------------------------

	@Override
	public void beforeAuthorization(final D domainObject, List<String> context) {
		service.beforeUpdating(domainObject, context);
	}

	@Override
	public void beforeCommiting(D domainObject, List<String> context) {
		service.beforeCommitingUpdate(domainObject, context);
	}

	@Override
	public void postAction(final D domainObject, final D duplicatedDomainObject, final Map<String, String> pathVariables) {
		int id = service.update(domainObject);
		service.afterCommitingUpdate(id);
	}

	@Override
	public void businessRules(final List<BusinessRule<D>> rules, final List<Validator> validators) {
		service.updateBusinessRules(rules, validators);
	}

	// Success code ------------------------------------------------------------

	@Override
	protected String successCode() {
		return Codes.UPDATE_SUCCESS_CODE;
	}

	// Crud action -------------------------------------------------------------

	@ModelAttribute("crudAction")
	public String crudAction() {
		return Codes.CRUD_ACTION_UPDATING;
	}

}