package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.MatchService;

import domain.Match;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotMatched implements BusinessRule<Match>{

	@Autowired
	private MatchService matchService;
	
	@Override
	public boolean rule(Match match) {
		Long offered;
		Long requested;
		
		offered = matchService.countOfferedBarters(match.getOffered().getId());
		requested = matchService.countRequestedBarters(match.getRequested().getId());
		
		return (offered.equals(new Long(0)) && requested.equals(new Long(0)));
	}

	@Override
	public String warning() {
		return "match.barters.notmachted";
	}

}
