package com.elorrieta.tcpConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import com.elorrieta.entities.User;
import com.elorrieta.tcpEnvios.mensajes.Mensaje;

public class TcpProfesores {

	public static List<User> obtenerProfesores() {
		Socket socket = null;
		Properties properties = new Properties();
		// Cargar las propiedades desde el archivo config.properties
		try (InputStream input = TcpProfesores.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				throw new IOException("Archivo config.properties no encontrado");
			}
			properties.load(input);
		} catch (IOException e) {
			System.err.println("Error al cargar config.properties: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		String ipServer = properties.getProperty("tcp.server.ip");
		int puertoServer = Integer.parseInt(properties.getProperty("tcp.server.port"));
		try {
			// Crear el socket para conectarse al servidor
			socket = new Socket(ipServer, puertoServer);
			System.out.println("Cliente - Iniciando conexión con " + ipServer + " en el puerto " + puertoServer);

			// Crear ObjectOutputStream para enviar objetos
			ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());

			// Generar el objeto MENSAJE
			Mensaje mensaje = new Mensaje("GET_PROFESORES", null);

			// Enviar el objeto
			objectOutput.writeObject(mensaje);
			objectOutput.flush();

			System.out.println("mensaje enviado:  " + "tipoOP:" + mensaje.getTipoOperacion());

			// recibir respuesta del servidor
			ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
			Mensaje mensajeRespuesta = (Mensaje) objectInput.readObject();
			System.out.println("Cliente - Mensaje recibido del servidor");

			// Convertir el CONTENIDO DEL MENSAJE recibido a un objeto User
			List<User> userList = (List<User>) mensajeRespuesta.getContenido();
			for (User user : userList) {
				System.out.println("Cliente - Usuario recibido: " + user.getUsername());
			}

			// Cerrar recursos
			objectOutput.close();
			objectInput.close();
			socket.close();

			// Devolver el usuario recibido
			return userList;

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
