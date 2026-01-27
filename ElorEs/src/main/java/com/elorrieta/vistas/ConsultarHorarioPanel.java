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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.elorrieta.entities.Horario;
import com.elorrieta.entities.Reuniones;
import com.elorrieta.entities.User;
import com.elorrieta.tcpConnection.TcpHorarioProfesor;
import com.elorrieta.tcpConnection.TcpReunionesProfe;

public class ConsultarHorarioPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ConsultarHorarioPanel(User user, JFrame frame) {
		frame.setSize(1100, 700);
		frame.setLocationRelativeTo(null);
		setLayout(null);
		setBackground(new Color(240, 240, 240));

		// ========== Título ========== \\
		JLabel lblTitulo = new JLabel("Consultar Horario");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(70, 130, 180));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(400, 20, 300, 40);
		add(lblTitulo);

		// ========== Panel blanco para la tabla ========== \\
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelTabla.setBounds(20, 80, 1050, 480);
		add(panelTabla);

		// ========== Tabla de horario ========== \\
		String[] columnas = { "", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" };
		DefaultTableModel model = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};

		JTable table = new JTable(model);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		table.setRowHeight(60);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
		table.getTableHeader().setBackground(new Color(70, 130, 180));
		table.getTableHeader().setForeground(Color.WHITE);
		table.setSelectionBackground(new Color(100, 149, 237));

		// Deshabilitar selección de filas
		table.setRowSelectionAllowed(false);
		table.setFocusable(false);

		// Centrar el texto en todas las celdas
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}


		// Ajustar el ancho de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		for (int i = 1; i < 6; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(180);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 15, 1020, 450);
		panelTabla.add(scrollPane);

		// ========== Cargar Horario del Profesor ========== \\
		List<Horario> horariosProfesor = TcpHorarioProfesor.getHorarioProfesor(user);
		for (int hora = 1; hora <= 6; hora++) {
			String[] fila = new String[6];
			fila[0] = "Hora " + hora;
			fila[1] = "";
			fila[2] = "";
			fila[3] = "";
			fila[4] = "";
			fila[5] = "";

			// Buscar módulos para esta hora en cada día
			for (Horario horario : horariosProfesor) {
				if (horario.getHora() == hora) {
					String nombreModulo = horario.getModulo() != null ? horario.getModulo().getNombre() : "";

					// Asignar a la columna del día correspondiente
					switch (horario.getDia().toLowerCase()) {
					case "lunes":
						fila[1] = nombreModulo;
						break;
					case "martes":
						fila[2] = nombreModulo;
						break;
					case "miercoles":
						fila[3] = nombreModulo;
						break;
					case "jueves":
						fila[4] = nombreModulo;
						break;
					case "viernes":
						fila[5] = nombreModulo;
						break;
					}
				}
			}
			model.addRow(fila);
		}
		
		// ========= Cargar Reuniones En El Horario ========== \\
			List<Reuniones> reunionesProfesor = TcpReunionesProfe.getReunionesDeProfesor(user);
			for (Reuniones reunion : reunionesProfesor) {
				int diaIndex = -1;
				switch (reunion.getDia()) {
				case 1:
					diaIndex = 1;
					break;
				case 2:
					diaIndex = 2;
					break;
				case 3:
					diaIndex = 3;
					break;
				case 4:
					diaIndex = 4;
					break;
				case 5:
					diaIndex = 5;
					break;
				}
				if (diaIndex != -1 && reunion.getHora() >= 1 && reunion.getHora() <= 6) {
					int filaIndex = reunion.getHora() - 1;
					String contenidoActual = (String) model.getValueAt(filaIndex, diaIndex);
					if (!contenidoActual.isEmpty()) {
						contenidoActual += " / ";
					}
					contenidoActual += "[Reunión: " + reunion.getTitulo() + "]";
					model.setValueAt(contenidoActual, filaIndex, diaIndex);
				}
			}
		// ========== Botón Volver (debajo de la tabla) ========== \\
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVolver.setBackground(new Color(70, 130, 180));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(475, 580, 150, 40);
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
