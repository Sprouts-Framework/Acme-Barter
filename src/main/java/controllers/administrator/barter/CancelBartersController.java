package controllers.administrator.barter;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.BarterService;
import es.us.lsi.dp.controllers.common.AbstractPostController;

@Controller("cancelBartersController")
@RequestMapping("barter/administrator/cancel-procedure")
public class CancelBartersController extends AbstractPostController<BarterService> {

	@Override
	protected void action(List<String> context) {
		service.cancelBarters();
	}

	@Override
	protected String onSuccess() {
		return "/home/barter/list.do";
	}
	
	@Override
	protected String successCode() {
		return "administrator.barters-cancelled-successfully";
	}
	
	@Override
	protected String view() {
		return "barter/cancel-procedure";
	}

}
