package es.us.lsi.dp.validation.contracts;

public interface BusinessRulesValidator<V extends Validable, E extends Validable> {

	public void businessRules(E domainEntity);

}
