package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.FolderRepository;
import validation.rules.IsAuthorisedToManageFolder;
import validation.rules.IsNotSystemFolder;
import domain.Actor;
import domain.Folder;
import domain.Message;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.services.contracts.CrudService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Service
@Transactional
public class FolderService extends AbstractService<Folder, FolderRepository> implements CrudService<Folder>, ListService<Folder> {

	// Supporting services --------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private MessageService messageService;

	// Rules

	@Autowired
	private IsAuthorisedToManageFolder authorisedToManageFolder;

	@Autowired
	private IsNotSystemFolder isNotSystemFolder;

	// Create methods ------------------------------

	@Override
	public Class<? extends DomainEntity> getEntityClass() {
		return Folder.class;
	}

	@Override
	public int save(Folder folder) {
		checkIsAuthorised(folder.getActor().getId());
		return super.save(folder);
	}

	@Override
	public void beforeCreating(Folder result, List<String> context) {
		result.setSystem(false);
		Actor actor;
		actor = actorService.findActorByPrincipal();
		result.setActor(actor);
	}

	@Override
	public void beforeCommitingCreate(Folder validable, List<String> context) {

	}

	@Override
	public void createBusinessRules(List<BusinessRule<Folder>> rules, List<Validator> validators) {
		rules.add(authorisedToManageFolder);
	}

	@Override
	public void afterCommitingCreate(int id) {

	}

	// Update methods --------------------------------------

	@Override
	public void beforeUpdating(Folder validable, List<String> context) {

	}

	@Override
	public void beforeCommitingUpdate(Folder validable, List<String> context) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<Folder>> rules, List<Validator> validators) {
		rules.add(isNotSystemFolder);

	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Delete methods -------------------------------------

	@Override
	public void beforeDeleting(Folder folder, List<String> context) {
	}

	@Override
	public void beforeCommitingDelete(Folder validable, List<String> context) {
		if(!validable.getSystem()){
			Collection<Message> messages;
			messages = messageService.findMessagesInFolder(validable.getId());
			if (!messages.isEmpty()) {
				Folder trashbox;
				trashbox = repository.findSystemFolder(validable.getActor().getId(), "trash box");
				while (messages.toArray().length != 0) {
					Message m = (Message) messages.toArray()[0];
					m.setFolder(trashbox);
					// Guardo la carpeta y el mensaje
					messageService.save(m);
					messages = messageService.findMessagesInFolder(validable.getId());
				}
			}
		}
	}

	@Override
	public void deleteBusinessRules(List<BusinessRule<Folder>> rules, List<Validator> validators) {
		rules.add(isNotSystemFolder);

	}

	@Override
	public void afterCommitingDelete(int id) {

	}

	// Find methods -------------------------------------------
	@Override
	public Folder findById(int id) {
		Folder result;
		result = super.findById(id);
		checkIsAuthorised(result.getActor().getId());
		return result;
	}

	@Override
	public Page<Folder> findPage(Pageable page, String searchCriteria) {
		Page<Folder> result;
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable auxPage = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		result = repository.findAll(auxPage);
		Assert.notNull(result);
		return result;
	}

	// Otros métodos

	public Page<Folder> findFolders(Integer actorId, final Pageable page) {
		Assert.notNull(SignInService.getPrincipal());
		checkIsAuthorised(actorId);
		Page<Folder> result;
		result = repository.findFoldersByActor(actorId, page);
		Assert.notNull(result);

		return result;
	}

	public Collection<Folder> findFolders(Integer actorId) {
		Assert.notNull(SignInService.getPrincipal());
		checkIsAuthorised(actorId);
		Collection<Folder> result;
		result = repository.findFoldersByActor(actorId);
		Assert.notNull(result);

		return result;
	}

	public Folder findSystemFolder(Integer actorId, String name) {
		Folder result;

		result = repository.findSystemFolder(actorId, name);
		Assert.notNull(result);

		return result;
	}

	public Collection<Folder> createAndSaveSystemFolders(Actor actor) {
		Assert.notNull(actor);
		Collection<Folder> folders = new ArrayList<Folder>();

		Folder inbox = new Folder();
		inbox.setSystem(true);
		inbox.setName("in box");
		inbox.setActor(actor);

		Folder outbox = new Folder();
		outbox.setSystem(true);
		outbox.setName("out box");
		outbox.setActor(actor);

		Folder trashbox = new Folder();
		trashbox.setSystem(true);
		trashbox.setName("trash box");
		trashbox.setActor(actor);

		Folder spambox = new Folder();
		spambox.setSystem(true);
		spambox.setName("spam box");
		spambox.setActor(actor);

		folders.add(inbox);
		folders.add(outbox);
		folders.add(trashbox);
		folders.add(spambox);

		Collection<Folder> result;
		result = (Collection<Folder>) repository.save(folders);

		return result;
	}

	public void edit(Folder folder) {
		Assert.notNull(folder);

		Assert.isTrue(folder.getSystem() == false);

		repository.save(folder);
	}

	public void checkIsAuthorised(int actorId) {
		Actor actor;
		UserAccount userAccount;
		userAccount = SignInService.getPrincipal();
		actor = actorService.findActorByUserAccount(userAccount.getUsername());
		Assert.isTrue(actor.getId() == actorId);
	}

}
