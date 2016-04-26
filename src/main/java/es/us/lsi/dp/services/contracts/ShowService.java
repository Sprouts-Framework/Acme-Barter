package es.us.lsi.dp.services.contracts;

import es.us.lsi.dp.validation.contracts.Validable;

public interface ShowService<D extends Validable> {

	public D findById(int id);

}
