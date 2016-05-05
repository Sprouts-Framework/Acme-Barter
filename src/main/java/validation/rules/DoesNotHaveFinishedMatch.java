package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.MatchService;

import domain.Barter;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class DoesNotHaveFinishedMatch implements BusinessRule<Barter>{

	@Autowired
	private MatchService matchService;
	
	@Override
	public boolean rule(Barter barter) {
		return (matchService.countFinishedOfferedBarters(barter.getId()) == 0 && matchService.countFinishedRequestedBarters(barter.getId())== 0);
	}

	@Override
	public String warning() {
		return "barter.finishedMatch";
	}

}
