package com.elorrieta.tcpEnvios.mensajes.parts;

import java.io.Serializable;

import com.elorrieta.entities.Ciclo;
import com.elorrieta.entities.Matriculaciones;
import com.elorrieta.entities.User;

public class FilterParts implements Serializable {
	private static final long serialVersionUID = -8632164890217148L;
	private User user;
	private Ciclo ciclo;
	private Matriculaciones matriculaciones;

	public FilterParts() {
	}

	public FilterParts(Ciclo ciclo, Matriculaciones matriculaciones, User user) {
		this.ciclo = ciclo;
		this.matriculaciones = matriculaciones;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ciclo getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	public Matriculaciones getMatriculaciones() {
		return matriculaciones;
	}

	public void setMatriculaciones(Matriculaciones matriculaciones) {
		this.matriculaciones = matriculaciones;
	}

}
