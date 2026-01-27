package com.elorrieta.vistas;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.elorrieta.entities.User;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public MenuPanel(User profesor, JFrame frame) {
		frame.setSize(600, 550);
		frame.setLocationRelativeTo(null);
		setLayout(null);
		setBackground(new Color(240, 240, 240));

		// ========== Título de bienvenida mejorado ========== \\
		JLabel lblTitulo = new JLabel("Bienvenido/a");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblTitulo.setForeground(new Color(70, 130, 180));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(50, 30, 500, 40);
		add(lblTitulo);

		JLabel lblUsername = new JLabel(
				profesor.getNombre() + " " + (profesor.getApellidos() != null ? profesor.getApellidos() : ""));
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblUsername.setForeground(new Color(100, 100, 100));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(50, 70, 500, 30);
		add(lblUsername);

		// ========== Panel blanco para los botones ========== \\
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(null);
		panelBotones.setBackground(Color.WHITE);
		panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelBotones.setBounds(50, 120, 500, 330);
		add(panelBotones);

		// ========== Botones de navegación ========== \\

		// ========== Ver Perfil ========== \\
		JButton btnPerfil = new JButton("Ver Perfil");
		btnPerfil.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnPerfil.setBackground(new Color(70, 130, 180));
		btnPerfil.setForeground(Color.WHITE);
		btnPerfil.setFocusPainted(false);
		btnPerfil.setBorderPainted(false);
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new PerfilPanel(profesor, profesor, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		btnPerfil.setBounds(20, 20, 210, 40);
		panelBotones.add(btnPerfil);

		// ========== Consultar Alumnos ========== \\
		JButton btnVerAlumnos = new JButton("Consultar Alumnos");
		btnVerAlumnos.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVerAlumnos.setBackground(new Color(70, 130, 180));
		btnVerAlumnos.setForeground(Color.WHITE);
		btnVerAlumnos.setFocusPainted(false);
		btnVerAlumnos.setBorderPainted(false);
		btnVerAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new ConsultarAlumnosPanel(profesor, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		btnVerAlumnos.setBounds(20, 75, 210, 40);
		panelBotones.add(btnVerAlumnos);

		// ========== Consultar Horario ========== \\
		JButton btnVerHorario = new JButton("Consultar Horario");
		btnVerHorario.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVerHorario.setBackground(new Color(70, 130, 180));
		btnVerHorario.setForeground(Color.WHITE);
		btnVerHorario.setFocusPainted(false);
		btnVerHorario.setBorderPainted(false);
		btnVerHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new ConsultarHorarioPanel(profesor, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		btnVerHorario.setBounds(20, 130, 210, 40);
		panelBotones.add(btnVerHorario);

		// ========== Consultar Otros Horarios ========== \\
		JButton btnVerOtrosHorarios = new JButton("Consultar otros Horarios");
		btnVerOtrosHorarios.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVerOtrosHorarios.setBackground(new Color(70, 130, 180));
		btnVerOtrosHorarios.setForeground(Color.WHITE);
		btnVerOtrosHorarios.setFocusPainted(false);
		btnVerOtrosHorarios.setBorderPainted(false);
		btnVerOtrosHorarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new ConsultarOtrosHorariosPanel(profesor, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		btnVerOtrosHorarios.setBounds(250, 20, 230, 40);
		panelBotones.add(btnVerOtrosHorarios);

		// ========== Gestionar Reuniones ========== \\
		JButton btnGestionarReuniones = new JButton("Gestionar Reuniones");
		btnGestionarReuniones.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnGestionarReuniones.setBackground(new Color(70, 130, 180));
		btnGestionarReuniones.setForeground(Color.WHITE);
		btnGestionarReuniones.setFocusPainted(false);
		btnGestionarReuniones.setBorderPainted(false);
		btnGestionarReuniones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new GestionarReunionesPanel(profesor, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		btnGestionarReuniones.setBounds(250, 75, 230, 40);
		panelBotones.add(btnGestionarReuniones);

		// ========== Cerrar Sesión ========== \\
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCerrarSesion.setBackground(new Color(255, 152, 0));
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setBorderPainted(false);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new LoginPanel(frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		btnCerrarSesion.setBounds(20, 210, 460, 40);
		panelBotones.add(btnCerrarSesion);

		// ========== Salir ========== \\
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSalir.setBackground(new Color(220, 53, 69));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(20, 265, 460, 40);
		panelBotones.add(btnSalir);

		// ========== Efecto hover de los botones ========== \\
		JButton[] botones = { btnPerfil, btnVerAlumnos, btnVerHorario, btnVerOtrosHorarios, btnGestionarReuniones,
				btnCerrarSesion, btnSalir };
		for (JButton boton : botones) {
			boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					boton.setBackground(new Color(100, 149, 237));
				}

				public void mouseExited(java.awt.event.MouseEvent evt) {
					boton.setBackground(new Color(70, 130, 180));
				}
			});
		}

		// =========== Efecto hover para botón cerrar sesión ==========\ \
		btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnCerrarSesion.setBackground(new Color(255, 167, 38));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnCerrarSesion.setBackground(new Color(255, 152, 0));
			}
		});

		// ========== Efecto hover para botón salir ==========\ \
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnSalir.setBackground(new Color(200, 35, 51));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnSalir.setBackground(new Color(220, 53, 69));
			}
		});

		// ========== Configuración de la tecla Escape para cerrar sesión ========== \\
		InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "volver");
		actionMap.put("volver", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				btnCerrarSesion.doClick();
			}
		});
	}
}