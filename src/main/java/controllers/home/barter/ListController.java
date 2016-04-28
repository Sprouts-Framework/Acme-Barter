package controllers.home.barter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;

import domain.Barter;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("barterControllerUC002")
@RequestMapping("home/barter")
public class ListController extends AbstractListController<Barter, BarterService>{

	@Override
	protected String view() {
		return "barter/list";
	}

}
