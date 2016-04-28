package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import cz.jirutka.validator.collection.constraints.EachNotNull;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	// Constructors -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 7976602760210895425L;

	public User() {
		super();
	}

	// Attributes -------------------------------------------------------------
	

	// Relationships ----------------------------------------------------------

	private Collection<User> followers;
	private Collection<User> followees;
	private Collection<SocialIdentity> identities;
	
	@NotNull
	@EachNotNull
	@OneToMany
	public Collection<SocialIdentity> getIdentities() {
		return identities;
	}

	
	public void setIdentities(Collection<SocialIdentity> identities) {
		this.identities = identities;
	}
	
	@ManyToMany
	@NotNull
	@EachNotNull
	public Collection<User> getFollowers() {
		return followers;
	}
	
	public void setFollowers(Collection<User> followers) {
		this.followers = followers;
	}
	
	@ManyToMany(mappedBy="followers")
	@NotNull
	@EachNotNull
	public Collection<User> getFollowees() {
		return followees;
	}

	public void setFollowees(Collection<User> followees) {
		this.followees = followees;
	}
	
	

}
