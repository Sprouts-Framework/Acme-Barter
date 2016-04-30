package controllers.home.barter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;
import domain.Barter;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("barterDisplayControllerUC002")
@RequestMapping("home/barter")
public class DisplayController extends AbstractShowController<Barter, BarterService> implements AddsToModel{

	@Override
	public boolean authorize(Barter domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "barter/display";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Barter barter;
		int barterId;
		
		barterId = new Integer(context.get(0));
		barter = service.findById(barterId);
		
		objects.put("barter", barter);
		objects.put("_viewName", "barter/display");
	}

}
