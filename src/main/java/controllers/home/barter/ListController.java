package controllers.home.barter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;

import domain.Barter;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("barterControllerUC002")
@RequestMapping("home/barter")
public class ListController extends AbstractListController<Barter, BarterService> implements AddsToModel{

	@Override
	protected String view() {
		return "barter/list";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		objects.put("searcheable",true);
		
	}
}
