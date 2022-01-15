package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.service.ReservationService;
import com.hotel.util.SwingComponentUtil;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.io.Serial;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ReservationRegister extends JFrame {

	@Serial
	private static final long serialVersionUID = -4144532333900919080L;
	private JTextField research;
	private DefaultListModel<Reservation> reservationDefaultListModel = new DefaultListModel<>();
	private JList<Reservation> reservationJList;
	private JScrollPane scrollPane = new JScrollPane();
	
	private JLabel id;
	private JLabel reference;
	private JLabel checkIn;
	private JLabel checkout;
	private JLabel adultNumberResult;
	private JLabel teenagersNumberResult;
	private JLabel childrenNumberResult;
	private JLabel animalNumberResult;
	private JLabel phoneNumberResult;
	private JLabel emailResult;
	private JLabel typeBoardResult;
	private JLabel sourceResult;
	private JLabel rooms;
	private JLabel paymentTypeResult;
	private JLabel depositPaidResult;
	private JLabel totalResult;
	
	public ReservationRegister(ReservationService reservationService) {
		SwingComponentUtil.addHotelIcons(this);
		reservationDefaultListModel.addAll(reservationService.getAll());
		reservationJList = new JList<>(reservationDefaultListModel);
		scrollPane.setViewportView(reservationJList);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 1537, 820);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		registerPanel.setBackground(new Color(0, 128, 128));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(224, 255, 255));
		
		JLabel registerLabel = new JLabel("Reservation register");
		registerLabel.setForeground(new Color(0, 128, 128));
		registerLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		research = new JTextField();
		research.setBackground(new Color(224, 255, 255));
		research.setForeground(new Color(0, 128, 128));
		research.setFont(new Font("Tahoma", Font.BOLD, 20));
		research.setFocusTraversalKeysEnabled(false);
		research.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(e -> searchResults(research.getText()));
		btnNewButton.setForeground(new Color(0, 128, 128));
		btnNewButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnNewButton.setBackground(new Color(176, 224, 230));
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBackground(new Color(0, 139, 139));
		resultPanel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		
		JLabel emailAddressLabel = new JLabel("Email");
		emailAddressLabel.setForeground(new Color(224, 255, 255));
		emailAddressLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel boardTypeLabel = new JLabel("Board");
		boardTypeLabel.setForeground(new Color(224, 255, 255));
		boardTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel lblNewLabel_1 = new JLabel("\u20AC");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel bookingLabel_1 = new JLabel("Id");
		bookingLabel_1.setForeground(new Color(224, 255, 255));
		bookingLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel referenceNameLabel_1 = new JLabel("Nominative");
		referenceNameLabel_1.setForeground(new Color(224, 255, 255));
		referenceNameLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel checkinLabel_1 = new JLabel("Check-in");
		checkinLabel_1.setForeground(new Color(224, 255, 255));
		checkinLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel checkoutLabel_1 = new JLabel("Check-out");
		checkoutLabel_1.setForeground(new Color(224, 255, 255));
		checkoutLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel childrenNumberLabel_1 = new JLabel("Children");
		childrenNumberLabel_1.setForeground(new Color(224, 255, 255));
		childrenNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel teenagersNumberLabel_1 = new JLabel("Minors");
		teenagersNumberLabel_1.setForeground(new Color(224, 255, 255));
		teenagersNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel adultsNumberLabel_1 = new JLabel("Adults");
		adultsNumberLabel_1.setForeground(new Color(224, 255, 255));
		adultsNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel animalsNumberLabel_1 = new JLabel("Pets");
		animalsNumberLabel_1.setForeground(new Color(224, 255, 255));
		animalsNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel phoneNumberLabel_1 = new JLabel("Phone number");
		phoneNumberLabel_1.setForeground(new Color(224, 255, 255));
		phoneNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel sourceSpinner_1 = new JLabel("Source");
		sourceSpinner_1.setForeground(new Color(224, 255, 255));
		sourceSpinner_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel paymentTypeLabel_1 = new JLabel("Paid");
		paymentTypeLabel_1.setForeground(new Color(224, 255, 255));
		paymentTypeLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel depositPaidLabel_1 = new JLabel("Deposit paid");
		depositPaidLabel_1.setForeground(new Color(224, 255, 255));
		depositPaidLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		id = new JLabel("");
		id.setForeground(new Color(224, 255, 255));
		id.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel totalPaymentLabel = new JLabel("Total");
		totalPaymentLabel.setForeground(new Color(224, 255, 255));
		totalPaymentLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		reference = new JLabel("");
		reference.setForeground(new Color(224, 255, 255));
		reference.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		checkout = new JLabel("");
		checkout.setForeground(new Color(224, 255, 255));
		checkout.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		checkIn = new JLabel("");
		checkIn.setForeground(new Color(224, 255, 255));
		checkIn.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		animalNumberResult = new JLabel("");
		animalNumberResult.setForeground(new Color(224, 255, 255));
		animalNumberResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		childrenNumberResult = new JLabel("");
		childrenNumberResult.setForeground(new Color(224, 255, 255));
		childrenNumberResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		teenagersNumberResult = new JLabel("");
		teenagersNumberResult.setForeground(new Color(224, 255, 255));
		teenagersNumberResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		adultNumberResult = new JLabel("");
		adultNumberResult.setForeground(new Color(224, 255, 255));
		adultNumberResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		totalResult = new JLabel("");
		totalResult.setForeground(new Color(224, 255, 255));
		totalResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		depositPaidResult = new JLabel("");
		depositPaidResult.setForeground(new Color(224, 255, 255));
		depositPaidResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		paymentTypeResult = new JLabel("");
		paymentTypeResult.setForeground(new Color(224, 255, 255));
		paymentTypeResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		sourceResult = new JLabel("");
		sourceResult.setForeground(new Color(224, 255, 255));
		sourceResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		typeBoardResult = new JLabel("");
		typeBoardResult.setForeground(new Color(224, 255, 255));
		typeBoardResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		emailResult = new JLabel("");
		emailResult.setForeground(new Color(224, 255, 255));
		emailResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		phoneNumberResult = new JLabel("");
		phoneNumberResult.setForeground(new Color(224, 255, 255));
		phoneNumberResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(0, 128, 128));
	
		reservationJList = new JList<>(reservationDefaultListModel);
		reservationJList.setSelectionBackground(new Color(0, 128, 128));
		reservationJList.setForeground(new Color(0, 128, 128));
		reservationJList.setBackground(new Color(224, 255, 255));
		reservationJList.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		scrollPane.setViewportView(reservationJList);
		
		JLabel titleJListLabel = new JLabel("Reservations");
		titleJListLabel.setForeground(new Color(0, 128, 128));
		titleJListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleJListLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		scrollPane.setColumnHeaderView(titleJListLabel);
		
		JButton btnNewButton_1_1 = new JButton("Show data");
		btnNewButton_1_1.addActionListener(e -> showSelectedBookingData());
		btnNewButton_1_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnNewButton_1_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1_1.setForeground(new Color(0, 128, 128));
		
		JLabel roomsLabel = new JLabel("Reserved rooms");
		roomsLabel.setForeground(new Color(224, 255, 255));
		roomsLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		rooms = new JLabel("");
		rooms.setForeground(new Color(224, 255, 255));
		rooms.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(registerPanel, GroupLayout.DEFAULT_SIZE, 1508, Short.MAX_VALUE)
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(registerPanel, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
					.addGap(5))
		);
		GroupLayout gl_registerPanel = new GroupLayout(registerPanel);
		gl_registerPanel.setHorizontalGroup(
			gl_registerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_registerPanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_registerPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_registerPanel.createSequentialGroup()
							.addComponent(resultPanel, GroupLayout.DEFAULT_SIZE, 1468, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_registerPanel.createSequentialGroup()
							.addComponent(research, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addGap(994))))
				.addComponent(titlePanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1498, Short.MAX_VALUE)
		);
		gl_registerPanel.setVerticalGroup(
			gl_registerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_registerPanel.createSequentialGroup()
					.addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addGroup(gl_registerPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton, 0, 0, Short.MAX_VALUE)
						.addComponent(research, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
					.addGap(14)
					.addComponent(resultPanel, GroupLayout.PREFERRED_SIZE, 604, Short.MAX_VALUE)
					.addGap(11))
		);
		GroupLayout gl_resultPanel = new GroupLayout(resultPanel);
		gl_resultPanel.setHorizontalGroup(
			gl_resultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_resultPanel.createSequentialGroup()
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(336)
							.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
							.addGap(376)))
					.addGap(4)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(bookingLabel_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(id, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(referenceNameLabel_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(reference, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(checkinLabel_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(checkIn, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(checkoutLabel_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(checkout, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(1)
							.addComponent(adultsNumberLabel_1, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(adultNumberResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(teenagersNumberLabel_1, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(teenagersNumberResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(childrenNumberLabel_1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(childrenNumberResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(animalsNumberLabel_1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(animalNumberResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(phoneNumberLabel_1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(phoneNumberResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(emailAddressLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(emailResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(boardTypeLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(typeBoardResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(sourceSpinner_1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(sourceResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(roomsLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(rooms, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(paymentTypeLabel_1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(paymentTypeResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(depositPaidLabel_1, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_resultPanel.createSequentialGroup()
									.addGap(190)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_resultPanel.createSequentialGroup()
									.addGap(214)
									.addComponent(depositPaidResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(totalPaymentLabel, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(totalResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)))
					.addGap(50))
		);
		gl_resultPanel.setVerticalGroup(
			gl_resultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_resultPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(14))
				.addGroup(gl_resultPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(bookingLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(id, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(referenceNameLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(reference, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(checkinLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkIn, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(checkoutLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(checkout, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(adultsNumberLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(adultNumberResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(8)
							.addComponent(teenagersNumberLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(teenagersNumberResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(childrenNumberLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(childrenNumberResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(animalsNumberLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(animalNumberResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(phoneNumberLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(phoneNumberResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(emailAddressLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(emailResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(boardTypeLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(typeBoardResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(sourceSpinner_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(sourceResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(roomsLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(rooms, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(paymentTypeLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(paymentTypeResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(depositPaidLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(depositPaidResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(totalPaymentLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(totalResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 5, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 604, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		resultPanel.setLayout(gl_resultPanel);
		GroupLayout gl_titlePanel = new GroupLayout(titlePanel);
		gl_titlePanel.setHorizontalGroup(
			gl_titlePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(registerLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1498, Short.MAX_VALUE)
		);
		gl_titlePanel.setVerticalGroup(
			gl_titlePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titlePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(registerLabel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		titlePanel.setLayout(gl_titlePanel);
		registerPanel.setLayout(gl_registerPanel);
		contentPane.setLayout(gl_contentPane);
		
		setVisible(true);
	}
	
	private void searchResults(String charSeq) {
		DefaultListModel<Reservation> results = new DefaultListModel<>();
		for(int i = 0; i < reservationDefaultListModel.size(); i++) {
			if (charSeq.length() != 0) {
				if (reservationDefaultListModel.get(i).toString().contains(charSeq))
					results.addElement(reservationDefaultListModel.get(i));
			}
		}
		if(results.size() > 0) {
			reservationJList.setModel(results);
			scrollPane.setViewportView(reservationJList);
		}
		research.setText("");
	}
	
//	private void refreshBookings() {
//		prenotazioniJList.setModel(prenotazioni);
//		scrollPane.setViewportView(prenotazioniJList);
//	}
	
	private void showSelectedBookingData() {
		if (reservationJList.getSelectedValue() != null) {
			Reservation reservation = reservationJList.getSelectedValue();
			id.setText(Long.toString(reservation.getId()));
			reference.setText(reservation.getSurname());
			checkIn.setText(reservation.getStartDate().toString());
			checkout.setText(reservation.getEndDate().toString());
			adultNumberResult.setText(Integer.toString(reservation.getNumberOfAdults()));
			teenagersNumberResult.setText(Integer.toString(reservation.getNumberOfMinors()));
			childrenNumberResult.setText(Integer.toString(reservation.getNumberOfChilds()));
			animalNumberResult.setText(Integer.toString(reservation.getNumberOfPets()));
			phoneNumberResult.setText(reservation.getPhoneNumber());
			emailResult.setText(reservation.getEmail());
			typeBoardResult.setText(reservation.getBoard().name());
			sourceResult.setText(reservation.getSource().name());
			paymentTypeResult.setText(reservation.getPaid().name());
			depositPaidResult.setText(reservation.getDeposit().toString());
			totalResult.setText(reservation.getTotalCost().toString());
				
			StringBuilder s = new StringBuilder();
			for (Room room : reservation.getRooms())
				s.append(room.getNumber()).append(" ");
			rooms.setText(s.toString());
		}
	}
	
	public void showRequiredData(Reservation reservation) {
		id.setText(Long.toString(reservation.getId()));
		reference.setText(reservation.getSurname());
		checkIn.setText(reservation.getStartDate().toString());
		checkout.setText(reservation.getEndDate().toString());
		adultNumberResult.setText(Integer.toString(reservation.getNumberOfAdults()));
		teenagersNumberResult.setText(Integer.toString(reservation.getNumberOfMinors()));
		childrenNumberResult.setText(Integer.toString(reservation.getNumberOfChilds()));
		animalNumberResult.setText(Integer.toString(reservation.getNumberOfPets()));
		phoneNumberResult.setText(reservation.getPhoneNumber());
		emailResult.setText(reservation.getEmail());
		typeBoardResult.setText(reservation.getBoard().name());
		sourceResult.setText(reservation.getSource().name());
		paymentTypeResult.setText(reservation.getPaid().name());
		depositPaidResult.setText(reservation.getDeposit().toString());
		totalResult.setText(reservation.getTotalCost().toString());
		
		StringBuilder s = new StringBuilder();
		for (Room room : reservation.getRooms())
			s.append(room.getNumber()).append(" ");
		
		rooms.setText(s.toString());
	}

	@Override
	public synchronized void setExtendedState(final int state) {
		if ((state & Planner.MAXIMIZED_BOTH) == Planner.MAXIMIZED_BOTH) {
			final GraphicsConfiguration cfg = getGraphicsConfiguration();
			final Insets screenInsets = getToolkit().getScreenInsets(cfg);
			final Rectangle screenBounds = cfg.getBounds();
			final int x = screenInsets.left;
			final int y = screenInsets.top;
			final int w = screenBounds.width - screenInsets.right - screenInsets.left;
			final int h = screenBounds.height - screenInsets.bottom - screenInsets.top;
			final Rectangle maximizedBounds = new Rectangle(x, y, w, h);
			super.setMaximizedBounds(maximizedBounds);
		}
		super.setExtendedState(state);
	}
}