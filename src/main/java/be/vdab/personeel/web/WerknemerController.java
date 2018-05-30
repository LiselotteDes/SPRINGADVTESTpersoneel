package be.vdab.personeel.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.services.WerknemerService;

@Controller
@RequestMapping("werknemers")
class WerknemerController {
	
	private static final String REDIRECT_WERKNEMER_NIET_GEVONDEN = "redirect:/";
	private final WerknemerService werknemerService;
	
	WerknemerController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}
	
	private static final String WERKNEMER_VIEW = "werknemers/werknemer";
	
	@GetMapping("{werknemer}")
	ModelAndView read(@PathVariable Optional<Werknemer> werknemer, RedirectAttributes redirectAttributes) {
		
		if (werknemer.isPresent()) {
			return new ModelAndView(WERKNEMER_VIEW).addObject(werknemer.get());
		}
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_WERKNEMER_NIET_GEVONDEN);
	}
	
	private static final String RIJKSREGISTERNUMMER_VIEW = "werknemers/rijksregisternummer";
	
	@GetMapping("{werknemer}/rijksregisternummer")
	ModelAndView rijksregisternummer(@PathVariable Optional<Werknemer> werknemer, RedirectAttributes redirectAttributes) {
		
		if (werknemer.isPresent()) {
			
			return new ModelAndView(RIJKSREGISTERNUMMER_VIEW).addObject(werknemer.get());
		}
		
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_WERKNEMER_NIET_GEVONDEN);
		
	}
	
	@PostMapping("{werknemer}/rijksregisternummer")
	ModelAndView rijksregisternummer(@PathVariable @Valid Optional<Werknemer> werknemer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (werknemer.isPresent()) {
			
			if (bindingResult.hasErrors()) {
				return new ModelAndView(RIJKSREGISTERNUMMER_VIEW).addObject(werknemer.get());
			}
			
			werknemerService.update(werknemer.get());
			
		}
		
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_WERKNEMER_NIET_GEVONDEN);
	}

}
