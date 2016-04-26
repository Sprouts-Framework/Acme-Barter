package es.us.lsi.dp.security;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.exceptions.PostHackingAttemptException;
import es.us.lsi.dp.utilities.ViewParser;
import es.us.lsi.dp.validation.contracts.Validable;

@Component
public class PostHackingProtection<D extends Validable> {

	public D getSafeObject(final D domainObject, final String viewName, final HttpServletRequest request, final HttpServletResponse response) {
		D result;
		Set<String> declaredFields;
		Set<String> protectedFields;
		DomainEntity postedObject;

		declaredFields = ViewParser.getDeclaredFields(viewName, request, response);
		protectedFields = ViewParser.getProtectedFields(viewName, request, response);

		postedObject = (DomainEntity) domainObject;

		result = makeSafe(postedObject, declaredFields, protectedFields);

		// checkForHackingAttempt(domainEntity, result, request);

		return result;
	}

	@SuppressWarnings("unchecked")
	protected D makeSafe(final DomainEntity postedObject, final Set<String> declaredFields, final Set<String> protectedFields) {
		D result;
		Set<String> mutableFields;

		mutableFields = new HashSet<>(declaredFields);

		mutableFields.removeAll(protectedFields);

		result = (D) postedObject.reconstruct(mutableFields);

		return result;
	}

	protected void checkForHackingAttempt(final DomainEntity postedObject, final D safeObject, final HttpServletRequest request) {
		boolean postHackingAttempt;
		DomainEntity reconstructedPostedObject;

		reconstructedPostedObject = getReconstructedPostedObject(postedObject, request);

		postHackingAttempt = !reconstructedPostedObject.reflectionEquals(safeObject);

		if (postHackingAttempt)
			throw new PostHackingAttemptException();
	}

	protected DomainEntity getReconstructedPostedObject(final DomainEntity domainEntity, final HttpServletRequest request) {
		DomainEntity result;
		List<String> params;

		params = Collections.list(request.getParameterNames());
		result = domainEntity.reconstruct(new HashSet<>(params));

		return result;
	}
}
