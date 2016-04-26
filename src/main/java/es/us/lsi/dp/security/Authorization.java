package es.us.lsi.dp.security;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.us.lsi.dp.controllers.core.contracts.Authorizable;
import es.us.lsi.dp.domain.UserAccount;
import es.us.lsi.dp.exceptions.HttpForbiddenException;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.validation.contracts.Validable;

@Component
@Scope("prototype")
public class Authorization<D extends Validable> {

	private Authorizable<D> authorizable;

	public void setAuthorizable(final Authorizable<D> authorizable) {
		this.authorizable = authorizable;
	}

	/**
	 * Checks whether the principal owns the resource
	 * 
	 * @param domainObject
	 *            Resource to be affected by the request
	 */
	public void authorize(final D domainObject) {
		UserAccount principal;
		boolean principalIsAuthorized;

		principal = SignInService.getPrincipalOrNull();

		principalIsAuthorized = authorizable.authorize(domainObject, principal);

		if (!principalIsAuthorized)
			throw new HttpForbiddenException();
	}

}
