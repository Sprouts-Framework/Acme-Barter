package repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Match;

@Repository("MatchRepository")
public interface MatchRepository extends PagingAndSortingRepository<Match, Integer>{

	@Query("select m from Match m where m.offered.user.id = ?1 or m.requested.user.id = ?1")
	Page<Match> findMatchesByUserId(int userId, Pageable page);
	
}
