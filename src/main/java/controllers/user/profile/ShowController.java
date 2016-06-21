package controllers.user.profile;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.UserService;
import domain.User;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("userProfileShowController")
@RequestMapping("profile/user")
public class ShowController extends AbstractShowController<User, UserService> implements AddsToModel{

	@Override
	public boolean authorize(User domainObject, UserAccount principal) {
		return domainObject.getUserAccount().equals(principal);
	}

	@Override
	protected String view() {
		return "profile/show";
	}
	
	@Override
	public User getObject(Map<String, String> pathVariables, User entity, List<String> context) {
		return service.findByPrincipal();
	}
	
	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		if(!context.isEmpty()){
			String error = context.get(0);
			objects.put("error", error);
		}
	}
}
