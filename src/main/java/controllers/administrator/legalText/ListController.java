package controllers.administrator.legalText;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.LegalTextService;
import domain.LegalText;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("legalTextAdministratorList")
@RequestMapping("legalText/administrator")
public class ListController extends AbstractListController<LegalText, LegalTextService>{

	@Override
	protected String view() {
		return "legalText/list";
	}

}
