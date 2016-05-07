package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import repositories.LegalTextRepository;
import validation.rules.IsNotAssigned;
import domain.LegalText;
import es.us.lsi.dp.domain.DomainObject;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.CrudService;
import es.us.lsi.dp.validation.contracts.BusinessRule;


@Service
@Transactional
public class LegalTextService extends AbstractService<LegalText, LegalTextRepository> implements CrudService<LegalText>{

	@Autowired
	private IsNotAssigned isNotAssigned;
	
	@Override
	public Class<? extends DomainObject> getEntityClass() {
		return LegalText.class;
	}

	@Override
	public void createBusinessRules(List<BusinessRule<LegalText>> rules, List<Validator> validators) {
	}

	@Override
	public void beforeCreating(LegalText validable, List<String> context) {
	}

	@Override
	public void beforeCommitingCreate(LegalText validable, List<String> context) {
	}

	@Override
	public void afterCommitingCreate(int id) {
	}

	@Override
	public void updateBusinessRules(List<BusinessRule<LegalText>> rules, List<Validator> validators) {
	}

	@Override
	public void beforeUpdating(LegalText validable, List<String> context) {
	}

	@Override
	public void beforeCommitingUpdate(LegalText validable, List<String> context) {
	}

	@Override
	public void afterCommitingUpdate(int id) {
	}

	@Override
	public void deleteBusinessRules(List<BusinessRule<LegalText>> rules, List<Validator> validators) {
		rules.add(isNotAssigned);
	}

	@Override
	public void beforeDeleting(LegalText validable, List<String> context) {	
	}

	@Override
	public void beforeCommitingDelete(LegalText validable, List<String> context) {		
	}

	@Override
	public void afterCommitingDelete(int id) {
	}

	@Override
	public Page<LegalText> findPage(Pageable page, String searchCriteria) {
		return repository.findAll(page);
	}


}
