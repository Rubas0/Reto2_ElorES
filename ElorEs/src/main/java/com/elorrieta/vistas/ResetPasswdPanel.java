package com.elorrieta.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.elorrieta.tcp.TcpPassReset;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ResetPasswdPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ResetPasswdPanel(JFrame frame) {
		frame.setSize(400, 430);
		setLayout(null);
		setBackground(new Color(240, 240, 240));

		// ========= Título ========== \\
		JLabel lblTitulo = new JLabel("Restablecer Contraseña");
		lblTitulo.setForeground(new Color(70, 130, 180));
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(50, 30, 300, 40);
		add(lblTitulo);

		// ========= Panel blanco para los campos ========== \\
		JPanel panelCampos = new JPanel();
		panelCampos.setLayout(null);
		panelCampos.setBackground(Color.WHITE);
		panelCampos.setBorder(new EmptyBorder(15, 15, 15, 15));
		panelCampos.setBounds(35, 90, 320, 150);
		add(panelCampos);

		// ========= Campo de correo electrónico ========== \\
		JLabel lblEmail = new JLabel("Correo Electrónico");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEmail.setBounds(10, 15, 280, 20);
		panelCampos.add(lblEmail);

		JTextField TfMail = new JTextField();
		TfMail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		TfMail.setBounds(10, 40, 280, 35);
		TfMail.setBackground(new Color(245, 245, 245));
		TfMail.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
		panelCampos.add(TfMail);

		// ========= Descripción ========== \\
		JLabel lblDescripcion = new JLabel("<html>Te enviaremos un correo con tu nueva contraseña</html>");
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDescripcion.setForeground(new Color(100, 100, 100));
		lblDescripcion.setBounds(10, 85, 280, 40);
		panelCampos.add(lblDescripcion);

		// ========= Botón de enviar ========== \\
		JButton btnSend = new JButton("Solicitar nueva contraseña");
		btnSend.setBounds(35, 260, 320, 40);
		btnSend.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSend.setForeground(Color.WHITE);
		btnSend.setBackground(new Color(70, 130, 180));
		btnSend.setBorderPainted(false);
		btnSend.setFocusPainted(false);
		btnSend.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnSend);

		// ========= Efecto hover del botón enviar ========== \\
		btnSend.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnSend.setBackground(new Color(100, 149, 237));
			}

			public void mouseExited(MouseEvent evt) {
				btnSend.setBackground(new Color(70, 130, 180));
			}
		});

		// ========= Botón de cancelar ========== \\
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(35, 320, 320, 40);
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCancel.setForeground(new Color(70, 130, 180));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
		btnCancel.setFocusPainted(false);
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnCancel);

		// ========== Efecto hover del botón cancelar ========== \\
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnCancel.setBackground(new Color(245, 245, 245));
			}

			public void mouseExited(MouseEvent evt) {
				btnCancel.setBackground(Color.WHITE);
			}
		});

		// ========= Acción del botón enviar ========== \\
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TcpPassReset.resetPasswd(TfMail.getText());
				frame.setContentPane(new LoginPanel(frame));
				frame.revalidate();
				frame.repaint();
			}
		});

		// ========= Acción del botón cancelar ========== \\
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new LoginPanel(frame));
				frame.revalidate();
				frame.repaint();
			}
		});

		// ========== Configuración de la tecla Enter para confirmar ========== \\
		frame.getRootPane().setDefaultButton(btnSend);

		// ========== Configuración de la tecla Escape para volver ========== \\
		InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "volver");
		actionMap.put("volver", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancel.doClick();
			}
		});
	}
}
