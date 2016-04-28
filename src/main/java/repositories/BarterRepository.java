package repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Barter;

@Repository("BarterRepository")
public interface BarterRepository extends PagingAndSortingRepository<Barter, Integer>{

}
