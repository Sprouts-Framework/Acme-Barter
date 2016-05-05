package controllers.home.legalText;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.LegalTextService;
import domain.LegalText;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("LegalTextInfoController")
@RequestMapping("home/legalText")
public class InfoController extends AbstractShowController<LegalText, LegalTextService> implements AddsToModel{

	@Autowired
	private LegalTextService legalTextService;

	@Override
	public boolean authorize(LegalText domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "legalText/info";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		LegalText legalText;
		int legalTextId;
		
		legalTextId = Integer.valueOf(context.get(0));
		legalText = legalTextService.findById(legalTextId);
		
		objects.put("legalText", legalText);
	}
	
}
