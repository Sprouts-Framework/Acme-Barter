package forms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import domain.Barter;
import es.us.lsi.dp.domain.DomainForm;


public class RelateBarterForm implements DomainForm{
	
	private Barter offered;
	private Barter requested;
	
	@NotNull
	@Valid
	public Barter getOffered() {
		return offered;
	}
	
	public void setOffered(Barter offered) {
		this.offered = offered;
	}
	
	@NotNull
	@Valid
	public Barter getRequested() {
		return requested;
	}
	
	public void setRequested(Barter requested) {
		this.requested = requested;
	}
	
	

}
