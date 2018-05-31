package be.vdab.personeel.entities;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class WerknemerTest {
	
	private Werknemer werknemer;
	private Validator validator;
	
	@Before
	public void before() {
		this.werknemer = new Werknemer();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

	}

	@Test
	public void correctRijksregisternr() {
		werknemer.setGeboorte(LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr(66080100153L);
		assertEquals(0, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksregisternrMetVerkeerdGeboortejaar() {
		werknemer.setGeboorte(LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr(65080100153L);
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksregisternrMetVerkeerdeGeboortemaand() {
		werknemer.setGeboorte(LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr(66180100153L);
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksRegisternrMetVerkeerdeGeboortedag() {
		werknemer.setGeboorte(LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr(66080200153L);
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksRegisternrMet10Cijfers() {
		werknemer.setGeboorte(LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr(6608010153L);
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksRegisternrMetVerkeerdControleGetal() {
		werknemer.setGeboorte(LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr(66080100143L);
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksRegisterMagNietNullZijn() {
		werknemer.setGeboorte(LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr(null);
		
	}
	
	@Test
	public void correctRijksregisternrIn2000() {
		werknemer.setGeboorte(LocalDate.of(2000, 05, 15));
		werknemer.setRijksregisternr(Long.valueOf("00051500297"));
		assertEquals(0, validator.validate(werknemer).size());
	}
}
