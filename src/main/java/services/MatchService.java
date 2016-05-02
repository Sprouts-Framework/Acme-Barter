package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MatchRepository;
import domain.Match;
import domain.User;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.services.contracts.ShowService;

@Service
@Transactional
public class MatchService extends AbstractService<Match, MatchRepository> implements ListService<Match>, ShowService<Match>{
	
	@Autowired
	private UserService userService;
	
	public Page<Match> findMatchesByUserId(int userId, Pageable page){
		Page<Match> result;
		
		result = repository.findMatchesByUserId(userId, page);
		Assert.notNull(result);
		 
		return result;
	}

	@Override
	public Page<Match> findPage(Pageable page, String searchCriteria) {
		return repository.findAll(page);
	}

	
	public Page<Match> findMatchesOfFollowedUsers(Pageable page){
		Page<Match> result;
		int userId;
		User user;
		
		user = userService.findByPrincipal();
		Assert.notNull(user);
		
		userId = user.getId();
		
		result = repository.findMatchesOfFollowedUsers(userId, page);
		Assert.notNull(result);
		
		return result;
	}

	
	public Collection<Match> findMatchesByLegalTextId(int legalTextId){
		Collection<Match> result;
		result = repository.findMatchesByLegalTextId(legalTextId);
		Assert.notNull(result);
		return result;
	}


}
