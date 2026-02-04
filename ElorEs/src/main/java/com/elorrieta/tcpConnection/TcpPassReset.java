package com.elorrieta.tcpConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.elorrieta.tcpEnvios.mensajes.Mensaje;

public class TcpPassReset {

	public static void resetPasswd(String email) {
		Socket socket = null;
		Properties properties = new Properties();
		// Cargar las propiedades desde el archivo config.properties
		try (InputStream input = TcpPassReset.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				throw new IOException("Archivo config.properties no encontrado");
			}
			properties.load(input);
		} catch (IOException e) {
			System.err.println("Error al cargar config.properties: " + e.getMessage());
			e.printStackTrace();
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
			Mensaje mensaje = new Mensaje("RESET_PASSWORD", email);

			// Enviar el objeto
			objectOutput.writeObject(mensaje);
			objectOutput.flush();

			System.out.println("Mensaje enviado:  " + "tipoOP:" + mensaje.getTipoOperacion() + ", contenido: "
					+ mensaje.getContenido().toString());
			// Cerrar recursos
			objectOutput.close();
			socket.close();
			
			JOptionPane.showMessageDialog(null, "Si el correo aportado se encuentra vinculado a una cuenta existente, se enviará un email con una nueva contraseña temporal.", "Info",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (IOException e) {
			System.err.println("Error de E/S: " + e.getMessage());
			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("Cliente - Error: " + e.getMessage());
		}
	}
}