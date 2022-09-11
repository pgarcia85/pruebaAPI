package com.heroes.pruebaSpringBoot.error;

public enum Errores {
	

	HEROE_NOTFOUND ("1001", "No se ha encontrado el super héroe");
	
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
