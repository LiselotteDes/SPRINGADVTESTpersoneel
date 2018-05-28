package be.vdab.personeel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {

	private static final String VIEW = "index";
	
	@GetMapping
	ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		return modelAndView;
	}
}
