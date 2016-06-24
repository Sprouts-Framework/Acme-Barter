package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.ActorService;
import domain.Actor;
import domain.Folder;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsAuthorisedToManageFolder implements BusinessRule<Folder> {

	@Autowired
	private ActorService actorService;

	@Override
	public boolean rule(final Folder folder) {

		Actor actor;
		UserAccount userAccount;
		userAccount = SignInService.getPrincipal();
		actor = actorService.findActorByUserAccount(userAccount.getUsername());
		return actor.getId() == folder.getActor().getId();
	}

	@Override
	public String warning() {
		return "folder.isAuthorised.error";
	}
}
