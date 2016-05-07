package validation.rules;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import services.MatchService;
import domain.Match;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotSignedMatch implements BusinessRule<Match> {

	@Autowired
	private MatchService matchService;

	@Override
	public boolean rule(Match domainObject) {
		UserAccount principal;
		UserAccount requestUser;
		UserAccount offerUser;
		Match persisted;
		Date requestSign;
		Date offerSign;

		persisted = matchService.findById(domainObject.getId());
		Assert.notNull(persisted);

		principal = SignInService.getPrincipal();
		requestUser = persisted.getRequested().getUser().getUserAccount();
		offerUser = persisted.getOffered().getUser().getUserAccount();
		requestSign = persisted.getRequestSignedDate();
		offerSign = persisted.getOfferSignedDate();

		Assert.notNull(principal);
		Assert.notNull(requestUser);
		Assert.notNull(offerUser);

		boolean valid = true;

		if (principal.equals(requestUser) && requestSign != null)
			valid = false;
		else if (principal.equals(offerUser) && offerSign != null)
			valid = false;

		return valid;
	}

	@Override
	public String warning() {
		return "match.isSigned.error";
	}

}
