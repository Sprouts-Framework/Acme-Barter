package es.us.lsi.dp.services.contracts.forms;

import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.domain.DomainForm;
import es.us.lsi.dp.services.contracts.CreateService;

public interface CreateFormService<F extends DomainForm, E extends DomainEntity> extends CreateService<F> {

	public E convertToEntity(F form);

}
