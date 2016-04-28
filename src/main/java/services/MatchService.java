package services;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MatchRepository;
import domain.Match;
import es.us.lsi.dp.services.AbstractService;

@Service
@Transactional
public class MatchService extends AbstractService<Match, MatchRepository> {
	
	public Page<Match> findMatchesByUserId(int userId, Pageable page){
		Page<Match> result;
		
		result = repository.findMatchesByUserId(userId, page);
		Assert.notNull(result);
		
		return result;
	}

}
