package controllers.auditor.match;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.SelfAssignService;

import domain.Match;

import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("auditorSelfAssignController")
@RequestMapping("match/auditor/self-assign")
public class SelfSssignController extends AbstractUpdateController<Match, SelfAssignService>{

	@Override
	public boolean authorize(Match domainObject, UserAccount principal) {
		return principal != null;
	}
	
	@Override
	protected String view() {
		return "match/self-assign";
	}

}
