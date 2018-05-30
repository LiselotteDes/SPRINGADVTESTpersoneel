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
		
		Long rijksregisternr = werknemer.getRijksregisternr();
		LocalDate geboorte = werknemer.getGeboorte();
		
		if (rijksregisternr == null || geboorte == null) {
			return true;
		}
		
		if (String.valueOf(rijksregisternr).length() != 11) {
			return false;
		}
		
		String eerstePaarCijfers = String.valueOf(rijksregisternr).substring(0,2);
		String tweedePaarCijfers = String.valueOf(rijksregisternr).substring(2,4);
		String derdePaarCijfers = String.valueOf(rijksregisternr).substring(4,6);
		
//		String eerste6Cijfers = String.valueOf(rijksregisternr).substring(0,6);
//		String geboorteString = String.valueOf(geboorte.getYear() % 100) + String.valueOf(geboorte.getMonthValue()) + String.valueOf(geboorte.getDayOfMonth());
		
		long geboortejaar = geboorte.getYear() % 100L;
		long geboortemaand = geboorte.getMonthValue();
		long geboortedag = geboorte.getDayOfMonth();
		
		if (! (Long.parseLong(eerstePaarCijfers) == geboortejaar && Long.parseLong(tweedePaarCijfers) == geboortemaand && Long.parseLong(derdePaarCijfers) == geboortedag)) {
			return false;
		}
		
		long controleGetal = rijksregisternr % 100L;
		long eerste9Cijfers = rijksregisternr / 100;
		
		if (geboorte.getYear() >= 2000) {
			eerste9Cijfers += 2_000_000_000;
		}
		
		return controleGetal == 97 - (eerste9Cijfers % 97);
		
	}
	
}
