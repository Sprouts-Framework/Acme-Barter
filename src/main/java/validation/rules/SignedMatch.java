package validation.rules;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import services.MatchService;
import domain.Match;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class SignedMatch implements BusinessRule<Match> {

	@Autowired
	private MatchService matchService;

	@Override
	public boolean rule(final Match domainObject) {
		Match match;
		UserAccount principal;

		match = matchService.findById(domainObject.getId());
		principal = SignInService.getPrincipal();
		Assert.notNull(match);
		Assert.notNull(principal);

		return (match.getOfferSignedDate() == null || match.getRequestSignedDate() == null);
	}

	@Override
	public String warning() {
		return "match.isSignedForBoth.error";
	}
}