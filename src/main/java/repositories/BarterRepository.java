package repositories;

import java.util.Collection;

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
	
	@Query("select b from Barter b where b.user.id IN(select u.id from User u2 join u2.followees u where u2.id=?1) and b.cancelled=false order by b.moment desc")
	Page<Barter> findBartersOfFollowedUsers(int userId, Pageable page);
	
	@Query("select b from Barter b where b.cancelled = false")
	Page<Barter> findAllDefaultFullText(Pageable page);
	@Query("select b from Barter b where b.user.id = ?1 and b.cancelled = false and b not IN (select m.offered from Match m) and b NOT IN (select m.requested from Match m)")
	Collection<Barter> findNotMatchedBartersByUserId(int userId);
	
	@Query("select b from Barter b where b.user.id != ?1 and b.cancelled = false and b not IN (select m.offered from Match m) and b NOT IN (select m.requested from Match m)")
	Collection<Barter> findNotMatchedBartersNotOwnedByUserId(int userId);
}
