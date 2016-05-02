package validation.rules;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.MatchService;
import domain.LegalText;
import domain.Match;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotAssigned implements BusinessRule<LegalText>{
	
	@Autowired
	private MatchService matchService;
	
	@Override
	public boolean rule(LegalText domainObject) {
		boolean result = false;
		Collection<Match> aux;
		aux = matchService.findMatchesByLegalTextId(domainObject.getId());
		if(aux.isEmpty()){
			result = true;
		}
		return result;
	}

	@Override
	public String warning() {
		return "legalText.isAssigned";
	}

}
