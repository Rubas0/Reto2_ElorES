package com.elorrieta.vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.elorrieta.entities.User;
import com.elorrieta.tcpConnection.TcpAlumnosDeProfesor;

public class ConsultarAlumnosPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ConsultarAlumnosPanel(User user, JFrame frame) {
		frame.setSize(900, 600);
		setLayout(null);
		setBackground(new Color(240, 240, 240));

		// ========== Título ========== \\
		JLabel lblTitulo = new JLabel("Consultar Alumnos");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(70, 130, 180));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(300, 20, 300, 40);
		add(lblTitulo);

		// ========== Panel blanco para la tabla ========== \\
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelTabla.setBounds(50, 80, 800, 420);
		add(panelTabla);

		// ========== Tabla de alumnos ========== \\
		String[] columnas = { "Nombre", "Apellidos", "Usuario", "Email", "DNI" };
		DefaultTableModel model = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Hacer la tabla de solo lectura
			}
		};

		JTable table = new JTable(model);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		table.getTableHeader().setBackground(new Color(70, 130, 180));
		table.getTableHeader().setForeground(Color.WHITE);
		table.setSelectionBackground(new Color(100, 149, 237));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 770, 390);
		panelTabla.add(scrollPane);

		// ========== Cargar datos de alumnos ========== \\
		List<User> alumnos = TcpAlumnosDeProfesor.getAlumnosDeProfesor(user);
		if (alumnos != null) {
			for (User alumno : alumnos) {
				if (alumno != null) {
					Object[] fila = { alumno.getNombre() != null ? alumno.getNombre() : "N/A",
							alumno.getApellidos() != null ? alumno.getApellidos() : "N/A",
							alumno.getUsername() != null ? alumno.getUsername() : "N/A",
							alumno.getEmail() != null ? alumno.getEmail() : "N/A",
							alumno.getDni() != null ? alumno.getDni() : "N/A" };
					model.addRow(fila);
				}
			}
		}

		// ========== Botón Volver ========== \\
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVolver.setBackground(new Color(70, 130, 180));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(375, 520, 150, 35);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new MenuPanel(user, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		add(btnVolver);

		// ========== Efecto hover del botón ========== \\
		btnVolver.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnVolver.setBackground(new Color(100, 149, 237));
			}

			public void mouseExited(MouseEvent evt) {
				btnVolver.setBackground(new Color(70, 130, 180));
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
