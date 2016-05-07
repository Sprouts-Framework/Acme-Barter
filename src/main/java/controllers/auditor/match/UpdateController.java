package controllers.auditor.match;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.WriteReportMatchService;
import domain.Auditor;
import domain.Match;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.BaseActor;
import es.us.lsi.dp.domain.UserAccount;

@Controller("matchAuditorUpdate")
@RequestMapping("match/auditor")
public class UpdateController extends AbstractUpdateController<Match, WriteReportMatchService>{

	@Override
	public boolean authorize(Match domainObject, UserAccount principal) {
		List<BaseActor> aux = (List<BaseActor>) principal.getActors();
		Auditor principalAuditor =  (Auditor) aux.get(0);
		return domainObject.getAuditor().getId() == principalAuditor.getId();
	}

	@Override
	protected String view() {
		return "match/writeReport/update";
	}

}
