package validation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import forms.AuditorRegistrationForm;
import forms.UserAccountForm;

@Component
public class SamePasswordsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAccountForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof UserAccountForm){
			UserAccountForm uaf = (UserAccountForm) target;
			
			if(!uaf.getPassword().equals(uaf.getPassword2()))
				errors.rejectValue("password", "acme.validators.password");
		} else if(target instanceof AuditorRegistrationForm){
			AuditorRegistrationForm arf = (AuditorRegistrationForm) target;
			
			if(!arf.getPassword().equals(arf.getPassword2()))
				errors.rejectValue("password", "acme.validators.password");
		}
	}

}
