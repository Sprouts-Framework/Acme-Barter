package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import repositories.BarterRepository;
import domain.Barter;
import domain.Item;
import domain.User;
import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.services.AbstractFormService;
import es.us.lsi.dp.services.contracts.forms.CreateFormService;
import es.us.lsi.dp.utilities.Moment;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.BarterForm;

@Service
@Transactional
public class BarterFormService extends AbstractFormService<Barter, BarterForm, BarterRepository> implements CreateFormService<BarterForm, Barter>{

	@Autowired
	private UserService userService;
	@Autowired
	private ItemService itemService;
	
	@Override
	public Class<? extends DomainObject> getEntityClass() {
		return BarterForm.class;
	}

	@Override
	public void createBusinessRules(List<BusinessRule<BarterForm>> rules, List<Validator> validators) {
	}

	@Override
	public void beforeCreating(BarterForm validable, List<String> context) {
	}

	@Override
	public void beforeCommitingCreate(BarterForm validable, List<String> context) {
	}

	@Override
	public void afterCommitingCreate(int id) {
	}

	@Override
	public Barter convertToEntity(BarterForm form) {
		Barter result;
		Item offered;
		Item requested;
		
		result = new Barter();
		offered = new Item();
		requested = new Item();
		
		offered.setName(form.getOfferedName());
		offered.setDescription(form.getOfferedDescription());
		offered.setPictures(form.getOfferedPictures());
		
		requested.setName(form.getRequestedName());
		requested.setDescription(form.getRequestedDescription());
		requested.setPictures(form.getRequestedPictures());
		
		int offeredId = itemService.save(offered);
		int requestedId = itemService.save(requested);
		Item offeredBd = itemService.findById(offeredId);
		Item requestedBd = itemService.findById(requestedId);
		
		User principal = userService.findByPrincipal();
		Date now = Moment.now();
		
		result.setTitle(form.getTitle());
		result.setCancelled(false);
		result.setMoment(now);
		result.setRequesteds(new ArrayList<Barter>());
		result.setOffereds(new ArrayList<Barter>());
		result.setUser(principal);
		result.setOffered(offeredBd);
		result.setRequested(requestedBd);
		return result;
	}

}
