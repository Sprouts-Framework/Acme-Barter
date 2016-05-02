package controllers.user.box;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.FolderService;
import domain.Folder;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;

@Controller("folderCreateUser")
@RequestMapping("folder/user")
public class CreateController extends AbstractCreateController<Folder, FolderService> implements AddsToModel {

	@Override
	public boolean authorize(Folder domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "folder/create";
	}

	@Override
	public void addToModel(final Map<String, Object> objects, List<String> context) {
		String rol = new String("user");
		objects.put("actor", rol);
	}

}
