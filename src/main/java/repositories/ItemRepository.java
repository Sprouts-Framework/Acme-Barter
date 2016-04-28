package repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Item;

@Repository("ItemRepository")
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer>{

}
