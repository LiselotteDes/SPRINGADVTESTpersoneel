package be.vdab.personeel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeel.entities.Jobtitel;

public interface JobtitelRepository extends JpaRepository<Jobtitel,Long> {

	@Override
	@EntityGraph(Jobtitel.MET_WERKNEMERS)
	Optional<Jobtitel> findById(Long id);
}
