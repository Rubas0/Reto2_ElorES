package com.elorrieta.tcpConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.elorrieta.entities.Ciclo;
import com.elorrieta.entities.Matriculaciones;
import com.elorrieta.entities.User;
import com.elorrieta.tcpEnvios.mensajes.Mensaje;
import com.elorrieta.tcpEnvios.mensajes.parts.FilterParts;

public class TcpAlumnosFilter {
	public static List<User> getAlumnosFiltrados(Ciclo ciclo, Matriculaciones matriculaciones, User profesor) {
	Socket socket = null;
	String ipServer = "10.5.104.109";
	int puertoServer = 49171;

	try
	{
		// Crear el socket para conectarse al servidor
		socket = new Socket(ipServer, puertoServer);
		System.out.println("Cliente - Iniciando conexión con " + ipServer + " en el puerto " + puertoServer);

		// Crear FilterParts con los filtros
		FilterParts filterParts = new FilterParts(ciclo, matriculaciones, profesor);
		
		// Crear ObjectOutputStream para enviar objetos
		ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());

		// Generar el objeto MENSAJE
		Mensaje mensaje = new Mensaje("GET_ALUMNOS_FILTRO", filterParts);

		// Enviar el objeto
		objectOutput.writeObject(mensaje);
		objectOutput.flush();

		System.out.println("usuario enviado:  " + "tipoOP:" + mensaje.getTipoOperacion());

		// recibir respuesta del servidor
		ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
		Mensaje mensajeRespuesta = (Mensaje) objectInput.readObject();
		System.out.println("Cliente - Mensaje recibido del servidor: " + mensajeRespuesta.getContenido().toString());

		// Convertir el CONTENIDO DEL MENSAJE recibido a una lista de usuarios
		List<User> respuestaList = (List<User>) mensajeRespuesta.getContenido();
		for (User user : respuestaList) {
			System.out.println("Cliente - Usuario recibido: " + user.getUsername());
		}

		// Cerrar recursos
		objectOutput.close();
		objectInput.close();
		socket.close();

		// Devolver el usuario recibido
		return respuestaList;

	}catch(
	IOException e)
	{
		System.err.println("Error de E/S: " + e.getMessage());
		e.printStackTrace();
		return null;

	}catch(
	Exception e)
	{
		System.out.println("Cliente - Error: " + e.getMessage());
		return null;
	}
}}
