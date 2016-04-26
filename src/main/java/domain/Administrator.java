package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3861200793218865594L;

}
