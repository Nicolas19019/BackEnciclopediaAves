package co.edu.unbosque.BackEciclopediaAves.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.BackEciclopediaAves.model.Biologo;




public interface BackEciclopediaAvesRepository extends CrudRepository<Biologo, Integer> {
	public List<Biologo> findAll();
	
	public Optional<List<Biologo>> findByNombre(String nombre);
	
	public void deleteByNombre(String nombre);
	
	public Optional<Biologo> findById(Integer id);
}
