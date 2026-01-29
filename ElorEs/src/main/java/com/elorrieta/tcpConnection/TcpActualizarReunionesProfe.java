package com.elorrieta.tcpConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.elorrieta.entities.Reuniones;
import com.elorrieta.entities.User;
import com.elorrieta.tcpEnvios.mensajes.Mensaje;
import com.elorrieta.tcpEnvios.mensajes.parts.ReunionesParts;

public class TcpActualizarReunionesProfe {

	public static void actualizarReuniones(List<Reuniones> reuniones, User profe) {
		Socket socket = null;
		String ipServer = "10.5.104.110";
		int puertoServer = 49171;

		try {
			// Crear el socket para conectarse al servidor
			socket = new Socket(ipServer, puertoServer);
			System.out.println("Cliente - Iniciando conexión con " + ipServer + " en el puerto " + puertoServer);

			// Crear ReunionesParts
			ReunionesParts reunionesParts = new ReunionesParts(reuniones, profe);

			// Crear ObjectOutputStream para enviar objetos
			ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());

			// Generar el objeto MENSAJE
			Mensaje mensaje = new Mensaje("UPDATE_REUNIONES_PROFESOR", reunionesParts);

			// Enviar el objeto
			objectOutput.writeObject(mensaje);
			objectOutput.flush();

			System.out.println("Mensaje enviado:  " + "tipoOP:" + mensaje.getTipoOperacion());

			// recibir respuesta del servidor
			ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
			Mensaje mensajeRespuesta = (Mensaje) objectInput.readObject();
			System.out
					.println("Cliente - Mensaje recibido del servidor: " + mensajeRespuesta.getTipoOperacion());

			// Cerrar recursos
			objectOutput.close();
			objectInput.close();
			socket.close();

		} catch (IOException e) {
			System.err.println("Error de E/S: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Cliente - Error: " + e.getMessage());
		}

	}

}
