package controllers.administrator.legalText;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.LegalTextService;
import domain.LegalText;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("legalTextAdministratorCreate")
@RequestMapping("legalText/administrator")
public class CreateController extends AbstractCreateController<LegalText, LegalTextService>{

	@Override
	public boolean authorize(LegalText domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "legalText/create";
	}

}
