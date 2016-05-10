package controllers.administrator.auditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.AuditorRegistrationService;
import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;
import forms.AuditorRegistrationForm;

@Controller("AuditorRegisterController")
@RequestMapping("auditor/administrator")
public class RegisterController extends AbstractCreateController<AuditorRegistrationForm, AuditorRegistrationService> {

	@Override
	public boolean authorize(AuditorRegistrationForm domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "home/sign-up-auditor";
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
