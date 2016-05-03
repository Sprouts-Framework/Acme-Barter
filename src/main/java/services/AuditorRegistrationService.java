package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import repositories.AuditorRepository;
import domain.Actor;
import domain.Auditor;
import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.domain.SocialAccount;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.AbstractFormService;
import es.us.lsi.dp.services.UserAccountService;
import es.us.lsi.dp.services.contracts.forms.CreateFormService;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import es.us.lsi.dp.validation.validators.PasswordValidator;
import forms.AuditorRegistrationForm;

@Service
@Transactional
public class AuditorRegistrationService extends AbstractFormService<Auditor, AuditorRegistrationForm, AuditorRepository> implements
		CreateFormService<AuditorRegistrationForm, Auditor> {

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private PasswordValidator passwordValidator;
	@Autowired
	private FolderService folderService;
	@Autowired
	private ActorService actorService;


	// Create methods ---------------------------------------
	@Override
	public Class<? extends DomainObject> getEntityClass() {
		return AuditorRegistrationForm.class;
	}

	@Override
	public void beforeCreating(AuditorRegistrationForm validable, List<String> context) {
	}

	@Override
	public void beforeCommitingCreate(AuditorRegistrationForm validable) {
	}

	@Override
	public void createBusinessRules(List<BusinessRule<AuditorRegistrationForm>> rules, List<Validator> validators) {
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
	public Auditor convertToEntity(AuditorRegistrationForm form) {
		Auditor result;
		result = new Auditor();
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

		return result;
	}

}
