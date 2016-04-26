package es.us.lsi.dp.services.contracts;

import java.util.List;

import org.springframework.validation.Validator;

import es.us.lsi.dp.validation.contracts.BusinessRule;
import es.us.lsi.dp.validation.contracts.Validable;

public interface DeleteService<D extends Validable> {

	public void delete(D domainObject);

	public void deleteBusinessRules(List<BusinessRule<D>> rules, List<Validator> validators);

	public D findById(int id);

	public void beforeDeleting(final D validable, List<String> context);

	public void beforeCommitingDelete(final D validable);

	public void afterCommitingDelete(final int id);
}
