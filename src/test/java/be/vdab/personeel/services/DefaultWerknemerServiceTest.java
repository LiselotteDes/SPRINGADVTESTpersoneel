package be.vdab.personeel.services;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;

@RunWith(MockitoJUnitRunner.class)
public class DefaultWerknemerServiceTest {
	
	private DefaultWerknemerService werknemerService;
	@Mock
	private WerknemerRepository werknemerRepository;
	private Werknemer werknemer;
	
	@Before
	public void before() {
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
