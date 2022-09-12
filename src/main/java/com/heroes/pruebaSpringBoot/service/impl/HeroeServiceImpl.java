package com.heroes.pruebaSpringBoot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heroes.pruebaSpringBoot.error.Errores;
import com.heroes.pruebaSpringBoot.error.HeroeException;
import com.heroes.pruebaSpringBoot.model.Heroe;
import com.heroes.pruebaSpringBoot.repository.HeroeRepository;
import com.heroes.pruebaSpringBoot.service.HeroeService;

@Service
public class HeroeServiceImpl implements HeroeService {
	
	@Autowired
	private HeroeRepository heroeRepository;
	
	@Override
	public List<Heroe> getHeroes() throws HeroeException {
		List<Heroe> lista = heroeRepository.findAll();
		if(lista.isEmpty()) {
			throw new HeroeException(Errores.HEROES_EMPTY.getCodigo(),Errores.HEROES_EMPTY.getMsg());
		}
		return lista;
	}

	@Override
	public Optional<Heroe> getHeroePorId(Long id) throws HeroeException {
		return Optional.ofNullable(heroeRepository.findById(id)
		.orElseThrow(() -> new HeroeException(Errores.HEROE_NOTFOUND.getCodigo(), Errores.HEROE_NOTFOUND.getMsg())));
	}	

	@Override
	public List<Heroe> getHeroesPorNombre(String parametro) throws HeroeException {	
		List<Heroe> lista = heroeRepository.findByNombreContainingIgnoreCase(parametro);
		if(lista.isEmpty()) {
			throw new HeroeException(Errores.HEROE_NOT_NAME.getCodigo(),Errores.HEROE_NOT_NAME.getMsg());
		}
		return lista;
	}

	@Override
	public Heroe actualizarHeroe(Long id, Heroe heroe) throws HeroeException {
		Optional<Heroe> heroeB = Optional.ofNullable(heroeRepository.findById(id)
				.orElseThrow(() -> new HeroeException(Errores.HEROE_NOTFOUND.getCodigo(), Errores.HEROE_NOTFOUND.getMsg())));
		heroeB.get().setNombre(heroe.getNombre());
		return heroeRepository.save(heroeB.get());
	}

	@Override
	public boolean eliminarHeroe(Long id) throws HeroeException {
		//comprobar si el heroe existe
		Optional.ofNullable(heroeRepository.findById(id)
				.orElseThrow(() -> new HeroeException(Errores.HEROE_NOTFOUND.getCodigo(), Errores.HEROE_NOTFOUND.getMsg())));
		//eliminar el heroe
		heroeRepository.deleteById(id);
		return true;
	}

	@Override
	public Heroe guardarHeroe(Heroe heroe) {		
		return heroeRepository.save(heroe);
	}

	

}
