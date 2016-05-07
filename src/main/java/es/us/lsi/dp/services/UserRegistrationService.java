package es.us.lsi.dp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import repositories.UserRepository;
import services.ActorService;
import services.FolderService;
import domain.Actor;
import domain.User;
import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.domain.SocialAccount;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.forms.BaseRegistrationForm;
import es.us.lsi.dp.services.contracts.forms.CreateFormService;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import es.us.lsi.dp.validation.validators.LegalTermsValidator;
import es.us.lsi.dp.validation.validators.PasswordValidator;

@Service
@Transactional
public class UserRegistrationService extends AbstractFormService<User, BaseRegistrationForm, UserRepository> implements
		CreateFormService<BaseRegistrationForm, User> {

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private LegalTermsValidator legalTermsValidator;
	@Autowired
	private PasswordValidator passwordValidator;
	@Autowired
	private FolderService folderService;
	@Autowired
	private ActorService actorService;

	// Create methods ---------------------------------------
	@Override
	public Class<? extends DomainObject> getEntityClass() {
		return BaseRegistrationForm.class;
	}

	@Override
	public void beforeCreating(BaseRegistrationForm validable, List<String> context) {
	}

	@Override
	public void beforeCommitingCreate(BaseRegistrationForm validable, List<String> context) {
	}

	@Override
	public void createBusinessRules(List<BusinessRule<BaseRegistrationForm>> rules, List<Validator> validators) {
		validators.add(legalTermsValidator);
		validators.add(passwordValidator);
	}

	@Override
	public void afterCommitingCreate(int id) {
		Actor actor;
		actor = actorService.findById(id);
		folderService.createAndSaveSystemFolders(actor);
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
		result.setPhone(form.getPhone());
		
		userAccount.setUsername(form.getUsername());
		userAccount.setPassword(form.getPassword());
		userAccount.setSocialAccounts(new ArrayList<SocialAccount>());

		userAccount = userAccountService.save(userAccount);

		result.setUserAccount(userAccount);
		
		result.setFollowees(new ArrayList<User>());
		result.setFollowers(new ArrayList<User>());

		return result;
	}

}
