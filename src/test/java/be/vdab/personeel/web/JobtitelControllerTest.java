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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.services.JobtitelService;

@RunWith(MockitoJUnitRunner.class)
public class JobtitelControllerTest {
	
	private JobtitelController jobtitelController;
	@Mock
	private JobtitelService dummyJobtitelService;
	@Mock
	private RedirectAttributes redirectAttributes;
	private MockMvc mvc;
	
	@Before
	public void before() {
		when(dummyJobtitelService.findAll()).thenReturn(Collections.singletonList(new Jobtitel()));
		jobtitelController = new JobtitelController(dummyJobtitelService);
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
	
//	@Test
//	public void jobtitelGeeftFoutDoorAlsJobtitelNietGevondenWordt() throws Exception {
////		ModelAndView modelAndView = jobtitelController.jobtitel(Optional.empty(), redirectAttributes);
////		assertTrue(modelAndView.getModel().containsKey("fout"));
//		
//		mvc.perform(get("/jobtitels/-1")).andExpect(redirectedUrl("/jobtitels/jobtitel"));
//		
//	}

}
