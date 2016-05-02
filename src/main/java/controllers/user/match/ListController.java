package controllers.user.match;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MatchService;
import domain.Match;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("MatchListController")
@RequestMapping("match/user/bulletin")
public class ListController extends AbstractListController<Match, MatchService>{

	@Override
	protected String view() {
		return "match/bulletin";
	}
	
	@Override
	protected Page<Match> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<Match> result;
		
		result = service.findMatchesOfFollowedUsers(page);
		
		return result;
	}

}
