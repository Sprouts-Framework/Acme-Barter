package es.us.lsi.dp.controllers.core;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.controllers.Codes;
import es.us.lsi.dp.controllers.core.contracts.Postable;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.exceptions.ExceptionHandler;
import es.us.lsi.dp.transactions.TxHandler;
import es.us.lsi.dp.utilities.ContextParser;
import es.us.lsi.dp.utilities.Response;
import es.us.lsi.dp.validation.ValidationHandler;
import es.us.lsi.dp.validation.contracts.Validable;

public abstract class PostController<D extends Validable, E extends Validable> extends GetController<D, E> implements Postable<D, E> {

	// Transactions ------------------------------------------------------------

	@Autowired
	private TxHandler transaction;

	// Validation --------------------------------------------------------------

	@Autowired
	private ValidationHandler<D, E> validate;

	// Exceptions --------------------------------------------------------------

	@Autowired
	private ExceptionHandler exceptionHandler;

	protected void exceptions(final Map<Class<? extends Throwable>, String> messages) {
	}

	// Initialisation ----------------------------------------------------------

	@PostConstruct
	protected final void loadExceptionMessages() {
		exceptions(exceptionHandler.getExceptionMessageMapping());
	}

	@PostConstruct
	protected final void setPostable() {
		validate.setPostable(this);
	}

	// Default redirect view and codes -----------------------------------------

	protected String onSuccess() {
		return Codes.DEFAULT_REDIRECT_VIEW_NAME;
	}

	protected String successCode() {
		return Codes.DEFAULT_SUCCESS_CODE;
	}

	protected String formErrorCode() {
		return Codes.DEFAULT_FORM_ERROR_CODE;
	}

	// Responses ---------------------------------------------------------------

	private ModelAndView errors(final D domainObject, final List<String> context) {
		return currentView(domainObject, context, Codes.FAILURE_CODE, formErrorCode());
	}

	private ModelAndView success() {
		return Response.redirect(onSuccess(), Codes.SUCCESS_CODE, successCode());
	}

	private ModelAndView failure(final D domainObject, final Throwable oops, final List<String> context) {

		ModelAndView result;

		LOG.error(oops, oops);

		result = currentView(domainObject, context, Codes.FAILURE_CODE, exceptionHandler.message(oops), Codes.THROWABLE, oops);

		return result;
	}

	// Post algorithm --------------------------------------------------------

	@SuppressWarnings("unchecked")
	protected ModelAndView post(final D entityOrDatatype, final E entity, final BindingResult bindingResult, final Map<String, String> pathVariables) {
		D safeObject;
		E newOrReconstructed;
		E entityToAuthorize;
		ModelAndView result = null;
		boolean success = true;

		transaction.begin();
		beforeAuthorization(entity, ContextParser.parse(pathVariables));


		safeObject = getSafeObject(entityOrDatatype, entity);

		entityToAuthorize = isEntity(entityOrDatatype) ? (E) safeObject : entity;

		security.authorize(entityToAuthorize);

		validate.constraints(safeObject, bindingResult);

		// We can't continue because business rules may need access some
		// fields
		// the user didn't fill in.
		if (ValidationHandler.validationFailed(bindingResult)) {
			success = false;
			transaction.rollback();
			result = errors(entityOrDatatype, ContextParser.parse(pathVariables));
		}
		
		if(success) {
			beforeCommiting(entity, ContextParser.parse(pathVariables));
			// This method if defined in this class and does nothing. It is
			// needed
			// to redefine if we are dealing with datatypes
			beforeCommiting(entityOrDatatype, entity, ContextParser.parse(pathVariables));
			
			safeObject = getSafeObject(entityOrDatatype, entity);
			
			try {
				newOrReconstructed = getNewOrReconstructed(safeObject, entity);
				validate.businessRules(newOrReconstructed);
			} catch (final Throwable oops) {
				transaction.rollback();
				return failure(safeObject, oops, ContextParser.parse(pathVariables));
			}

			result = attempt(safeObject, entity, pathVariables);
		}


		return result;

	}

	protected ModelAndView attempt(final D entityOrDatatype, final E entity, final Map<String, String> pathVariables) {
		ModelAndView result;

		try {
			postAction(entityOrDatatype, entity, pathVariables);
			result = success();
			transaction.commit();
		} catch (final Throwable oops) {
			result = failure(entityOrDatatype, oops, ContextParser.parse(pathVariables));
			transaction.rollback();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	protected D getSafeObject(final D entityOrDatatype, final E entity) {
		D result;
		E safeObject;

		result = entityOrDatatype;

		if (isEntity(entityOrDatatype)) {
			safeObject = security.getPostHackingSafeObject(entity, getCurrentViewName(), getRequest(), getResponse());
			result = (D) safeObject;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	protected E getNewOrReconstructed(final D safeObject, final E newVersion) {
		E result;

		result = isEntity(safeObject) ? (E) safeObject : newVersion;

		return result;
	}

	// Alternative invocations -------------------------------------------------

	@SuppressWarnings("unchecked")
	protected ModelAndView post(final E newVersion, final BindingResult bindingResult, final Map<String, String> pathVariables) {
		return post((D) newVersion, newVersion, bindingResult, pathVariables);
	}

	protected ModelAndView attempt(final Map<String, String> pathVariables) {
		transaction.begin();
		return attempt(null, null, pathVariables);
	}

	// Helpers -----------------------------------------------------------------

	protected boolean isEntity(Validable validable) {
		return validable instanceof DomainEntity;
	}

	// Default definitions -----------------------------------------------------
	@Override
	public void beforeCommiting(D datatype, E entity, List<String> context) {

	}

	@Override
	public void beforeCommiting(E entity, List<String> context) {

	}

}
