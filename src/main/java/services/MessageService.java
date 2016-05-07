package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
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
public class MessageService extends AbstractService<Message, MessageRepository> implements CrudService<Message>, ListService<Message> {

	
	@Autowired
	private ActorService actorService;

	@Autowired
	private FolderService folderService;

	// Create methods --------------------------------

	@Override
	public Class<? extends DomainEntity> getEntityClass() {
		return Message.class;
	}
	

	@Override
	public void beforeCreating(Message result, List<String> context) {

		Date currentMoment;
		currentMoment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(currentMoment);

		Actor actor;
		actor = actorService.findActorByPrincipal();

		Folder folder;
		folder = folderService.findSystemFolder(actor.getId(), "out box");
		Assert.notNull(folder);

		result.setFolder(folder);
		result.setSender(actor);
	}

	@Override
	public void beforeCommitingCreate(Message validable, List<String> context) {

	}

	@Override
	public void createBusinessRules(List<BusinessRule<Message>> rules, List<Validator> validators) {
	
	}

	@Override
	public void afterCommitingCreate(int id) {
		
	}

	@Override
	public int save(Message messageSender) {
		Assert.notNull(messageSender);
		checkIsAuthorised(messageSender.getFolder().getActor().getId());
		Actor sender;
		sender = messageSender.getSender();

		Actor recipient;
		recipient = messageSender.getRecipient();

		String subject;
		subject = messageSender.getSubject();

		String body;
		body = messageSender.getBody();

		Message messageRecipient;
		messageRecipient = new Message();

		Date currentMoment;
		currentMoment = new Date(System.currentTimeMillis() - 1);

		messageSender.setMoment(currentMoment);
		messageRecipient.setMoment(currentMoment);

		messageRecipient.setSender(sender);
		messageRecipient.setRecipient(recipient);

		messageRecipient.setSubject(subject);
		messageRecipient.setBody(body);

		Folder recipientFolder;

		recipientFolder = folderService.findSystemFolder(recipient.getId(), "in box");

		messageRecipient.setFolder(recipientFolder);

		Message saved;
		saved = repository.save(messageRecipient);
		repository.save(messageSender);
		return saved.getId();
	}
	
	// Update methods ------------------------------

	@Override
	public void beforeUpdating(Message validable, List<String> context) {

	}

	@Override
	public void beforeCommitingUpdate(Message validable, List<String> context) {

	}

	@Override
	public void updateBusinessRules(List<BusinessRule<Message>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingUpdate(int id) {

	}

	// Delete methods ------------------------

	@Override
	public void beforeDeleting(Message validable, List<String> context) {

	}

	@Override
	public void beforeCommitingDelete(Message validable, List<String> context) {

	}

	@Override
	public void deleteBusinessRules(List<BusinessRule<Message>> rules, List<Validator> validators) {

	}

	@Override
	public void afterCommitingDelete(int id) {

	}

	@Override
	public void delete(Message message) {
		Assert.notNull(message);
		checkIsAuthorised(message.getFolder().getActor().getId());

		if (message.getFolder().getSystem() == true && message.getFolder().getName().equals("trash box"))
			repository.delete(message);
		else {
			Actor actor;
			actor = actorService.findActorByPrincipal();
			Folder trashbox;
			trashbox = folderService.findSystemFolder(actor.getId(), "trash box");
			message.setFolder(trashbox);
			repository.save(message);
		}
	}

	// Find methods -----------------------------
	@Override
	public Message findById(int id) {
		Message result;
		result = super.findById(id);
		checkIsAuthorised(result.getFolder().getActor().getId());
		return result;
	}

	public Page<Message> findMessagesInFolder(Integer folderId, Pageable page) {
		Assert.notNull(folderId);

		Folder folder;
		// El motivo de llamar a este método es que el findOne verifique
		// que el usuario logueado es el dueño de la carpeta.
		folder = folderService.findById(folderId);

		Page<Message> result;
		result = repository.findMessagesInFolder(folder.getId(), page);
		Assert.notNull(result);
		return result;
	}

	public Collection<Message> findMessagesInFolder(Integer folderId) {
		Assert.notNull(folderId);

		Folder folder;
		// El motivo de llamar a este método es que el findOne verifique
		// que el usuario logueado es el dueño de la carpeta.
		folder = folderService.findById(folderId);

		Collection<Message> result;
		result = repository.findMessagesInFolder(folder.getId());
		Assert.notNull(result);
		return result;
	}

	@Override
	public Page<Message> findPage(Pageable page, String searchCriteria) {
		return repository.findAll(page);
	}

	public void checkIsAuthorised(int actorId) {
		Actor actor;
		UserAccount userAccount;
		userAccount = SignInService.getPrincipal();
		actor = actorService.findActorByUserAccount(userAccount.getUsername());
		Assert.isTrue(actor.getId() == actorId);
	}

}
