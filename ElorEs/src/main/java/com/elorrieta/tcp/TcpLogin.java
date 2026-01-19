package com.elorrieta.tcp;

import com.elorrieta.controladores.ControladorJSON;
import com.elorrieta.entities.User;
import com.elorrieta.threads.mensajes.Mensaje;
import com.elorrieta.threads.mensajes.MensajeRespuesta;
import com.elorrieta.threads.mensajes.parts.LoginParts;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TcpLogin {

    public static User login(String nickname, String password) {
        Socket socket = null;
        String ipServer = "127.0.0.1";
        int puertoServer = 49171;
        try {
            // Crear el socket para conectarse al servidor
            socket = new Socket(ipServer, puertoServer);
            System.out.println("Cliente - Iniciando conexión con " + ipServer + " en el puerto " + puertoServer);

            // Crear ObjectOutputStream para enviar objetos
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());

            // Crear un objeto LOGINPARTS
            LoginParts loginParts = new LoginParts(nickname, password);

            // convertir LOGINPARTS a JSON
            String jsonLogin = ControladorJSON.LoginPartsToJSON(loginParts);

            // Generar el objeto MENSAJE
            Mensaje mensaje = new Mensaje("LOGIN", jsonLogin);

            // Enviar el objeto
            objectOutput.writeObject(mensaje);
            objectOutput.flush();

            System.out.println(
                    "usuario enviado:  " + "tipoOP:" + mensaje.getTipoOperacion() + ", json:" + mensaje.getJson());

            // recibir respuesta del servidor

            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
            MensajeRespuesta mensajeRespuesta = (MensajeRespuesta) objectInput.readObject();
            System.out.println("Cliente - Mensaje recibido del servidor: " + mensajeRespuesta.getJson());
            // Convertir el JSON recibido a un objeto User
            User userRespuesta = ControladorJSON.JSONToUser(mensajeRespuesta.getJson());
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