package com.elorrieta.tcpConnection;

import com.elorrieta.entities.User;
import com.elorrieta.tcpEnvios.mensajes.Mensaje;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.elorrieta.entities.Reuniones;

public class TcpReunionesProfe {

	public static java.util.List<Reuniones> getReunionesDeProfesor(User profesor) {
		Socket socket = null;
		String ipServer = "10.5.104.110";
		int puertoServer = 49171;

		try {
			// Crear el socket para conectarse al servidor
			socket = new Socket(ipServer, puertoServer);
			System.out.println("Cliente - Iniciando conexión con " + ipServer + " en el puerto " + puertoServer);

			// Crear ObjectOutputStream para enviar objetos
			ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());

			// Generar el objeto MENSAJE
			Mensaje mensaje = new Mensaje("GET_REUNIONES_PROFESOR", profesor);

			// Enviar el objeto
			objectOutput.writeObject(mensaje);
			objectOutput.flush();

			System.out.println("usuario enviado:  " + "tipoOP:" + mensaje.getTipoOperacion() + ", Profesor: "
					+ profesor.getNombre());

			// recibir respuesta del servidor
			ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
			Mensaje mensajeRespuesta = (Mensaje) objectInput.readObject();
			System.out
					.println("Cliente - Mensaje recibido del servidor: " + mensajeRespuesta.getContenido().toString());

			// Convertir el CONTENIDO DEL MENSAJE recibido a una lista de usuarios
			List<Reuniones> respuestaList = (List<Reuniones>) mensajeRespuesta.getContenido();
			for (Reuniones reunion : respuestaList) {
				System.out.println("Cliente - Reunion recibida: " + reunion.getTitulo());
			}

			// Cerrar recursos
			objectOutput.close();
			objectInput.close();
			socket.close();

			// Devolver el usuario recibido
			return respuestaList;

		} catch (IOException e) {
			System.err.println("Error de E/S: " + e.getMessage());
			e.printStackTrace();
			return null;

		} catch (Exception e) {
			System.out.println("Cliente - Error: " + e.getMessage());
			return null;
		}
	}
}
