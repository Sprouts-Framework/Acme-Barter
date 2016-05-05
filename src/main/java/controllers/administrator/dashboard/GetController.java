package controllers.administrator.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;
import services.UserService;
import es.us.lsi.dp.controllers.common.AbstractGetController;
import es.us.lsi.dp.controllers.core.contracts.AddsToModel;

@Controller("dashboardGetController")
@RequestMapping(value={"dashboard/administrator/list","dashboard/administrator/{context}/list"})
public class GetController extends AbstractGetController implements AddsToModel{

	@Autowired
	private BarterService barterService;
	@Autowired
	private UserService userService;
	
	@Override
	protected String view() {
		return "dashboard/list";
	}

	@Override
	public void addToModel(Map<String, Object> objects, List<String> context) {
		if(context.size() >0){
			Integer option = new Integer(context.get(0));

			Double radioOfBarter = null;
			Long totalNumberOfUsers = null;
			
			switch(option){
			case 2:
				radioOfBarter = barterService.ratioOfBartersThatAreNotRelated();
				break;
			case 3:
				totalNumberOfUsers = userService.totalNumberOfUsersRegistedInTheSystem();
				break;
			default:
				break;
			}
			
			objects.put("radioOfBarter", radioOfBarter);		
			objects.put("totalNumberOfUsers", totalNumberOfUsers);	

			objects.put("option", option);
		}
	}
	
}
