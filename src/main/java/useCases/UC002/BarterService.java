package useCases.UC002;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import domain.Barter;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.ListService;

@Service("barterServiceUC002")
public class BarterService extends AbstractService<Barter, BarterRepository> implements ListService<Barter>{

	@Override
	public Page<Barter> findPage(Pageable page, String searchCriteria) {
//		Page<Barter> result;
//		result = fullTextSearch(clazz, pageable, searchCriteria, fields)
		return repository.findAll(page);
	}

}
