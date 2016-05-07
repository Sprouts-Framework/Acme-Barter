package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.MatchService;

import domain.Match;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class MatchMustNotBeCancelled implements BusinessRule<Match>{

	@Autowired
	private MatchService matchService;
	
	@Override
	public boolean rule(Match domainObject) {
		Match match = matchService.findById(domainObject.getId());
		return (!match.isCancelled());
	}

	@Override
	public String warning() {
		return "match.cancelled";
	}

}
