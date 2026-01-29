package com.elorrieta;

import java.awt.Image;

import javax.swing.*;

import com.elorrieta.vistas.LoginPanel;

public class ElorEs extends JFrame {

	private static final long serialVersionUID = 1L;
	private LoginPanel loginPanel;

	public ElorEs() {
		setTitle("ElorEs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);

		// Establecer el icono de la aplicación
		try {
			ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("ElorLogo.png"));
			Image imagen = icono.getImage();
			setIconImage(imagen);
		} catch (Exception e) {
			System.err.println("No se pudo cargar el icono: " + e.getMessage());
		}

		loginPanel = new LoginPanel(this); // Le pasamos la referencia
		setContentPane(loginPanel);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new ElorEs();
		});

	}

}
