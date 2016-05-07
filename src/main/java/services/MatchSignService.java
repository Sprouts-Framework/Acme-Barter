package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.MatchRepository;
import validation.rules.IsNotCancelledMatch;
import validation.rules.IsNotSignedMatch;
import domain.Match;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.utilities.Moment;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class MatchSignService extends AbstractService<Match, MatchRepository> implements UpdateService<Match> {

	@Autowired
	private IsNotSignedMatch isNotSignedMatch;
	@Autowired
	private IsNotCancelledMatch isNotCancelledMatch;
	
	@Override
	public void updateBusinessRules(List<BusinessRule<Match>> rules, List<Validator> validators) {
		rules.add(isNotSignedMatch);
		rules.add(isNotCancelledMatch);
	}

	@Override
	public void beforeUpdating(Match validable, List<String> context) {
		Match persisted;
		persisted = findById(validable.getId());
		Assert.notNull(persisted);

		validable.setCancelled(persisted.isCancelled());
		validable.setOfferSignedDate(persisted.getOfferSignedDate());
		validable.setRequestSignedDate(persisted.getRequestSignedDate());

	}

	@Override
	public void beforeCommitingUpdate(Match validable, List<String> context) {
		UserAccount principal;
		UserAccount requestUser;
		UserAccount offerUser;

		principal = SignInService.getPrincipal();
		requestUser = validable.getRequested().getUser().getUserAccount();
		offerUser = validable.getOffered().getUser().getUserAccount();

		Assert.notNull(principal);
		Assert.notNull(requestUser);
		Assert.notNull(offerUser);

		if (principal.equals(requestUser))
			validable.setRequestSignedDate(Moment.now());
		else if (principal.equals(offerUser))
			validable.setOfferSignedDate(Moment.now());
	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

}
