package es.us.lsi.dp.controllers.core.contracts;

import java.util.List;

import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.validation.contracts.Validable;

public interface Authorizable<D extends Validable> {

	public void beforeAuthorization(final D object, List<String> context);

	public boolean authorize(final D domainObject, final UserAccount principal);

}
