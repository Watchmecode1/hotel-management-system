package com.hotel.gui;

import java.awt.EventQueue;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.hotel.service.RoomService;
import com.hotel.service.CustomerService;
import com.hotel.service.ConsumptionService;
import com.hotel.service.DocumentService;
import com.hotel.service.OrderService;
import com.hotel.service.ReservationService;
import com.hotel.service.ProductService;
import com.hotel.util.FileUtils;
import com.hotel.util.NoScalingIcon;
import com.hotel.util.SwingComponentUtil;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;

public class HomePage extends JFrame {

	@Serial
	private static final long serialVersionUID = 5263091083750632811L;
	private static final String BOOKING_URL = "https://www.booking.com";
	private static final String WHATSAPP_URL = "https://web.whatsapp.com";
	private static final String EMAIL_URL = "https://mail.ovh.net/roundcube/?_task=login";
	private static final String ALLOGGIATI_URL = "https://alloggiatiweb.poliziadistato.it/PortaleAlloggiati/";
	
	private final Desktop desktop = Desktop.getDesktop();

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		EventQueue.invokeLater(() -> {
			try {
				HomePage frame = new HomePage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public HomePage() {
		SwingComponentUtil.addHotelIcons(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1537, 820);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JLabel lblNewLabel_1 = new JLabel("Gestionale");
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 60));
//		lblNewLabel_1.setForeground(new Color(255, 255, 204));
//		lblNewLabel_1.setBounds(400, 556, 512, 75);
//		contentPane.add(lblNewLabel_1);
//
//		JLabel lblNewLabel_1_1 = new JLabel("Multifunzionale");
//		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1_1.setForeground(new Color(255, 255, 204));
//		lblNewLabel_1_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 60));
//		lblNewLabel_1_1.setBounds(400, 628, 512, 58);
//		contentPane.add(lblNewLabel_1_1);
//
//		JLabel lblNewLabel_1_1_1 = new JLabel("per Hotel");
//		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 204));
//		lblNewLabel_1_1_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 60));
//		lblNewLabel_1_1_1.setBounds(400, 685, 512, 58);
//		contentPane.add(lblNewLabel_1_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(0, 0, 400, 792);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Plan");
		btnNewButton.addActionListener(e -> new Planner(LocalDate.now(), new ReservationService(), new RoomService(), new ConsumptionService(), new CustomerService(), new DocumentService()));
		btnNewButton.setForeground(new Color(224, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnNewButton.setBounds(90, 10, 220, 65);
		panel.add(btnNewButton);
		
		JButton btnNewReservation = new JButton("New reservation");
		btnNewReservation.addActionListener(e -> new NewReservation(new ReservationService(), new RoomService(), new CustomerService(), new DocumentService()));
		btnNewReservation.setForeground(new Color(224, 255, 255));
		btnNewReservation.setBackground(new Color(0, 128, 128));
		btnNewReservation.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnNewReservation.setBounds(90, 85, 220, 65);
		panel.add(btnNewReservation);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(e -> new Storage(new OrderService(), new ProductService()));
		btnInventory.setForeground(new Color(224, 255, 255));
		btnInventory.setBackground(new Color(0, 128, 128));
		btnInventory.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnInventory.setBounds(90, 235, 220, 65);
		panel.add(btnInventory);
		
		JButton btnReservationRegister = new JButton("Reservation register");
		btnReservationRegister.addActionListener(e -> new ReservationRegister(new ReservationService()));
		btnReservationRegister.setForeground(new Color(224, 255, 255));
		btnReservationRegister.setBackground(new Color(0, 128, 128));
		btnReservationRegister.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnReservationRegister.setBounds(90, 310, 220, 65);
		panel.add(btnReservationRegister);
		
		JButton btnCustomerRegister = new JButton("Customer register");
		btnCustomerRegister.addActionListener(e -> new CustomerRegister(new CustomerService()));
		btnCustomerRegister.setForeground(new Color(224, 255, 255));
		btnCustomerRegister.setBackground(new Color(0, 128, 128));
		btnCustomerRegister.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnCustomerRegister.setBounds(90, 385, 220, 65);
		panel.add(btnCustomerRegister);
		
		JButton btnRoomSection = new JButton("Room section");
		btnRoomSection.addActionListener(e -> new RoomPrices());
		btnRoomSection.setForeground(new Color(224, 255, 255));
		btnRoomSection.setBackground(new Color(0, 128, 128));
		btnRoomSection.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnRoomSection.setBounds(90, 460, 220, 65);
		panel.add(btnRoomSection);
		
		JButton btnCleaningSection = new JButton("Cleaning section");
		btnCleaningSection.addActionListener(e -> new CleanRooms(new RoomService()));
		btnCleaningSection.setForeground(new Color(224, 255, 255));
		btnCleaningSection.setBackground(new Color(0, 128, 128));
		btnCleaningSection.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnCleaningSection.setBounds(90, 535, 220, 65);
		panel.add(btnCleaningSection);
		
		JButton btnProducts = new JButton("Products");
		btnProducts.addActionListener(e -> new ProductRegister(new ProductService()));
		btnProducts.setForeground(new Color(224, 255, 255));
		btnProducts.setBackground(new Color(0, 128, 128));
		btnProducts.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnProducts.setBounds(90, 610, 220, 65);
		panel.add(btnProducts);
		
		JButton btnSitra = new JButton("Sitra page");
		btnSitra.addActionListener(e -> FileUtils.writeSitraAndOpen(LocalDate.now()));
		btnSitra.setForeground(new Color(224, 255, 255));
		btnSitra.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnSitra.setBackground(new Color(0, 128, 128));
		btnSitra.setBounds(90, 160, 220, 65);
		panel.add(btnSitra);
		
		JButton cateringButton = new JButton("Catering");
		cateringButton.addActionListener(e -> {
			new Catering(new ReservationService());
		});
		cateringButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		cateringButton.setForeground(new Color(224, 255, 255));
		cateringButton.setBackground(new Color(0, 139, 139));
		cateringButton.setBounds(90, 685, 220, 65);
		panel.add(cateringButton);
		
		JButton btnNewButton_1 = new JButton("Booking.com");
		btnNewButton_1.addActionListener(e -> {
			try {
				desktop.browse(new URI(BOOKING_URL));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(255, 204, 102));
		btnNewButton_1.setBounds(1312, 637, 200, 70);
		contentPane.add(btnNewButton_1);
		
		JButton mailButton = new JButton("Mail");
		mailButton.addActionListener(e -> {
			try {
				desktop.browse(new URI(EMAIL_URL));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		mailButton.setForeground(new Color(255, 255, 255));
		mailButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		mailButton.setBackground(new Color(255, 204, 102));
		mailButton.setBounds(1312, 712, 200, 70);
		contentPane.add(mailButton);
		
		JButton btnNewButton_1_1 = new JButton("Whatsapp");
		btnNewButton_1_1.addActionListener(e -> {
			try {
				desktop.browse(new URI(WHATSAPP_URL));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnNewButton_1_1.setBackground(new Color(255, 204, 102));
		btnNewButton_1_1.setBounds(1312, 561, 200, 70);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnAlloggiati = new JButton("Alloggiati Web");
		btnAlloggiati.addActionListener(e -> {
			try {
				desktop.browse(new URI(ALLOGGIATI_URL));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}

		});
		btnAlloggiati.setForeground(Color.WHITE);
		btnAlloggiati.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		btnAlloggiati.setBackground(new Color(255, 204, 102));
		btnAlloggiati.setBounds(1312, 10, 200, 70);
		contentPane.add(btnAlloggiati);
		
		JLabel lblNewLabel = new JLabel("");
		try {
			lblNewLabel.setIcon(new NoScalingIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("imgs/wallPaper.jpg")))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel.setBounds(198, 0, 1335, 792);
		contentPane.add(lblNewLabel);
	}
	
	@Override
	public void dispose() {
		int yn;
		yn = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?", "Exit", JOptionPane.YES_NO_OPTION);
		if (yn == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}
