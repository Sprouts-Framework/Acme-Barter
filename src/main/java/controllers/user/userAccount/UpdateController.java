package controllers.user.userAccount;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.UserAccountChangePasswordService;
import es.us.lsi.dp.controllers.entities.crud.AbstractUpdateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.SignInService;
import forms.UserAccountForm;

@Controller("userProfileUpdateController")
@RequestMapping("profile/userAccount/user")
public class UpdateController extends AbstractUpdateController<UserAccountForm, UserAccountChangePasswordService>{

	@Override
	public boolean authorize(UserAccountForm domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "profile/editUserAccount";
	}

	@Override
	public UserAccountForm getObject(Map<String, String> pathVariables, UserAccountForm entity, List<String> context) {
		return service.convertToForm(SignInService.getPrincipal());
	}
	
	@Override
	protected String onSuccess() {
		return "/profile/user/show.do";
	}
}
