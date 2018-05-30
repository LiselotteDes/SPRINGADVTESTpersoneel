package be.vdab.personeel.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.repositories.JobtitelRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultJobtitelService implements JobtitelService {
	
	private final JobtitelRepository jobtitelRepository;
	
	DefaultJobtitelService(JobtitelRepository jobtitelRepository) {
		this.jobtitelRepository = jobtitelRepository;
	}

	@Override
	public List<Jobtitel> findAll() {
		return jobtitelRepository.findAll();
	}
	
}
