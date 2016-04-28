package services;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.UserRepository;
import domain.User;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.ListService;

@Service
@Transactional
public class UserService extends AbstractService<User, UserRepository> implements ListService<User>{

	@Override
	public Page<User> findPage(Pageable page, String searchCriteria) {
		Page<User> result;
		result = repository.findAll(page);
		Assert.notNull(result);
		
		return result;
	}

	
}
