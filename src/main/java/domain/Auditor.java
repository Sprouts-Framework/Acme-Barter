package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Auditor extends Actor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2050630687465467742L;
	
}
