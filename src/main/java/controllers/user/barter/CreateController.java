package controllers.user.barter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterFormService;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;
import forms.BarterForm;

@Controller("barterUserCreate")
@RequestMapping("barter/user")
public class CreateController extends AbstractCreateController<BarterForm, BarterFormService>{

	@Override
	public boolean authorize(BarterForm domainObject, UserAccount principal) {
		return principal!=null;
	}

	@Override
	protected String view() {
		return "barter/create";
	}
	
	@Override
	protected String onSuccess() {
		return "../../home/barter/list.do";
	}
	
}
