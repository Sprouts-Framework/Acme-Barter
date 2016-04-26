package es.us.lsi.dp.services.contracts.forms;

import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.domain.DomainForm;
import es.us.lsi.dp.services.contracts.UpdateService;

public interface UpdateFormService<F extends DomainForm, D extends DomainEntity> extends UpdateService<F> {

	public D convertToEntity(F form);

	public F convertToForm(D entity);

}
