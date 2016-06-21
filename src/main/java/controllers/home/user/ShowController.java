package controllers.home.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.UserService;
import domain.User;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("UserShowController")
@RequestMapping("home/user/profile")
public class ShowController extends AbstractShowController<User, UserService>{

	@Override
	public boolean authorize(User domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "user/profile";
	}

}
