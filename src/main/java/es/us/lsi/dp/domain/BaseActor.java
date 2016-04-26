package es.us.lsi.dp.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseActor extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 868764066568947758L;

	// Attributes -------------------------------------------------------------

	private String name;
	private String surname;

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

	// Derived attributes -----------------------------------------------------

	@NotBlank
	@Transient
	public String getFullName() {
		StringBuilder result;

		result = new StringBuilder();
		result.append(surname != null ? surname : "null");
		result.append(", ");
		result.append(name != null ? name : "null");

		return result.toString();
	}

	// Relationships ----------------------------------------------------------

	private UserAccount userAccount;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
