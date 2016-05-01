package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.UserRepository;
import domain.User;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.services.contracts.ShowService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class UserService extends AbstractService<User, UserRepository> implements ListService<User>, ShowService<User>, UpdateService<User> {

	// Update methods
	@Override
	public void updateBusinessRules(List<BusinessRule<User>> rules, List<Validator> validators) {
	}

	@Override
	public void beforeUpdating(User validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(User validable) {
	}

	@Override
	public void afterCommitingUpdate(int id) {
	}

	//Find methods
	@Override
	public Page<User> findPage(Pageable page, String searchCriteria) {
		Page<User> result;
		result = repository.findAll(page);
		Assert.notNull(result);

		return result;
	}

	public User findByPrincipal() {
		UserAccount userAccount;
		User user;
		userAccount = SignInService.getPrincipal();
		Assert.notNull(userAccount);
		user = repository.findByPrincipal(userAccount.getId());
		Assert.notNull(user);
		return user;
	}
	
	
	
	public Page<User> findFollowersByPrincipal(final Pageable page){		
		User principal;
		Page<User> users;
		List<User> followers;
		principal = findByPrincipal();
		followers = (List<User>) principal.getFollowers();
		users = new PageImpl<>(followers, page, followers.size());
		Assert.notNull(users);
		return users;
	}
	
	public Page<User> findFollowingByPrincipal(final Pageable page){		
		User principal;
		Page<User> users;
		List<User> followers;
		principal = findByPrincipal();
		followers = (List<User>) principal.getFollowees();
		users = new PageImpl<>(followers, page, followers.size());
		Assert.notNull(users);
		return users;
	}
}
