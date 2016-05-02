package validation.rules;

import org.springframework.stereotype.Component;

import domain.Barter;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.RelateBarterForm;

@Component
public class IsNotRelated implements BusinessRule<RelateBarterForm> {

	@Override
	public boolean rule(RelateBarterForm domainObject) {
		Boolean res = true;
		Barter offered = domainObject.getOffered();
		Barter requested = domainObject.getRequested();
		if(offered.getRequesteds().contains(requested) || requested.getRequesteds().contains(offered)){
			res = false;
		}
		return res;
	}

	@Override
	public String warning() {
		return "barter.alreadyRelated";
	}

}
