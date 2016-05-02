package controllers.user.message;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ActorService;
import services.MessageService;
import domain.Actor;
import domain.Message;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("messageCreateUser")
@RequestMapping("message/user")
public class CreateController extends AbstractCreateController<Message, MessageService> implements AddsToModel{
	
	@Autowired
	private ActorService actorService;
	
	@Override
	protected String view() {
		return "message/create";
	}

	
	@Override
	public boolean authorize(Message domainObject, UserAccount principal) {
		return domainObject.getFolder().getActor().equals(principal.getId());
	}


	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Collection<Actor> actors;
		String rol = new String("user");
		
		actors = actorService.findAll();
		
		objects.put("actor", rol);
		objects.put("actors", actors);
	}
	
	@Override
	protected String onSuccess() {
		return "../../folder/user/list.do";
	}

}
