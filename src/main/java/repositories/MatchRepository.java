package repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Match;

@Repository("MatchRepository")
public interface MatchRepository extends PagingAndSortingRepository<Match, Integer>{

}
