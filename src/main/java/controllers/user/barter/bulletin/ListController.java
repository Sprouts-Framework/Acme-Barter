package controllers.user.barter.bulletin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;
import domain.Barter;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("barterUserBulletinListController")
@RequestMapping("barter/user/bulletin")
public class ListController extends AbstractListController<Barter, BarterService>{
	
	@Override
	protected String view() {
		return "barter/bulletin/list";
	}

	@Override
	protected Page<Barter> getPage(Pageable page, String searchCriteria, List<String> context) {
		return service.findBartersOfFollowedUsers(page);
	}
	
}
