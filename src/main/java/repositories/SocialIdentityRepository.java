package repositories;


import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.SocialIdentity;


@Repository("SocialIdentityRepository")
public interface SocialIdentityRepository extends PagingAndSortingRepository<SocialIdentity, Integer> {

	@Query("select s from SocialIdentity s where s.user.id = ?1")
	Page<SocialIdentity> findSocialIdentitiesByUserId(int userId, Pageable page);
	
	@Query("select s from SocialIdentity s where s.user.id = ?1 and s.socialNetwork = ?2")
	Collection<SocialIdentity> findSocialIdentityByUserAndSocialNetwork(int userId, String socialNetwork);
}
