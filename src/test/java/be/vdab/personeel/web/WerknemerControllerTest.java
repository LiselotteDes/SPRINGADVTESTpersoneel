package be.vdab.personeel.web;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.services.WerknemerService;

@RunWith(MockitoJUnitRunner.class)
public class WerknemerControllerTest {

	private WerknemerController werknemerController;
	@Mock
	private WerknemerService dummyWerknemerService;
	private Werknemer werknemer;
	@Mock
	private RedirectAttributes redirectAttributes;
	
	@Before
	public void before() {
		werknemer = new Werknemer();
		werknemerController = new WerknemerController(dummyWerknemerService);
	}
	
	@Test
	public void readGeeftJuisteHtml() {
		ModelAndView modelAndView = werknemerController.read(Optional.of(werknemer), redirectAttributes);
		assertEquals("werknemers/werknemer", modelAndView.getViewName());
	}
	
	@Test
	public void getOpslagGeeftJuisteHtml() {
		ModelAndView modelAndView = werknemerController.opslag(Optional.of(werknemer), redirectAttributes);
		assertEquals("werknemers/opslag", modelAndView.getViewName());
	}

}
