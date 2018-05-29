package be.vdab.personeel.services;

import java.math.BigDecimal;
import java.util.Optional;

import be.vdab.personeel.entities.Werknemer;

public interface WerknemerService {

	Optional<Werknemer> findChef();
	
	void opslag(long id, BigDecimal bedrag);
}
