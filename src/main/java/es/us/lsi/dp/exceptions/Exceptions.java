package es.us.lsi.dp.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class Exceptions {

	public static final Class<? extends Throwable> DATA_INTEGRITY_VIOLATION = DataIntegrityViolationException.class;

}
