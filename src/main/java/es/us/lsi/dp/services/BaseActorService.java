package es.us.lsi.dp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import es.us.lsi.dp.domain.BaseActor;
import es.us.lsi.dp.repositories.BaseActorRepository;

@Service
@Transactional
public class BaseActorService extends AbstractService<BaseActor, BaseActorRepository> {

	// Managed repository --------------------

	@Autowired
	private BaseActorRepository actorRepository;

	// Supporting services --------------------

	// Constructors --------------------

	public BaseActorService() {
		super();
	}

	// Simple CRUD methods ------------------

	// Other business methods ---------------------
	// Encuentra en actor asociado al nombre de usuario que recibe como
	// parámetro
	public BaseActor findActorByUserAccount(String userAccount) {
		BaseActor result;
		result = actorRepository.findActorByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}

}
