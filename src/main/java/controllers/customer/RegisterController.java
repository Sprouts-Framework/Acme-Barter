package controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.us.lsi.dp.controllers.entities.crud.AbstractCreateController;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.forms.BaseRegistrationForm;
import es.us.lsi.dp.services.CustomerRegistrationService;

@Controller
@RequestMapping("home/customer")
public class RegisterController extends AbstractCreateController<BaseRegistrationForm, CustomerRegistrationService> {

	@Override
	public boolean authorize(BaseRegistrationForm domainObject, UserAccount principal) {
		return true;
	}

	@Override
	protected String view() {
		return "home/sign-up-customer";
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
