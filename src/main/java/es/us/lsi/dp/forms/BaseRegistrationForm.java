package es.us.lsi.dp.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import es.us.lsi.dp.domain.DomainForm;
import es.us.lsi.dp.validation.contracts.RegistersUsers;

public class BaseRegistrationForm implements DomainForm, RegistersUsers {

	// Attributes -------------------------------------------------------------

	private String name;
	private String surname;
	private String contactPhone;
	private String username;
	private String password;
	private String password2;
	private boolean checkBox;
	
	@Override
	@NotBlank
	@Size(min = 5, max = 32)
	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
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

	@NotBlank
	@Size(min = 5, max = 32)
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@NotBlank
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(final String contactPhone) {
		this.contactPhone = contactPhone;
	}
	

	@NotNull
	public Boolean getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(Boolean checkBox) {
		this.checkBox = checkBox;
	}

}
