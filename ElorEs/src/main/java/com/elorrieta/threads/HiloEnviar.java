// package com.elorrieta.threads;

// import java.io.IOException;
// import java.io.ObjectOutputStream;
// import java.net.Socket;

// public class HiloEnviar {
//     Socket socket;

//     public HiloEnviar(Socket socket) {
//         this.socket = socket;
//     }

//     public void run() {
//          String host = "localhost";
//         int port = 8080;
        
//         try (Socket socket = new Socket(host, port)) {
//             System.out.println("Conectado al servidor en " + host + ":" + port);
            
//             // Crear ObjectOutputStream para enviar objetos
//             ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            
//             // Crear un objeto para enviar
//             Persona persona = new Persona("Juan", 25);
            
//             // Enviar el objeto
//             objectOutput.writeObject(persona);
//             objectOutput.flush();
            
//             System.out.println("Objeto enviado:  " + persona);
            
//             // Cerrar recursos
//             objectOutput.close();
            
//         } catch (IOException e) {
//             System.err.println("Error de E/S: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }
// }
