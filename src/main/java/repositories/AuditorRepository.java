package repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Auditor;

@Repository("AuditorRepository")
public interface AuditorRepository extends PagingAndSortingRepository<Auditor, Integer>{

}
