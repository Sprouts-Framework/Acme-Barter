package es.us.lsi.dp.controllers.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.us.lsi.dp.controllers.core.BaseController;

public abstract class AbstractGetController extends BaseController {

	// Actions -----------------------------------------------------------------

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get(final HttpServletRequest request) {
		setRequest(request);
		return currentView();
	}

}