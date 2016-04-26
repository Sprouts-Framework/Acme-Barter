package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import es.us.lsi.dp.domain.BaseActor;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Actor extends BaseActor {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 868764066568947758L;

	// Relationships ----------------------------------------------------------

}
