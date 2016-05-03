package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.UserService;

import domain.Match;
import domain.User;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsUserMatch implements BusinessRule<Match>{
	
	@Autowired
	private UserService userService;

	@Override
	public boolean rule(Match match) {
		User user;
		user = userService.findByPrincipal();
		
		return (user != null && match.getOffered().getUser().equals(user));
	}

	@Override
	public String warning() {
		return "match.offered.self";
	}

}
