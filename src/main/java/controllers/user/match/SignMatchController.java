package controllers.user.match;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MatchSignService;
import domain.Match;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("userSignMatchController")
@RequestMapping("match/user/sign")
public class SignMatchController extends AbstractUpdateController<Match, MatchSignService> implements AddsToModel{

	@Override
	public boolean authorize(Match domainObject, UserAccount principal) {
		return (domainObject.getOffered().getUser().getUserAccount().equals(principal) || domainObject.getRequested().getUser().getUserAccount()
				.equals(principal));
	}

	@Override
	protected String view() {
		return "match/sign";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		objects.put("type", "sign");
	}

}
