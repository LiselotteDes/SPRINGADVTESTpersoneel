package be.vdab.personeel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeel.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer,Long> {
	
	Optional<Werknemer> findByChefIsNull();

	@Override
	@EntityGraph(Werknemer.MET_ONDERGESCHIKTEN_EN_CHEF)
	Optional<Werknemer> findById(Long id);
}
