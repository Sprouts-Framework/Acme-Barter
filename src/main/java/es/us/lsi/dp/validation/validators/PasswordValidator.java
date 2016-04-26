package es.us.lsi.dp.validation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.us.lsi.dp.forms.BaseRegistrationForm;

@Component
public class PasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BaseRegistrationForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof BaseRegistrationForm){
			BaseRegistrationForm brf = (BaseRegistrationForm) target;
			
			if(!brf.getPassword().equals(brf.getPassword2()))
				errors.rejectValue("password", "acme.validators.password");
		} 
	}

}
