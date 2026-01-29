package com.elorrieta.vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.elorrieta.entities.User;

public class NuevaReunionPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public NuevaReunionPanel(User user, JFrame frame) {
		frame.setSize(700, 650);
		frame.setLocationRelativeTo(null);
		setLayout(null);
		setBackground(new Color(240, 240, 240));

		// ========== Título ========== \\
		JLabel lblTitulo = new JLabel("Nueva Reunión");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(0, 123, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(200, 20, 300, 40);
		add(lblTitulo);

		// ========== Panel blanco para los campos ========== \\
		JPanel panelCampos = new JPanel();
		panelCampos.setLayout(null);
		panelCampos.setBackground(Color.WHITE);
		panelCampos.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelCampos.setBounds(50, 80, 600, 420);
		add(panelCampos);

		// ========== Título ========== \\
		JLabel lblTituloReunion = new JLabel("Título:");
		lblTituloReunion.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTituloReunion.setBounds(20, 20, 250, 25);
		panelCampos.add(lblTituloReunion);

		JTextField txtTitulo = new JTextField();
		txtTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTitulo.setBounds(20, 50, 560, 35);
		panelCampos.add(txtTitulo);

		// ========== Día ========== \\
		JLabel lblDia = new JLabel("Día:");
		lblDia.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDia.setBounds(20, 100, 250, 25);
		panelCampos.add(lblDia);

		String[] dias = { "Selecciona un día...", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" };
		JComboBox<String> comboDia = new JComboBox<>(dias);
		comboDia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboDia.setBackground(Color.WHITE);
		comboDia.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboDia.setBounds(20, 130, 270, 35);
		panelCampos.add(comboDia);

		// ========== Hora ========== \\
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHora.setBounds(310, 100, 250, 25);
		panelCampos.add(lblHora);

		String[] horas = { "Selecciona una hora...", "Hora 1", "Hora 2", "Hora 3", "Hora 4", "Hora 5", "Hora 6" };
		JComboBox<String> comboHora = new JComboBox<>(horas);
		comboHora.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboHora.setBackground(Color.WHITE);
		comboHora.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboHora.setBounds(310, 130, 270, 35);
		panelCampos.add(comboHora);

		// ========== Semana ========== \\
		JLabel lblSemana = new JLabel("Semana:");
		lblSemana.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSemana.setBounds(20, 180, 250, 25);
		panelCampos.add(lblSemana);

		String[] semanas = { "Selecciona una semana...", "Semana 1", "Semana 2", "Semana 3" };
		JComboBox<String> comboSemana = new JComboBox<>(semanas);
		comboSemana.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboSemana.setBackground(Color.WHITE);
		comboSemana.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboSemana.setBounds(20, 210, 560, 35);
		panelCampos.add(comboSemana);

		// ========== Alumno ========== \\
		JLabel lblAlumno = new JLabel("Alumno:");
		lblAlumno.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblAlumno.setBounds(20, 260, 250, 25);
		panelCampos.add(lblAlumno);

		JComboBox<String> comboAlumno = new JComboBox<>();
		comboAlumno.addItem("Selecciona un alumno...");
		// Aquí deberías cargar los alumnos desde TcpAlumnos
		comboAlumno.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboAlumno.setBackground(Color.WHITE);
		comboAlumno.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboAlumno.setBounds(20, 290, 560, 35);
		panelCampos.add(comboAlumno);

		// ========== Centro ========== \\
		JLabel lblCentro = new JLabel("Centro:");
		lblCentro.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCentro.setBounds(20, 340, 250, 25);
		panelCampos.add(lblCentro);

		String[] centros = { "Selecciona un centro...", "Elorrieta-Erreka Mari", "Armeria", "Otro" };
		JComboBox<String> comboCentro = new JComboBox<>(centros);
		comboCentro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboCentro.setBackground(Color.WHITE);
		comboCentro.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboCentro.setBounds(20, 370, 560, 35);
		panelCampos.add(comboCentro);

		// ========== Botón Crear Reunión ========== \\
		JButton btnCrear = new JButton("Crear Reunión");
		btnCrear.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCrear.setBackground(new Color(40, 167, 69));
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFocusPainted(false);
		btnCrear.setBorderPainted(false);
		btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCrear.setBounds(190, 520, 150, 40);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Validar campos
				if (txtTitulo.getText().trim().isEmpty() || comboDia.getSelectedIndex() == 0
						|| comboHora.getSelectedIndex() == 0 || comboSemana.getSelectedIndex() == 0
						|| comboAlumno.getSelectedIndex() == 0 || comboCentro.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(frame, "Por favor, completa todos los campos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// Aquí implementar la lógica para crear la reunión
					JOptionPane.showMessageDialog(frame, "Reunión creada correctamente", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					frame.setContentPane(new GestionarReunionesPanel(user, frame));
					frame.revalidate();
					frame.repaint();
				}
			}
		});
		add(btnCrear);

		btnCrear.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnCrear.setBackground(new Color(52, 195, 85));
			}

			public void mouseExited(MouseEvent evt) {
				btnCrear.setBackground(new Color(40, 167, 69));
			}
		});

		// ========== Botón Cancelar ========== \\
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCancelar.setBackground(new Color(108, 117, 125));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelar.setBounds(360, 520, 150, 40);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new GestionarReunionesPanel(user, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		add(btnCancelar);

		btnCancelar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnCancelar.setBackground(new Color(138, 147, 155));
			}

			public void mouseExited(MouseEvent evt) {
				btnCancelar.setBackground(new Color(108, 117, 125));
			}
		});

		// ========== Configuración de tecla Escape ========== \\
		InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelar");
		actionMap.put("cancelar", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancelar.doClick();
			}
		});
	}
}
