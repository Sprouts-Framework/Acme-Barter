package controllers.user.message;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ActorService;
import services.FolderService;
import services.MessageService;
import domain.Actor;
import domain.Folder;
import domain.Message;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;

@Controller("messageUpdateUser")
@RequestMapping("message/user")
public class UpdateController extends AbstractUpdateController<Message, MessageService> implements AddsToModel{
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;
	
	@Override
	protected String view() {
		return "message/update";
	}

	
	@Override
	public boolean authorize(Message domainObject, UserAccount principal) {
		return domainObject.getFolder().getActor().equals(principal.getId());
	}


	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Collection<Folder> folders;
		Actor thisActor;
		String rol = new String("user");
		thisActor = actorService.findActorByUserAccount(SignInService.getPrincipal().getUsername());
		
		folders = folderService.findFolders(thisActor.getId());
		objects.put("folders", folders);
		objects.put("actor", rol);
	}

	@Override
	protected String onSuccess() {
		return "../../../folder/user/list.do";
	}
}
