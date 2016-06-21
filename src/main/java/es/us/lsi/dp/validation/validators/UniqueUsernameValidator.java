package es.us.lsi.dp.validation.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.validation.Rules;
import es.us.lsi.dp.validation.contracts.RegistersUsers;

@Component(Rules.UNIQUE_USERNAME)
public class UniqueUsernameValidator<R extends DomainObject & RegistersUsers> implements Validator {

	@Autowired
	private SignInService signInService;

	@Override
	public boolean supports(final Class<?> cls) {
		return RegistersUsers.class.isAssignableFrom(cls);
	}
/*
	@Override
	public void validate(final Object target, final Errors errors) {

		boolean usedUsername;
		String username;

		usedUsername = false;

		try {
			username = ((RegistersUsers) target).getUsername();
			signInService.loadUserByUsername(username);
		} catch (final UsernameNotFoundException e) {
			usedUsername = true;
		}

		if (!usedUsername)
			errors.rejectValue(Rules.UNIQUE_USERNAME_FIELD, Rules.UNIQUE_USERNAME_WARNING_CODE);

	}
*/
	
	@Override
	public void validate(final Object target, final Errors errors) {

		boolean usedUsername;
		String username;

		usedUsername = false;
		username = ((RegistersUsers) target).getUsername();
		
		usedUsername = signInService.isUsernameInUse(username);

		if (usedUsername)
			errors.rejectValue(Rules.UNIQUE_USERNAME_FIELD, Rules.UNIQUE_USERNAME_WARNING_CODE);

	}
	
}
