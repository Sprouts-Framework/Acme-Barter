package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import es.us.lsi.dp.domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class Auditor extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2050630687465467742L;
	
}
