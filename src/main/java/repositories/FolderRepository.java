package repositories;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Folder;

@Repository("FolderRepository")
public interface FolderRepository extends PagingAndSortingRepository<Folder, Integer>{

	@Query("select f from Folder f where f.actor.id=?1")
	Page<Folder> findFoldersByActor(int actorId, Pageable page);
	
	@Query("select f from Folder f where f.actor.id=?1")
	Collection<Folder> findFoldersByActor(int actorId);
	
	@Query("select f from Folder f where f.actor.id=?1 and f.system=true and f.name=?2")
	Folder findSystemFolder(Integer actorId, String name);
	
}
