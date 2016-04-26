package es.us.lsi.dp.validation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.us.lsi.dp.forms.BaseRegistrationForm;

@Component
public class LegalTermsValidator implements Validator {

	@Override
	public boolean supports(final Class<?> clazz) {
		return BaseRegistrationForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BaseRegistrationForm brf;
		brf = (BaseRegistrationForm) target;

		if (!brf.getCheckBox())
			errors.rejectValue("checkBox", "acme.validators.legal-terms");

	}

}
