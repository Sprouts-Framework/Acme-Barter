package controllers.user.match;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;
import services.LegalTextService;
import services.MatchService;
import domain.Barter;
import domain.LegalText;
import domain.Match;
import domain.User;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("MatchCreateController")
@RequestMapping("match/user")
public class CreateController extends AbstractCreateController<Match, MatchService> implements AddsToModel{
	
	@Autowired
	private BarterService barterService;
	@Autowired
	private LegalTextService legalTextService;

	@Override
	public boolean authorize(Match domainObject, UserAccount principal) {
		GrantedAuthority authority;
		authority = new SimpleGrantedAuthority(User.class.getSimpleName());
		
		return (principal.getAuthorities().contains(authority));
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Collection<Barter> offered;
		Collection<Barter> requested;
		Collection<LegalText> legalText;
		
		offered = barterService.findNotMatchedBartersByUserId();
		requested = barterService.findNotMatchedBartersNotOwnByUserId();
		legalText = legalTextService.findAll();
	
		objects.put("offereds", offered);
		objects.put("requesteds", requested);
		objects.put("legalTexts", legalText);
	}

	@Override
	protected String view() {
		return "match/create";
	}

}
