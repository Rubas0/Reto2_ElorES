package com.elorrieta.vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.elorrieta.entities.User;
import com.elorrieta.tcpConnection.TcpProfesores;

public class ConsultarOtrosHorariosPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ConsultarOtrosHorariosPanel(User user, JFrame frame) {
		frame.setSize(750, 550);
		frame.setLocationRelativeTo(null);
		setLayout(null);
		setBackground(new Color(240, 240, 240));

		// ========== Título ========== \\
		JLabel lblTitulo = new JLabel("Consultar Otros Horarios");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblTitulo.setForeground(new Color(70, 130, 180));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(50, 40, 650, 45);
		add(lblTitulo);

		// ========== Subtítulo ========== \\
		JLabel lblSubtitulo = new JLabel("Selecciona un profesor para ver su horario");
		lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblSubtitulo.setForeground(new Color(100, 100, 100));
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setBounds(50, 85, 650, 25);
		add(lblSubtitulo);

		// ========== Panel blanco para el selector ========== \\
		JPanel panelSelector = new JPanel();
		panelSelector.setLayout(null);
		panelSelector.setBackground(Color.WHITE);
		panelSelector.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelSelector.setBounds(75, 140, 600, 140);
		add(panelSelector);

		// ========== Etiqueta para el ComboBox ========== \\
		JLabel lblProfesor = new JLabel("Profesor:");
		lblProfesor.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblProfesor.setForeground(new Color(70, 130, 180));
		lblProfesor.setBounds(20, 25, 200, 30);
		panelSelector.add(lblProfesor);

		// ========== ComboBox de profesores ========== \\
		JComboBox<String> comboProfesores = new JComboBox<>();
		comboProfesores.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboProfesores.setBackground(Color.WHITE);
		comboProfesores.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboProfesores.setBounds(20, 65, 560, 40);
		panelSelector.add(comboProfesores);

		// ========== Cargar profesores en el ComboBox ========== \\
		List<User> profesores = TcpProfesores.obtenerProfesores();

		// crear una nueva lista sin el usuario actual
		List<User> profesoresFiltrados = new ArrayList<User>();
		if (profesores != null) {
			for (User profesor : profesores) {
				if (!profesor.getUsername().equals(user.getUsername())) {
					profesoresFiltrados.add(profesor);
				}
			}
		}

		comboProfesores.addItem("Selecciona un profesor...");
		for (User profesor : profesoresFiltrados) {
			String nombreCompleto = profesor.getNombre() + " " + profesor.getApellidos();
			comboProfesores.addItem(nombreCompleto);
		}

		// ========== Panel de botones ========== \\
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(null);
		panelBotones.setBackground(new Color(240, 240, 240));
		panelBotones.setBounds(75, 320, 600, 120);
		add(panelBotones);

		// ========== Botón Confirmar ========== \\
		JButton btnConfirmar = new JButton("Confirmar Selección");
		btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnConfirmar.setBackground(new Color(70, 130, 180));
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setFocusPainted(false);
		btnConfirmar.setBorderPainted(false);
		btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnConfirmar.setBounds(180, 20, 240, 45);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = comboProfesores.getSelectedIndex();
				if (selectedIndex > 0) {
					User profesorSeleccionado = profesoresFiltrados.get(selectedIndex - 1);
					frame.setContentPane(new ConsultarHorarioPanel(user, profesorSeleccionado, frame));
					frame.revalidate();
					frame.repaint();
				} else {
					JOptionPane.showMessageDialog(frame, "Por favor, selecciona un profesor.", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(btnConfirmar);

		// ========== Efecto hover del botón Confirmar ========== \\
		btnConfirmar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnConfirmar.setBackground(new Color(100, 149, 237));
			}

			public void mouseExited(MouseEvent evt) {
				btnConfirmar.setBackground(new Color(70, 130, 180));
			}
		});

		// ========== Botón Volver ========== \\
		JButton btnVolver = new JButton("Volver al Menú");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVolver.setBackground(new Color(120, 120, 120));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(220, 75, 160, 40);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new MenuPanel(user, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		panelBotones.add(btnVolver);

		// ========== Efecto hover del botón Volver ========== \\
		btnVolver.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnVolver.setBackground(new Color(150, 150, 150));
			}

			public void mouseExited(MouseEvent evt) {
				btnVolver.setBackground(new Color(120, 120, 120));
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
				btnVolver.doClick();
			}
		});
	}
}
