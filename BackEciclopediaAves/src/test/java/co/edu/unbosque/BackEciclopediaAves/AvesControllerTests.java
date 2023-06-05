package co.edu.unbosque.BackEciclopediaAves;

import co.edu.unbosque.BackEciclopediaAves.controller.AvesController;
import co.edu.unbosque.BackEciclopediaAves.model.Aves;
import co.edu.unbosque.BackEciclopediaAves.respository.AvesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class AvesControllerTests {

	 @InjectMocks
	    private AvesController avesController;

	    @Mock
	    private AvesRepository avesRepository;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);

	        // Configurar comportamiento simulado para los métodos del repositorio
	        List<Aves> listaAves = new ArrayList<>();
	        listaAves.add(new Aves());
	        when(avesRepository.findAll()).thenReturn(listaAves);
	        when(avesRepository.findById(anyInt())).thenReturn(Optional.of(new Aves()));
	        when(avesRepository.save(any(Aves.class))).thenReturn(new Aves());
	    }

	    @Test
	    public void testMostrarTodo() {
	        ResponseEntity<List<Aves>> response = avesController.mostrarTodo();

	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	        assertNotNull(response.getBody());
	    }

	    @Test
	    public void testMostrarPorID() {
	        ResponseEntity<Optional<Aves>> response = avesController.mostrarPorID(1);

	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	        assertNotNull(response.getBody());
	    }

	    @Test
	    public void testAgregar() {
	        ResponseEntity<String> response = avesController.agregar("Biologo", "NombreCientifico", "Familia", "Orden", "Clase", "Filo", "Reino");

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals("Dato creado con éxito: 201", response.getBody());
	    }

	    @Test
	    public void testActualizarPorID() {
	        ResponseEntity<String> response = avesController.actualizarPorID(1, "NuevoBiologo", "NuevoNombreCientifico", "NuevaFamilia", "NuevoOrden", "NuevaClase", "NuevoFilo", "NuevoReino");

	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	        assertEquals("Actualizado con exito", response.getBody());
	    }

	    @Test
	    public void testActualizarBiologo() {
	        ResponseEntity<String> response = avesController.actualizarBiologo(1, "NuevoBiologo");

	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	        assertEquals("Actualizado con exito", response.getBody());
	    }

	    @Test
	    public void testEliminarPajaro() {
	        ResponseEntity<String> response = avesController.Eliminarpajaro(1);

	        assertEquals(HttpStatus.FOUND, response.getStatusCode());
	        assertEquals("Deleted", response.getBody());
	    }

}
