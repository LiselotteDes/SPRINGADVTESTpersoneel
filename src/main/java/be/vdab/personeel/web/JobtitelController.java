package be.vdab.personeel.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.services.JobtitelService;

@Controller
@RequestMapping("jobtitels")
class JobtitelController {
	
	private final JobtitelService jobtitelService;
	
	JobtitelController(JobtitelService jobtitelService) {
		this.jobtitelService = jobtitelService;
	}
	
	private static final String JOBTITELS_VIEW = "jobtitels/jobtitel";
	
	@GetMapping
	ModelAndView jobtitels() {
		
		return new ModelAndView(JOBTITELS_VIEW).addObject("jobtitels", jobtitelService.findAll());
	}
	
	private static final String REDIRECT_JOBTITEL_NIET_GEVONDEN = "redirect:/jobtitels";
	
	@GetMapping("{jobtitel}")
	ModelAndView jobtitel(@PathVariable Optional<Jobtitel> jobtitel, RedirectAttributes redirectAttributes) {
		
		if (jobtitel.isPresent()) {
			return new ModelAndView(JOBTITELS_VIEW).addObject("jobtitels", jobtitelService.findAll())
													.addObject("jobtitel", jobtitel.get());
		}
		
		redirectAttributes.addAttribute("fout", "Jobtitel niet gevonden");
		return new ModelAndView(REDIRECT_JOBTITEL_NIET_GEVONDEN);
		
	}

}
