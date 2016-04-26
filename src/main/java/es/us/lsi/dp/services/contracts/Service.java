package es.us.lsi.dp.services.contracts;

import es.us.lsi.dp.domain.DomainObject;

public interface Service<D extends DomainObject> {

	public D create();

	public int save(D domainObject);

	public int update(D domainObject);

	public long count();

	public D findById(int id);

}
