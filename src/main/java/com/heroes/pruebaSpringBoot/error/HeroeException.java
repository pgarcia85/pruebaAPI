package com.heroes.pruebaSpringBoot.error;

public class HeroeException extends Exception{
	

	private static final long serialVersionUID = -6097969541559952415L;
	private String codigo;
	private String msg;
	

	public HeroeException() {
		super();		
	}


	public HeroeException(String codigo, String msg) {
		super();
		this.codigo = codigo;
		this.msg = msg;
	}


	/*
	 * GETTER & SETTER
	 */
	public String getCode() {
		return codigo;
	}


	public void setCode(String code) {
		this.codigo = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
