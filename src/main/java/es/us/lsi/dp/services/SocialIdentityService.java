package es.us.lsi.dp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.SocialIdentityRepository;

import domain.User;
import domain.SocialIdentity;

import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.contracts.CreateService;
import es.us.lsi.dp.services.contracts.DeleteService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class SocialIdentityService extends AbstractService<SocialIdentity, SocialIdentityRepository> implements DeleteService<SocialIdentity>,
		CreateService<SocialIdentity>, UpdateService<SocialIdentity> {

	@Autowired
	private CustomerService customerService;

	// Create methods ------------------------------------------

	@Override
	public Class<? extends DomainEntity> getEntityClass() {
		return SocialIdentity.class;
	}

	@Override
	public void beforeCreating(SocialIdentity validable, List<String> context) {
	}

	@Override
	public void beforeCommitingCreate(SocialIdentity validable) {

	}

	@Override
	public void createBusinessRules(List<BusinessRule<SocialIdentity>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingCreate(int id) {
		SocialIdentity result;
		result = super.findById(id);
		Assert.notNull(result);

		User customer;
		customer = customerService.findByPrincipal();
		Assert.notNull(customer);

		//FIXME Arreglar para usar con m�s de una socialIdentity.
		//customer.setSocialIdentity(result);
		customerService.update(customer);
	}

	// Update methods -------------------------------------------
	@Override
	public void beforeUpdating(SocialIdentity validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(SocialIdentity validable) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<SocialIdentity>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Delete methods ----------------------------------------

	@Override
	public void beforeDeleting(SocialIdentity validable, List<String> context) {
	}

	@Override
	public void beforeCommitingDelete(SocialIdentity validable) {
		Assert.notNull(validable);
		User customer;
		customer = customerService.findByPrincipal();
		Assert.notNull(customer);
		//FIXME Arreglar para usar con m�s de una socialIdentity.
		//customer.setSocialIdentity(null);
		customerService.save(customer);
	}

	@Override
	public void deleteBusinessRules(List<BusinessRule<SocialIdentity>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingDelete(int id) {
	}

}
