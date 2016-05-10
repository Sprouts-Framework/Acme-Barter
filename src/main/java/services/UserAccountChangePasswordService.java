package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import validation.validators.OldPasswordValidator;
import validation.validators.SamePasswordsValidator;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.repositories.UserAccountRepository;
import es.us.lsi.dp.services.AbstractFormService;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.services.contracts.forms.UpdateFormService;
import es.us.lsi.dp.utilities.PasswordEncoder;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.UserAccountForm;

@Service
@Transactional
public class UserAccountChangePasswordService extends AbstractFormService<UserAccount, UserAccountForm, UserAccountRepository> implements
		UpdateFormService<UserAccountForm, UserAccount> {

	@Autowired
	private SamePasswordsValidator samePasswordsValidator;
	@Autowired
	private OldPasswordValidator oldPasswordValidator;

	// Update methods -------------------------------------

	@Override
	public void beforeUpdating(UserAccountForm validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(UserAccountForm validable, List<String> context) {
	}

	@Override
	public void updateBusinessRules(List<BusinessRule<UserAccountForm>> rules, List<Validator> validators) {
		validators.add(samePasswordsValidator);
		validators.add(oldPasswordValidator);
	}

	@Override
	public void afterCommitingUpdate(int id) {
	}

	// Convert methods ------------------------------------
	@Override
	public UserAccount convertToEntity(UserAccountForm form) {
		Assert.notNull(form);

		UserAccount userAccount;
		String hashedPassword;

		userAccount = SignInService.getPrincipal();
		Assert.notNull(userAccount);

		// Hasheo de la contraseña
		hashedPassword = PasswordEncoder.encode(form.getPassword());
		Assert.notNull(hashedPassword);

		userAccount.setUsername(form.getUsername());
		userAccount.setPassword(hashedPassword);

		return userAccount;
	}

	@Override
	public UserAccountForm convertToForm(UserAccount entity) {
		UserAccountForm result;
		result = new UserAccountForm();
		result.setUsername(entity.getUsername());
		return result;
	}
}
