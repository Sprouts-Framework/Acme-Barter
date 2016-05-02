package validation.rules;

import org.springframework.stereotype.Component;

import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.RelateBarterForm;

@Component
public class IsDifferentBarter implements BusinessRule<RelateBarterForm>{

	@Override
	public boolean rule(RelateBarterForm domainObject) {
		return !domainObject.getOffered().equals(domainObject.getRequested());
	}

	@Override
	public String warning() {
		return "barter.differentBarter";
	}

}
