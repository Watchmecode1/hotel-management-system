package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hotel.entity.Reservation;
import com.hotel.service.RoomService;
import com.hotel.service.CustomerService;
import com.hotel.service.ConsumptionService;
import com.hotel.service.DocumentService;
import com.hotel.service.ReservationService;
import com.hotel.service.ProductService;
import com.hotel.util.FileUtils;
import com.hotel.util.SwingComponentUtil;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.Serial;

public class MenuPrenotazione extends JFrame {

	@Serial
	private static final long serialVersionUID = -7515500934140028996L;
	
	private NuovaConsumazione nuovaConsumazione;

	public MenuPrenotazione(int x, int y, Reservation reservation, ReservationService reservationService,
							ConsumptionService consumptionService, RoomService roomService,
							CustomerService customerService, DocumentService documentService) {
		SwingComponentUtil.addHotelIcons(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 358, 222);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addConsumazioneButton = new JButton("Aggiungi Consumazione");
		addConsumazioneButton.addActionListener(e -> {
			dispose();
			nuovaConsumazione = new NuovaConsumazione(x, y, reservation, consumptionService, new ProductService());
		});
		addConsumazioneButton.setForeground(Color.BLACK);
		addConsumazioneButton.setBackground(new Color(0, 191, 255));
		addConsumazioneButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		addConsumazioneButton.setBounds(10, 55, 331, 35);
		contentPane.add(addConsumazioneButton);
		
		JButton btnSalvaPdf = new JButton("Genera PDF");
		btnSalvaPdf.addActionListener(e -> {
			try {
				FileUtils.writePDFAndOpen(reservation);
			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "SI E' VERIFICATO UN ERRORE, IMPOSSIBILE GENERARE IL PDF");
			}
		});
		btnSalvaPdf.setForeground(Color.BLACK);
		btnSalvaPdf.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		btnSalvaPdf.setBackground(new Color(0, 191, 255));
		btnSalvaPdf.setBounds(10, 100, 331, 35);
		contentPane.add(btnSalvaPdf);
		
		JButton changeReservationButton = new JButton("Modifica Prenotazione");
		changeReservationButton.addActionListener(e -> {
			dispose();
			new NuovaPrenotazione(reservation, reservationService, roomService, customerService, documentService);
		});
		changeReservationButton.setForeground(Color.BLACK);
		changeReservationButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		changeReservationButton.setBackground(new Color(0, 191, 255));
		changeReservationButton.setBounds(10, 10, 331, 35);
		contentPane.add(changeReservationButton);
		
		JButton eliminaPrenotazioneButton = new JButton("Elimina Prenotazione");
		eliminaPrenotazioneButton.setForeground(Color.BLACK);
		eliminaPrenotazioneButton.setBackground(new Color(0, 191, 255));
		eliminaPrenotazioneButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		eliminaPrenotazioneButton.setBounds(10, 145, 331, 35);
		eliminaPrenotazioneButton.addActionListener(e -> {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "CONFERMI DI VOLER ELIMINARE LA PRENOTAZIONE?", "ELIMINA PRENOTAZIONE", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				reservationService.deleteReservation(reservation);
				JOptionPane.showMessageDialog(null, "LA PRENOTAZIONE E' STATA ELIMINATA CORRETTAMENTE");
				dispose();
			}
		});
		contentPane.add(eliminaPrenotazioneButton);
		
		this.setVisible(true);
	}
	
	@Override
	public void dispose() {
		if(nuovaConsumazione != null) nuovaConsumazione.dispose();
		super.dispose();
	}
}