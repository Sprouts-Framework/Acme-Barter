package services;


import java.util.Collection;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BarterRepository;
import domain.Barter;
import domain.User;
import es.us.lsi.dp.fulltext.FullTextConstraint;
import es.us.lsi.dp.fulltext.FullTextCustomQuery;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.ListService;
import es.us.lsi.dp.services.contracts.ShowService;

@Service
@Transactional
public class BarterService extends AbstractService<Barter, BarterRepository> implements ListService<Barter>, ShowService<Barter> {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private UserService userService;

	public void cancelBarters() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query storedProcedure = entityManager.createNativeQuery("CALL `acme-barter`.cancelBarters()");
		entityManager.joinTransaction();
		storedProcedure.executeUpdate();
	}

	@Override
	public Page<Barter> findPage(Pageable page, String searchCriteria) {
		Page<Barter> result;
		List<FullTextCustomQuery> queries = new ArrayList<>();
		queries.add(new FullTextCustomQuery("cancelled", FullTextConstraint.EQUALS, false));
		result = fullTextSearch(Barter.class, page, searchCriteria, queries, "title", "offered.name", "offered.description", "requested.name",
				"requested.description");
		return result;
	}

	@Override
	public Page<Barter> findAllDefaultFullText(Pageable page) {
		return repository.findAllDefaultFullText(page);
	}

	public Page<Barter> findBarterByUserId(int userId, Pageable page) {
		Page<Barter> result;

		result = repository.findBartersByUserId(userId, page);
		Assert.notNull(result);

		return result;
	}

	public Page<Barter> findBartersOfFollowedUsers(Pageable page) {
		Page<Barter> result;
		User principal;
		principal = userService.findByPrincipal();
		result = repository.findBartersOfFollowedUsers(principal.getId(), page);
		Assert.notNull(result);
		return result;
	}
	
	public Collection<Barter> findNotMatchedBartersByUserId(){
		Collection<Barter> result;
		User user;
		
		user = userService.findByPrincipal();
		Assert.notNull(user);
		
		result = repository.findNotMatchedBartersByUserId(user.getId());
		
		return result;
	}
	
	public Collection<Barter> findNotMatchedBartersNotOwnByUserId(){
		Collection<Barter> result;
		User user;
		
		user = userService.findByPrincipal();
		Assert.notNull(user);
		
		result = repository.findNotMatchedBartersNotOwnedByUserId(user.getId());
		
		return result;
	}

}
