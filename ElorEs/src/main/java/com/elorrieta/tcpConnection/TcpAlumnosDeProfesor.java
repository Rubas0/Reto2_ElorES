package com.elorrieta.tcpConnection;

import java.io.InputStream;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import com.elorrieta.entities.User;
import com.elorrieta.tcpEnvios.mensajes.Mensaje;

public class TcpAlumnosDeProfesor {

	public static List<User> getAlumnosDeProfesor(User profesor) {
        Socket socket = null;
        Properties properties = new Properties();
        // Cargar las propiedades desde el archivo config.properties
        try (InputStream input = TcpAlumnosDeProfesor.class.getClassLoader().getResourceAsStream("config.properties")) {
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
            Mensaje mensaje = new Mensaje("GET_ALUMNOS_PROFESOR", profesor);

            // Enviar el objeto
            objectOutput.writeObject(mensaje);
            objectOutput.flush();

            System.out.println(
                    "usuario enviado:  " + "tipoOP:" + mensaje.getTipoOperacion() + ", Profesor: "
                            + mensaje.getContenido().toString());

            // recibir respuesta del servidor
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
            Mensaje mensajeRespuesta = (Mensaje) objectInput.readObject();
            System.out
                    .println("Cliente - Mensaje recibido del servidor: " + mensajeRespuesta.getContenido().toString());

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
