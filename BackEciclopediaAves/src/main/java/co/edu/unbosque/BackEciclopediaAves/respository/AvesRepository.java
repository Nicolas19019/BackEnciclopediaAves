package co.edu.unbosque.BackEciclopediaAves.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.BackEciclopediaAves.model.Aves;


public interface AvesRepository extends CrudRepository<Aves, Integer> {
	public List<Aves> findAll();


	public Optional<Aves> findById(Integer id);
}
