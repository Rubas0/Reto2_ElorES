package com.elorrieta.vistas;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.DefaultCellEditor;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.elorrieta.entities.Reuniones;
import com.elorrieta.entities.User;
import com.elorrieta.tcpConnection.TcpActualizarReunionesProfe;
import com.elorrieta.tcpConnection.TcpReunionesProfe;

public class GestionarReunionesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private List<Reuniones> reuniones;
	private JTable table;

	public GestionarReunionesPanel(User user, JFrame frame) {
		frame.setSize(1200, 700);
		frame.setLocationRelativeTo(null);
		setLayout(null);
		setBackground(new Color(240, 240, 240));

		// ========== Título ========== \\
		JLabel lblTitulo = new JLabel("Gestionar Reuniones");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(70, 130, 180));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(400, 20, 400, 40);
		add(lblTitulo);

		// ========== Panel blanco para la tabla ========== \\
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelTabla.setBounds(20, 80, 1150, 480);
		add(panelTabla);

		// ========== Tabla de reuniones ========== \\
		String[] columnas = { "ID", "Título", "Día", "Hora", "Semana", "Alumno", "Aula", "Centro", "Estado" };
		model = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 8;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 8) {
					return String.class;
				}
				return super.getColumnClass(columnIndex);
			}
		};

		table = new JTable(model);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		table.setRowHeight(40);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		table.getTableHeader().setBackground(new Color(70, 130, 180));
		table.getTableHeader().setForeground(Color.WHITE);
		table.setSelectionBackground(new Color(100, 149, 237));

		// ========== ComboBox para el estado ========== \\
		JComboBox<String> comboEstado = new JComboBox<>(new String[] { "Pendiente", "Aceptada", "Rechazada" });
		comboEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(comboEstado));

		// ========== Renderizador para centrar y colorear según estado ========== \\
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setHorizontalAlignment(SwingConstants.CENTER);

				if (column == 8 && !isSelected) {
					String estado = value != null ? value.toString().toLowerCase() : "";
					switch (estado) {
					case "aceptada":
						c.setBackground(new Color(144, 238, 144));
						break;
					case "pendiente":
						c.setBackground(new Color(255, 255, 224));
						break;
					case "rechazada":
						c.setBackground(new Color(255, 182, 193));
						break;
					default:
						c.setBackground(Color.WHITE);
					}
				} else if (!isSelected) {
					c.setBackground(Color.WHITE);
				}

				return c;
			}
		};

		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// ========== Ajustar anchos de columna ========== \\
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 15, 1120, 400);
		panelTabla.add(scrollPane);

		// ========== Cargar datos iniciales ========== \\
		cargarReuniones(user);

		// ========== Botón Nueva Reunión ========== \\
		JButton btnNuevaReunion = new JButton("Nueva Reunión");
		btnNuevaReunion.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNuevaReunion.setBackground(new Color(40, 167, 69));
		btnNuevaReunion.setForeground(Color.WHITE);
		btnNuevaReunion.setFocusPainted(false);
		btnNuevaReunion.setBorderPainted(false);
		btnNuevaReunion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNuevaReunion.setBounds(220, 580, 180, 40);
		btnNuevaReunion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new NuevaReunionPanel(user, frame));
			}
		});
		add(btnNuevaReunion);

		btnNuevaReunion.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnNuevaReunion.setBackground(new Color(52, 195, 85));
			}

			public void mouseExited(MouseEvent evt) {
				btnNuevaReunion.setBackground(new Color(40, 167, 69));
			}
		});

		// ========== Botón Guardar Cambios ========== \\
		JButton btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnGuardar.setBackground(new Color(0, 123, 255));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorderPainted(false);
		btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGuardar.setBounds(420, 580, 180, 40);
		btnGuardar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        guardarCambios(user);
		    }
		});
		add(btnGuardar);

		btnGuardar.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		        btnGuardar.setBackground(new Color(0, 149, 255));
		    }

		    public void mouseExited(MouseEvent evt) {
		        btnGuardar.setBackground(new Color(0, 123, 255));
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
		btnVolver.setBounds(620, 580, 180, 40);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new MenuPanel(user, frame));
				frame.revalidate();
				frame.repaint();
			}
		});
		add(btnVolver);

		btnVolver.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnVolver.setBackground(new Color(100, 149, 237));
			}

			public void mouseExited(MouseEvent evt) {
				btnVolver.setBackground(new Color(70, 130, 180));
			}
		});

		// ========== Configuración de tecla Escape ========== \\
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

	// ========== Método para cargar reuniones ========== \\
	private void cargarReuniones(User user) {
		reuniones = TcpReunionesProfe.getReunionesDeProfesor(user);
		model.setRowCount(0);

		if (reuniones != null && !reuniones.isEmpty()) {
			for (Reuniones reunion : reuniones) {
				String dia = obtenerNombreDia(reunion.getDia());
				String alumno = reunion.getAlumno().getNombre() + " " + reunion.getAlumno().getApellidos();
				String estado = reunion.getEstado();
				String aula = reunion.getAula() != null ? reunion.getAula() : "-";
				String centro = reunion.getIdCentro() != null ? reunion.getIdCentro() : "-";

				model.addRow(new Object[] { 
					reunion.getId(), 
					reunion.getTitulo(), 
					dia, 
					"Hora " + reunion.getHora(),
					"Semana " + reunion.getSemana(), 
					alumno, 
					aula, 
					centro, 
					estado 
				});
			}
		}
	}

	// ========== Método para guardar cambios ========== \\
	private void guardarCambios(User user) {
		if (table.isEditing()) {
			table.getCellEditor().stopCellEditing();
		}

		for (int i = 0; i < model.getRowCount(); i++) {
			int id = (int) model.getValueAt(i, 0);
			String nuevoEstado = (String) model.getValueAt(i, 8);

			for (Reuniones reunion : reuniones) {
				if (reunion.getId() == id) {
					reunion.setEstado(nuevoEstado);
					break;
				}
			}
			TcpActualizarReunionesProfe.actualizarReuniones(reuniones, user);
		}

		JOptionPane.showMessageDialog(this, "Cambios guardados correctamente", "Éxito",
				JOptionPane.INFORMATION_MESSAGE);
		cargarReuniones(user);
	}

	// ========== Método auxiliar para obtener nombre del día ========== \\
	private String obtenerNombreDia(int dia) {
		switch (dia) {
		case 1:
			return "Lunes";
		case 2:
			return "Martes";
		case 3:
			return "Miércoles";
		case 4:
			return "Jueves";
		case 5:
			return "Viernes";
		default:
			return "Desconocido";
		}
	}
}
