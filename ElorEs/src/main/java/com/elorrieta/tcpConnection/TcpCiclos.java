package com.elorrieta.tcpConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import com.elorrieta.entities.Ciclo;
import com.elorrieta.tcpEnvios.mensajes.Mensaje;

public class TcpCiclos {
	public static List<Ciclo> getCiclos() {
		Socket socket = null;
		Properties properties = new Properties();
		// Cargar las propiedades desde el archivo config.properties
		try (InputStream input = TcpCiclos.class.getClassLoader().getResourceAsStream("config.properties")) {
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
			// Crear el objeto MENSAJE
			Mensaje mensaje = new Mensaje("GET_CICLOS", null);
			// Enviar el objeto
			objectOutput.writeObject(mensaje);
			objectOutput.flush();
			System.out.println("Mensaje enviado:  " + "tipoOP:" + mensaje.getTipoOperacion() + ", contenido: "
					+ mensaje.getContenido());
			// recibir respuesta del servidor
			ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
			Mensaje mensajeRespuesta = (Mensaje) objectInput.readObject();
			List<Ciclo> respuestaList = (List<Ciclo>) mensajeRespuesta.getContenido();
			
			//cerrar recursos
			objectOutput.close();
			objectInput.close();
			socket.close();
			return respuestaList;
			
		} catch (Exception e) {
			return null;
		}
	}
}
