package repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Barter;

@Repository("BarterRepository")
public interface BarterRepository extends PagingAndSortingRepository<Barter, Integer>{
	
	@Query("select b from Barter b where b.user.id = ?1")
	Page<Barter> findBartersByUserId(int userId, Pageable page);	

}
