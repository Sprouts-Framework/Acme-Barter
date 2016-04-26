package es.us.lsi.dp.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {
			"providerId", "userId"
	})
})
public class SocialAccount extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4378804254001027185L;

	private String providerId;
	private String userId;

	@NotBlank
	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@NotBlank
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
