package services;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BarterRepository;

import domain.Barter;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.ListService;

@Service
@Transactional
public class BarterService extends AbstractService<Barter, BarterRepository> implements ListService<Barter>{

	@Override
	public Page<Barter> findPage(Pageable page, String searchCriteria) {
//		Page<Barter> result;
//		result = fullTextSearch(clazz, pageable, searchCriteria, fields)
		return repository.findAll(page);
	}
	
	public Page<Barter> findBarterByUserId(int userId, Pageable page){
		Page<Barter> result;
		
		result = repository.findBartersByUserId(userId, page);
		Assert.notNull(result);
		
		return result;
	}

}
