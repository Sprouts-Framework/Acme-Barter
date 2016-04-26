package repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;


@Repository
public interface SocialIdentityRepository extends PagingAndSortingRepository<SocialIdentity, Integer> {

	/**
	 * It returns the customer's social identity whose id is received by the
	 * method.
	 * */
	@Query("select c.socialIdentity from Customer c where c.id = ?1")
	SocialIdentity findSocialIdentityByCustomerId(int customerId);

}
