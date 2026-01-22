package com.elorrieta.vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.elorrieta.entities.User;
import com.elorrieta.tcp.TcpLogin;

public class LoginPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton loginButton;

	public LoginPanel(JFrame frame) {
		frame.setSize(400, 430);
		setLayout(null);
		setBackground(new Color(240, 240, 240));

		// Título
		titleLabel = new JLabel("Inicia Sesión");
		titleLabel.setForeground(new Color(70, 130, 180));
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(50, 30, 300, 40);
		add(titleLabel);

		// Panel blanco para los campos
		JPanel panelCampos = new JPanel();
		panelCampos.setLayout(null);
		panelCampos.setBackground(Color.WHITE);
		panelCampos.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelCampos.setBounds(35, 90, 320, 220);
		add(panelCampos);

		// Campo de nickname
		JLabel lblNickname = new JLabel("Usuario");
		lblNickname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNickname.setBounds(10, 15, 280, 20);
		panelCampos.add(lblNickname);

		nicknameField = new JTextField();
		nicknameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		nicknameField.setBounds(10, 40, 280, 35);
		nicknameField.setBackground(new Color(245, 245, 245));
		nicknameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
		panelCampos.add(nicknameField);

		// Campo de password
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPassword.setBounds(10, 90, 280, 20);
		panelCampos.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passwordField.setBounds(10, 115, 280, 35);
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
		panelCampos.add(passwordField);

		// Texto "¿Has olvidado tu contraseña?"
		JTextPane txtForgot = new JTextPane();
		txtForgot.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtForgot.setEditable(false);
		txtForgot.setOpaque(false);
		txtForgot.setText("¿Has olvidado tu contraseña?");
		txtForgot.setBounds(10, 160, 280, 25);
		txtForgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
		txtForgot.setForeground(new Color(70, 130, 180));
		panelCampos.add(txtForgot);

		// Botón de login
		loginButton = new JButton("Iniciar Sesión");
		loginButton.setBounds(35, 330, 320, 40);
		loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(new Color(70, 130, 180));
		loginButton.setBorderPainted(false);
		loginButton.setFocusPainted(false);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(loginButton);

		// Efecto hover del botón login
		loginButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				loginButton.setBackground(new Color(100, 149, 237));
			}

			public void mouseExited(MouseEvent evt) {
				loginButton.setBackground(new Color(70, 130, 180));
			}
		});

		// Hacer que el botón login sea el predeterminado al pulsar Enter
		frame.getRootPane().setDefaultButton(loginButton);

		// Acción del texto "¿Has olvidado tu contraseña?"
		txtForgot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setContentPane(new ResetPasswdPanel(frame));
				frame.revalidate();
				frame.repaint();
			}
		});

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
								"Login", JOptionPane.INFORMATION_MESSAGE);
						frame.setContentPane(new MenuPanel(userLogin, frame));
						frame.revalidate();
						frame.repaint();
					}
				} catch (Exception ex) {
					System.out.println("error en el login tcp");
				}
			}
		});
	}
}
