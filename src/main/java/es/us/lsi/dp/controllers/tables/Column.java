package es.us.lsi.dp.controllers.tables;

public class Column {

	private String field;
	private String title;
	private String nullCode;
	private String format;
	private String width;
	private String outFormat;
	private String toShow;
	private String trueCode;
	private String falseCode;
	private String imgSize;

	private boolean sortable;
	private String[] sortCriteria;

	public Column(final String field) {
		this.field = field;
		sortable = true;
		sortCriteria = new String[] {
			field
		};
		title = field;
		nullCode = "column.null";
	}

	public String getNullCode() {
		return nullCode;
	}

	public void setNullCode(final String nullCode) {
		this.nullCode = nullCode;
	}

	public Column nullCode(final String nullCode) {
		setNullCode(nullCode);
		return this;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public Column title(final String title) {
		setTitle(title);
		return this;
	}

	public String[] getSortCriteria() {
		return sortCriteria;
	}

	public void setSortCriteria(final String[] sortCriteria) {
		this.sortCriteria = sortCriteria;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(final String format) {
		this.format = format;
	}

	public Column format(final String format) {
		setFormat(format);
		return this;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(final String width) {
		this.width = width;
	}

	public Column width(final String width) {
		setWidth(width);
		return this;
	}

	public String getField() {
		return field;
	}

	public void setField(final String field) {
		this.field = field;
	}

	public Column name(final String field) {
		setField(field);
		return this;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(final boolean sortable) {
		this.sortable = sortable;
	}

	public Column sortable(final String sortable) {
		this.sortable = Boolean.getBoolean(sortable);
		return this;
	}

	public Column sortBy(final String... fields) {
		setSortCriteria(fields);
		return this;
	}

	public boolean hasDefaultSorting() {
		return getSortCriteria()[0].equals(getField());
	}

	public String getOutFormat() {
		return outFormat;
	}

	public void setOutFormat(String outFormat) {
		this.outFormat = outFormat;
	}

	public Column outFormat(final String outFormat) {
		setOutFormat(outFormat);
		return this;
	}

	public String getToShow() {
		return toShow;
	}

	public void setToShow(String toShow) {
		this.toShow = toShow;
	}

	public Column toShow(final String toShow) {
		setToShow(toShow);
		return this;
	}

	public String getTrueCode() {
		return trueCode;
	}

	public void setTrueCode(String trueCode) {
		this.trueCode = trueCode;
	}

	public Column trueCode(final String trueCode) {
		setTrueCode(trueCode);
		return this;
	}

	public String getFalseCode() {
		return falseCode;
	}

	public void setFalseCode(String falseCode) {
		this.falseCode = falseCode;
	}

	public Column falseCode(final String falseCode) {
		setFalseCode(falseCode);
		return this;
	}

	public String getImgSize() {
		return imgSize;
	}

	public void setImgSize(String imgSize) {
		this.imgSize = imgSize;
	}

	public Column imgSize(final String imgSize) {
		setImgSize(imgSize);
		return this;
	}

	// Parsing

	public void code(final String code) {
		title = code;
	}

	public void path(final String path) {
		field = path;
	}

}
