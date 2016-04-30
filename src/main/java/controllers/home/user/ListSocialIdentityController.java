package controllers.home.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.SocialIdentityService;
import domain.SocialIdentity;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("ListUserSocialIdentityController")
@RequestMapping("home/user/socialIdentity")
public class ListSocialIdentityController extends AbstractListController<SocialIdentity, SocialIdentityService> {

	@Override
	protected String view() {
		return "socialIdentity/list";
	}
	
	@Override
	protected Page<SocialIdentity> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<SocialIdentity> result;
		int userId;
		
		userId = Integer.valueOf(context.get(0));		
		result = service.findSocialIdentitiesByUserId(userId, page);
		
		return result;
	}

}
