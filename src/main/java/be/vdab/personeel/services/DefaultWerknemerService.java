package be.vdab.personeel.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.exceptions.WerknemerNietGevondenException;
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
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void opslag(long id, BigDecimal bedrag) {
		
		Optional<Werknemer> optionalWerknemer = werknemerRepository.findById(id);
		
		if (optionalWerknemer.isPresent()) {
			optionalWerknemer.get().opslag(bedrag);
		} else {
			throw new WerknemerNietGevondenException();
		}
		
	}
	
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void update(Werknemer werknemer) {
		
		werknemerRepository.save(werknemer);
		
	}
}
