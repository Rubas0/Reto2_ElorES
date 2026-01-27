package com.elorrieta.tcpConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.elorrieta.entities.Horario;
import com.elorrieta.entities.User;
import com.elorrieta.tcpEnvios.mensajes.Mensaje;
import com.elorrieta.tcpEnvios.mensajes.parts.FilterParts;

public class TcpHorarioProfesor {

	public static List<Horario> getHorarioProfesor(User profesor) {
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
			Mensaje mensaje = new Mensaje("GET_HORARIO_PROFESOR", profesor);

			// Enviar el objeto
			objectOutput.writeObject(mensaje);
			objectOutput.flush();

			System.out.println("profesor enviado:  " + "tipoOP:" + mensaje.getTipoOperacion());

			// recibir respuesta del servidor
			ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
			Mensaje mensajeRespuesta = (Mensaje) objectInput.readObject();
			System.out
					.println("Cliente - Mensaje recibido del servidor: " + mensajeRespuesta.getContenido().toString());

			// Convertir el CONTENIDO DEL MENSAJE recibido a una lista de horarios
			List<Horario> respuestaList = (List<Horario>) mensajeRespuesta.getContenido();
			for (Horario horario : respuestaList) {
				System.out.println("Cliente - Horario recibido: " + horario.getModulo().getNombre());
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
