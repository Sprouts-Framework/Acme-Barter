package es.us.lsi.dp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.us.lsi.dp.domain.SocialAccount;

@Repository
public interface SocialAccountRepository extends JpaRepository<SocialAccount, Integer> {

	@Query("select s from SocialAccount s where s.providerId = ?1 and s.userId = ?2")
	SocialAccount findSocialAccount(String providerId, String userId);

	@Query("select u.socialAccounts.size from UserAccount u where u.id = ?1")
	Long countSocialAccount(int userAccountId);
}
