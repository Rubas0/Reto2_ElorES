package com.elorrieta.tcpConnection;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.imageio.ImageIO;

import com.elorrieta.entities.User;
import com.elorrieta.tcpEnvios.mensajes.Mensaje;

public class TcpImagenPerfil {

	public static Image getImagenPerfilDeUsuario(User usuario) {
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
			Mensaje mensaje = new Mensaje("GET_PROFILE_IMG", usuario);

			// Enviar el objeto
			objectOutput.writeObject(mensaje);
			objectOutput.flush();

			System.out.println("usuario enviado:  " + "tipoOP:" + mensaje.getTipoOperacion());

			// recibir respuesta del servidor
			ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
			Mensaje mensajeRespuesta = (Mensaje) objectInput.readObject();
			System.out
					.println("Cliente - Mensaje recibido del servidor: " + mensajeRespuesta.getContenido().toString());

			// Convertir el CONTENIDO DEL MENSAJE recibido a un bytes[]
			byte[] respuesta = (byte[]) mensajeRespuesta.getContenido();
			// Convertir bytes a Imagen
			ByteArrayInputStream bais = new ByteArrayInputStream(respuesta);
			BufferedImage imagenPerfil = ImageIO.read(bais);
			// Escalar la imagen al tamaño deseado
			Image pfp = imagenPerfil.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			// Cerrar recursos
			objectOutput.close();
			objectInput.close();
			bais.close();
			socket.close();

			// Devolver el usuario recibido
			return pfp;

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
