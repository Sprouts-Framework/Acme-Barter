package controllers.home.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Barter;

import services.BarterService;

import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("ListUserBarterController")
@RequestMapping("home/user/barter")
public class ListBarterController extends AbstractListController<Barter, BarterService>{

	@Override
	protected String view() {
		return "barter/list";
	}

	@Override
	protected Page<Barter> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<Barter> result;
		Integer id;
		
		id = Integer.valueOf(context.get(0));
		result = service.findBarterByUserId(id, page);
		
		return result;
	}

}
