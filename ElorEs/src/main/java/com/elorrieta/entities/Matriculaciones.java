package com.elorrieta.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Matriculaciones implements Serializable {

	private static final long serialVersionUID = -8632164890217148L;
	private Integer id;
	private User alum;
	private Ciclo ciclo;
	private Integer curso;
	private LocalDate fecha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getAlum() {
		return alum;
	}

	public void setAlum(User alum) {
		this.alum = alum;
	}

	public Ciclo getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
