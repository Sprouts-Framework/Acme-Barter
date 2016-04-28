package repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.LegalText;


@Repository("LegalTextRepository")
public interface LegalTextRepository extends PagingAndSortingRepository<LegalText, Integer>{

}
