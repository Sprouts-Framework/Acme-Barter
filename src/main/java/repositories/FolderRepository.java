package repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import domain.Folder;

@Repository("FolderRepository")
public interface FolderRepository extends PagingAndSortingRepository<Folder, Integer>{

}
