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
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.contracts.DeleteService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Controller
public abstract class AbstractDeleteController<E extends DomainEntity, S extends DeleteService<E>> extends PostController<E, E> {

	// Attributes --------------------------------------------------------------

	protected S service;

	@Autowired
	public void setService(final S service) {
		this.service = service;
	}

	// Action methods ----------------------------------------------------------

	@RequestMapping(value = {
			Codes.DELETE_MAPPING_VALUE, Codes.DELETE_MAPPING_VALUE_PARAMS
	}, method = RequestMethod.GET)
	public ModelAndView deleteGet(final HttpServletRequest request) {
		return get(getPathVariables(request));
	}

	@RequestMapping(value = {
			Codes.DELETE_MAPPING_VALUE, Codes.DELETE_MAPPING_VALUE_PARAMS
	}, method = RequestMethod.POST)
	public ModelAndView deletePost(@ModelAttribute(Codes.MODEL_OBJECT_NAME) final E domainObject, final BindingResult bindingResult,
			final HttpServletRequest request, final HttpServletResponse response) {
		setRequestAndResponse(request, response);
		return post(domainObject, bindingResult, getPathVariables(request));
	}

	// Gettable ----------------------------------------------------------------

	@Override
	public E getObject(final Map<String, String> pathVariables, final E entity, List<String> context) {
		return service.findById(entityId(pathVariables));
	}

	// Postable ----------------------------------------------------------------

	@Override
	public void beforeAuthorization(final E domainObject, List<String> context) {
		service.beforeDeleting(domainObject, context);
	}

	@Override
	public void beforeCommiting(E domainObject) {
		service.beforeCommitingDelete(domainObject);
	}

	@Override
	public void postAction(final E domainObject, final E duplicatedDomainObject, final Map<String, String> pathVariables) {
		service.delete(domainObject);
		service.afterCommitingDelete(domainObject.getId());
	}

	@Override
	public void businessRules(final List<BusinessRule<E>> rules, final List<Validator> validators) {
		service.deleteBusinessRules(rules, validators);
	}

	// Success code ------------------------------------------------------------

	@Override
	protected String successCode() {
		return Codes.DELETE_SUCCESS_CODE;
	}

	// Crud action -------------------------------------------------------------

	@ModelAttribute("crudAction")
	public String crudAction() {
		return Codes.CRUD_ACTION_DELETING;
	}

}