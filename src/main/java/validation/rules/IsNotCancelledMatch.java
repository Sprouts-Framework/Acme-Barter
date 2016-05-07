package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import services.MatchService;
import domain.Match;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotCancelledMatch implements BusinessRule<Match> {

	@Autowired
	private MatchService matchService;

	@Override
	public boolean rule(Match domainObject) {
		Match match;
		match = matchService.findById(domainObject.getId());
		Assert.notNull(match);

		return !match.isCancelled();
	}

	@Override
	public String warning() {
		return "match.isNotCancelled.error";
	}

}
