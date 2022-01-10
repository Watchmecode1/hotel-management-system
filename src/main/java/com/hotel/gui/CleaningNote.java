package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import com.hotel.util.SwingComponentUtil;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.io.Serial;

public class CleaningNote extends JFrame {

	@Serial
	private static final long serialVersionUID = -8135019696595591043L;

	public CleaningNote(JButton roomButton, Room room, RoomService roomService, int x, int y) {
		SwingComponentUtil.addHotelIcons(this);
		setMainFrame(x, y);
		setContentPane(roomButton, room, roomService);
		
		this.setVisible(true);
	}
	
	private void setMainFrame(int x, int y) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 317, 231);
	}
	
	private void setContentPane(JButton roomButton, Room room, RoomService roomService) {
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 297, 143);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(room.getNote());
		textPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		scrollPane.setViewportView(textPane);
		
		JLabel noteJLabel = new JLabel("Note");
		scrollPane.setColumnHeaderView(noteJLabel);
		noteJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noteJLabel.setForeground(Color.BLACK);
		noteJLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton saveNoteButton = new JButton("Save note");
		saveNoteButton.addActionListener(e -> {
			String noteText = textPane.getText().trim();
			room.setNote(noteText);
			roomService.saveRoom(room);
			if(noteText.isBlank())
				roomButton.setForeground(Color.BLACK);
			else
				roomButton.setForeground(Color.YELLOW);
			CleaningNote.this.dispose();
		});
		saveNoteButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		saveNoteButton.setForeground(Color.DARK_GRAY);
		saveNoteButton.setBounds(10, 163, 293, 30);
		contentPane.add(saveNoteButton);
	}
}