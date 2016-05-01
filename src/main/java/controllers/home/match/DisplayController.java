package controllers.home.match;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Match;
import services.MatchService;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("MatchDisplayController")
@RequestMapping("home/match")
public class DisplayController extends AbstractShowController<Match, MatchService> implements AddsToModel{

	@Override
	public boolean authorize(Match domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "match/display";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Match result;
		int matchId;
		
		matchId = Integer.valueOf(context.get(0));
		result = service.findById(matchId);
		
		objects.put("match", result);		
	}

}
