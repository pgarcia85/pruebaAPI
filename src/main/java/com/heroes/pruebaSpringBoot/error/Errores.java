package com.heroes.pruebaSpringBoot.error;

public enum Errores {
	

	HEROE_NOTFOUND ("2001", "No se ha encontrado el super héroe"),
	HEROES_EMPTY ("2002", "No hay super heroes en el sistema"),
	HEROE_NOT_NAME ("2003", "No hay super heroes que contengan en su nombre '@param'");
	
	public final String codigo;
	public final String msg;
	
	Errores(String codigo, String msg) {
		this.codigo = codigo;
		this.msg = msg;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMsg() {
		return msg;
	}
	
	
	
}
