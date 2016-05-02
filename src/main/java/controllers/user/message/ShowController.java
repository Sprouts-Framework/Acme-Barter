package controllers.user.message;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MessageService;
import domain.Message;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractShowController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("messageShowUser")
@RequestMapping("message/user")
public class ShowController extends AbstractShowController<Message, MessageService> implements AddsToModel{


	@Override
	protected String view() {
		return "message/show";
	}

	@Override
	public boolean authorize(Message domainObject, UserAccount principal) {
		return domainObject.getFolder().getActor().equals(principal.getId());
	}
	
	@Override
	public void addToModel(final Map<String, Object> objects, List<String> context) {
		String rol = new String("user");
		objects.put("actor", rol);
	}
}
