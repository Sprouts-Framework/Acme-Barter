package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BarterRepository;
import domain.Barter;
import domain.User;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.services.contracts.ShowService;

@Service
@Transactional
public class BarterService extends AbstractService<Barter, BarterRepository> implements ListService<Barter>, ShowService<Barter>{

	@Autowired
	private UserService userService;
	
	@Override
	public Page<Barter> findPage(Pageable page, String searchCriteria) {
		Page<Barter> result;
		result = fullTextSearch(Barter.class, page, searchCriteria, "title", "offered.name" , "offered.description", "requested.name", "requested.description");
		return result;
	}
	
	public Page<Barter> findBarterByUserId(int userId, Pageable page){
		Page<Barter> result;
		
		result = repository.findBartersByUserId(userId, page);
		Assert.notNull(result);
		
		return result;
	}
	
	public Page<Barter> findBartersOfFollowedUsers(Pageable page){
		Page<Barter> result;
		User principal;
		principal = userService.findByPrincipal();
		result = repository.findBartersOfFollowedUsers(principal.getId(), page);
		Assert.notNull(result);
		return result;
	}

}
