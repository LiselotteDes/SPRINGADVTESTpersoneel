package be.vdab.personeel.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.services.JobtitelService;

@RunWith(MockitoJUnitRunner.class)
public class JobtitelControllerTest {
	
	private JobtitelController jobtitelController;
	@Mock
	private JobtitelService dummyJobtitelService;
	//@Mock
	private RedirectAttributes redirectAttributes;
	
	@Before
	public void before() {
		when(dummyJobtitelService.findAll()).thenReturn(Collections.singletonList(new Jobtitel()));
		jobtitelController = new JobtitelController(dummyJobtitelService);
		redirectAttributes=new RedirectAttributesModelMap();
	}

	@Test
	public void jobtitelsGeeftJuisteHTML() {
		ModelAndView modelAndView = jobtitelController.jobtitels();
		assertEquals("jobtitels/jobtitel", modelAndView.getViewName());
	}
	
	@Test
	public void jobtitelsGeeftJobtitelsDoor() {
		ModelAndView modelAndView = jobtitelController.jobtitels();
		assertTrue(modelAndView.getModel().containsKey("jobtitels"));
	}
	
	@Test
	public void jobtitelGeeftJuisteHTML() {
		ModelAndView modelAndView = jobtitelController.jobtitel(Optional.of(new Jobtitel()), redirectAttributes);
		assertEquals("jobtitels/jobtitel", modelAndView.getViewName());
		
	}
	
	@Test
	public void jobtitelGeeftJobtitelsDoor() {
		ModelAndView modelAndView = jobtitelController.jobtitel(Optional.of(new Jobtitel()), redirectAttributes);
		assertTrue(modelAndView.getModel().containsKey("jobtitels"));
	}
	
	@Test
	public void jobtitelGeeftJobtitelDoor() {
		ModelAndView modelAndView = jobtitelController.jobtitel(Optional.of(new Jobtitel()), redirectAttributes);
		assertTrue(modelAndView.getModel().containsKey("jobtitel"));
	}
	
	@Test
	public void jobtitelGeeftFoutDoorAlsJobtitelNietGevondenWordt() throws Exception {
		
		jobtitelController.jobtitel(Optional.empty(), redirectAttributes);
		assertTrue(redirectAttributes.containsAttribute("fout"));
		
		/*
		 * Deze test eerst met een gemockte RedirectAttributes geprobeerd, maar dit faalde.
		 * Hier heb je niet voldoende met een Mock, want de implementatie die on the fly gemaakt wordt door Mockito heeft "lege" methods.
		 * Je zou de mock dan moeten trainen, maar hiermee kan je niet testen wat je hier wil.
		 * 
		 * In de plaats: zoek een concrete implementatie van de RedirectAttributes interface.
		 * Gebruik de RedirectAttributesModelMap class.
		 * Je kent een object van dit type dan toe aan de redirectAttributes variabele in de before method.
		 * Hiermee lukt de test wel, want het redirectAttributes object kan zijn methods uitvoeren (containsAttribute).
		 */
		
	}

}
