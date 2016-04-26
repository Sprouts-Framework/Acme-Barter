package es.us.lsi.dp.services.contracts;

import java.util.Collection;

import es.us.lsi.dp.domain.DomainEntity;

public interface EntityService<E extends DomainEntity> extends Service<E> {

	public void delete(E domainObject);

	public Collection<E> findAll();

}
