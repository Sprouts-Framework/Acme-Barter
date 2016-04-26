package es.us.lsi.dp.validation.contracts;

import org.springframework.validation.BindingResult;

public interface ConstraintsValidator<V extends Validable> {

	public void constraints(V validable, BindingResult bindingResult);

}
