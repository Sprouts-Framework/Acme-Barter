package es.us.lsi.dp.controllers.entities.crud;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.controllers.Codes;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.contracts.ListService;

@Controller
public abstract class AbstractListController<E extends DomainEntity, S extends ListService<E>> extends AbstractGetListDataController<E, S> {

	// Action methods ----------------------------------------------------------

	@RequestMapping(value = {
			Codes.LIST_MAPPING_VALUE, Codes.LIST_MAPPING_VALUE_PARAMS
	}, method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		setRequest(request);
		return currentView();
	}

	// Crud action -------------------------------------------------------------

	@ModelAttribute("crudAction")
	public String crudAction() {
		return Codes.CRUD_ACTION_LISTING;
	}

	// Template methods --------------------------------------------------------

	@Override
	protected abstract String view();

}