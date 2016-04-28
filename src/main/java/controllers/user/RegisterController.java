package controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.forms.BaseRegistrationForm;
import es.us.lsi.dp.services.UserRegistrationService;

@Controller
@RequestMapping("home/user")
public class RegisterController extends AbstractCreateController<BaseRegistrationForm, UserRegistrationService> {

	@Override
	public boolean authorize(BaseRegistrationForm domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "home/sign-up-user";
	}

	@Override
	protected String onSuccess() {
		return "../../";
	}

	@Override
	protected String successCode() {
		return "sign-up.success";
	}
}
