package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public void beforeCommitingUpdate(User validable, List<String> context) {
	}

	@Override
	public void afterCommitingUpdate(int id) {
	}

	// Find methods
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

	public Page<User> findFollowersByPrincipal(final Pageable page) {
		User principal;
		Page<User> users;
		List<User> followers;
		principal = findByPrincipal();
		followers = (List<User>) principal.getFollowers();
		users = new PageImpl<>(followers, page, followers.size());
		Assert.notNull(users);
		return users;
	}

	public Page<User> findFollowingByPrincipal(final Pageable page) {
		User principal;
		Page<User> users;
		List<User> followers;
		principal = findByPrincipal();
		followers = (List<User>) principal.getFollowees();
		users = new PageImpl<>(followers, page, followers.size());
		Assert.notNull(users);
		return users;
	}

	// Follow/unfollow methods
	public boolean isFollowing(int userId) {
		User toFollow = findById(userId);
		Assert.notNull(toFollow);

		User principal = findByPrincipal();

		User toFollowAux = repository.findFollower(principal.getId(), userId);
		if (toFollowAux == null)
			return false;
		else
			return true;

	}
	
	public void followOrUnfollow(int userId){
		boolean isFollowing = isFollowing(userId);
		
		User principal = findByPrincipal();
		User toFollow = findById(userId);
		Assert.notNull(principal);
		Assert.notNull(toFollow);
		Assert.isTrue(!principal.equals(toFollow));
		
		if(isFollowing){
			principal.getFollowees().remove(toFollow);
			toFollow.getFollowers().remove(principal);
		} else {
			principal.getFollowees().add(toFollow);
			toFollow.getFollowers().add(principal);
		}
		
		update(principal);
		update(toFollow);
	}

	public Page<User> usersWhoHaveGotMoreMatchesAudited(Pageable page){
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable aux = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		return repository.usersWhoHaveGotMoreMatchesAudited(aux);
	}
	
	public Long quantityOfMatchesAudited(){
		return repository.quantityOfMatchesAudited();
	}
	
	public Long totalNumberOfUsersRegistedInTheSystem(){
		return repository.totalNumberOfUsersRegistedInTheSystem();
	}
	
	
}
