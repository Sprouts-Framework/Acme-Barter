package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import repositories.BarterRepository;
import validation.rules.IsDifferentBarter;
import validation.rules.IsNotRelated;
import domain.Barter;
import es.us.lsi.dp.services.AbstractFormService;
import es.us.lsi.dp.services.contracts.forms.UpdateFormService;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import forms.RelateBarterForm;


@Service
@Transactional
public class RelateBarterFormService extends AbstractFormService<Barter, RelateBarterForm, BarterRepository> implements UpdateFormService<RelateBarterForm, Barter>{

	@Autowired
	private IsNotRelated isNotRelated;
	@Autowired
	private IsDifferentBarter isDifferentBarter;
	
	@Override
	public void updateBusinessRules(List<BusinessRule<RelateBarterForm>> rules, List<Validator> validators) {
		rules.add(isNotRelated);
		rules.add(isDifferentBarter);
		
	}

	@Override
	public void beforeUpdating(RelateBarterForm validable, List<String> context) {
		
	}

	@Override
	public void beforeCommitingUpdate(RelateBarterForm validable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCommitingUpdate(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Barter convertToEntity(RelateBarterForm form) {
		Barter offered = form.getOffered();
		Barter requested = form.getRequested();
		requested.getOffereds().add(offered);
		repository.save(requested);
		
		offered.getRequesteds().add(requested);
		
		return offered;
	}

	@Override
	public RelateBarterForm convertToForm(Barter entity) {
		RelateBarterForm result = new RelateBarterForm();
		result.setOffered(entity);
		return result;
	}

	
	
}
