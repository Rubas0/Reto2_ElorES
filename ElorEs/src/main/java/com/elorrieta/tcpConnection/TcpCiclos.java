package com.elorrieta.tcpConnection;

import java.util.List;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.elorrieta.entities.Ciclo;
import com.elorrieta.tcpEnvios.mensajes.Mensaje;

public class TcpCiclos {
	public static List<Ciclo> getCiclos() {
		Socket socket = null;
		String ipserver = "10.5.104.110";
		int puertoServer = 49171;
		try {
			// Crear el socket para conectarse al servidor
			socket = new Socket(ipserver, puertoServer);
			System.out.println("Cliente - Iniciando conexión con " + ipserver + " en el puerto " + puertoServer);
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
