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
import es.us.lsi.dp.services.SignInService;

@Controller("barterDisplayController")
@RequestMapping("home/barter")
public class DisplayController extends AbstractShowController<Barter, BarterService> implements AddsToModel{

	@Override
	public boolean authorize(Barter domainObject, UserAccount principal) {
		return SignInService.checkAuthority("Administrator") || (domainObject.getCancelled() == false);
	}

	@Override
	protected String view() {
		return "barter/display";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		objects.put("_viewName", "barter/display");
	}
}
