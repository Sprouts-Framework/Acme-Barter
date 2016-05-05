package repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Auditor;

@Repository("AuditorRepository")
public interface AuditorRepository extends PagingAndSortingRepository<Auditor, Integer>{
	
	@Query("select m.auditor from Match m where (m.report is not null and m.auditor is not null) group by m.auditor having count(m.auditor) >= ALL(select count(m.auditor) from Match m where m.report is not null and m.auditor is not null group by m.auditor)")
	Page<Auditor> findAuditorsWhoHaveAuditedMoreMatches(Pageable page);
	
	@Query("select count(m) from Match m where (m.report is not null and m.auditor is not null) group by m.auditor having count(m.auditor) >= ALL(select count(m.auditor) from Match m where m.report is not null and m.auditor is not null group by m.auditor)")
	Long quantityOfMatchesAuditedByAuditors();

}
