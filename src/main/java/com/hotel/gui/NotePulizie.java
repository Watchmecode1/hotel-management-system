package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hotel.entity.Camera;
import com.hotel.service.CameraService;
import com.hotel.util.SwingComponentUtil;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.awt.event.ActionEvent;

public class NotePulizie extends JFrame {

	@Serial
	private static final long serialVersionUID = -8135019696595591043L;

	public NotePulizie(JButton roomButton, Camera camera, CameraService cameraService, int x, int y) {
		SwingComponentUtil.addHotelIcons(this);
		setMainFrame(x, y);
		setContentPane(roomButton, camera, cameraService);
		
		this.setVisible(true);
	}
	
	private void setMainFrame(int x, int y) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 317, 231);
	}
	
	private void setContentPane(JButton roomButton, Camera camera, CameraService cameraService) {
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 297, 143);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(camera.getNote());
		textPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setViewportView(textPane);
		
		JLabel noteJLabel = new JLabel("NOTE");
		scrollPane.setColumnHeaderView(noteJLabel);
		noteJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noteJLabel.setForeground(Color.BLACK);
		noteJLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton saveNoteButton = new JButton("Salva Note");
		saveNoteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String noteText = textPane.getText().trim();
				camera.setNote(noteText);
				cameraService.saveCamera(camera);
				JOptionPane.showMessageDialog(null, "NOTE SALVATE");
				if(noteText.isBlank())
					roomButton.setForeground(Color.BLACK);
				else
					roomButton.setForeground(Color.YELLOW);
				NotePulizie.this.dispose();
			}
		});
		saveNoteButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		saveNoteButton.setForeground(Color.DARK_GRAY);
		saveNoteButton.setBounds(10, 163, 293, 30);
		contentPane.add(saveNoteButton);
	}
}