package com.heroes.pruebaSpringBoot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heroes.pruebaSpringBoot.error.HeroeException;
import com.heroes.pruebaSpringBoot.model.Heroe;
import com.heroes.pruebaSpringBoot.service.HeroeService;

@RestController
@RequestMapping("/api/heroes")
public class HeroeController {

	@Autowired
	private HeroeService heroeService;

	@GetMapping(path = "/allHeroes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllHeroes() {

		Map<String, Object> response = new HashMap<String, Object>();
		List<Heroe> listaHeroes = null;
		try {
			listaHeroes = heroeService.getHeroes();
		} catch (HeroeException e) {
			response.put("error", e.getCode());
			response.put("mensaje", e.getMsg());
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		response.put("listaHeroes", listaHeroes);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping(path = "/heroe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getHeroeById(@PathVariable(value = "id") Long id) {

		Map<String, Object> response = new HashMap<String, Object>();
		Optional<Heroe> h = null;
		try {
			h = heroeService.getHeroePorId(id);
		} catch (HeroeException e) {
			response.put("error", e.getCode());
			response.put("mensaje", e.getMsg());
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		response.put("heroe", h);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@GetMapping(path = "/heroesByName/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getHeroeByNombre(@PathVariable(value = "param") String param) {

		Map<String, Object> response = new HashMap<String, Object>();
		List<Heroe> listaHeroes = null;
		try {
			listaHeroes = heroeService.getHeroesPorNombre(param);
		} catch (HeroeException e) {
			response.put("error", e.getCode());
			response.put("mensaje", e.getMsg().replace("@param", param));
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		response.put("listaHeroes", listaHeroes);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	
	@PutMapping(path = "/updateHeroe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateHeroe(@PathVariable("id") Long id, @RequestBody Heroe heroe) {

		Map<String, Object> response = new HashMap<String, Object>();
		
		Heroe h;
		try {
			h = heroeService.actualizarHeroe(id, heroe);
		} catch (HeroeException e) {
			response.put("error", e.getCode());
			response.put("mensaje", e.getMsg());
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		response.put("Heroe actualizado", h);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/deleteHeroe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteHeroe(@PathVariable("id") Long id) {

		Map<String, Object> response = new HashMap<String, Object>();
		boolean eliminado;
		try {
			eliminado=heroeService.eliminarHeroe(id);
		} catch (HeroeException e) {
			response.put("error", e.getCode());
			response.put("mensaje", e.getMsg());
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		response.put("Heroe eliminado", eliminado);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}


}
