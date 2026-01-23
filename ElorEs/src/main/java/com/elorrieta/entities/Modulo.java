package com.elorrieta.entities;

import java.io.Serializable;

public class Modulo implements Serializable {

	private static final long serialVersionUID = -8632164890217148L;
	private Integer id;
	private String nombre;
	private String nombre_eus;

	public Modulo() {
	}

	public Modulo(Integer id, String nombre, String nombre_eus) {
		this.id = id;
		this.nombre = nombre;
		this.nombre_eus = nombre_eus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre_eus() {
		return nombre_eus;
	}

	public void setNombre_eus(String nombre_eus) {
		this.nombre_eus = nombre_eus;
	}

}
