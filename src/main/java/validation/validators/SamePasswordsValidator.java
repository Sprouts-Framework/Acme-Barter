package validation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import forms.UserAccountForm;

@Component
public class SamePasswordsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAccountForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserAccountForm uaf = (UserAccountForm) target;
		
		if(!uaf.getPassword().equals(uaf.getPassword2()))
			errors.rejectValue("password", "acme.validators.password");
	}

}
