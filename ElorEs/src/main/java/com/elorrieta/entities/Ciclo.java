package com.elorrieta.entities;

import java.io.Serializable;

public class Ciclo  implements Serializable{

    private static final long serialVersionUID = -8632164890217148L;
	private Integer id;
	private String nombre;

	public Ciclo() {
	}

	public Ciclo(String nombre) {
		this.nombre = nombre;
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
}
