package com.elorrieta.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.elorrieta.tcp.TcpPassReset;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ResetPasswdPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TfMail;

	public ResetPasswdPanel(JFrame frame) {
		frame.setSize(400, 480);
		setLayout(null);
		setBackground(java.awt.Color.decode("#232637"));

		TfMail = new JTextField();
		TfMail.setBounds(97, 135, 215, 20);
		add(TfMail);
		TfMail.setColumns(10);

		JLabel lblNewLabel = new JLabel("Introduce tu correo electrónico");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(97, 110, 191, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("RESTABLECER CONTRASEÑA");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(97, 29, 271, 34);
		add(lblNewLabel_1);

		JButton btnSend = new JButton("Solicitar nueva contraseña");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TcpPassReset.resetPasswd(TfMail.getText());
				frame.setContentPane(new LoginPanel(frame));
				frame.revalidate(); // Actualizar el contenido del frame
				frame.repaint(); // Refrescar la ventana
				
			}
		});
		btnSend.setBounds(53, 208, 169, 23);
		add(btnSend);

		// Hacer que el botón login sea el predeterminado al pulsar Enter
		frame.getRootPane().setDefaultButton(btnSend);

		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(new LoginPanel(frame));
				frame.revalidate(); // Actualizar el contenido del frame
				frame.repaint(); // Refrescar la ventana
			}
		});
		btnCancel.setBounds(279, 208, 89, 23);
		add(btnCancel);

		// Hacer que al pulsar ESC se cancele la operación
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					btnCancel.doClick();
				}
			}
		});

	}
}
