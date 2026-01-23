package com.elorrieta.entities;

import java.io.Serializable;

public class Horario implements Serializable {

	private static final long serialVersionUID = -8632164890217148L;
	private Integer id;
	private String dia;
	private Integer hora;
	private User user;
	private Modulo modulo;
	private String aula;
	private String observaciones;

	public Horario() {
	}

	public Horario(Integer id, String dia, Integer hora, User user, Modulo modulo, String aula, String observaciones) {
		this.id = id;
		this.dia = dia;
		this.hora = hora;
		this.user = user;
		this.modulo = modulo;
		this.aula = aula;
		this.observaciones = observaciones;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
