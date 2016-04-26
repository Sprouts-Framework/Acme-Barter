package es.us.lsi.dp.validation.contracts;

public interface BusinessRule<D extends Validable> {

	public boolean rule(D domainObject);

	public String warning();

}
