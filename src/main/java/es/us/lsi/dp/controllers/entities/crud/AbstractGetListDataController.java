package es.us.lsi.dp.controllers.entities.crud;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.us.lsi.dp.controllers.Codes;
import es.us.lsi.dp.controllers.entities.core.GetCollectionController;
import es.us.lsi.dp.controllers.tables.DatatableJson;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.contracts.ListService;

@Controller
public abstract class AbstractGetListDataController<E extends DomainEntity, S extends ListService<E>> extends GetCollectionController<E> {

	// Attributes --------------------------------------------------------------

	protected S service;

	@Autowired
	public void setService(final S service) {
		this.service = service;
	}

	// Action methods ----------------------------------------------------------

	@RequestMapping(value = {
			Codes.LIST_DATA_MAPPING_VALUE, Codes.LIST_DATA_MAPPING_VALUE_PARAMS
	}, method = RequestMethod.GET)
	// @RequestMapping(value = Codes.LIST_DATA_MAPPING_VALUE_PARAMS, method =
	// RequestMethod.GET)
	public @ResponseBody
	DatatableJson data(@RequestParam(value = Codes.VIEW_NAME, required = true) final String viewName,
			@RequestParam(value = Codes.TABLE_INDEX, required = true) final String tableIndex, @RequestParam(required = true) final String sEcho,
			@RequestParam(value = "iDisplayLength", required = true) final Integer displayLength,
			@RequestParam(value = "iDisplayStart", required = true) final Integer displayStart,
			@RequestParam(value = "sSearch", required = false) final String searchCriteria,
			@RequestParam(value = "iSortCol_0", required = false) final Integer sortColumn,
			@RequestParam(value = "sSortDir_0", required = false) final String sortDirection, final HttpServletRequest request,
			final HttpServletResponse response) {
		return getData(request, response, viewName, tableIndex, sEcho, displayLength, displayStart, searchCriteria, sortColumn, sortDirection,
				getPathVariables(request));
	}

	// GetCollectionController -------------------------------------------------

	@Override
	protected Page<E> getPage(final Pageable page, final String searchCriteria, List<String> context) {
		return service.findPage(page, searchCriteria);
	}

	// Adaptation --------------------------------------------------------------

	@Override
	protected String view() {
		return null;
	}

}
