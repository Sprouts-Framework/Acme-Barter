package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	private static final long serialVersionUID = 1113293724788670122L;

	// Constructors -----------------------------------------------------------

	public Message() {
		super();
	}

	// Attributes -------------------------------------------------------------
	private String subject;
	private String body;
	private Date moment;
	private int priority;
	
	//Getters y setters
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Past
	@Temporal(TemporalType.TIMESTAMP) //Obligatorio
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") //Formato de la fecha
	@NotNull
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@Range(min = -1, max = 1)
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	// Relationships ----------------------------------------------------------
	private Actor sender;
	private Actor recipient;
	private Folder folder;
	
	//Getters y setters
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	public Actor getSender() {
		return sender;
	}
	public void setSender(Actor sender) {
		this.sender = sender;
	}
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	public Actor getRecipient() {
		return recipient;
	}
	public void setRecipient(Actor recipient) {
		this.recipient = recipient;
	}
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	

}

