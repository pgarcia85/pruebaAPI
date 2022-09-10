package com.heroes.pruebaSpringBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heroes.pruebaSpringBoot.model.Heroe;

public interface HeroeRepository extends JpaRepository<Heroe, Long>{
	
	/**
	 * Lista los super heroes que contengan en su nombre valor que se pasa como parámetro
	 * 
	 * @param parametro
	 * @return lista de super heroes
	 */
	List<Heroe> findByNombreContainingIgnoreCase(String parametro);
}
