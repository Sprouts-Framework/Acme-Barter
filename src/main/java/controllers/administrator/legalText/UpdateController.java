package controllers.administrator.legalText;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.LegalTextService;
import domain.LegalText;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("legalTextAdministratorUpdate")
@RequestMapping("legalText/administrator")
public class UpdateController extends AbstractUpdateController<LegalText, LegalTextService> {

	@Override
	public boolean authorize(LegalText domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "legalText/update";
	}

}
