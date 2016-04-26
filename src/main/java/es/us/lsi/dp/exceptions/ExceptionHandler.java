package es.us.lsi.dp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ExceptionHandler {

	private final Map<Class<? extends Throwable>, String> exceptionMessageMapping;

	public ExceptionHandler() {
		exceptionMessageMapping = new HashMap<>();
	}

	public Map<Class<? extends Throwable>, String> getExceptionMessageMapping() {
		return exceptionMessageMapping;
	}

	public String message(final Throwable oops) {
		String result;
		Class<? extends Throwable> exceptionClass;

		exceptionClass = oops.getClass();

		result = exceptionClass.getName();

		if (isBusinessRule(oops))
			result = oops.getMessage();
		else if (hasCustomMessage(exceptionClass))
			result = customExceptionMessage(exceptionClass);

		return result;
	}

	private boolean isBusinessRule(final Throwable oops) {
		return oops instanceof IllegalArgumentException;
	}

	private boolean hasCustomMessage(final Class<? extends Throwable> exceptionClass) {
		return getExceptionMessageMapping().containsKey(exceptionClass);
	}

	private String customExceptionMessage(final Class<? extends Throwable> exceptionClass) {
		return getExceptionMessageMapping().get(exceptionClass);
	}

}
