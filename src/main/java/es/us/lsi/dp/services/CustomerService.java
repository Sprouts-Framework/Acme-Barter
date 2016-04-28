package es.us.lsi.dp.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.UserRepository;
import domain.User;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.contracts.ShowService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class CustomerService extends AbstractService<User, UserRepository> implements ShowService<User>, UpdateService<User> {

	public User findByPrincipal() {
		User result;
		UserAccount userAccount;
		userAccount = SignInService.getPrincipal();
		Assert.notNull(userAccount);
		result = repository.findByPrincipal(userAccount.getId());
		Assert.notNull(result);
		
		return result;
	}

	// Update methods ------------------------------

	@Override
	public void beforeUpdating(User validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(User validable) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<User>> rules, List<Validator> validators) {
	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	public User findBySocialAccount(String providerId, String userId) {
		Assert.notNull(providerId);
		Assert.notNull(userId);

		User result;

		result = repository.findBySocialAccount(providerId, userId);

		return result;
	}

}
