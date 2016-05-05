package validation.rules;

import org.springframework.stereotype.Component;

import domain.Barter;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotCancelled implements BusinessRule<Barter>{

	@Override
	public boolean rule(Barter barter) {
		return !barter.getCancelled();
	}

	@Override
	public String warning() {
		return "barter.cancelled";
	}

}
