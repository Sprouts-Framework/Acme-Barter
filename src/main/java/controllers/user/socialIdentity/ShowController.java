package controllers.user.socialIdentity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.SocialIdentityService;
import domain.SocialIdentity;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("showUserSocialIdentityController")
@RequestMapping("socialIdentity/user")
public class ShowController extends AbstractShowController<SocialIdentity, SocialIdentityService>{

	@Override
	public boolean authorize(SocialIdentity domainObject, UserAccount principal) {
		return domainObject.getUser().getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "socialIdentity/show";
	}

}
