package es.us.lsi.dp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class HttpForbiddenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 280676271311348688L;

}
