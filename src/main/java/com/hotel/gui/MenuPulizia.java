package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hotel.entity.Camera;
import com.hotel.service.CameraService;
import com.hotel.util.SwingComponentUtil;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenuPulizia extends JFrame {

	@Serial
	private static final long serialVersionUID = 48798174319558900L;

	public MenuPulizia(JButton button, CameraService cameraService, Camera camera, int x, int y) {
		SwingComponentUtil.addHotelIcons(this);
		this.setBackground(Color.DARK_GRAY);
		setMainFrame(x, y);
		setContentPane(button, cameraService, camera);
	}
	
	private void setMainFrame(int x, int y) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 205, 165);
	}
	
	private void setContentPane(JButton button, CameraService cameraService, Camera camera) {
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addMostraNoteButton(button, camera, cameraService, contentPane);
		addEliminaNoteButton(button, camera, cameraService, contentPane);
		
		JButton cambiaStatoButton = new JButton("CAMBIA STATO");
		cambiaStatoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (camera.getCondizione().equals(Camera.Condizione.DA_PULIRE)) {
					int yn;
					yn = JOptionPane.showConfirmDialog(null, "VUOI ASSEGNARE ALLA CAMERA LA CONDIZIONE \"PULITA\"?", "", JOptionPane.YES_NO_OPTION);
					if (yn == JOptionPane.YES_OPTION) {
						camera.setCondizione(Camera.Condizione.PULITA);
						cameraService.saveCamera(camera);
						JOptionPane.showMessageDialog(null, "CONDIZIONE CAMERA AGGIORNATA CORRETTAMENTE");
						button.setBackground(new Color(51, 204, 51));
					}
				} else {
					int yn;
					yn = JOptionPane.showConfirmDialog(null, "VUOI ASSEGNARE ALLA CAMERA LA CONDIZIONE \"DA PULIRE\"?", "", JOptionPane.YES_NO_OPTION);
					if (yn == JOptionPane.YES_OPTION) {
						camera.setCondizione(Camera.Condizione.DA_PULIRE);
						cameraService.saveCamera(camera);
						JOptionPane.showMessageDialog(null, "CONDIZIONE CAMERA AGGIORNATA CORRETTAMENTE");
						button.setBackground(new Color(204, 0, 0));
					}
				}
			}
		});
		cambiaStatoButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		cambiaStatoButton.setBounds(10, 10, 181, 32);
		contentPane.add(cambiaStatoButton);
		
		this.setVisible(true);
	}
	
	private void addMostraNoteButton(JButton roomButton, Camera camera, CameraService cameraService, JPanel contentPane) {
		JButton mostraNoteButton = new JButton("MOSTRA NOTE");
		mostraNoteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NotePulizie(roomButton, camera, cameraService, getX(), getY());
			}
		});
		mostraNoteButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		mostraNoteButton.setBounds(10, 52, 181, 32);
		contentPane.add(mostraNoteButton);
	}
	
	private void addEliminaNoteButton(JButton roomButton, Camera camera, CameraService cameraService, JPanel contentPane) {
		JButton eliminaNoteButton = new JButton("ELIMINA NOTE");
		eliminaNoteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int yn;
				yn = JOptionPane.showConfirmDialog(null, "VUOI ELIMINARE LE NOTE DELLA STANZA " + camera.getNumero()+"?", "ELIMINA NOTE", JOptionPane.YES_NO_OPTION);
				if(yn == JOptionPane.YES_OPTION) {
					camera.setNote("");
					cameraService.saveCamera(camera);
					roomButton.setForeground(Color.BLACK);
					JOptionPane.showMessageDialog(null, "NOTE CANCELLATE CORRETTAMENTE");
				}
			}
		});
		eliminaNoteButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		eliminaNoteButton.setBounds(10, 94, 181, 32);
		contentPane.add(eliminaNoteButton);
	}
}