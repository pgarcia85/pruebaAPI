package com.heroes.pruebaSpringBoot.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
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
public class HeroeServiceImplTest {
	
	@Autowired
	private HeroeService heroeService;
	
	
	@Test
	@DisplayName("Consultar todos los super heroes")
	@Transactional
	public void getHeroesTest() throws HeroeException {
		assertEquals(5, heroeService.getHeroes().size());
	}
	
	
	@Test
	@DisplayName("Consultar super heroe por id")
	@Transactional
	public void getHeroePorIdTest() throws HeroeException {
		assertEquals("superman", heroeService.getHeroePorId(1L).get().getNombre());
	}
	
	@Test
	@DisplayName("Consultar super heroe por id. No existe el heroe")
	@Transactional
	public void getHeroePorIdErrorTest() throws HeroeException {
		assertThrows(HeroeException.class, () -> heroeService.getHeroePorId(500L));
	}
	
	@Test
	@DisplayName("Consultar todos los super heroes que tengan en su nombre un parametro. La lista está vacía")
	@Transactional
	public void getHeroesPorNombreTest() throws HeroeException {
		assertEquals(4, heroeService.getHeroesPorNombre("man").size());
	}
	
	@Test
	@DisplayName("Actualizar un super heroe")
	@Transactional
	public void actualizarHeroeTest() throws HeroeException {
		Heroe heroe= new Heroe("Modificado");
		Heroe heroeModificado = heroeService.actualizarHeroe(5L, heroe);
		assertEquals("Modificado", heroeModificado.getNombre());
	}
	
	@Test
	@DisplayName("Actualizar un super heroe. No existe el heroe")
	@Transactional
	public void actualizarHeroeErrorTest() throws HeroeException {
		Heroe heroe= new Heroe("Modificado");
		assertThrows(HeroeException.class, () -> heroeService.actualizarHeroe(100L, heroe));
	}
	
	@Test
	@DisplayName("Eliminar un super heroe")
	@Transactional
	public void eliminarHeroeTest() throws HeroeException {
		assertTrue(heroeService.eliminarHeroe(2L));
	}
	
	@Test
	@DisplayName("Eliminar un super heroe. No existe el heroe")
	@Transactional
	public void eliminarHeroeErrorTest() throws HeroeException {
		assertThrows(HeroeException.class, () -> heroeService.eliminarHeroe(200L));
	}
	
}
