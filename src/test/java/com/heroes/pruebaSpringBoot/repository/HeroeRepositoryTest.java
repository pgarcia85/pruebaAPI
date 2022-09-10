package com.heroes.pruebaSpringBoot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.heroes.pruebaSpringBoot.model.Heroe;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class HeroeRepositoryTest {
	
	@Autowired
	private HeroeRepository heroesRepository;
	
    private List<Heroe> lista;
	
	@BeforeEach
	public void inicializar() {
		heroesRepository.save(new Heroe("superman"));
		heroesRepository.save(new Heroe("spiderman"));
		heroesRepository.save(new Heroe("batman"));
		heroesRepository.save(new Heroe("Manolito el fuerte"));
		heroesRepository.save(new Heroe("nemesis"));
		lista = heroesRepository.findAll();
		lista.forEach(h -> System.out.println(h.toString()));
	}
	
	@Test
	@DisplayName("Consultar todos los super heroes")
	public void consultarSuperHeroesTest() {
		assertEquals(5, lista.size());
	}
	
	@Test
	@DisplayName("Consultar un super heroe por id")
	public void consultarSuperHeroePorIdTest() {
		Heroe h= heroesRepository.findById(1L).get();
		assertEquals("superman", h.getNombre());
	}
	
	@Test
	@DisplayName("Consultar un super heroe por id y no existe")
	public void consultarHeroePorIdErrorTest() {
		Long id = 6L;
		Exception exception = null;
		try {
			heroesRepository.findById(id).orElseThrow(() -> new Exception("Heroe con id: "+id+" no encontrado"));
		} catch (Exception e) {
			exception=e;
		}
		assertEquals("Heroe con id: 6 no encontrado", exception.getMessage());
	}
	
	@Test
	@DisplayName("Consultar todos los super heroes que contengan en su nombre un parámetro")
	public void consultarSuperHeroesPorNombreTest() {
		List<Heroe> lista= heroesRepository.findByNombreContainingIgnoreCase("man");
		assertEquals(4, lista.size());
	}
	

	
	@Test
	@DisplayName("Modificar un super heroe")
	public void modificarSuperHeroeTest() {
		Heroe modificado= new Heroe("Modificado");
		Optional<Heroe> h = heroesRepository.findById(lista.get(0).getId());
		h.get().setNombre(modificado.getNombre());
		Heroe hm = heroesRepository.save(h.get());
		assertEquals("Modificado", hm.getNombre());
		
	}
	
	
	@Test
	@DisplayName("Eliminar un super heroe")
	public void eliminarSuperHeroeTest() {
		Long idEliminar= lista.get(0).getId();
		
		heroesRepository.deleteById(idEliminar);
		assertEquals(4, heroesRepository.findAll().size());
	}
	
	
}
