package controllers.user.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.UserService;
import domain.User;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;


@Controller("userUserFollowingList")
@RequestMapping("user/user/following")
public class ListFollowingController extends AbstractListController<User, UserService>{

	@Override
	protected String view() {
		return "user/following";
	}
	
	@Override
	protected Page<User> getPage(Pageable page, String searchCriteria, List<String> context) {
		return service.findFollowingByPrincipal(page);
	}

}
