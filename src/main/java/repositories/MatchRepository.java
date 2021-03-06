package repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Match;

@Repository("MatchRepository")
public interface MatchRepository extends PagingAndSortingRepository<Match, Integer>{
	
	@Query("select m from Match m where m.offered.id = ?1 or m.requested.id = ?1")
	Match findMatchByBarterId(int barterId);
	
	@Query("select m from Match m where (m.offered.user.id = ?1 or m.requested.user.id = ?1) and m.cancelled=false")
	Page<Match> findMatchesByUserId(int userId, Pageable page);

	@Query("select m from Match m where (m.offered.user IN(select u from User u2 join u2.followees u where u2.id=?1) or m.requested.user IN(select u from User u2 join u2.followees u where u2.id=?1)) and m.cancelled=false order by m.moment desc")
	Page<Match> findMatchesOfFollowedUsers(int userId, Pageable page);

	@Query("select m from Match m where m.cancelled = false")
	Page<Match> findNonCancelledMatches(Pageable page);

	@Query("select count(m) from Match m where m.legalText.id = ?1")
	Long matchesAssignedToLegalText(int legalTextId);
	
	@Query("select count(m.offered) from Match m where m.offered.id = ?1")
	Long countOfferedBarters(int barterId);
	
	@Query("select count(m.requested) from Match m where m.requested.id = ?1 and m.requestSignedDate is not null and m.offerSignedDate is not null")
	Long countRequestedFinishedBarters(int barterId);
	
	@Query("select count(m.offered) from Match m where m.offered.id = ?1 and m.requestSignedDate is not null and m.offerSignedDate is not null")
	Long countOfferedFinishedBarters(int barterId);
	
	@Query("select count(m.requested) from Match m where m.requested.id = ?1")
	Long countRequestedBarters(int barterId);

	@Query("select m from Match m where m.auditor.id = ?1")
	Page<Match> findMatchesByAuditor(int auditorId, Pageable page);

}
