package controllers.administrator.barter;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;
import domain.Barter;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("BarterAdministratorListController")
@RequestMapping("barter/administrator")
public class ListController extends AbstractListController<Barter, BarterService> implements AddsToModel{

	@Override
	protected String view() {
		return "barter/list-admin";
	}
	
	@Override
	protected Page<Barter> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<Barter> result;
		result = service.findAllPaged(page);
		
		return result;
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		objects.put("requestURI", "barter/administrator/list");		
	}

}
