package services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ItemRepository;
import domain.Item;
import es.us.lsi.dp.services.AbstractService;

@Service
@Transactional
public class ItemService extends AbstractService<Item, ItemRepository>{
	
	public int save(Item i){
		Item result;
		Assert.notNull(i);
		result = repository.save(i);
		Assert.notNull(result);
		return result.getId();	
	}

}
