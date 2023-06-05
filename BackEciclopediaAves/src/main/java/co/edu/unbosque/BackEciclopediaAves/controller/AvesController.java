package co.edu.unbosque.BackEciclopediaAves.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.BackEciclopediaAves.model.Aves;
import co.edu.unbosque.BackEciclopediaAves.model.Biologo;
import co.edu.unbosque.BackEciclopediaAves.respository.AvesRepository;
import co.edu.unbosque.BackEciclopediaAves.respository.BackEciclopediaAvesRepository;
import jakarta.persistence.ForeignKey;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/enciclopediaapi")

public class AvesController {
	@Autowired
	private AvesRepository opresp;

	@PostMapping("/enciclopediaaves")
	public ResponseEntity<String> agregar(@RequestParam String biologo, @RequestParam String nombrecientifico,
			@RequestParam String familia, @RequestParam String orden, @RequestParam String clase,
			@RequestParam String filo, @RequestParam String reino) {
		Aves temp = new Aves();
		temp.setBiologo(biologo);
		temp.setClase(clase);
		temp.setFamilia(familia);
		temp.setFilo(filo);
		temp.setNombrecientifico(nombrecientifico);
		temp.setOrden(orden);
		temp.setReino(reino);

		opresp.save(temp);
		return ResponseEntity.status(HttpStatus.CREATED).body("Dato creado con Ã©xito: 201");
	}

	@GetMapping("/enciclopediaaves")
	public ResponseEntity<List<Aves>> mostrarTodo() {
		List<Aves> lista = (List<Aves>) opresp.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}

	@GetMapping("/enciclopediaaves/{id}")
	public ResponseEntity<Optional<Aves>> mostrarPorID(@RequestParam Integer id) {
		Optional<Aves> dato = opresp.findById(id);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(dato);
	}

	@PutMapping("/enciclopediaaves/{id}")
	public ResponseEntity<String> actualizarPorID(@RequestParam Integer id, @RequestParam String biologo,
			@RequestParam String nombrecientifico, @RequestParam String familia, @RequestParam String orden,
			@RequestParam String clase, @RequestParam String filo, @RequestParam String reino) {
		Optional<Aves> dato = opresp.findById(id);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dato no encontrado");
		}
		Aves temp = dato.get();
		temp.setBiologo(biologo);
		temp.setClase(clase);
		temp.setFamilia(familia);
		temp.setFilo(filo);
		temp.setNombrecientifico(nombrecientifico);
		temp.setOrden(orden);
		temp.setReino(reino);
		
		

		opresp.save(temp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Actualizado con exito");
	}

	
	
	@PutMapping("/enciclopediaavesb/{id}")
	public ResponseEntity<String> actualizarBiologo(@RequestParam Integer id, @RequestParam String biologo) {
		Optional<Aves> dato = opresp.findById(id);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dato no encontrado");
		}
		Aves temp = dato.get();
		temp.setBiologo(temp.getBiologo() + ", " + biologo);
		temp.setClase(temp.getClase());
		temp.setFamilia(temp.getFamilia());
		temp.setFilo(temp.getFilo());
		temp.setNombrecientifico(temp.getNombrecientifico());
		temp.setOrden(temp.getOrden());
		temp.setReino(temp.getReino());

		opresp.save(temp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Actualizado con exito");

	}

	@DeleteMapping("/enciclopediaaves/{id}")
	public ResponseEntity<String> Eliminarpajaro(@PathVariable Integer id) {
		Optional<Aves> op = opresp.findById(id);
		if (!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		}
		opresp.deleteById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
	}

}
