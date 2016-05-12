package controllers.administrator.match;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MatchService;
import domain.Match;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("matchAdminListController")
@RequestMapping("match/administrator/")
public class ListController extends AbstractListController<Match, MatchService>{

	@Override
	protected String view() {
		return "match/list";
	}
	

}
