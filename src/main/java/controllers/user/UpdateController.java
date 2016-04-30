package controllers.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.UserService;
import domain.User;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("userUpdateController")
@RequestMapping("user")
public class UpdateController extends AbstractUpdateController<User, UserService> {

	@Override
	public boolean authorize(User domainObject, UserAccount principal) {
		return domainObject.getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "user/edit";
	}

	@Override
	public User getObject(Map<String, String> pathVariables, User entity, List<String> context) {
		return service.findByPrincipal();
	}
	
	@Override
	protected String onSuccess() {
		return "/profile/user/show.do";
	}

}
