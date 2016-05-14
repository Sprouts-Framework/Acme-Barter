/*
 * UserAccount.java Copyright (C) 2013 Universidad de Sevilla The use of this
 * project is hereby constrained to the conditions of the TDG Licence, a copy of
 * which you may download from http://www.tdg-seville.info/License.html
 */

package es.us.lsi.dp.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

@Entity
@Access(AccessType.PROPERTY)
public class UserAccount extends DomainEntity implements UserDetails {

	// Constructors -----------------------------------------------------------

	public UserAccount() {
	}

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 868764066568947758L;

	// Attributes -------------------------------------------------------------

	private String username;
	private String password;

	@NotBlank
	@Size(min = 5, max = 32)
	@Column(unique = true)
	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@NotBlank
	@Size(min = 5, max = 32)
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// Derived attributes -----------------------------------------------------

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> result;

		result = new ArrayList<GrantedAuthority>();
		Assert.notEmpty(getActors());
		for (final BaseActor actor : getActors()) {
			GrantedAuthority authority;

			authority = new SimpleGrantedAuthority(actor.getClass().getSimpleName());
			result.add(authority);
		}

		return result;
	}

	// Relationships ----------------------------------------------------------

	private Collection<BaseActor> actors;
	private Collection<SocialAccount> socialAccounts;

	@Valid
	@OneToMany(mappedBy = "userAccount")
	public Collection<BaseActor> getActors() {
		return actors;
	}

	public void setActors(final Collection<BaseActor> actors) {
		this.actors = actors;
	}

	@Valid
	@OneToMany
	public Collection<SocialAccount> getSocialAccounts() {
		return socialAccounts;
	}

	public void setSocialAccounts(Collection<SocialAccount> socialAccounts) {
		this.socialAccounts = socialAccounts;
	}

}
