package es.us.lsi.dp.controllers.core.contracts;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Validator;

import es.us.lsi.dp.validation.contracts.BusinessRule;
import es.us.lsi.dp.validation.contracts.Validable;

public interface Postable<V extends Validable, E extends Validable> {

	public void postAction(V object, E entity, Map<String, String> pathVariables);

	/**
	 * Check for Business Rules before executing <code>action</code>
	 * 
	 * @param rules
	 *            Business rules to be checked for before calling
	 *            <code>action</code>. They are evaluated in the same order they
	 *            have been added.
	 * 
	 * @param validators
	 *            General purpose validators not attached to the Domain.
	 */
	public void businessRules(List<BusinessRule<E>> rules, List<Validator> validators);

	/**
	 * These methods are called before checking for business rules. They will be
	 * called depending on if we are dealing with datatypes or entities.
	 * 
	 * */
	public void beforeCommiting(E object, List<String> context);

	public void beforeCommiting(V datatype, E object, List<String> context);
}
