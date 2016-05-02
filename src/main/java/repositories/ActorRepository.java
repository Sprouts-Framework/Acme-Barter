package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository("ActorRepository")
public interface ActorRepository extends JpaRepository<Actor, Integer>{
	
	@Query("select a from Actor a where a.userAccount.username=?1")
	Actor findActorByUserAccount(String userAccount);

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findActorByPrincipal(int id);

}
