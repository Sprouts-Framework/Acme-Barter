package es.us.lsi.dp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import repositories.CustomerRepository;

import domain.User;

import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.forms.BaseRegistrationForm;
import es.us.lsi.dp.services.contracts.forms.CreateFormService;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import es.us.lsi.dp.validation.validators.LegalTermsValidator;
import es.us.lsi.dp.validation.validators.PasswordValidator;

@Service
@Transactional
public class CustomerRegistrationService extends AbstractFormService<User, BaseRegistrationForm, CustomerRepository> implements
		CreateFormService<BaseRegistrationForm, User> {

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private LegalTermsValidator legalTermsValidator;
	@Autowired
	private PasswordValidator passwordValidator;

	// Create methods ---------------------------------------
	@Override
	public Class<? extends DomainObject> getEntityClass() {
		return BaseRegistrationForm.class;
	}

	@Override
	public void beforeCreating(BaseRegistrationForm validable, List<String> context) {
	}

	@Override
	public void beforeCommitingCreate(BaseRegistrationForm validable) {
	}

	@Override
	public void createBusinessRules(List<BusinessRule<BaseRegistrationForm>> rules, List<Validator> validators) {
		validators.add(legalTermsValidator);
		validators.add(passwordValidator);
	}

	@Override
	public void afterCommitingCreate(int id) {

	}

	// Convert metods -----------------------------------
	@Override
	public User convertToEntity(BaseRegistrationForm form) {
		User result;
		result = new User();
		UserAccount userAccount;
		userAccount = userAccountService.create(result);

		result.setName(form.getName());
		result.setSurname(form.getSurname());

		userAccount.setUsername(form.getUsername());
		userAccount.setPassword(form.getPassword());

		userAccount = userAccountService.save(userAccount);

		result.setUserAccount(userAccount);

		return result;
	}

}
