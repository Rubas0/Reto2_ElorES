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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import com.elorrieta.entities.User;

public class ConsultarOtrosHorariosPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ConsultarOtrosHorariosPanel(User user, JFrame frame) {
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		setLayout(null);
		setBackground(new Color(240, 240, 240));

		// ========== Título ========== \\
		JLabel lblTitulo = new JLabel("Consultar Otros Horarios");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(70, 130, 180));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(200, 30, 300, 40);
		add(lblTitulo);

		// ========== Botón Volver ========== \\
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnVolver.setBackground(new Color(70, 130, 180));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(275, 400, 150, 40);
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
