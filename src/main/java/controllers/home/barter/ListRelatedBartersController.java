package controllers.home.barter;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;
import domain.Barter;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("listRelatedBartersController")
@RequestMapping("home/barter/related")
public class ListRelatedBartersController extends AbstractListController<Barter, BarterService> {

	@Override
	protected String view() {
		return "barter/list";
	}

	@Override
	protected Page<Barter> getPage(Pageable page, String searchCriteria, List<String> context) {
		Page<Barter> result;
		Integer barterId;
		String type;

		barterId = Integer.valueOf(context.get(0));
		type = context.get(1);
		result = service.findRelatedBarters(barterId, type, page);

		return result;
	}
}
