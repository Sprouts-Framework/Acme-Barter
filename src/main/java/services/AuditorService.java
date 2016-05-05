package services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuditorRepository;
import domain.Auditor;
import es.us.lsi.dp.services.AbstractService;
import es.us.lsi.dp.services.contracts.ListService;


@Service
@Transactional
public class AuditorService extends AbstractService<Auditor, AuditorRepository> implements ListService<Auditor>{

	@Override
	public Page<Auditor> findPage(Pageable page, String searchCriteria) {
		return repository.findAll(page);
	}

	public Page<Auditor> findAuditorsWhoHaveAuditedMoreMatches(Pageable page){
		Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "id");
		Pageable aux = new PageRequest(page.getPageNumber(), page.getPageSize(), sort);
		return repository.findAuditorsWhoHaveAuditedMoreMatches(aux);
	}
	
	public Long quantityOfMatchesAuditedByAuditors(){
		return repository.quantityOfMatchesAuditedByAuditors();
	}
}
