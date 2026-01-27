package com.elorrieta.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import com.elorrieta.entities.User;
import com.elorrieta.tcpConnection.TcpImagenPerfil;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class PerfilPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public PerfilPanel(User profesor, User perfil, JFrame frame) {
		frame.setSize(700, 650);
		frame.setLocationRelativeTo(null);
		setLayout(null);
		setBackground(new Color(240, 240, 240));

		// ========= Título ========== \\
		JLabel lblTitulo = new JLabel("Perfil de Usuario");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(70, 130, 180));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(250, 20, 200, 40);
		add(lblTitulo);

		// ========== Foto de perfil ========== \\
		JLabel lblFoto = new JLabel();
		lblFoto.setBounds(50, 80, 150, 150);
		lblFoto.setBackground(Color.WHITE);
		lblFoto.setOpaque(true);
		lblFoto.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);

		if (perfil.getArgazkiaUrl() != null && !perfil.getArgazkiaUrl().isEmpty()) {
			try {
				lblFoto.setIcon(new ImageIcon(TcpImagenPerfil.getImagenPerfilDeUsuario(perfil)));
			} catch (Exception e) {
				lblFoto.setText("Sin foto");
				lblFoto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				e.printStackTrace();
			}
		} else {
			lblFoto.setText("Sin foto");
			lblFoto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		}
		add(lblFoto);

		// ========== Panel de información del usuario ========== \\
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(null);
		panelInfo.setBackground(Color.WHITE);
		panelInfo.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelInfo.setBounds(220, 80, 430, 400);
		add(panelInfo);

		// ========== Etiquetas de información ========== \\
		int yPosition = 20;
		int spacing = 35;

		JLabel lblNombre = new JLabel("Nombre: " + (perfil.getNombre() != null ? perfil.getNombre() : "N/A"));
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNombre.setBounds(20, yPosition, 390, 25);
		panelInfo.add(lblNombre);
		yPosition += spacing;

		JLabel lblApellidos = new JLabel("Apellidos: " + (perfil.getApellidos() != null ? perfil.getApellidos() : "N/A"));
		lblApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblApellidos.setBounds(20, yPosition, 390, 25);
		panelInfo.add(lblApellidos);
		yPosition += spacing;

		JLabel lblUsername = new JLabel("Usuario: " + (perfil.getUsername() != null ? perfil.getUsername() : "N/A"));
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblUsername.setBounds(20, yPosition, 390, 25);
		panelInfo.add(lblUsername);
		yPosition += spacing;

		JLabel lblEmail = new JLabel("Email: " + (perfil.getEmail() != null ? perfil.getEmail() : "N/A"));
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEmail.setBounds(20, yPosition, 390, 25);
		panelInfo.add(lblEmail);
		yPosition += spacing;

		JLabel lblDni = new JLabel("DNI: " + (perfil.getDni() != null ? perfil.getDni() : "N/A"));
		lblDni.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDni.setBounds(20, yPosition, 390, 25);
		panelInfo.add(lblDni);
		yPosition += spacing;

		JLabel lblDireccion = new JLabel("Dirección: " + (perfil.getDireccion() != null ? perfil.getDireccion() : "N/A"));
		lblDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDireccion.setBounds(20, yPosition, 390, 25);
		panelInfo.add(lblDireccion);
		yPosition += spacing;

		JLabel lblTelefono1 = new JLabel("Teléfono 1: " + (perfil.getTelefono1() != null ? perfil.getTelefono1() : "N/A"));
		lblTelefono1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTelefono1.setBounds(20, yPosition, 390, 25);
		panelInfo.add(lblTelefono1);
		yPosition += spacing;

		JLabel lblTelefono2 = new JLabel("Teléfono 2: " + (perfil.getTelefono2() != null ? perfil.getTelefono2() : "N/A"));
		lblTelefono2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTelefono2.setBounds(20, yPosition, 390, 25);
		panelInfo.add(lblTelefono2);
		yPosition += spacing;

		JLabel lblTipo = new JLabel("Tipo de usuario: " + (perfil.getTipo() != null ? perfil.getTipo().getName() : "N/A"));
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTipo.setBounds(20, yPosition, 390, 25);
		panelInfo.add(lblTipo);

		// ========= Botón Volver ========== \\
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(70, 130, 180));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(275, 550, 150, 35);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new MenuPanel(profesor, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		add(btnNewButton);

		// ========== Efecto hover del botón ========== \\
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnNewButton.setBackground(new Color(100, 149, 237));
			}

			public void mouseExited(MouseEvent evt) {
				btnNewButton.setBackground(new Color(70, 130, 180));
			}
		});

		// ========== Configuración de la tecla Escape para volver ========== \\
		InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "volver");
		actionMap.put("volver", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				btnNewButton.doClick();
			}
		});
	}
}
