package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MatchRepository;
import domain.Match;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.services.contracts.ShowService;

@Service
@Transactional
public class MatchService extends AbstractService<Match, MatchRepository> implements ListService<Match>, ShowService<Match>{
	
	public Page<Match> findMatchesByUserId(int userId, Pageable page){
		Page<Match> result;
		
		result = repository.findMatchesByUserId(userId, page);
		Assert.notNull(result);
		 
		return result;
	}

	@Override
	public Page<Match> findPage(Pageable page, String searchCriteria) {
		return repository.findAll(page);
	}
	
	public Collection<Match> findMatchesByLegalTextId(int legalTextId){
		Collection<Match> result;
		result = repository.findMatchesByLegalTextId(legalTextId);
		Assert.notNull(result);
		return result;
	}

}
