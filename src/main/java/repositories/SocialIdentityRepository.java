package repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;


@Repository("SocialIdentityRepository")
public interface SocialIdentityRepository extends PagingAndSortingRepository<SocialIdentity, Integer> {

	/**
	 * It returns the customer's social identity whose id is received by the
	 * method.
	 * */

	@Query("select u.identities from User u where u.id = ?1")
	Collection<SocialIdentity> findSocialIdentityByUserId(int userId);


}
