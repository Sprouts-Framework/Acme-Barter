package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
@Table(name="_Match")
public class Match extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5222936545257270090L;

	// Constructors -----------------------------------------------------------

	public Match() {
		super();
	}

	// Attributes -------------------------------------------------------------
	private Date moment;
	private Date requestSignedDate;
	private Date offerSignedDate;
	private boolean cancelled;
	private String report;

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRequestSignedDate() {
		return requestSignedDate;
	}

	public void setRequestSignedDate(Date requestSignedDate) {
		this.requestSignedDate = requestSignedDate;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOfferSignedDate() {
		return offerSignedDate;
	}

	public void setOfferSignedDate(Date offerSignedDate) {
		this.offerSignedDate = offerSignedDate;
	}

	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	// Relationships ----------------------------------------------------------

	private Auditor auditor;
	private LegalText legalText;
	private Barter offered;
	private Barter requested;

	@ManyToOne(optional=true)
	@Valid
	public Auditor getAuditor() {
		return auditor;
	}

	
	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	@ManyToOne(optional=false)
	@Valid
	@NotNull
	public LegalText getLegalText() {
		return legalText;
	}

	
	public void setLegalText(LegalText legalText) {
		this.legalText = legalText;
	}

	@OneToOne(optional=false)
	@Valid
	@NotNull
	public Barter getOffered() {
		return offered;
	}

	
	public void setOffered(Barter offered) {
		this.offered = offered;
	}

	@OneToOne(optional=false)
	@Valid
	@NotNull
	public Barter getRequested() {
		return requested;
	}

	
	public void setRequested(Barter requested) {
		this.requested = requested;
	}

}
