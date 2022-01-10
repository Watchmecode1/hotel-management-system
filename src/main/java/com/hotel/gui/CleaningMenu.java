package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import com.hotel.util.SwingComponentUtil;

import javax.swing.JButton;
import java.awt.Font;
import java.io.Serial;
import java.awt.Color;

public class CleaningMenu extends JFrame {

	@Serial
	private static final long serialVersionUID = 48798174319558900L;

	public CleaningMenu(JButton button, RoomService roomService, Room room, int x, int y) {
		SwingComponentUtil.addHotelIcons(this);
		this.setBackground(Color.DARK_GRAY);
		setMainFrame(x, y);
		setContentPane(button, roomService, room);
	}
	
	private void setMainFrame(int x, int y) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 205, 165);
	}
	
	private void setContentPane(JButton button, RoomService roomService, Room room) {
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addShowNoteButton(button, room, roomService, contentPane);
		addDeleteNoteButton(button, room, roomService, contentPane);
		
		JButton changeStateButton = new JButton("Change state");
		changeStateButton.addActionListener(e -> {
			if (room.getStatus().equals(Room.Status.TO_CLEAN)) {
				int yn;
				yn = JOptionPane.showConfirmDialog(null, "Do you want to assign the \"clean\" condition to the room?", "", JOptionPane.YES_NO_OPTION);
				if (yn == JOptionPane.YES_OPTION) {
					room.setStatus(Room.Status.CLEAN);
					roomService.saveRoom(room);
					button.setBackground(new Color(51, 204, 51));
				}
			} else {
				int yn;
				yn = JOptionPane.showConfirmDialog(null, "Do you want to assign the \"to clean\" condition to the room?", "", JOptionPane.YES_NO_OPTION);
				if (yn == JOptionPane.YES_OPTION) {
					room.setStatus(Room.Status.TO_CLEAN);
					roomService.saveRoom(room);
					button.setBackground(new Color(204, 0, 0));
				}
			}
		});
		changeStateButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		changeStateButton.setBounds(10, 10, 181, 32);
		contentPane.add(changeStateButton);
		
		this.setVisible(true);
	}
	
	private void addShowNoteButton(JButton roomButton, Room room, RoomService roomService, JPanel contentPane) {
		JButton showNoteButton = new JButton("Show note");
		showNoteButton.addActionListener(e -> new CleaningNote(roomButton, room, roomService, getX(), getY()));
		showNoteButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		showNoteButton.setBounds(10, 52, 181, 32);
		contentPane.add(showNoteButton);
	}
	
	private void addDeleteNoteButton(JButton roomButton, Room room, RoomService roomService, JPanel contentPane) {
		JButton deleteNoteButton = new JButton("Delete note");
		deleteNoteButton.addActionListener(e -> {
			room.setNote("");
			roomService.saveRoom(room);
			roomButton.setForeground(Color.BLACK);
		});
		deleteNoteButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		deleteNoteButton.setBounds(10, 94, 181, 32);
		contentPane.add(deleteNoteButton);
	}
}