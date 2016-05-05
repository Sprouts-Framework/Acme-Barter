package controllers.administrator.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.AuditorService;
import domain.Auditor;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;
import es.us.lsi.dp.controllers.entities.crud.AbstractListController;

@Controller("auditorListAdministrator")
@RequestMapping("dashboard/administrator/auditor")
public class ListAuditorsController extends AbstractListController<Auditor, AuditorService> implements AddsToModel{

	@Override
	protected String view() {
		return "dashboard/auditors/list";
	}
	
	@Override
	protected Page<Auditor> getPage(Pageable page, String searchCriteria, List<String> context) {
		Integer option = new Integer(context.get(0));
		
		switch(option){
		case 0:
			return service.findAuditorsWhoHaveAuditedMoreMatches(page);
		default:
			return null;
		}
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		Integer option = new Integer(context.get(0));
		
		Long quantity = null;

	
		switch(option){
		case 0:
			quantity = service.quantityOfMatchesAuditedByAuditors();
			break;
		default:
			break;
		}
		
		objects.put("quantity", quantity);
		objects.put("option", option);
	}

}
