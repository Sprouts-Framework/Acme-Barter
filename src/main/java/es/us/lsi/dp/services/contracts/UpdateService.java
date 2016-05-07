package es.us.lsi.dp.services.contracts;

import java.util.List;

import org.springframework.validation.Validator;

import es.us.lsi.dp.validation.contracts.BusinessRule;
import es.us.lsi.dp.validation.contracts.Validable;

public interface UpdateService<D extends Validable> {

	public int update(D domainObject);

	public void updateBusinessRules(List<BusinessRule<D>> rules, List<Validator> validators);

	public D findById(int id);

	public void beforeUpdating(final D validable, List<String> context);

	public void beforeCommitingUpdate(final D validable, List<String> context);

	public void afterCommitingUpdate(final int id);
}
