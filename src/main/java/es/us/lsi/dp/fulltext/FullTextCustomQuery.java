package es.us.lsi.dp.fulltext;

public class FullTextCustomQuery {

	private String field;
	private FullTextConstraint constraint;
	private Object object;

	public FullTextCustomQuery(String field, FullTextConstraint constraint, Object object) {
		super();
		this.field = field;
		this.constraint = constraint;
		this.object = object;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public FullTextConstraint getConstraint() {
		return constraint;
	}

	public void setConstraint(FullTextConstraint constraint) {
		this.constraint = constraint;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
