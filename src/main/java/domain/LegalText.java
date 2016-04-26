package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class LegalText extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8613112734210097215L;

	// Constructors -----------------------------------------------------------
	public LegalText(){
		super();
	}
	
	// Attributes -------------------------------------------------------------
	private String text;

	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getText() {
		return text;
	}

	
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
}
