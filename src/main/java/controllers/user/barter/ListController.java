package controllers.user.barter;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;
import services.UserService;
import domain.Barter;
import domain.User;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("listBarterUserController")
@RequestMapping("barter/user")
public class ListController extends AbstractListController<Barter, BarterService> implements AddsToModel{

	@Autowired
	private UserService userService;

	@Override
	protected String view() {
		return "barter/list";
	}

	@Override
	protected Page<Barter> getPage(Pageable page, String searchCriteria, List<String> context) {
		User principal;
		principal = userService.findByPrincipal();
		return service.findBarterByUserId(principal.getId(), page);
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		objects.put("searcheable",false);
		
	}

}
