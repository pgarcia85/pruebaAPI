package com.heroes.pruebaSpringBoot.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.heroes.pruebaSpringBoot.error.HeroeException;
import com.heroes.pruebaSpringBoot.model.Heroe;
import com.heroes.pruebaSpringBoot.service.HeroeService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class HeroeServiceImplTest {
	
	@Autowired
	private HeroeService heroeService;
	
		
	@BeforeAll
	public void inicializar() {
		heroeService.guardarHeroe(new Heroe("superman"));
		heroeService.guardarHeroe(new Heroe("spiderman"));
		heroeService.guardarHeroe(new Heroe("batman"));
		heroeService.guardarHeroe(new Heroe("Manolito el fuerte"));
		heroeService.guardarHeroe(new Heroe("nemesis"));	
		
	}
	
	@Test
	public void getHeroesTest() {
		assertEquals(5, heroeService.getHeroes().size());
	}

	
	@Test
	public void getHeroePorIdTest() throws HeroeException {
		assertEquals("superman", heroeService.getHeroePorId(1L).get().getNombre());
	}
	
	@Test
	public void getHeroePorIdErrorTest() throws HeroeException {
		assertThrows(HeroeException.class, () -> heroeService.getHeroePorId(500L));
	}
	
	@Test
	public void getHeroesPorNombreTest() {
		assertEquals(4, heroeService.getHeroesPorNombre("man").size());
	}
	
	@Test
	public void actualizarHeroeTest() throws HeroeException {
		Heroe heroe= new Heroe("Modificado");
		Heroe heroeModificado = heroeService.actualizarHeroe(5L, heroe);
		assertEquals("Modificado", heroeModificado.getNombre());
	}
	
	@Test
	public void actualizarHeroeErrorTest() throws HeroeException {
		Heroe heroe= new Heroe("Modificado");
		assertThrows(HeroeException.class, () -> heroeService.actualizarHeroe(100L, heroe));
	}
	
	@Test
	public void eliminarHeroeTest() throws HeroeException {
		assertTrue(heroeService.eliminarHeroe(2L));
	}
	
	@Test
	public void eliminarHeroeErrorTest() throws HeroeException {
		assertThrows(HeroeException.class, () -> heroeService.eliminarHeroe(200L));
	}
	
}
