package co.edu.unbosque.BackEciclopediaAves;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.edu.unbosque.BackEciclopediaAves.controller.BackEciclopediaAvesController;
import co.edu.unbosque.BackEciclopediaAves.model.Biologo;
import co.edu.unbosque.BackEciclopediaAves.respository.BackEciclopediaAvesRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest

class BackEciclopediaAvesApplicationTests {

	   @Mock
	    private BackEciclopediaAvesRepository repository;

	    @InjectMocks
	    private BackEciclopediaAvesController controller;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testAgregar() {
	        Biologo biologo = new Biologo();
	        biologo.setNombre("John Doe");
	        biologo.setCedula("123456789");
	        biologo.setCorreo("johndoe@example.com");
	        biologo.setEdad(30);
	        biologo.setFecha(Date.valueOf("2022-01-01"));
	        biologo.setCursando("Biología");
	        biologo.setAve("Ave1");

	        when(repository.save(any(Biologo.class))).thenReturn(biologo);

	        ResponseEntity<String> response = controller.agregar("Sara yakarta", "Sara@example.com", 30,
	                Date.valueOf("2022-01-01"), "123456789", "Biología", "Ave1");

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals("Dato creado con éxito: 201", response.getBody());
	    }

	    @Test
	    public void testMostrarTodo() {
	        Biologo biologo1 = new Biologo();
	        biologo1.setNombre("Nicolas Machado");
	        biologo1.setCedula("123456789");
	        biologo1.setCorreo("Nicolas@example.com");
	        biologo1.setEdad(30);
	        biologo1.setFecha(Date.valueOf("2022-01-01"));
	        biologo1.setCursando("Biología");
	        biologo1.setAve("Ave1");

	        Biologo biologo2 = new Biologo();
	        biologo2.setNombre("Carlos hernando");
	        biologo2.setCedula("987654321");
	        biologo2.setCorreo("Carlos@example.com");
	        biologo2.setEdad(25);
	        biologo2.setFecha(Date.valueOf("2022-02-01"));
	        biologo2.setCursando("Biología");
	        biologo2.setAve("Ave2");

	        List<Biologo> biologos = Arrays.asList(biologo1, biologo2);

	        when(repository.findAll()).thenReturn(biologos);

	        ResponseEntity<List<Biologo>> response = controller.mostrarTodo();

	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	        assertEquals(biologos, response.getBody());
	    }

	    @Test
	    public void testMostrarPorID() {
	        Biologo biologo = new Biologo();
	        biologo.setNombre("Juan alvarado");
	        biologo.setCedula("123456789");
	        biologo.setCorreo("juan@example.com");
	        biologo.setEdad(30);
	        biologo.setFecha(Date.valueOf("2022-01-01"));
	        biologo.setCursando("Biología");
	        biologo.setAve("Ave1");

	        when(repository.findById(anyInt())).thenReturn(Optional.of(biologo));

	        ResponseEntity<Optional<Biologo>> response = controller.mostrarPorID(1);

	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	        assertEquals(Optional.of(biologo), response.getBody());
	    }

	    @Test
	    public void testEliminarPajaro() {
	        when(repository.findById(anyInt())).thenReturn(Optional.empty());

	        ResponseEntity<String> response = controller.Eliminarbiologo(1);

	        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	        assertEquals("", response.getBody());
	    }

	    @Test
	    public void testActualizarPorID() {
	        Biologo existingBiologo = new Biologo();
	        existingBiologo.setNombre("arturo balcazar");
	        existingBiologo.setCedula("123456789");
	        existingBiologo.setCorreo("arturo@example.com");
	        existingBiologo.setEdad(30);
	        existingBiologo.setFecha(Date.valueOf("2022-01-01"));
	        existingBiologo.setCursando("Biología");
	        existingBiologo.setAve("Ave1");

	        when(repository.findById(anyInt())).thenReturn(Optional.of(existingBiologo));

	        ResponseEntity<String> response = controller.actualizarPorID(1, "John Doe Updated",
	                "johndoe@example.com", 30, Date.valueOf("2022-01-01"), "123456789", "Biología", "Ave1 Updated");

	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	        assertEquals("Actualizado con exito", response.getBody());

	        Biologo updatedBiologo = new Biologo();
	        updatedBiologo.setNombre("John Doe Updated");
	        updatedBiologo.setCedula("123456789");
	        updatedBiologo.setCorreo("johndoe@example.com");
	        updatedBiologo.setEdad(30);
	        updatedBiologo.setFecha(Date.valueOf("2022-01-01"));
	        updatedBiologo.setCursando("Biología");
	        updatedBiologo.setAve("Ave1 Updated");

	        Mockito.verify(repository, Mockito.times(1)).save(updatedBiologo);
	    }

	    @Test
	    public void testActualizarAve() {
	        Biologo existingBiologo = new Biologo();
	        existingBiologo.setNombre("John Doe");
	        existingBiologo.setCedula("123456789");
	        existingBiologo.setCorreo("johndoe@example.com");
	        existingBiologo.setEdad(30);
	        existingBiologo.setFecha(Date.valueOf("2022-01-01"));
	        existingBiologo.setCursando("Biología");
	        existingBiologo.setAve("Ave1");

	        when(repository.findById(anyInt())).thenReturn(Optional.of(existingBiologo));

	        ResponseEntity<String> response = controller.actualizarAve(1, "Ave2");

	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	        assertEquals("Actualizado con exito", response.getBody());

	        Biologo updatedBiologo = new Biologo();
	        updatedBiologo.setNombre("John Doe");
	        updatedBiologo.setCedula("123456789");
	        updatedBiologo.setCorreo("johndoe@example.com");
	        updatedBiologo.setEdad(30);
	        updatedBiologo.setFecha(Date.valueOf("2022-01-01"));
	        updatedBiologo.setCursando("Biología");
	        updatedBiologo.setAve("Ave1, Ave2");

	        Mockito.verify(repository, Mockito.times(1)).save(updatedBiologo);
	    }
}
