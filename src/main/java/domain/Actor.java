package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import es.us.lsi.dp.domain.BaseActor;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Actor extends BaseActor{

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 868764066568947758L;
	
	//Attributes
	
	private String phone;

	@SafeHtml(whitelistType=WhiteListType.NONE)
	@NotBlank
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	// Relationships ----------------------------------------------------------

}
