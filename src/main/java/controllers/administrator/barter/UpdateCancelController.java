package controllers.administrator.barter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;
import domain.Barter;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("BarterUpdateCancelController")
@RequestMapping("barter/administrator")
public class UpdateCancelController extends AbstractUpdateController<Barter, BarterService>{

	@Override
	public boolean authorize(Barter domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "barter/cancel";
	}
	
	@Override
	protected String onSuccess() {
		return "../list.do";
	}

}
