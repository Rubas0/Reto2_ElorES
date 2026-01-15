package com.elorrieta.controladores;

import com.elorrieta.bbdd.entidades.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;

public class ControladorJSON {
    public static void UserToJSON(User user){
        Gson gson = null;
        // convert User object to JSON
        try {
            gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter("user.json");
            gson.toJson(user, writer);
            writer.flush();
            writer.close();
        }catch (Exception e){
            System.out.println("error");
        }
    }
    
    public static User JSONToUser(String filename){
		Gson gson = null;
		User user = null;
		// convert JSON to User object
		try {
			gson = new GsonBuilder().setPrettyPrinting().create();
			java.io.Reader reader = new java.io.FileReader(filename);
			user = gson.fromJson(reader, User.class);
			reader.close();
		}catch (Exception e){
			System.out.println("error");
		}
		return user;
	}
    
    public static void borrarFicherosJSON(String... filenames) { // Uso de varargs para múltiples ficheros
		for (String filename : filenames) {
			java.io.File file = new java.io.File(filename);
			if (file.exists()) {
				if (file.delete()) {
					System.out.println("Fichero " + filename + " borrado correctamente.");
				} else {
					System.out.println("No se pudo borrar el fichero " + filename + ".");
				}
			} else {
				System.out.println("El fichero " + filename + " no existe.");
			}
		}
	}
}
