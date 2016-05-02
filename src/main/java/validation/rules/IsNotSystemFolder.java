package validation.rules;

import org.springframework.stereotype.Component;

import domain.Folder;
import es.us.lsi.dp.validation.contracts.BusinessRule;

@Component
public class IsNotSystemFolder implements BusinessRule<Folder> {
	
	
	@Override
	public boolean rule(final Folder folder) {
		return !folder.getSystem();
	}

	@Override
	public String warning() {
		return "box.isSystemFolder.error";
	}

}
