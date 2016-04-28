package repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository("ActorRepository")
public interface ActorRepository extends PagingAndSortingRepository<Actor, Integer>{

}
