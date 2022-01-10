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

public class ReservationMenu extends JFrame {

	@Serial
	private static final long serialVersionUID = -7515500934140028996L;
	
	private newConsumption newConsumption;

	public ReservationMenu(int x, int y, Reservation reservation, ReservationService reservationService,
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
		
		JButton addConsumptionButton = new JButton("Add consumption");
		addConsumptionButton.addActionListener(e -> {
			dispose();
			newConsumption = new newConsumption(x, y, reservation, consumptionService, new ProductService());
		});
		addConsumptionButton.setForeground(Color.BLACK);
		addConsumptionButton.setBackground(new Color(0, 191, 255));
		addConsumptionButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		addConsumptionButton.setBounds(10, 55, 331, 35);
		contentPane.add(addConsumptionButton);
		
		JButton btnSavePdf = new JButton("Generate PDF");
		btnSavePdf.addActionListener(e -> {
			try {
				FileUtils.writePDFAndOpen(reservation);
			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occurred, the PDF cannot be generated");
			}
		});
		btnSavePdf.setForeground(Color.BLACK);
		btnSavePdf.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		btnSavePdf.setBackground(new Color(0, 191, 255));
		btnSavePdf.setBounds(10, 100, 331, 35);
		contentPane.add(btnSavePdf);
		
		JButton changeReservationButton = new JButton("Change reservation");
		changeReservationButton.addActionListener(e -> {
			dispose();
			new NewReservation(reservation, reservationService, roomService, customerService, documentService);
		});
		changeReservationButton.setForeground(Color.BLACK);
		changeReservationButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		changeReservationButton.setBackground(new Color(0, 191, 255));
		changeReservationButton.setBounds(10, 10, 331, 35);
		contentPane.add(changeReservationButton);
		
		JButton deleteReservationButton = new JButton("Delete reservation");
		deleteReservationButton.setForeground(Color.BLACK);
		deleteReservationButton.setBackground(new Color(0, 191, 255));
		deleteReservationButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		deleteReservationButton.setBounds(10, 145, 331, 35);
		deleteReservationButton.addActionListener(e -> {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "Do you confirm that you want to delete the reservation?", "Delete reservation", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				reservationService.deleteReservation(reservation);
				JOptionPane.showMessageDialog(null, "The reservation has been deleted correctly");
				dispose();
			}
		});
		contentPane.add(deleteReservationButton);
		
		this.setVisible(true);
	}
	
	@Override
	public void dispose() {
		if(newConsumption != null) newConsumption.dispose();
		super.dispose();
	}
}