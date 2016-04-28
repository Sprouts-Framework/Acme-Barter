package controllers.home.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.User;
import services.UserService;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("UserListControllerUC004")
@RequestMapping("home/user")
public class ListController extends AbstractListController<User, UserService>{

	@Override
	protected String view() {
		return "user/list";
	}

}
