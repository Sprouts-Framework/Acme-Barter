package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import com.lowagie.text.pdf.AcroFields.Item;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class Barter extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2981213903707226809L;

	// Constructors -----------------------------------------------------------

	public Barter() {
		super();
	}

	// Attributes -------------------------------------------------------------
	private Date moment;
	private String title;
	private boolean cancelled;
	
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return moment;
	}
	
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	// Relationships ----------------------------------------------------------
	
	private User user;
	private Collection<Barter> requesteds;
	private Collection<Barter> offereds;
	private Item offered;
	private Item requested;
	
	@ManyToOne(optional=false)
	@Valid
	@NotNull
	public User getUser() {
		return user;
	}

	
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToMany(mappedBy = "offereds")
	@ElementCollection
	@NotNull
	public Collection<Barter> getRequesteds() {
		return requesteds;
	}

	
	public void setRequesteds(Collection<Barter> requesteds) {
		this.requesteds = requesteds;
	}

	@ManyToMany(mappedBy = "requesteds")
	@ElementCollection
	@NotNull
	public Collection<Barter> getOffereds() {
		return offereds;
	}

	
	public void setOffereds(Collection<Barter> offereds) {
		this.offereds = offereds;
	}

	@OneToOne(optional=false)
	@Valid
	public Item getOffered() {
		return offered;
	}

	
	public void setOffered(Item offered) {
		this.offered = offered;
	}

	@OneToOne(optional=false)
	@Valid
	public Item getRequested() {
		return requested;
	}

	
	public void setRequested(Item requested) {
		this.requested = requested;
	}

	
	
}
