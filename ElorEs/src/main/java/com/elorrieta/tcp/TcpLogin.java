package com.elorrieta.tcp;

import com.elorrieta.bbdd.entidades.User;
import com.elorrieta.controladores.ControladorJSON;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;

public class TcpLogin {

    public static void main(String[] args) {
        Socket socket = null;
        String ipServer = "127.0.0.1";
        int puertoServer = 49171;
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            // Crear el socket para conectarse al servidor
            System.out.println("Cliente - Iniciando conexión con " + ipServer + " en el puerto " + puertoServer);
            socket = new Socket(ipServer, puertoServer);

            // Crear un objeto User con las credenciales de inicio de sesión (mas a delante se recibira por parametro en esta clase)
            User user = new User("goduser", "123456");
            ControladorJSON.UserToJSON(user);

            // enviar el archivo JSON al servidor
            fis = new FileInputStream("user.json");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            socket.getOutputStream().write(buffer);
            socket.getOutputStream().flush();
            fis.close();
            System.out.println("Cliente - Archivo JSON enviado al servidor.");

            //recibir respuesta del servidor

            byte[] response = new byte[1024];
            int bytesRead = socket.getInputStream().read(response);
            fos = new FileOutputStream("response.json");
            fos.write(response, 0, bytesRead);
            fos.close();
            System.out.println("Cliente - Respuesta recibida del servidor.");

//            fos = new FileOutputStream("user.json");
//            socket.getOutputStream().read();
//            fos.close();
//            System.out.println("Cliente - Respuesta recibida del servidor.");


        } catch (Exception e) {
            System.out.println("Cliente - Error: " + e.getMessage());
        }
    }
}
