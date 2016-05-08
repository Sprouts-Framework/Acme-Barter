package controllers.home.match;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MatchService;
import domain.Match;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("MatchDisplayController")
@RequestMapping("home/match")
public class DisplayController extends AbstractShowController<Match, MatchService> {

	@Override
	public boolean authorize(Match domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "match/display";
	}

}
