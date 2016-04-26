package es.us.lsi.dp.forms;

import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.domain.DomainForm;

@Deprecated
public interface FormConverter<F extends DomainForm, E extends DomainEntity> {

	public E convert(F form);

}