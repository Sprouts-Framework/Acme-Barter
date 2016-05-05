package repositories;

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
	
}
