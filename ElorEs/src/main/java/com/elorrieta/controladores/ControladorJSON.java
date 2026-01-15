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
}
