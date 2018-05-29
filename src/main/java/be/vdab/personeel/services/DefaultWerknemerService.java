package be.vdab.personeel.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultWerknemerService implements WerknemerService {

	private final WerknemerRepository werknemerRepository;
	
	DefaultWerknemerService(WerknemerRepository werknemerRepository) {
		this.werknemerRepository = werknemerRepository;
	}
	
	@Override
	public Optional<Werknemer> findChef() {
		return werknemerRepository.findByChefIsNull();
	}
	
	@Override
	public void opslag(long id, BigDecimal bedrag) {
		
	}
}
