package es.us.lsi.dp.controllers.entities.core;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import es.us.lsi.dp.controllers.core.BaseController;
import es.us.lsi.dp.controllers.tables.DatatableJson;
import es.us.lsi.dp.controllers.tables.Table;
import es.us.lsi.dp.controllers.tables.builders.contracts.TableBuilder;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.utilities.ContextParser;

public abstract class GetCollectionController<E extends DomainEntity> extends BaseController {

	@Autowired
	private TableBuilder tableBuilder;

	protected DatatableJson getData(final HttpServletRequest request, final HttpServletResponse response, final String viewName, final String tableIndex,
			final String sEcho, final Integer displayLength, final Integer displayStart, final String searchCriteria, final Integer sortColumn,
			final String sortDirection, Map<String, String> params) {
		DatatableJson result;
		Table table;

		table = tableBuilder.build(viewName, tableIndex, request, response);

		result = getJson(table, sEcho, displayLength, displayStart, searchCriteria, sortColumn, sortDirection, params, response, request);

		return result;
	}

	protected DatatableJson getJson(final Table table, final String sEcho, final Integer displayLength, final Integer displayStart,
			final String searchCriteria, final Integer sortColumn, final String sortDirection, Map<String, String> params, HttpServletResponse response,
			HttpServletRequest auxRequest) {
		DatatableJson result;

		Pageable request;
		Page<E> queryResult;
		List<E> domainObjects;

		long totalRecords;
		long totalDisplayRecords;

		request = prepareRequest(displayLength, displayStart, table, sortDirection, sortColumn);

		queryResult = getPage(request, searchCriteria, ContextParser.parse(params));
		domainObjects = queryResult.getContent();

		totalRecords = totalDisplayRecords = queryResult.getTotalElements();

		final String[][] aaData = tableBuilder.getRows(domainObjects, table, auxRequest.getLocale().toLanguageTag());

		result = new DatatableJson(sEcho, totalRecords, totalDisplayRecords, aaData);

		return result;
	}

	// Private methods --------------------------------------------------------

	private Pageable prepareRequest(final Integer displayLength, final Integer displayStart, final Table fields, final String sortDirection,
			final Integer sortColumn) {
		Pageable result;

		Sort sort;
		int pageNumber;
		String[] sortCriteria;
		Sort.Direction direction;

		sortCriteria = fields.getColumn(sortColumn).getSortCriteria();
		direction = sortDirection(sortDirection);

		sort = new Sort(direction, sortCriteria);
		pageNumber = displayStart / displayLength;

		result = new PageRequest(pageNumber, displayLength, sort);

		return result;
	}

	private Direction sortDirection(final String sortDirection) {
		return sortDirection.equals("asc") ? Direction.ASC : Direction.DESC;
	}

	// Template methods -------------------------------------------------------

	protected abstract Page<E> getPage(Pageable page, String searchCriteria, List<String> context);

}
