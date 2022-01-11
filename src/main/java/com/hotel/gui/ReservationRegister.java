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
		contentPane.setLayout(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		registerPanel.setBackground(new Color(0, 128, 128));
		registerPanel.setBounds(60, 10, 1453, 763);
		contentPane.add(registerPanel);
		registerPanel.setLayout(null);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(224, 255, 255));
		titlePanel.setBounds(0, 0, 1453, 73);
		registerPanel.add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel registerLabel = new JLabel("Reservation register");
		registerLabel.setForeground(new Color(0, 128, 128));
		registerLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerLabel.setBounds(10, 10, 1433, 53);
		titlePanel.add(registerLabel);
		
		research = new JTextField();
		research.setBackground(new Color(224, 255, 255));
		research.setForeground(new Color(0, 128, 128));
		research.setFont(new Font("Tahoma", Font.BOLD, 20));
		research.setBounds(10, 86, 250, 42);
		research.setFocusTraversalKeysEnabled(false);
		registerPanel.add(research);
		research.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(e -> searchResults(research.getText()));
		btnNewButton.setForeground(new Color(0, 128, 128));
		btnNewButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnNewButton.setBackground(new Color(176, 224, 230));
		btnNewButton.setBounds(270, 89, 173, 35);
		registerPanel.add(btnNewButton);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBackground(new Color(0, 139, 139));
		resultPanel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		resultPanel.setBounds(20, 138, 1423, 604);
		registerPanel.add(resultPanel);
		resultPanel.setLayout(null);
		
		JLabel emailAddressLabel = new JLabel("Email");
		emailAddressLabel.setForeground(new Color(224, 255, 255));
		emailAddressLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		emailAddressLabel.setBounds(1014, 337, 205, 28);
		resultPanel.add(emailAddressLabel);
		
		JLabel boardTypeLabel = new JLabel("Board");
		boardTypeLabel.setForeground(new Color(224, 255, 255));
		boardTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		boardTypeLabel.setBounds(1014, 375, 205, 28);
		resultPanel.add(boardTypeLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u20AC");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(1204, 531, 26, 28);
		resultPanel.add(lblNewLabel_1);
		
		JLabel bookingLabel_1 = new JLabel("Id");
		bookingLabel_1.setForeground(new Color(224, 255, 255));
		bookingLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		bookingLabel_1.setBounds(1012, 10, 162, 28);
		resultPanel.add(bookingLabel_1);
		
		JLabel referenceNameLabel_1 = new JLabel("Nominative");
		referenceNameLabel_1.setForeground(new Color(224, 255, 255));
		referenceNameLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		referenceNameLabel_1.setBounds(1014, 43, 162, 28);
		resultPanel.add(referenceNameLabel_1);
		
		JLabel checkinLabel_1 = new JLabel("Check-in");
		checkinLabel_1.setForeground(new Color(224, 255, 255));
		checkinLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		checkinLabel_1.setBounds(1012, 81, 162, 28);
		resultPanel.add(checkinLabel_1);
		
		JLabel checkoutLabel_1 = new JLabel("Check-out");
		checkoutLabel_1.setForeground(new Color(224, 255, 255));
		checkoutLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		checkoutLabel_1.setBounds(1012, 120, 162, 28);
		resultPanel.add(checkoutLabel_1);
		
		JLabel childrenNumberLabel_1 = new JLabel("Children");
		childrenNumberLabel_1.setForeground(new Color(224, 255, 255));
		childrenNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		childrenNumberLabel_1.setBounds(1012, 229, 205, 28);
		resultPanel.add(childrenNumberLabel_1);
		
		JLabel teenagersNumberLabel_1 = new JLabel("Minors");
		teenagersNumberLabel_1.setForeground(new Color(224, 255, 255));
		teenagersNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		teenagersNumberLabel_1.setBounds(1012, 193, 204, 28);
		resultPanel.add(teenagersNumberLabel_1);
		
		JLabel adultsNumberLabel_1 = new JLabel("Adults");
		adultsNumberLabel_1.setForeground(new Color(224, 255, 255));
		adultsNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		adultsNumberLabel_1.setBounds(1013, 155, 161, 28);
		resultPanel.add(adultsNumberLabel_1);
		
		JLabel animalsNumberLabel_1 = new JLabel("Pets");
		animalsNumberLabel_1.setForeground(new Color(224, 255, 255));
		animalsNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		animalsNumberLabel_1.setBounds(1012, 261, 205, 28);
		resultPanel.add(animalsNumberLabel_1);
		
		JLabel phoneNumberLabel_1 = new JLabel("Phone number");
		phoneNumberLabel_1.setForeground(new Color(224, 255, 255));
		phoneNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		phoneNumberLabel_1.setBounds(1012, 299, 205, 28);
		resultPanel.add(phoneNumberLabel_1);
		
		JLabel sourceSpinner_1 = new JLabel("Source");
		sourceSpinner_1.setForeground(new Color(224, 255, 255));
		sourceSpinner_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		sourceSpinner_1.setBounds(1014, 413, 205, 28);
		resultPanel.add(sourceSpinner_1);
		
		JLabel paymentTypeLabel_1 = new JLabel("Paid");
		paymentTypeLabel_1.setForeground(new Color(224, 255, 255));
		paymentTypeLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		paymentTypeLabel_1.setBounds(1014, 490, 205, 28);
		resultPanel.add(paymentTypeLabel_1);
		
		JLabel depositPaidLabel_1 = new JLabel("Deposit paid");
		depositPaidLabel_1.setForeground(new Color(224, 255, 255));
		depositPaidLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		depositPaidLabel_1.setBounds(1014, 528, 204, 28);
		resultPanel.add(depositPaidLabel_1);
		
		id = new JLabel("");
		id.setForeground(new Color(224, 255, 255));
		id.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		id.setBounds(1228, 10, 185, 28);
		resultPanel.add(id);
		
		JLabel totalPaymentLabel = new JLabel("Total");
		totalPaymentLabel.setForeground(new Color(224, 255, 255));
		totalPaymentLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		totalPaymentLabel.setBounds(1014, 566, 204, 28);
		resultPanel.add(totalPaymentLabel);
		
		reference = new JLabel("");
		reference.setForeground(new Color(224, 255, 255));
		reference.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		reference.setBounds(1228, 43, 185, 28);
		resultPanel.add(reference);
		
		checkout = new JLabel("");
		checkout.setForeground(new Color(224, 255, 255));
		checkout.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		checkout.setBounds(1228, 114, 185, 28);
		resultPanel.add(checkout);
		
		checkIn = new JLabel("");
		checkIn.setForeground(new Color(224, 255, 255));
		checkIn.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		checkIn.setBounds(1228, 81, 185, 28);
		resultPanel.add(checkIn);
		
		animalNumberResult = new JLabel("");
		animalNumberResult.setForeground(new Color(224, 255, 255));
		animalNumberResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		animalNumberResult.setBounds(1227, 261, 185, 28);
		resultPanel.add(animalNumberResult);
		
		childrenNumberResult = new JLabel("");
		childrenNumberResult.setForeground(new Color(224, 255, 255));
		childrenNumberResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		childrenNumberResult.setBounds(1227, 223, 185, 28);
		resultPanel.add(childrenNumberResult);
		
		teenagersNumberResult = new JLabel("");
		teenagersNumberResult.setForeground(new Color(224, 255, 255));
		teenagersNumberResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		teenagersNumberResult.setBounds(1227, 185, 185, 28);
		resultPanel.add(teenagersNumberResult);
		
		adultNumberResult = new JLabel("");
		adultNumberResult.setForeground(new Color(224, 255, 255));
		adultNumberResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		adultNumberResult.setBounds(1227, 152, 185, 28);
		resultPanel.add(adultNumberResult);
		
		totalResult = new JLabel("");
		totalResult.setForeground(new Color(224, 255, 255));
		totalResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		totalResult.setBounds(1228, 566, 185, 28);
		resultPanel.add(totalResult);
		
		depositPaidResult = new JLabel("");
		depositPaidResult.setForeground(new Color(224, 255, 255));
		depositPaidResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		depositPaidResult.setBounds(1228, 528, 185, 28);
		resultPanel.add(depositPaidResult);
		
		paymentTypeResult = new JLabel("");
		paymentTypeResult.setForeground(new Color(224, 255, 255));
		paymentTypeResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		paymentTypeResult.setBounds(1228, 490, 185, 28);
		resultPanel.add(paymentTypeResult);
		
		sourceResult = new JLabel("");
		sourceResult.setForeground(new Color(224, 255, 255));
		sourceResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		sourceResult.setBounds(1227, 408, 185, 28);
		resultPanel.add(sourceResult);
		
		typeBoardResult = new JLabel("");
		typeBoardResult.setForeground(new Color(224, 255, 255));
		typeBoardResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		typeBoardResult.setBounds(1228, 370, 185, 28);
		resultPanel.add(typeBoardResult);
		
		emailResult = new JLabel("");
		emailResult.setForeground(new Color(224, 255, 255));
		emailResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		emailResult.setBounds(1228, 337, 185, 28);
		resultPanel.add(emailResult);
		
		phoneNumberResult = new JLabel("");
		phoneNumberResult.setForeground(new Color(224, 255, 255));
		phoneNumberResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		phoneNumberResult.setBounds(1228, 299, 185, 28);
		resultPanel.add(phoneNumberResult);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(994, 0, 5, 604);
		resultPanel.add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(0, 128, 128));
		scrollPane.setBounds(10, 10, 979, 537);
		resultPanel.add(scrollPane);	
	
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
		btnNewButton_1_1.setBounds(341, 557, 272, 37);
		resultPanel.add(btnNewButton_1_1);
		
		JLabel roomsLabel = new JLabel("Reserved rooms");
		roomsLabel.setForeground(new Color(224, 255, 255));
		roomsLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		roomsLabel.setBounds(1014, 451, 205, 28);
		resultPanel.add(roomsLabel);
		
		rooms = new JLabel("");
		rooms.setForeground(new Color(224, 255, 255));
		rooms.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		rooms.setBounds(1228, 451, 185, 28);
		resultPanel.add(rooms);
		
		JPanel leftRegisterPanel = new JPanel();
		leftRegisterPanel.setBackground(new Color(224, 255, 255));
		leftRegisterPanel.setBounds(10, 10, 51, 763);
		contentPane.add(leftRegisterPanel);
		
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