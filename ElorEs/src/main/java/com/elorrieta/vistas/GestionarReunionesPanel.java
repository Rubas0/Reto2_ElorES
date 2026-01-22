package com.elorrieta.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.elorrieta.entities.User;

public class GestionarReunionesPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GestionarReunionesPanel(User user, JFrame frame) {
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
