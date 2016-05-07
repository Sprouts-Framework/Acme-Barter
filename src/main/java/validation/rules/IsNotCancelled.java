package validation.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.BarterService;
import domain.Barter;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotCancelled implements BusinessRule<Barter> {

	@Autowired
	private BarterService barterService;

	@Override
	public boolean rule(Barter barter) {
		Barter persisted;
		persisted = barterService.findById(barter.getId());
		return !persisted.getCancelled();
	}

	@Override
	public String warning() {
		return "barter.cancelled";
	}

}
