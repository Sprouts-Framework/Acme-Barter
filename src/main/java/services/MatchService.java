package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.MatchRepository;
import domain.Auditor;
import domain.Match;
import domain.User;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.services.contracts.ShowService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class MatchService extends AbstractService<Match, MatchRepository> implements ListService<Match>, ShowService<Match>, UpdateService<Match>{
	
	@Autowired
	private ActorService actorService;
	
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

	
	public Long matchesAssignedToLegalText(int legalTextId){
		Long result;
		result = repository.matchesAssignedToLegalText(legalTextId);
		Assert.notNull(result);
		return result;
	}
	
	public Page<Match> findMatchesByAuditor(Pageable page){
		Auditor principal = (Auditor) actorService.findActorByPrincipal();
		return repository.findMatchesByAuditor(principal.getId(), page);
	}

	@Override
	public void updateBusinessRules(List<BusinessRule<Match>> rules, List<Validator> validators) {
	}

	@Override
	public void beforeUpdating(Match validable, List<String> context) {	
	}

	@Override
	public void beforeCommitingUpdate(Match validable) {	
	}

	@Override
	public void afterCommitingUpdate(int id) {		
	}
}
