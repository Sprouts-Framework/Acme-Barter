package controllers.administrator.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.UserService;
import domain.User;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("userListAdministrator")
@RequestMapping("dashboard/administrator/user")
public class ListUsersController extends AbstractListController<User, UserService> implements AddsToModel{

	@Override
	protected String view() {
		return "dashboard/users/list";
	}
	
	@Override
	protected Page<User> getPage(Pageable page, String searchCriteria, List<String> context) {
		Integer option = new Integer(context.get(0));
		
		switch(option){
		case 1:
			return service.usersWhoHaveGotMoreMatchesAudited(page);
		default:
			return null;
		}
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Integer option = new Integer(context.get(0));
		
		Long quantity = null;

	
		switch(option){
		case 1:
			quantity = service.quantityOfMatchesAudited();
			break;
		default:
			break;
		}
		
		objects.put("quantity", quantity);
		objects.put("option", option);
	}

}
