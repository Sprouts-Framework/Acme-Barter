package es.us.lsi.dp.services.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.us.lsi.dp.validation.contracts.Validable;

public interface ListService<D extends Validable> {

	public Page<D> findPage(Pageable page, String searchCriteria);

}