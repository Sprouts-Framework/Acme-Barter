package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5222936545257270090L;

	// Constructors -----------------------------------------------------------

	public Folder() {
		super();
	}

	// Attributes -------------------------------------------------------------
	private String name;
	private boolean system;
	
	//Getters y setters
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean getSystem() {
		return system;
	}
	public void setSystem(boolean system) {
		this.system = system;
	}
	
	
	// Relationships ----------------------------------------------------------
	
	private Actor actor;

	//Getters y setters
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}

	
	
}
