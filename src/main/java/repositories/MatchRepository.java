package repositories;

import java.util.Collection;

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
	

	@Query("select m from Match m where (m.offered.user IN(select u from User u2 join u2.followees u where u2.id=?1) or m.requested.user IN(select u from User u2 join u2.followees u where u2.id=?1)) and m.cancelled=false order by m.moment desc")
	Page<Match> findMatchesOfFollowedUsers(int userId, Pageable page);
	

	@Query("select m from Match m where m.legalText.id = ?1")
	Collection<Match> findMatchesByLegalTextId(int legalTextId);
	
	@Query("select m from Match m where m.auditor.id = ?1")
	Page<Match> findMatchesByAuditor(int auditorId, Pageable page);
}
