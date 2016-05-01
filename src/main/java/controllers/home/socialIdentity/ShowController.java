package controllers.home.socialIdentity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.SocialIdentityService;
import domain.SocialIdentity;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("SocialIdentityShowController")
@RequestMapping("home/user/socialIdentity")
public class ShowController extends AbstractShowController<SocialIdentity, SocialIdentityService>{

	@Override
	public boolean authorize(SocialIdentity domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "socialIdentity/show";
	}

}
