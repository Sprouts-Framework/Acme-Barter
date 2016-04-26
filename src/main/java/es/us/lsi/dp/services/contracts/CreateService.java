package es.us.lsi.dp.services.contracts;

import java.util.List;

import org.springframework.validation.Validator;

import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import es.us.lsi.dp.validation.contracts.Validable;

public interface CreateService<D extends Validable> {

	public Class<? extends DomainObject> getEntityClass();

	public D create();

	public int save(final D domainObject);

	public void createBusinessRules(final List<BusinessRule<D>> rules, final List<Validator> validators);

	public void beforeCreating(final D validable, List<String> context);

	public void beforeCommitingCreate(final D validable);

	public void afterCommitingCreate(final int id);

}
