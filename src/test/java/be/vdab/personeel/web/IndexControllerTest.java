package be.vdab.personeel.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.services.WerknemerService;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
	
	private IndexController indexController;
	@Mock
	private WerknemerService dummyWerknemerService;
	
	@Before
	public void before() {
		when(dummyWerknemerService.findChef()).thenReturn(Optional.of(new Werknemer()));
		indexController = new IndexController(dummyWerknemerService);
	}

	@Test
	public void indexWerktSamenMetDeJuisteHTML() {
		ModelAndView modelAndView = indexController.index();
		assertEquals("index", modelAndView.getViewName());
	}
	
	@Test
	public void indexGeeftChefDoor() {
		ModelAndView modelAndView = indexController.index();
		assertTrue(modelAndView.getModel().containsKey("chef"));
	}

}
