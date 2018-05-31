package be.vdab.personeel.entities;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.time.LocalDate;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

public class WerknemerTest {
	
	private Werknemer werknemer;
	private Validator validator;
	private Field geboorteVeld = ReflectionUtils.findField(Werknemer.class, "geboorte");
	
	@Before
	public void before() {
		
		this.werknemer = new Werknemer();
		
		ReflectionUtils.makeAccessible(geboorteVeld);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

	}

	@Test
	public void correctRijksregisternr() {
		
		ReflectionUtils.setField(geboorteVeld, werknemer, LocalDate.of(1966,8,1));	
		werknemer.setRijksregisternr("66080100153");
		assertEquals(0, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksregisternrMetVerkeerdGeboortejaar() {
		ReflectionUtils.setField(geboorteVeld, werknemer, LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr("65080100153");
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksregisternrMetVerkeerdeGeboortemaand() {
		ReflectionUtils.setField(geboorteVeld, werknemer, LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr("66180100153");
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksRegisternrMetVerkeerdeGeboortedag() {
		ReflectionUtils.setField(geboorteVeld, werknemer, LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr("66080200153");
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksRegisternrMet10Cijfers() {
		ReflectionUtils.setField(geboorteVeld, werknemer, LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr("6608010153");
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksRegisternrMetVerkeerdControleGetal() {
		ReflectionUtils.setField(geboorteVeld, werknemer, LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr("66080100143");
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void rijksRegisterMagNietNullZijn() {
		ReflectionUtils.setField(geboorteVeld, werknemer, LocalDate.of(1966,8,1));
		werknemer.setRijksregisternr(null);
		assertEquals(1, validator.validate(werknemer).size());
	}
	
	@Test
	public void correctRijksregisternrIn2000() {
		ReflectionUtils.setField(geboorteVeld, werknemer, LocalDate.of(2000, 05, 15));
		werknemer.setRijksregisternr("00051500297");
		assertEquals(0, validator.validate(werknemer).size());
	}

}
