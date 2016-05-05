package controllers.administrator.barter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Barter;

import services.BarterService;
import services.RelateBarterFormService;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;
import forms.RelateBarterForm;

@Controller("barterUserUpdate")
@RequestMapping("barter/administrator/relate")
public class UpdateController extends AbstractUpdateController<RelateBarterForm, RelateBarterFormService> implements AddsToModel{

	@Autowired
	private BarterService barterService;
	
	@Override
	public boolean authorize(RelateBarterForm domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "barter/relate";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Collection<Barter> barters = barterService.findAll();
		objects.put("barters",barters);
	}

	@Override
	protected String onSuccess() {
		return "../../../home/barter/list.do";
	}
}
