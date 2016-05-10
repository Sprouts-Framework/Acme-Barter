package repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.User;


@Repository("UserRepository")
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

	@Query("select c from User c, SocialAccount s where s member of c.userAccount.socialAccounts and s.providerId = ?1 and s.userId = ?2")
	User findBySocialAccount(String providerId, String userId);
	
	/**
	 * It returns the User whose user account has the ID passed as a parameter.
	 * */
	@Query("select c from User c where c.userAccount.id = ?1")
	User findByPrincipal(int userAccountId);
	
	@Query("select u2 from User u join u.followees u2 where u.id = ?1 and u2.id = ?2")
	User findFollower(int principalId, int followerId);
	
	@Query("select b.user from Barter b where (b IN(select m.offered from Match m where m.auditor is not null and m.report is not null) or b IN(select m.requested from Match m where m.auditor is not null and m.report is not null)) group by b.user having count(b.user) >=  ALL(select count(b.user) from Barter b where (b IN(select m.offered from Match m where m.auditor is not null and m.report is not null) or b IN(select m.requested from Match m where m.auditor is not null and m.report is not null)) group by b.user)")
	Page<User> usersWhoHaveGotMoreMatchesAudited(Pageable page);

	@Query("select count(b) from Barter b where (b IN(select m.offered from Match m where m.auditor is not null and m.report is not null) or b IN(select m.requested from Match m where m.auditor is not null and m.report is not null)) group by b.user having count(b.user) >=  ALL(select count(b.user) from Barter b where (b IN(select m.offered from Match m where m.auditor is not null and m.report is not null) or b IN(select m.requested from Match m where m.auditor is not null and m.report is not null)) group by b.user)")
	Long quantityOfMatchesAudited();
	
	@Query("select count(u) from User u")
	Long totalNumberOfUsersRegistedInTheSystem();
	
	@Query("select b.user from Barter b group by b.user having count(b) >= ALL(select 0.9*count(b) from Barter b group by b.user)")
	Page<User> findUsersMaxCreatedBarters(Pageable page);
	
	@Query("select 0.9*count(b) from Barter b group by b.user")
	List<Double> ninetyPercentileMaxCreatedBarters();
	
	@Query("select u from User u where u NOT IN (select b.user from Barter b where b.moment >= ?1 group by b.user)")
	Page<User> findNonActiveUsersLastMonth(Date lastMonth, Pageable page);
	
	@Query("select b.user from Barter b group by b.user having count(b.user) >= ALL(select count(b2.user) from Barter b2 group by b2.user)")
	Page<User> theUsersWhoHaveRegisteredMoreBarters(Pageable page);

	@Query("select b.user from Barter b where b.cancelled = true group by b.user having count(b.user) >= ALL(select count(b2.user) from Barter b2 where b2.cancelled = true group by b2.user)")
	Page<User> theUsersWhoHaveCancelledMoreBarters(Pageable page);
	
	@Query("select count(b) from Barter b where b.cancelled = true group by b.user having count(b.user) >= ALL(select count(b2.user) from Barter b2 where b2.cancelled = true group by b2.user)")
	Long quantityBartersCancelled();
	
	@Query("select b.user from Barter b where (b IN(select m.offered from Match m) or b IN(select m.requested from Match m)) group by b.user having count(b.user) >=  ALL(select count(b.user) from Barter b where (b IN(select m.offered from Match m) or b IN(select m.requested from Match m)) group by b.user)")
	Page<User> theUsersWhoHaveMoreMatches(Pageable page);
	
	@Query("select count(b) from Barter b where (b IN(select m.offered from Match m) or b IN(select m.requested from Match m)) group by b.user having count(b.user) >=  ALL(select count(b.user) from Barter b where (b IN(select m.offered from Match m) or b IN(select m.requested from Match m)) group by b.user)")
	Long quantityMatches();
}
