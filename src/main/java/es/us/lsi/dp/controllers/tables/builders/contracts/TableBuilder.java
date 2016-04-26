package es.us.lsi.dp.controllers.tables.builders.contracts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.us.lsi.dp.controllers.tables.Table;
import es.us.lsi.dp.domain.DomainEntity;

public interface TableBuilder {

	public Table build(String viewName, String tableId, HttpServletRequest request, HttpServletResponse response);

	public String[][] getRows(List<? extends DomainEntity> entities, Table table, String locale);

}
