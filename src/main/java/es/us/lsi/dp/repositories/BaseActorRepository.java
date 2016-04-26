package es.us.lsi.dp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.us.lsi.dp.domain.BaseActor;

@Repository
public interface BaseActorRepository extends JpaRepository<BaseActor, Integer> {

	@Query("select a from BaseActor a where a.userAccount.username=?1")
	BaseActor findActorByUserAccount(String userAccount);

	@Query("select a from BaseActor a where a.userAccount.id = ?1")
	BaseActor findActorByPrincipal(int id);
}