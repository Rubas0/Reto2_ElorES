package com.elorrieta.vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.elorrieta.entities.Horario;
import com.elorrieta.entities.User;
import com.elorrieta.tcpConnection.TcpAlumnosDeProfesor;
import com.elorrieta.tcpConnection.TcpHorarioProfesor;

public class ConsultarHorarioPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ConsultarHorarioPanel(User user, JFrame frame) {
		frame.setSize(500, 400);
		setLayout(null);

		JButton btnNewButton = new JButton("volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new MenuPanel(user, frame));
				frame.revalidate(); // Actualizar el contenido del frame
				frame.repaint(); // Refrescar la ventana
			}
		});
		btnNewButton.setBounds(94, 204, 89, 23);
		add(btnNewButton);

		// ========== Panel blanco para la tabla ========== \\
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelTabla.setBounds(50, 129, 800, 420);
		add(panelTabla);

		// ========= Tabla de horario ========= \\

		String[] columnas = { "", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" };
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

		// ========== Cargar Horario del Profesor ========== \\
		List<Horario> horariosProfesor = TcpHorarioProfesor.getHorarioProfesor(user);
//		if (alumnos != null) {
//			for (User alumno : alumnos) {
//				if (alumno != null) {
//					String[] fila = { alumno.getNombre() != null ? alumno.getNombre() : "N/A",
//							alumno.getApellidos() != null ? alumno.getApellidos() : "N/A",
//							alumno.getUsername() != null ? alumno.getUsername() : "N/A",
//							alumno.getEmail() != null ? alumno.getEmail() : "N/A",
//							alumno.getDni() != null ? alumno.getDni() : "N/A" };
//					model.addRow(fila);
//				}
//			}
//		}

		// Hacer que el botón "volver" sea el predeterminado al pulsar Escape
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
