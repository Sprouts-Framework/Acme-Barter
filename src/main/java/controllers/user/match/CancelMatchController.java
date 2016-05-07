package controllers.user.match;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MatchCancelService;
import services.MatchService;
import domain.Match;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;

/*
 * TODO: Dos controladores: uno sign.do y otro cancel.do. Las comprobaciones en
 * ambos serán distintas. En sign, compruebo que se puede firmar y que no está
 * cancelado. En cancel, compruebo que no estaba cancelado.
 */

@Controller("userCancelMatchController")
@RequestMapping("match/user/cancel")
public class CancelMatchController extends AbstractUpdateController<Match, MatchCancelService> implements AddsToModel{

	@Override
	public boolean authorize(Match domainObject, UserAccount principal) {

		return (domainObject.getOffered().getUser().getUserAccount().equals(principal) || domainObject.getRequested().getUser().getUserAccount()
				.equals(principal));
	}

	@Override
	protected String view() {
		return "match/cancel";
	}

	@Override
	protected String onSuccess() {
		return "../../list.do";
	}
	
	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		objects.put("type", "cancel");
	}

}
