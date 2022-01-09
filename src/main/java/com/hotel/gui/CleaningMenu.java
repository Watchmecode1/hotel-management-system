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
		
		addMostraNoteButton(button, room, roomService, contentPane);
		addEliminaNoteButton(button, room, roomService, contentPane);
		
		JButton cambiaStatoButton = new JButton("CAMBIA STATO");
		cambiaStatoButton.addActionListener(e -> {
			if (room.getStatus().equals(Room.Status.TO_CLEAN)) {
				int yn;
				yn = JOptionPane.showConfirmDialog(null, "VUOI ASSEGNARE ALLA CAMERA LA CONDIZIONE \"PULITA\"?", "", JOptionPane.YES_NO_OPTION);
				if (yn == JOptionPane.YES_OPTION) {
					room.setStatus(Room.Status.CLEAN);
					roomService.saveRoom(room);
					JOptionPane.showMessageDialog(null, "CONDIZIONE CAMERA AGGIORNATA CORRETTAMENTE");
					button.setBackground(new Color(51, 204, 51));
				}
			} else {
				int yn;
				yn = JOptionPane.showConfirmDialog(null, "VUOI ASSEGNARE ALLA CAMERA LA CONDIZIONE \"DA PULIRE\"?", "", JOptionPane.YES_NO_OPTION);
				if (yn == JOptionPane.YES_OPTION) {
					room.setStatus(Room.Status.TO_CLEAN);
					roomService.saveRoom(room);
					JOptionPane.showMessageDialog(null, "CONDIZIONE CAMERA AGGIORNATA CORRETTAMENTE");
					button.setBackground(new Color(204, 0, 0));
				}
			}
		});
		cambiaStatoButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		cambiaStatoButton.setBounds(10, 10, 181, 32);
		contentPane.add(cambiaStatoButton);
		
		this.setVisible(true);
	}
	
	private void addMostraNoteButton(JButton roomButton, Room room, RoomService roomService, JPanel contentPane) {
		JButton mostraNoteButton = new JButton("MOSTRA NOTE");
		mostraNoteButton.addActionListener(e -> new CleaningNote(roomButton, room, roomService, getX(), getY()));
		mostraNoteButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		mostraNoteButton.setBounds(10, 52, 181, 32);
		contentPane.add(mostraNoteButton);
	}
	
	private void addEliminaNoteButton(JButton roomButton, Room room, RoomService roomService, JPanel contentPane) {
		JButton eliminaNoteButton = new JButton("ELIMINA NOTE");
		eliminaNoteButton.addActionListener(e -> {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "VUOI ELIMINARE LE NOTE DELLA STANZA " + room.getNumber()+"?", "ELIMINA NOTE", JOptionPane.YES_NO_OPTION);
			if(yn == JOptionPane.YES_OPTION) {
				room.setNote("");
				roomService.saveRoom(room);
				roomButton.setForeground(Color.BLACK);
				JOptionPane.showMessageDialog(null, "NOTE CANCELLATE CORRETTAMENTE");
			}
		});
		eliminaNoteButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		eliminaNoteButton.setBounds(10, 94, 181, 32);
		contentPane.add(eliminaNoteButton);
	}
}