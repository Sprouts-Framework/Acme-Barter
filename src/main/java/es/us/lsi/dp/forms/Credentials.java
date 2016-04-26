/*
 * Credentials.java Copyright (C) 2013 Universidad de Sevilla The use of this
 * project is hereby constrained to the conditions of the TDG Licence, a copy of
 * which you may download from http://www.tdg-seville.info/License.html
 */

package es.us.lsi.dp.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Credentials {

	// Constructors -----------------------------------------------------------

	public Credentials() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String username;
	private String password;

	@NotBlank
	@Size(min = 5, max = 32)
	public String getUsername() {
		return username;
	}

	public void setJ_username(final String username) {
		this.username = username;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
