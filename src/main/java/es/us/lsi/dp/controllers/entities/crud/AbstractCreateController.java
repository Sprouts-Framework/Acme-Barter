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
import es.us.lsi.dp.services.contracts.CreateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Controller
public abstract class AbstractCreateController<D extends DomainObject, S extends CreateService<D>> extends PostController<D, D> {

	// Attributes --------------------------------------------------------------

	protected S service;

	@Autowired
	public void setService(final S service) {
		this.service = service;
	}

	// Action methods ----------------------------------------------------------

	@RequestMapping(value = {
			Codes.CREATE_MAPPING_VALUE, Codes.CREATE_MAPPING_VALUE_PARAMS
	}, method = RequestMethod.GET)
	public ModelAndView createGet(final HttpServletRequest request) {
		return get(getPathVariables(request));
	}

	@RequestMapping(value = {
			Codes.CREATE_MAPPING_VALUE, Codes.CREATE_MAPPING_VALUE_PARAMS
	}, method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute(Codes.MODEL_OBJECT_NAME) final D domainObject, final BindingResult bindingResult,
			final HttpServletRequest request, final HttpServletResponse response) {
		setRequestAndResponse(request, response);
		return post(domainObject, bindingResult, getPathVariables(request));
	}

	// Gettable ----------------------------------------------------------------

	@Override
	public D getObject(final Map<String, String> pathVariables, final D entity, List<String> context) {
		return service.create();
	}

	// Postable ----------------------------------------------------------------

	@Override
	public void beforeAuthorization(final D domainObject, List<String> context) {
		service.beforeCreating(domainObject, context);
	}

	@Override
	public void beforeCommiting(D entity, List<String> context) {
		service.beforeCommitingCreate(entity, context);
	}

	@Override
	public void postAction(final D domainObject, final D duplicatedDomainObject, final Map<String, String> pathVariables) {
		int id = service.save(domainObject);
		service.afterCommitingCreate(id);
	}

	@Override
	public void businessRules(final List<BusinessRule<D>> rules, final List<Validator> validators) {
		service.createBusinessRules(rules, validators);
	}

	// Success ----------------------------------------------------------------

	@Override
	protected String onSuccess() {
		return Codes.CREATE_REDIRECT_VIEW_NAME;
	}

	@Override
	protected String successCode() {
		return Codes.CREATE_SUCCESS_CODE;
	}

	// Crud action -------------------------------------------------------------

	@ModelAttribute("crudAction")
	public String crudAction() {
		return Codes.CRUD_ACTION_CREATING;
	}

}