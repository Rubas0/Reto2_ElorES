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

import com.elorrieta.entities.Ciclo;
import com.elorrieta.entities.Matriculaciones;
import com.elorrieta.entities.User;
import com.elorrieta.tcpConnection.TcpAlumnosDeProfesor;
import com.elorrieta.tcpConnection.TcpAlumnosFilter;
import com.elorrieta.tcpConnection.TcpCiclos;

import javax.swing.JComboBox;

public class ConsultarAlumnosPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ConsultarAlumnosPanel(User profesor, JFrame frame) {
		frame.setSize(900, 650);
		frame.setLocationRelativeTo(null);
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
		panelTabla.setBounds(50, 129, 800, 420);
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
		List<User> alumnos = TcpAlumnosDeProfesor.getAlumnosDeProfesor(profesor);
		if (alumnos != null) {
			for (User alumno : alumnos) {
				if (alumno != null) {
					String[] fila = { alumno.getNombre() != null ? alumno.getNombre() : "N/A",
							alumno.getApellidos() != null ? alumno.getApellidos() : "N/A",
							alumno.getUsername() != null ? alumno.getUsername() : "N/A",
							alumno.getEmail() != null ? alumno.getEmail() : "N/A",
							alumno.getDni() != null ? alumno.getDni() : "N/A" };
					model.addRow(fila);
				}
			}
		}

		// ========= Listener para mostrar datos de un alumno ========= \\
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Doble clic
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						String username = (String) model.getValueAt(selectedRow, 2);
						User alumnoSeleccionado = null;
						for (User alumno : alumnos) {
							if (alumno.getUsername().equals(username)) {
								alumnoSeleccionado = alumno;
								break;
							}
						}
						if (alumnoSeleccionado != null) {
							frame.setContentPane(new PerfilPanel(profesor, alumnoSeleccionado, frame));
							frame.revalidate();
							frame.repaint();
						}
					}
				}
			}
		});

		// ========== Botón Volver ========== \\
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVolver.setBackground(new Color(70, 130, 180));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(376, 560, 150, 35);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new MenuPanel(profesor, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		add(btnVolver);

		// ========== Filtros ========== \\
		JComboBox<String> comboBoxCiclo = new JComboBox<String>();
		comboBoxCiclo.setBounds(189, 96, 128, 22);
		add(comboBoxCiclo);

		JLabel lblCicloFilter = new JLabel("Filtrar por ciclo:");
		lblCicloFilter.setBounds(82, 100, 97, 14);
		List<Ciclo> ciclos = TcpCiclos.getCiclos();
		if (ciclos != null) {
			comboBoxCiclo.addItem("Todos");
			for (Ciclo ciclo : ciclos) {
				comboBoxCiclo.addItem(ciclo.getNombre());
			}
		}
		add(lblCicloFilter);

		JLabel lblCursoFilter = new JLabel("Filtrar por Curso");
		lblCursoFilter.setBounds(523, 100, 110, 14);
		add(lblCursoFilter);

		JComboBox<Object> comboBoxCurso = new JComboBox<Object>();
		comboBoxCurso.setBounds(654, 96, 190, 22);
		comboBoxCurso.addItem("Todos");
		comboBoxCurso.addItem(1);
		comboBoxCurso.addItem(2);
		add(comboBoxCurso);

		// =========== Lógica de filtrado ========== \\
		ActionListener filtroListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cicloSeleccionado = (String) comboBoxCiclo.getSelectedItem();
				Object cursoSeleccionado = comboBoxCurso.getSelectedItem();

				// Limpiar la tabla
				model.setRowCount(0);

				// Preparar los filtros para enviar al servidor
				Matriculaciones matriculacionFiltro = new Matriculaciones();
				if (cursoSeleccionado instanceof Integer) {
					matriculacionFiltro.setCurso((Integer) cursoSeleccionado);
				} else {
					matriculacionFiltro = null;
				}

				Ciclo cicloFiltro = new Ciclo();
				if (cicloSeleccionado != null && !cicloSeleccionado.equals("Todos")) {
					for (Ciclo ciclo : ciclos) {
						if (ciclo.getNombre().equals(cicloSeleccionado)) {
							cicloFiltro = ciclo;
							break;
						}
					}
				} else {
					cicloFiltro = null;
				}
				// Obtener los alumnos filtrados desde el servidor
				List<User> usersFilter = TcpAlumnosFilter.getAlumnosFiltrados(cicloFiltro, matriculacionFiltro, profesor);
				// Actualizar la tabla con los alumnos filtrados
				if (usersFilter != null) {
					for (User alumno : usersFilter) {
						if (alumno != null) {
							String[] fila = { alumno.getNombre() != null ? alumno.getNombre() : "N/A",
									alumno.getApellidos() != null ? alumno.getApellidos() : "N/A",
									alumno.getUsername() != null ? alumno.getUsername() : "N/A",
									alumno.getEmail() != null ? alumno.getEmail() : "N/A",
									alumno.getDni() != null ? alumno.getDni() : "N/A" };
							model.addRow(fila);
						}
					}
				}
			}
		};
		
		// ========== Añadir listeners a los comboBoxes ========== \\
		comboBoxCiclo.addActionListener(filtroListener);
		comboBoxCurso.addActionListener(filtroListener);

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
