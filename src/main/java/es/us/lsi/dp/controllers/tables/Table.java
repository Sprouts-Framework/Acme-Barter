package es.us.lsi.dp.controllers.tables;

import java.util.List;

public class Table {

	private final List<Column> columns;

	public Table(final List<Column> columns) {
		this.columns = columns;
	}

	public Column addColumn(final String field) {
		Column column;

		column = new Column(field);

		columns.add(column);

		return column;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public Column getColumn(final Integer index) {
		return columns.get(index);
	}

	public int size() {
		return columns.size();
	}

}
