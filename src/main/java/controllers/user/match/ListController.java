package controllers.user.match;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MatchService;
import services.UserService;
import domain.Match;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("userMatchListController")
@RequestMapping("match/user")
public class ListController extends AbstractListController<Match, MatchService>{

	@Autowired
	private UserService userService;

	@Override
	protected String view() {
		return "match/list-user";
	}

	@Override
	protected Page<Match> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<Match> result;
		Integer userId;

		userId = userService.findByPrincipal().getId();
		result = service.findMatchesByUserId(userId, page);

		return result;
	}

}
