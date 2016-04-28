package repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository("AdministratorRepository")
public interface AdministratorRepository extends PagingAndSortingRepository<Administrator, Integer>{

}
