package repositories;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository("MessageRepository")
public interface MessageRepository extends PagingAndSortingRepository<Message, Integer>{

	@Query("select m from Message m where m.folder.id=?1 order by m.moment desc")
	Page<Message> findMessagesInFolder(int folderId, final Pageable page);
	
	@Query("select m from Message m where m.folder.id=?1 order by m.moment desc")
	Collection<Message> findMessagesInFolder(int folderId);
	
}
