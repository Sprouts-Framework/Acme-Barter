package repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository("MessageRepository")
public interface MessageRepository extends PagingAndSortingRepository<Message, Integer>{

}
