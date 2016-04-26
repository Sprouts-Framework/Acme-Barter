package es.us.lsi.dp.controllers.entities.crud;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.controllers.Codes;
import es.us.lsi.dp.controllers.core.GetController;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.contracts.ShowService;

@Controller
public abstract class AbstractShowController<E extends DomainEntity, S extends ShowService<E>> extends GetController<E, E> {

	// Attributes --------------------------------------------------------------

	protected S service;

	@Autowired
	public void setService(final S service) {
		this.service = service;
	}

	// Action methods ----------------------------------------------------------

	public void beforeAuthorization(E entity, List<String> context) {

	}

	@RequestMapping(value = {
			Codes.SHOW_MAPPING_VALUE, Codes.SHOW_MAPPING_VALUE_PARAMS
	}, method = RequestMethod.GET)
	public ModelAndView show(final HttpServletRequest request) {
		return get(getPathVariables(request));
	}

	// Gettable ----------------------------------------------------------------

	@Override
	public E getObject(final Map<String, String> pathVariables, final E entity, List<String> context) {
		return service.findById(entityId(pathVariables));
	}

	// Crud action -------------------------------------------------------------

	@ModelAttribute("crudAction")
	public String crudAction() {
		return Codes.CRUD_ACTION_SHOWING;
	}

}