package com.elorrieta.controladores;

import com.elorrieta.entities.User;
import com.elorrieta.tcpEnvios.mensajes.parts.LoginParts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ControladorJSON {
    
    public static User JSONToUser(String jsonString){
		Gson gson = null;
		User user = null;
		// convert JSON to User object
		try {
			gson = new GsonBuilder().setPrettyPrinting().create();
			user = gson.fromJson(jsonString, User.class);
		}catch (Exception e){
			System.out.println("error");
		}
		// Borrar ficheros JSON usados en el login para que no queden restos
            // ControladorJSON.borrarFicherosJSON("response.json","user.json");
		return user;
	}

	public static String LoginPartsToJSON(LoginParts loginParts){
		Gson gson = null;
		String jsonString = null;
		try {
			gson = new GsonBuilder().setPrettyPrinting().create();
			jsonString = gson.toJson(loginParts);
		}catch (Exception e){
			System.out.println("error");
		}
		return jsonString;
	} 
}
