package controllers.home.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.UserService;
import domain.User;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("UserShowController")
@RequestMapping("home/user/profile")
public class ShowController extends AbstractShowController<User, UserService> implements AddsToModel{

	@Override
	public boolean authorize(User domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "user/profile";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		objects.put("_viewName", "user/profile");
		
	}

}
