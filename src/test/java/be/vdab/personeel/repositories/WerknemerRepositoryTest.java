package be.vdab.personeel.repositories;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.personeel.entities.Werknemer;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertWerknemer.sql")
public class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private Werknemer werknemer;
	@Autowired
	private WerknemerRepository werknemerRepository;
	
	@Before
	public void before() {
		this.werknemer = new Werknemer();
	}
	
	private long idVanTestWerknemer() {
		return super.jdbcTemplate.queryForObject("select id from werknemers where voornaam='testWerknemer'", Long.class);
	}

	@Test
	public void findById() {
		Werknemer werknemer = werknemerRepository.findById(idVanTestWerknemer()).get();
		assertEquals("testWerknemer", werknemer.getVoornaam());
	}
	
	@Test
	public void findByChefIsNull() {
		Werknemer baas = werknemerRepository.findByChefIsNull().get();
		assertEquals(baas.getChef(),null);
	}

	@Test
	public void opslag() {
		Werknemer werknemer = werknemerRepository.findById(idVanTestWerknemer()).get();
		werknemer.opslag(BigDecimal.valueOf(200));
		assertEquals(0, BigDecimal.valueOf(1200).compareTo(werknemer.getSalaris()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void opslagMagNietKleinerZijnDan1() {
		Werknemer werknemer = werknemerRepository.findById(idVanTestWerknemer()).get();
		werknemer.opslag(BigDecimal.ZERO);
	}
	
	@Test
	public void opslagMagMet1() {
		Werknemer werknemer = werknemerRepository.findById(idVanTestWerknemer()).get();
		werknemer.opslag(BigDecimal.ONE);
		assertEquals(0, BigDecimal.valueOf(1001).compareTo(werknemer.getSalaris()));
	}
	
	@Test(expected = NullPointerException.class)
	public void opslagMetNullKanNiet() {
		Werknemer werknemer = werknemerRepository.findById(idVanTestWerknemer()).get();
		werknemer.opslag(null);
	}
}
