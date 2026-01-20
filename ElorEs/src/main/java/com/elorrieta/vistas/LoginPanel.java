package com.elorrieta.vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.elorrieta.entities.User;
import com.elorrieta.tcp.TcpLogin;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton registerButton;
	// private JLabel logoLabel;

	public LoginPanel(JFrame frame) {
		frame.setSize(400, 480);
		setLayout(null);
		setBackground(Color.decode("#232637"));

		// Logito
		// logoLabel = new JLabel(new ImageIcon(getClass().getResource("/Logo01.svg")));
		// logoLabel.setBounds(140, 15, 100, 50);
		// add(logoLabel);

		// Etiqueta de título
		titleLabel = new JLabel("<html><b>Inicia Sesión con tu cuenta de SpinningCat</b></html>");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(0, 0, 0, 100)); // Negro con transparencia
		titleLabel.setOpaque(true);
		titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
		titleLabel.setBounds(35, 30, 300, 60);
		add(titleLabel);

		// Campos de texto
		nicknameField = new JTextField();
		nicknameField.setFont(new Font("Arial", Font.PLAIN, 16));
		nicknameField.setBounds(35, 120, 300, 35);
		nicknameField.setBackground(new Color(39, 42, 61));
		nicknameField.setForeground(Color.WHITE);
		nicknameField.setCaretColor(Color.WHITE);
		nicknameField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Nickname", 0, 0,
				null, new Color(180, 180, 180)));
		add(nicknameField);

		// Campo de contraseña
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordField.setBounds(35, 170, 300, 35);
		passwordField.setBackground(new Color(39, 42, 61));
		passwordField.setForeground(Color.WHITE);
		passwordField.setCaretColor(Color.WHITE);
		passwordField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Password", 0, 0,
				null, new Color(180, 180, 180)));
		add(passwordField);

		// Botón de login
		loginButton = new JButton("Log in");
		loginButton.setBounds(35, 270, 300, 45);
		loginButton.setFont(new Font("Arial", Font.BOLD, 18));
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(new Color(255, 98, 0));
		loginButton.setBorderPainted(false);
		loginButton.setFocusPainted(false);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(loginButton);

		// Botón de registro
		registerButton = new JButton("Register");
		registerButton.setBounds(35, 320, 300, 40);
		registerButton.setFont(new Font("Arial", Font.BOLD, 16));
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(new Color(100, 100, 100));
		registerButton.setBorderPainted(false);
		registerButton.setFocusPainted(false);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(registerButton);

		// Hacer que el botón login sea el predeterminado al pulsar Enter
		frame.getRootPane().setDefaultButton(loginButton);

		// Imagen de fondo (está en /resources)
		// ImageIcon fondo = new
		// ImageIcon(getClass().getResource("/BackgroundLogin.png"));
		// JLabel fondoLabel = new JLabel(fondo);
		// fondoLabel.setBounds(0, 0, 400, 400);
		// add(fondoLabel);

		// Texto de "¿Has olvidado tu contraseña?"
		JTextPane txtForgot = new JTextPane();
		txtForgot.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtForgot.setEditable(false);
		txtForgot.setOpaque(false);
		txtForgot.setBackground(new Color(0, 0, 0, 0));
		txtForgot.setText("¿Has olvidado tu contraseña?");
		txtForgot.setBounds(35, 377, 234, 26);
		txtForgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtForgot.setForeground(Color.WHITE);
		add(txtForgot);

		// Evento del botón login
		loginButton.addActionListener(e -> {
			String nickname = nicknameField.getText().trim();
			String pass = new String(passwordField.getPassword());

			if (nickname.isEmpty() || pass.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {

				try {
					User userLogin = TcpLogin.login(nickname, pass);
					if (userLogin == null) {

						JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error",
								JOptionPane.ERROR_MESSAGE);

						return;
					} else {
						JOptionPane.showMessageDialog(this, "Login correcto. ¡Bienvenido/a " + userLogin.getUsername(),
								"Login",
								JOptionPane.INFORMATION_MESSAGE);

					}
				} catch (Exception ex) {
					System.out.println("error en el login tcp");
				}
			}
		});
	}
}