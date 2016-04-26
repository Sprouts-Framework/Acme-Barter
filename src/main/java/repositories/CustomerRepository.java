package repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.User;


@Repository
public interface CustomerRepository extends PagingAndSortingRepository<User, Integer>{

	@Query("select c from Customer c, SocialAccount s where s member of c.userAccount.socialAccounts and s.providerId = ?1 and s.userId = ?2")
	User findBySocialAccount(String providerId, String userId);
	
	/**
	 * It returns the Customer whose user account has the ID passed as a parameter.
	 * */
	@Query("select c from Customer c where c.userAccount.id = ?1")
	User findByPrincipal(int userAccountId);
	
}
