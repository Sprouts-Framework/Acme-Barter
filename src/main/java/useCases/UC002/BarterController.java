package useCases.UC002;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Barter;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("barterControllerUC002")
@RequestMapping("home/barter")
public class BarterController extends AbstractListController<Barter, BarterService>{

	@Override
	protected String view() {
		return "barter/list";
	}

}
