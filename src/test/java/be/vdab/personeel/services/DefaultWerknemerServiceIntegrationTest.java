package be.vdab.personeel.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.exceptions.WerknemerNietGevondenException;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(DefaultWerknemerService.class)
@ComponentScan(basePackageClasses = be.vdab.personeel.repositories.WerknemerRepositoryTest.class)
@Sql("/insertWerknemer.sql")
public class DefaultWerknemerServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private WerknemerService service;
	@Autowired
	private EntityManager manager;
	
	private long idVanTestWerknemer() {
		return super.jdbcTemplate.queryForObject("select id from werknemers where voornaam = 'testWerknemer'", Long.class);
	}

	@Test
	public void findChef() {
		
		Optional<Werknemer> optionalChef = service.findChef();
		assertTrue(optionalChef.isPresent());
		assertTrue(optionalChef.get().getChef() == null);
	}
	
	@Test
	public void opslag() {
		
		long id = idVanTestWerknemer();
		service.opslag(id, BigDecimal.TEN);
		manager.flush();
		BigDecimal nieuwSalaris = super.jdbcTemplate.queryForObject("select salaris from werknemers where id = ?", BigDecimal.class, id);
		assertEquals(0, BigDecimal.valueOf(1010).compareTo(nieuwSalaris));
	}
	
	@Test(expected = WerknemerNietGevondenException.class)
	public void opslagVoorOnbestaandeWerknemer() {
		service.opslag(-1, BigDecimal.TEN);
	}
	
//	@Test
//	public void update() {
//		
//		Werknemer chef = service.findChef().get();
//		chef.opslag(BigDecimal.TEN);
//		service.update(chef);
//		manager.flush();
//		BigDecimal nieuwSalaris = super.jdbcTemplate.queryForObject("select salaris from werknemers where chef = null", BigDecimal.class);
//		assertEquals(0, BigDecimal.valueOf(1010).compareTo(nieuwSalaris));
//		
//	}

}
