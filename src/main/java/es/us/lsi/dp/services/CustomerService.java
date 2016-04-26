package es.us.lsi.dp.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.CustomerRepository;

import domain.Customer;

import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.contracts.ShowService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class CustomerService extends AbstractService<Customer, CustomerRepository> implements ShowService<Customer>, UpdateService<Customer> {

	@Autowired
	private KieContainer kieContainer;

	public Customer findByPrincipal() {
		Customer result;
		UserAccount userAccount;
		userAccount = SignInService.getPrincipal();
		Assert.notNull(userAccount);
		result = repository.findByPrincipal(userAccount.getId());
		Assert.notNull(result);

		KieSession kieSession = kieContainer.newKieSession("KSession");
		Collection<Customer> customers = (Collection<Customer>) repository.findAll();
		Date date = new Date(System.currentTimeMillis() - 15778500000L * 2);
		kieSession.insert(date);

		for (Customer e : customers) {
			kieSession.insert(e);
		}

		kieSession.fireAllRules();

		return result;
	}

	// Update methods ------------------------------

	@Override
	public void beforeUpdating(Customer validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(Customer validable) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<Customer>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	public Customer findBySocialAccount(String providerId, String userId) {
		Assert.notNull(providerId);
		Assert.notNull(userId);

		Customer result;

		result = repository.findBySocialAccount(providerId, userId);

		return result;
	}

}
