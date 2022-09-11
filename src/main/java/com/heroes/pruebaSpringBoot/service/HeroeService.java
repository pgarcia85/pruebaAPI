package com.heroes.pruebaSpringBoot.service;

import java.util.List;
import java.util.Optional;

import com.heroes.pruebaSpringBoot.error.HeroeException;
import com.heroes.pruebaSpringBoot.model.Heroe;

public interface HeroeService {
	
	/**
	 * Método que guarda un super heroe que se para como parametro
	 * @param heroe
	 * @return heroe guardado
	 */
	Heroe guardarHeroe(Heroe heroe);
	
	/**
	 * Método que lista todos los super heroes
	 * @return lista de super heroes
	 */
	List<Heroe> getHeroes();
	
	/**
	 * Método que busca un heroe por su id
	 * @param id
	 * @return heroe
	 * @throws HeroeException
	 */
	Optional<Heroe> getHeroePorId(Long id) throws HeroeException;	
	
	/**
	 * Método que busca todos los super heroes que contienen en su nombre 
	 * el texto que se pasa como parametro
	 * @param parametro
	 * @return lista con los heroes
	 */
	List<Heroe> getHeroesPorNombre(String parametro);
	
	/**
	 * Método que actualiza un super heroe
	 * 
	 * @param id del heroe a actualizar
	 * @param heroe a modicar
	 * @return Heroe con los datos actualizados
	 * @throws HeroeException
	 */
	Heroe actualizarHeroe(Long id, Heroe heroe)throws HeroeException;
	
	/**
	 * Método que elimina un super heroe
	 * 
	 * @param id del super heroe a eliminar
	 * @return true si ha sido eliminado
	 * @throws HeroeException
	 */
	boolean eliminarHeroe(Long id) throws HeroeException;
	
	

}
