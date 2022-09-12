package com.heroes.pruebaSpringBoot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.heroes.pruebaSpringBoot.error.HeroeException;
import com.heroes.pruebaSpringBoot.model.Heroe;
import com.heroes.pruebaSpringBoot.service.HeroeService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class HeroeControllerTest {
	
	@Autowired
	private HeroeController heroeController;
	
	@Autowired
	private HeroeService heroeService;

	@Test
	@DisplayName("Consultar todos los super heroes. La lista está vacía")
	@Transactional
	public void getAllHeroesListaVaciaTest() throws HeroeException {
		heroeService.getHeroes().forEach(h-> {
			try {
				heroeService.eliminarHeroe(h.getId());
			} catch (HeroeException e) {
				e.printStackTrace();
			}
		});
		Map<String, Object> respuesta = (Map<String, Object>) heroeController.getAllHeroes().getBody();
		assertEquals("No hay super heroes en el sistema", respuesta.get("mensaje"));

	}
	
	@Test
	@DisplayName("Consultar todos los super heroes")
	@Transactional
	public void getAllHeroesTest() throws HeroeException {

		Map<String, Object> respuesta = (Map<String, Object>) heroeController.getAllHeroes().getBody();
		List<Heroe> lista = (List<Heroe>) respuesta.get("listaHeroes");
		assertEquals(5, lista.size());
		
	}
	
	@Test
	@DisplayName("Consultar heroe por id.")
	@Transactional
	public void getHeroeByIdTest() throws HeroeException {
		
		Map<String, Object> respuesta = (Map<String, Object>) heroeController.getHeroeById(1L).getBody();
		Optional<Heroe> heroe =  (Optional<Heroe>) respuesta.get("heroe");
		assertEquals("superman", heroe.get().getNombre());
	
	}
	
	@Test
	@DisplayName("Consultar heroe por id. No existe super heroe para ese id")
	@Transactional
	public void getHeroeByIdNoExisteTest() {
		Map<String, Object> respuesta = (Map<String, Object>) heroeController.getHeroeById(100L).getBody();
		assertEquals("No se ha encontrado el super héroe", respuesta.get("mensaje"));

	}
	
	@Test
	@DisplayName("Consultar heroe por nombre.")
	@Transactional
	public void getHeroeByNombreTest() throws HeroeException {
		
		Map<String, Object> respuesta = (Map<String, Object>) heroeController.getHeroeByNombre("man").getBody();
		List<Heroe> heroes =  (List<Heroe>) respuesta.get("listaHeroes");
		assertEquals(4, heroes.size());
		
	}
	
	@Test
	@DisplayName("Consultar heroe por nombre. No existen super heroes para ese nombre")
	@Transactional
	public void getHeroeByNombreNoExisteTest() {
		Map<String, Object> respuesta = (Map<String, Object>) heroeController.getHeroeByNombre("asi").getBody();
		assertEquals("No hay super heroes que contengan en su nombre 'asi'", respuesta.get("mensaje"));

	}
	
	
	@Test
	@DisplayName("Actualizar heroe")
	@Transactional
	public void updateHeroeTest() throws HeroeException {
		
		Heroe heroeMod= new Heroe("modificado");
		Map<String, Object> respuesta = (Map<String, Object>) heroeController.updateHeroe(5L, heroeMod).getBody();
		Heroe heroe =  (Heroe) respuesta.get("Heroe actualizado");
		assertEquals("modificado", heroe.getNombre());
		
	}
	
	@Test
	@DisplayName("Actualizar heroe. No existe super heroe para ese id")
	@Transactional
	public void updateHeroeNoExisteTest() {
		Heroe heroeMod= new Heroe("modificado");
		Map<String, Object> respuesta = (Map<String, Object>) heroeController.updateHeroe(10L, heroeMod).getBody();
		assertEquals("No se ha encontrado el super héroe", respuesta.get("mensaje"));

	}
	
	@Test
	@DisplayName("Eliminar heroe")
	//@Order(8)
	@Transactional
	public void deleteHeroeTest() throws HeroeException {
		
		Map<String, Object> respuesta = (Map<String, Object>) heroeController.deleteHeroe(5L).getBody();
		assertTrue((boolean) respuesta.get("Heroe eliminado"));
		
	}
	
	@Test
	@DisplayName("Eliminar heroe. No existe super heroe para ese id")
	@Transactional
	public void deleteHeroeNoExisteTest() {
		Map<String, Object> respuesta = (Map<String, Object>) heroeController.deleteHeroe(10L).getBody();
		assertEquals("No se ha encontrado el super héroe", respuesta.get("mensaje"));

	}
}
