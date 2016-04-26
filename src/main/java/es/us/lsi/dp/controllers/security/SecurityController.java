package es.us.lsi.dp.controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("securityController")
@RequestMapping("security")
public class SecurityController {

	@RequestMapping(value = "/manage-security", method = RequestMethod.GET)
	public ModelAndView manageSecurity() {
		ModelAndView result;
		result = new ModelAndView("home/welcome");
		return result;
	}

	@RequestMapping(value = "/manage-security-logout", method = RequestMethod.GET)
	public ModelAndView manageSecurityLogout() {
		ModelAndView result;
		result = new ModelAndView("home/welcome");
		return result;
	}
}
