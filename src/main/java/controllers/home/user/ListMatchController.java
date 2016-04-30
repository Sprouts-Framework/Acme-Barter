package controllers.home.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MatchService;
import domain.Match;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("ListUserMatchController")
@RequestMapping("home/user/match")
public class ListMatchController extends AbstractListController<Match, MatchService> {

	@Override
	protected String view() {
		return "match/list";
	}
	
	@Override
	protected Page<Match> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<Match> result;
		Integer userId;
		
		userId = Integer.valueOf(context.get(0));
		result = service.findMatchesByUserId(userId, page);		
		
		return result;
	}

}
