package validation.rules;

import org.springframework.stereotype.Component;

import domain.Match;

import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class MustNotBeCancelled implements BusinessRule<Match>{

	@Override
	public boolean rule(Match match) {
		return (!match.getOffered().getCancelled() && !match.getRequested().getCancelled());
	}

	@Override
	public String warning() {
		return "match.barters.cancelled";
	}

}
