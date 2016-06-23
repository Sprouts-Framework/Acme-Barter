package es.us.lsi.dp.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.Validator;

import es.us.lsi.dp.controllers.core.contracts.Postable;
import es.us.lsi.dp.validation.contracts.BusinessRule;
import es.us.lsi.dp.validation.contracts.BusinessRulesValidator;
import es.us.lsi.dp.validation.contracts.ConstraintsValidator;
import es.us.lsi.dp.validation.contracts.RegistersUsers;
import es.us.lsi.dp.validation.contracts.Validable;

@Component
@Scope("prototype")
public class ValidationHandler<V extends Validable, D extends Validable> implements ConstraintsValidator<V>, BusinessRulesValidator<V, D> {

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private SmartValidator constraintsValidator;

	private Postable<V, D> postable;

	private boolean initialized = false;
	private List<BusinessRule<D>> rules;
	private List<Validator> validators;

	public void setPostable(final Postable<V, D> postable) {
		this.postable = postable;
	}

	@Override
	public void businessRules(final D domainObject) {
		for (final BusinessRule<D> businessRule : rules) {
			Assert.isTrue(businessRule.rule(domainObject), businessRule.warning());
		}
	}

	@Override
	public void constraints(final V domainObject, final BindingResult bindingResult) {
		if (!initialized) {
			loadRulesAndValidators();
			initialized = true;
		}

		addDefaultRules(domainObject, validators);

		constraintsValidator.validate(domainObject, bindingResult);

		for (final Validator v : validators) {
			v.validate(domainObject, bindingResult);
		}
	}

	public static boolean validationFailed(final BindingResult bindingResult) {
		return bindingResult.hasErrors();
	}

	private void loadRulesAndValidators() {
		rules = new ArrayList<>();
		validators = new ArrayList<>();
		postable.businessRules(rules, validators);
	}

	private void addDefaultRules(final V domainObject, final List<Validator> validators) {
		if (domainObject instanceof RegistersUsers)
			if (!validators.contains((Validator) appContext.getBean(Rules.UNIQUE_USERNAME)))
				validators.add((Validator) appContext.getBean(Rules.UNIQUE_USERNAME));

	}

}
