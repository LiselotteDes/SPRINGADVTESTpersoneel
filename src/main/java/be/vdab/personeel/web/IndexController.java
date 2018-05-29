package be.vdab.personeel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.services.WerknemerService;

@Controller
@RequestMapping("/")
class IndexController {

	private static final String VIEW = "index";
	private final WerknemerService werknemerService;
	
	IndexController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}
	
	@GetMapping
	ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		modelAndView.addObject("chef", werknemerService.findChef().get());
		return modelAndView;
	}
}
