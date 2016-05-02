package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.MatchService;
import domain.LegalText;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotAssigned implements BusinessRule<LegalText>{
	
	@Autowired
	private MatchService matchService;
	
	@Override
	public boolean rule(LegalText domainObject) {
		boolean result = false;
		Long aux;
		aux = matchService.matchesAssignedToLegalText(domainObject.getId());
		if(aux.equals(new Long(0))){
			result = true;
		}
		return result;
	}

	@Override
	public String warning() {
		return "legalText.isAssigned";
	}

}
