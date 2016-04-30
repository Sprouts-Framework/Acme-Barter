package repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;


@Repository("SocialIdentityRepository")
public interface SocialIdentityRepository extends PagingAndSortingRepository<SocialIdentity, Integer> {

	@Query("select u.identities from User u where u.id = ?1")
	Page<SocialIdentity> findSocialIdentitiesByUserId(int userId, Pageable page);

}
