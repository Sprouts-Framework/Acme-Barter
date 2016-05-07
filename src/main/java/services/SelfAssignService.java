package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import repositories.MatchRepository;
import validation.rules.MatchMustNotBeCancelled;
import validation.rules.MustNotBeAssigned;
import validation.rules.MustNotBeSigned;
import domain.Auditor;
import domain.Match;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;


@Service
@Transactional

public class SelfAssignService extends AbstractService<Match, MatchRepository> implements UpdateService<Match>{

	@Autowired
	private MustNotBeAssigned mustNotBeAssigned;
	
	@Autowired
	private MatchMustNotBeCancelled matchMustNotBeCancelled;
	
	@Autowired
	private MustNotBeSigned mustNotBeSigned;
	
	@Autowired
	private ActorService actorService;
	
	@Override
	public void updateBusinessRules(List<BusinessRule<Match>> rules, List<Validator> validators) {
		rules.add(mustNotBeAssigned);
		rules.add(matchMustNotBeCancelled);
		rules.add(mustNotBeSigned);
		
	}

	@Override
	public void beforeUpdating(Match validable, List<String> context) {
//		Auditor principal;
//		
//		principal = (Auditor) actorService.findActorByPrincipal();
//		Match prueba = repository.findOne(validable.getId());
//		prueba.setAuditor(principal);
	}


	@Override
	public void afterCommitingUpdate(int id) {		
	}

	@Override
	public void beforeCommitingUpdate(Match validable, List<String> context) {
		Auditor principal;
		
		principal = (Auditor) actorService.findActorByPrincipal();
		validable.setAuditor(principal);
		
	}
	
}
