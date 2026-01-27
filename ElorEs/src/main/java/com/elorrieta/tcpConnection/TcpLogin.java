package com.elorrieta.tcpConnection;

import com.elorrieta.entities.User;
import com.elorrieta.tcpEnvios.mensajes.Mensaje;
import com.elorrieta.tcpEnvios.mensajes.parts.LoginParts;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TcpLogin {

    public static User login(String nickname, String password) {
        Socket socket = null;
        String ipServer = "10.5.104.110";
        int puertoServer = 49171;
        try {
            // Crear el socket para conectarse al servidor
            socket = new Socket(ipServer, puertoServer);
            System.out.println("Cliente - Iniciando conexión con " + ipServer + " en el puerto " + puertoServer);

            // Crear ObjectOutputStream para enviar objetos
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());

            // Crear un objeto LOGINPARTS
            LoginParts loginParts = new LoginParts(nickname, password);

            // Generar el objeto MENSAJE
            Mensaje mensaje = new Mensaje("LOGIN", loginParts);

            // Enviar el objeto
            objectOutput.writeObject(mensaje);
            objectOutput.flush();

            System.out.println(
                    "usuario enviado:  " + "tipoOP:" + mensaje.getTipoOperacion() + ", loginparts: "
                            + mensaje.getContenido().toString());

            // recibir respuesta del servidor
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
            Mensaje mensajeRespuesta = (Mensaje) objectInput.readObject();
            System.out
                    .println("Cliente - Mensaje recibido del servidor: " + mensajeRespuesta.getContenido().toString());

            // Convertir el CONTENIDO DEL MENSAJE recibido a un objeto User
            User userRespuesta = (User) mensajeRespuesta.getContenido();
            System.out.println("Cliente - Usuario recibido: " + userRespuesta.getUsername());

            // Cerrar recursos
            objectOutput.close();
            objectInput.close();
            socket.close();

            // Devolver el usuario recibido
            return userRespuesta;

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