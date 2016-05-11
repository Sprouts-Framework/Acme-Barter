package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.MatchRepository;
import validation.rules.IsAuthorisedToUpdateMatch;
import validation.rules.IsNotCancelledMatch;
import validation.rules.SignedMatch;
import domain.Match;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class MatchCancelService extends AbstractService<Match, MatchRepository> implements UpdateService<Match> {

	@Autowired
	private IsAuthorisedToUpdateMatch isAuthorisedToUpdateMatch;
	@Autowired
	private IsNotCancelledMatch isNotCancelledMatch;
	@Autowired
	private SignedMatch signedMatch;
	
	@Override
	public void updateBusinessRules(List<BusinessRule<Match>> rules, List<Validator> validators) {
		rules.add(isAuthorisedToUpdateMatch);
		rules.add(isNotCancelledMatch);
		rules.add(signedMatch);
	}

	@Override
	public void beforeUpdating(Match validable, List<String> context) {
		Match persisted;
		persisted = findById(validable.getId());
		Assert.notNull(persisted);
		
		validable.setCancelled(persisted.isCancelled());
		validable.setRequestSignedDate(persisted.getRequestSignedDate());
		validable.setOfferSignedDate(validable.getOfferSignedDate());
	}

	@Override
	public void beforeCommitingUpdate(Match validable, List<String> context) {
		validable.setCancelled(true);
	}

	@Override
	public void afterCommitingUpdate(int id) {
	}

}
