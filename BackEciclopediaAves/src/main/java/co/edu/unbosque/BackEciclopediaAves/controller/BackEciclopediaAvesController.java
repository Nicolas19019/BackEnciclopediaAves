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

import co.edu.unbosque.BackEciclopediaAves.respository.BackEciclopediaAvesRepository;

import co.edu.unbosque.BackEciclopediaAves.model.*;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/enciclopediaapi")
public class BackEciclopediaAvesController {
	@Autowired
	private BackEciclopediaAvesRepository opresp;

	@PostMapping("/enciclopedia")
	public ResponseEntity<String> agregar(@RequestParam String nombre, @RequestParam String correo,
			@RequestParam int edad, @RequestParam Date fecha, @RequestParam String cedula,
			@RequestParam String cursando,@RequestParam String ave) {
		Biologo temp = new Biologo();
		temp.setNombre(nombre);
		temp.setCedula(cedula);
		temp.setCorreo(correo);
		temp.setCursando(cursando);
		temp.setEdad(edad);
		temp.setFecha(fecha);
		temp.setAve(ave);
		opresp.save(temp);
		return ResponseEntity.status(HttpStatus.CREATED).body("Dato creado con éxito: 201");
	}

	@GetMapping("/enciclopedia")
	public ResponseEntity<List<Biologo>> mostrarTodo() {
		List<Biologo> lista = (List<Biologo>) opresp.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}

	@GetMapping("/enciclopedia/{id}")
	public ResponseEntity<Optional<Biologo>> mostrarPorID(@RequestParam Integer id) {
		Optional<Biologo> dato = opresp.findById(id);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(dato);
	}

	@DeleteMapping("/enciclopedia/{id}")
	public ResponseEntity<String> Eliminarbiologo(@PathVariable Integer id) {
		Optional<Biologo> op = opresp.findById(id);
		if (!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		}
		opresp.deleteById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
	}


	@PutMapping("/enciclopedia/{id}")
	public ResponseEntity<String> actualizarPorID(@RequestParam Integer id, @RequestParam String nombre,
			@RequestParam String correo, @RequestParam int edad, @RequestParam Date fecha, @RequestParam String cedula,
			@RequestParam String cursando, @RequestParam String ave) {
		Optional<Biologo> dato = opresp.findById(id);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dato no encontrado");
		}
		Biologo temp = dato.get();
		temp.setCedula(cedula);
		temp.setCorreo(correo);
		temp.setCursando(cursando);
		temp.setEdad(edad);
		temp.setFecha(fecha);
		temp.setNombre(nombre);
		temp.setAve(ave);
		opresp.save(temp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Actualizado con exito");
	}
	
	@PutMapping("/enciclopediaA/{id}")
	public ResponseEntity<String> actualizarAve(@RequestParam Integer id, @RequestParam String ave) {
		Optional<Biologo> dato = opresp.findById(id);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dato no encontrado");
		}
		Biologo temp = dato.get();

		temp.setCedula(temp.getCedula());
		temp.setCorreo(temp.getCorreo());
		temp.setCursando(temp.getCursando());
		temp.setEdad(temp.getEdad());
		temp.setFecha(temp.getFecha());
		temp.setNombre(temp.getNombre());
		temp.setAve(temp.getAve() + ", " + ave);

		opresp.save(temp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Actualizado con exito");

	}

}
