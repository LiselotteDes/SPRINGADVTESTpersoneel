package be.vdab.personeel.constraints;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.personeel.entities.Werknemer;

public class RijksregisternummerValidator implements ConstraintValidator<Rijksregisternummer,Werknemer> {

	@Override
	public void initialize(Rijksregisternummer rijksregisternummer) {
	}
	
	@Override
	public boolean isValid(Werknemer werknemer, ConstraintValidatorContext context) {
		
		String rijksregisternr = werknemer.getRijksregisternr();
		LocalDate geboorte = werknemer.getGeboorte();
		
		if (rijksregisternr == null || geboorte == null) {
			return true;
		}
		
		if (rijksregisternr.length() != 11) {
			return false;
		}
		
		String eerstePaarCijfers = rijksregisternr.substring(0,2);
		String tweedePaarCijfers = rijksregisternr.substring(2,4);
		String derdePaarCijfers = rijksregisternr.substring(4,6);
		
		long geboortejaar = geboorte.getYear() % 100L;
		long geboortemaand = geboorte.getMonthValue();
		long geboortedag = geboorte.getDayOfMonth();
		
		if (! (Long.parseLong(eerstePaarCijfers) == geboortejaar && Long.parseLong(tweedePaarCijfers) == geboortemaand && Long.parseLong(derdePaarCijfers) == geboortedag)) {
			return false;
		}
		
		String controleGetal = rijksregisternr.substring(9);
		String eerste9Cijfers = rijksregisternr.substring(0,9);
		
		if (geboorte.getYear() >= 2000) {
			eerste9Cijfers = 2 + eerste9Cijfers;
		}
		
		return Long.parseLong(controleGetal) == 97L - (Long.parseLong(eerste9Cijfers) % 97);
		
	}
	
}
