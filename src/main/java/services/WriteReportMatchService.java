package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import repositories.MatchRepository;
import domain.Match;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.UpdateService;
import es.us.lsi.dp.validation.contracts.BusinessRule;


@Service
@Transactional
public class WriteReportMatchService extends AbstractService<Match, MatchRepository> implements UpdateService<Match>{

	@Override
	public void updateBusinessRules(List<BusinessRule<Match>> rules, List<Validator> validators) {		
	}

	@Override
	public void beforeUpdating(Match validable, List<String> context) {		
	}

	@Override
	public void beforeCommitingUpdate(Match validable, List<String> context) {
	}

	@Override
	public void afterCommitingUpdate(int id) {
	}

}
