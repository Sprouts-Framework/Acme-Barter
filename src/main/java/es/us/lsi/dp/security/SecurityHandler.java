package es.us.lsi.dp.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.us.lsi.dp.controllers.core.contracts.Authorizable;
import es.us.lsi.dp.validation.contracts.Validable;

@Component
@Scope("prototype")
public class SecurityHandler<D extends Validable> {

	@Autowired
	private Authorization<D> authorization;
	
	@Autowired
	private PostHackingProtection<D> postHackingProtection;

	public void setAuthorizable(final Authorizable<D> authorizable) {
		authorization.setAuthorizable(authorizable);
	}

	public void authorize(final D domainObject) {
		authorization.authorize(domainObject);
	}

	public D getPostHackingSafeObject(final D domainObject, final String viewName, final HttpServletRequest request, final HttpServletResponse response) {
		D result;
		result = postHackingProtection.getSafeObject(domainObject, viewName, request, response);
		return result;
	}
}
