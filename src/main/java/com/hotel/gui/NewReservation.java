package com.hotel.gui;

import java.awt.*;
import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.entity.Customer;
import com.hotel.entity.Reservation.Source;
import com.hotel.entity.Reservation.Paid;
import com.hotel.entity.Reservation.Board;
import com.hotel.service.RoomService;
import com.hotel.service.CustomerService;
import com.hotel.service.DocumentService;
import com.hotel.service.ReservationService;
import com.hotel.util.DateUtils;
import com.hotel.util.SwingComponentUtil;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JRadioButton;

import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class NewReservation extends JFrame {
	
	@Serial
	private static final long serialVersionUID = 2654050621459550287L;
	private JTextField phoneNumberTextField;
	private JTextField emailAddressTextField;
	private JTextField referenceNameTextField;
	private JTextField depositPaidTextField;
	private JDateChooser checkoutDateChooser;
	private JDateChooser checkinDateChooser;
	private JComboBox<Source> sourceComboBox;
	private JComboBox<Board> boardTypeComboBox;
	private JComboBox<Paid> paymentTypeComboBox;
	private JRadioButton depositRadioButton;
	private JLabel calculatePaymentLabel;
	
	private final Reservation initialReservation;
	private Reservation reservation;
	
	private DefaultListModel<Room> rooms = new DefaultListModel<>();
	private JList<Room> roomsJList;
	private JScrollPane scrollPane;
	private DefaultListModel<Customer> customerList = new DefaultListModel<>();
	private JList<Customer> customerJList;
	private JScrollPane customerScrollPane;
	private JTextField animalsTextField;
	

	public NewReservation(Reservation reservation, ReservationService reservationService, RoomService roomService, CustomerService customerService, DocumentService documentService) {
		this.initialReservation = reservation;
		this.reservation = reservation;
		
		SwingComponentUtil.addHotelIcons(this);
		setResizable(false);
		setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("New reservation");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1537, 820);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel newBookingLabel = new JPanel();
		newBookingLabel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		newBookingLabel.setBackground(new Color(0, 128, 128));
		
		JLabel checkinLabel = new JLabel("Check-in date");
		checkinLabel.setForeground(new Color(224, 255, 255));
		checkinLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		
		JLabel checkoutLabel = new JLabel("Check-out date");
		checkoutLabel.setForeground(new Color(224, 255, 255));
		checkoutLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		
		checkoutDateChooser = new JDateChooser();
		((JTextFieldDateEditor) checkoutDateChooser.getDateEditor()).setEditable(false);
		checkoutDateChooser.setBackground(new Color(224, 255, 255));
		checkoutDateChooser.setForeground(new Color(0, 128, 128));
		checkoutDateChooser.setFont(new Font("Tahoma", Font.BOLD, 20));
		checkoutDateChooser.getDateEditor().addPropertyChangeListener(evt -> {
			if(evt.getPropertyName().equals("date"))
				refreshRooms(roomService,checkinDateChooser.getDate(), checkoutDateChooser.getDate());
		});
		
		JLabel sourceSpinner = new JLabel("Reservation source");
		sourceSpinner.setForeground(new Color(224, 255, 255));
		sourceSpinner.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		
		sourceComboBox = new JComboBox<>();
		sourceComboBox.setModel(new DefaultComboBoxModel<>(new Source[]{Source.BOOKING, Source.HOTEL}));
		sourceComboBox.setSelectedIndex(-1);
		sourceComboBox.setForeground(new Color(0, 128, 128));
		sourceComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		sourceComboBox.setBackground(new Color(224, 255, 255));
		
		JLabel boardTypeLabel = new JLabel("Pension type");
		boardTypeLabel.setForeground(new Color(224, 255, 255));
		boardTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		
		boardTypeComboBox = new JComboBox<>();
		boardTypeComboBox.setModel(new DefaultComboBoxModel<>(new Board[]{Board.FULL_BOARD, Board.HALF_BOARD, Board.BNB}));
		boardTypeComboBox.setSelectedIndex(-1);
		boardTypeComboBox.setForeground(new Color(0, 128, 128));
		boardTypeComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		boardTypeComboBox.setBackground(new Color(224, 255, 255));
		
		JLabel phoneNumberLabel = new JLabel("Phone");
		phoneNumberLabel.setForeground(new Color(224, 255, 255));
		phoneNumberLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setDocument(SwingComponentUtil.numberPlainDocument());
		phoneNumberTextField.setForeground(new Color(0, 128, 128));
		phoneNumberTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		phoneNumberTextField.setColumns(10);
		phoneNumberTextField.setBackground(new Color(224, 255, 255));
		
		JButton saveReservationButton = new JButton("Save reservation");
		saveReservationButton.addActionListener(e -> addBooking(createBooking(), reservationService));
		saveReservationButton.setForeground(new Color(0, 128, 128));
		saveReservationButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		saveReservationButton.setBackground(new Color(224, 255, 255));
		
		JPanel upPanel = new JPanel();
		upPanel.setBackground(new Color(224, 255, 255));
		
		JLabel addBookingLabel = new JLabel("New reservation");
		addBookingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addBookingLabel.setForeground(new Color(0, 128, 128));
		addBookingLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 40));
		
		JButton refreshButton = new JButton("Reset");
		refreshButton.addActionListener(e -> refreshFields());
		refreshButton.setForeground(new Color(0, 128, 128));
		refreshButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		refreshButton.setBackground(new Color(224, 255, 255));
		
		checkinDateChooser = new JDateChooser();
		((JTextFieldDateEditor) checkinDateChooser.getDateEditor()).setEditable(false);
		checkinDateChooser.setForeground(new Color(0, 128, 128));
		checkinDateChooser.setFont(new Font("Tahoma", Font.BOLD, 20));
		checkinDateChooser.getDateEditor().addPropertyChangeListener(evt -> {
			if(evt.getPropertyName().equals("date"))
				refreshRooms(roomService, checkinDateChooser.getDate(), checkoutDateChooser.getDate());
		});
		
		emailAddressTextField = new JTextField();
		emailAddressTextField.setForeground(new Color(0, 128, 128));
		emailAddressTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBackground(new Color(224, 255, 255));
		
		JLabel emailAddressLabel = new JLabel("Email");
		emailAddressLabel.setForeground(new Color(224, 255, 255));
		emailAddressLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		
		JButton calculatePaymentButton = new JButton("Calculate total");
		calculatePaymentButton.addActionListener(e -> {
			NewReservation.this.reservation = createBooking();
			if(NewReservation.this.reservation != null)
				calculatePaymentLabel.setText("\u20AC. " + NewReservation.this.reservation.getTotalCost().toString());
		});
		calculatePaymentButton.setForeground(new Color(0, 128, 128));
		calculatePaymentButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		calculatePaymentButton.setBackground(new Color(224, 255, 255));
		
		JPanel panel = new JPanel();
		
		calculatePaymentLabel = new JLabel("\u20AC. ");
		calculatePaymentLabel.setForeground(new Color(0, 128, 128));
		calculatePaymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		calculatePaymentLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel paymentTypeLabel = new JLabel("Payment type");
		paymentTypeLabel.setForeground(new Color(224, 255, 255));
		paymentTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		
		paymentTypeComboBox = new JComboBox<>();
		paymentTypeComboBox.setModel(new DefaultComboBoxModel<>(new Paid[]{Paid.PAID, Paid.NOT_PAID, Paid.DEPOSIT}));
		paymentTypeComboBox.setSelectedIndex(-1);
		paymentTypeComboBox.setForeground(new Color(0, 128, 128));
		paymentTypeComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		paymentTypeComboBox.setBackground(new Color(224, 255, 255));
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(224, 255, 255));
		
		roomsJList = new JList<>(rooms);
		roomsJList.setSelectionBackground(new Color(0, 128, 128));
		roomsJList.setForeground(new Color(0, 128, 128));
		roomsJList.setFont(new Font("Tahoma", Font.BOLD, 17));
		roomsJList.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(roomsJList);
		
		JLabel roomsLabel = new JLabel("Reserved rooms");
		roomsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roomsLabel.setOpaque(true);
		roomsLabel.setBackground(new Color(224, 255, 255));
		roomsLabel.setForeground(new Color(0, 128, 128));
		roomsLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		scrollPane.setColumnHeaderView(roomsLabel);
		
		referenceNameTextField = new JTextField();
		referenceNameTextField.setForeground(new Color(0, 128, 128));
		referenceNameTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		referenceNameTextField.setColumns(10);
		referenceNameTextField.setBackground(new Color(224, 255, 255));
		
		JLabel referenceNameLabel = new JLabel("Nominative");
		referenceNameLabel.setForeground(new Color(224, 255, 255));
		referenceNameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		
		depositPaidTextField = new JTextField();
		depositPaidTextField.setDocument(SwingComponentUtil.financesPlainDocument());
		depositPaidTextField.setEnabled(false);
		depositPaidTextField.setEditable(false);
		depositPaidTextField.setForeground(new Color(0, 128, 128));
		depositPaidTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		depositPaidTextField.setColumns(10);
		depositPaidTextField.setBackground(new Color(224, 255, 255));
		
		JLabel depositPaidLabel = new JLabel("Deposit paid");
		depositPaidLabel.setForeground(new Color(224, 255, 255));
		depositPaidLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		
		depositRadioButton = new JRadioButton("Deposit");
		depositRadioButton.setBackground(new Color(0, 128, 128));
		depositRadioButton.addActionListener(e -> {
			if(depositRadioButton.isSelected()) {
				depositPaidTextField.setEnabled(true);
				depositPaidTextField.setEditable(true);
			} else {
				depositPaidTextField.setEnabled(false);
				depositPaidTextField.setEditable(false);
			}
		});
		
		JPanel euroPanel = new JPanel();
		euroPanel.setBackground(new Color(224, 255, 255));
		
		JLabel lblNewLabel_1 = new JLabel("\u20AC");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel animalsNumberLabel = new JLabel("Pets");
		animalsNumberLabel.setForeground(new Color(224, 255, 255));
		animalsNumberLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		
		customerJList = new JList<>(customerList);
		customerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerJList.setFont(new Font("Dialog", Font.PLAIN, 23));
		customerJList.setForeground(new Color(0, 128, 128));
		customerJList.setBackground(new Color(224, 255, 255));
		customerJList.setBounds(755, 523, 479, 130);
		customerScrollPane = new JScrollPane(customerJList);
		
		JButton addCustomer = new JButton("Add customer");
		addCustomer.setForeground(new Color(0, 128, 128));
		addCustomer.setFont(new Font("Dialog", Font.PLAIN, 33));
		addCustomer.setBackground(new Color(224, 255, 255));
		addCustomer.addActionListener(e -> {
			new newCustomer(NewReservation.this.reservation, reservationService, customerService, documentService, customerList);
			refreshCustomers();
		});
		
		JButton deleteCustomerButton = new JButton("Delete customer");
		deleteCustomerButton.setBackground(new Color(224, 255, 255));
		deleteCustomerButton.setForeground(new Color(0, 128, 128));
		deleteCustomerButton.setFont(new Font("Dialog", Font.PLAIN, 33));
		deleteCustomerButton.addActionListener(e -> {
			Customer selectedCustomer = customerJList.getSelectedValue();
			if(selectedCustomer == null)
				JOptionPane.showMessageDialog(null, "Select a customer to delete");
			else {
				reservation.getCustomers().remove(selectedCustomer);
				reservationService.saveReservation(reservation);
				customerService.deleteCustomer(selectedCustomer);
				customerList.removeElement(selectedCustomer);
				refreshCustomers();
				JOptionPane.showMessageDialog(null, "Customer deleted");
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(newBookingLabel, GroupLayout.DEFAULT_SIZE, 1513, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(15)
					.addComponent(newBookingLabel, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
					.addGap(17))
		);
		
		animalsTextField = new JTextField();
		animalsTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		animalsTextField.setForeground(new Color(0, 128, 128));
		animalsTextField.setBackground(new Color(224, 255, 255));
		animalsTextField.setColumns(10);
		GroupLayout gl_newBookingLabel = new GroupLayout(newBookingLabel);
		gl_newBookingLabel.setHorizontalGroup(
			gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newBookingLabel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_newBookingLabel.createSequentialGroup()
							.addGap(2)
							.addComponent(phoneNumberLabel, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(phoneNumberTextField, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
							.addGap(2))
						.addGroup(gl_newBookingLabel.createSequentialGroup()
							.addGap(2)
							.addComponent(emailAddressLabel, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(emailAddressTextField, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
							.addGap(2))
						.addGroup(Alignment.LEADING, gl_newBookingLabel.createSequentialGroup()
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_newBookingLabel.createSequentialGroup()
									.addGap(1)
									.addComponent(checkoutLabel, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
								.addComponent(checkinLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
								.addComponent(referenceNameLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
								.addComponent(boardTypeLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
								.addComponent(sourceSpinner, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
								.addComponent(animalsNumberLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_newBookingLabel.createSequentialGroup()
									.addGap(3)
									.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
										.addComponent(animalsTextField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
										.addComponent(checkoutDateChooser, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
										.addGroup(gl_newBookingLabel.createSequentialGroup()
											.addComponent(checkinDateChooser, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
											.addGap(1))
										.addGroup(Alignment.TRAILING, gl_newBookingLabel.createSequentialGroup()
											.addComponent(referenceNameTextField, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGap(1))
								.addGroup(Alignment.TRAILING, gl_newBookingLabel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.TRAILING)
										.addComponent(sourceComboBox, Alignment.LEADING, 0, 428, Short.MAX_VALUE)
										.addComponent(boardTypeComboBox, Alignment.LEADING, 0, 428, Short.MAX_VALUE))
									.addGap(2)))))
					.addGap(37)
					.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
						.addGroup(gl_newBookingLabel.createSequentialGroup()
							.addComponent(calculatePaymentButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(64)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_newBookingLabel.createSequentialGroup()
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(paymentTypeLabel, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addComponent(depositPaidLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
							.addGap(6)
							.addComponent(depositRadioButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_newBookingLabel.createSequentialGroup()
									.addComponent(euroPanel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addGap(379))
								.addGroup(Alignment.LEADING, gl_newBookingLabel.createSequentialGroup()
									.addGap(36)
									.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
										.addComponent(paymentTypeComboBox, Alignment.TRAILING, 0, 380, Short.MAX_VALUE)
										.addComponent(depositPaidTextField, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))))))
					.addGap(23))
				.addGroup(gl_newBookingLabel.createSequentialGroup()
					.addGap(351)
					.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
						.addComponent(addCustomer, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
						.addComponent(deleteCustomerButton, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
					.addGap(38)
					.addComponent(customerScrollPane, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
					.addGap(259))
				.addGroup(gl_newBookingLabel.createSequentialGroup()
					.addGap(145)
					.addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
					.addGap(429)
					.addComponent(saveReservationButton, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
					.addGap(187))
				.addComponent(upPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1493, Short.MAX_VALUE)
		);
		gl_newBookingLabel.setVerticalGroup(
			gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_newBookingLabel.createSequentialGroup()
					.addComponent(upPanel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_newBookingLabel.createSequentialGroup()
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(sourceComboBox, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(sourceSpinner, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE))
							.addGap(14)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(boardTypeComboBox, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(boardTypeLabel, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE))
							.addGap(14)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(referenceNameTextField, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(referenceNameLabel, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE))
							.addGap(14)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(checkinLabel, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)
								.addComponent(checkinDateChooser, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(checkoutDateChooser, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(checkoutLabel, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE))
							.addGap(14)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(animalsTextField, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(animalsNumberLabel, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE))
							.addGap(14)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(phoneNumberTextField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(phoneNumberLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_newBookingLabel.createSequentialGroup()
									.addComponent(emailAddressLabel, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)
									.addGap(30))
								.addComponent(emailAddressTextField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_newBookingLabel.createSequentialGroup()
							.addGap(3)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
							.addGap(22)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(calculatePaymentButton, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(paymentTypeComboBox, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(paymentTypeLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
								.addComponent(depositPaidLabel, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)
								.addGroup(gl_newBookingLabel.createSequentialGroup()
									.addGap(10)
									.addComponent(depositRadioButton))
								.addComponent(euroPanel, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(depositPaidTextField, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))))
					.addGap(34)
					.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_newBookingLabel.createSequentialGroup()
							.addComponent(addCustomer, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(deleteCustomerButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
						.addComponent(customerScrollPane, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
					.addGap(13)
					.addGroup(gl_newBookingLabel.createParallelGroup(Alignment.LEADING)
						.addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(saveReservationButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addGap(20))
		);
		GroupLayout gl_euroPanel = new GroupLayout(euroPanel);
		gl_euroPanel.setHorizontalGroup(
			gl_euroPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_euroPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
		);
		gl_euroPanel.setVerticalGroup(
			gl_euroPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
		);
		euroPanel.setLayout(gl_euroPanel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(calculatePaymentLabel, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(calculatePaymentLabel, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_upPanel = new GroupLayout(upPanel);
		gl_upPanel.setHorizontalGroup(
			gl_upPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(addBookingLabel, GroupLayout.DEFAULT_SIZE, 1493, Short.MAX_VALUE)
		);
		gl_upPanel.setVerticalGroup(
			gl_upPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_upPanel.createSequentialGroup()
					.addComponent(addBookingLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		upPanel.setLayout(gl_upPanel);
		newBookingLabel.setLayout(gl_newBookingLabel);
		contentPane.setLayout(gl_contentPane);
		
		refreshFields();
		setVisible(true);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public NewReservation(ReservationService reservationService, RoomService roomService, CustomerService customerService, DocumentService documentService) {
		this(null, reservationService, roomService, customerService, documentService);
	}
	
	public void refreshFields() {
		if(initialReservation != null) {
			phoneNumberTextField.setText(initialReservation.getPhoneNumber());
			emailAddressTextField.setText(initialReservation.getEmail());
			referenceNameTextField.setText(initialReservation.getSurname());
			depositPaidTextField.setText(initialReservation.getDeposit().toString());
			checkinDateChooser.setDate(DateUtils.convertLocalDateToDate(initialReservation.getStartDate()));
			checkoutDateChooser.setDate(DateUtils.convertLocalDateToDate(initialReservation.getEndDate()));
			sourceComboBox.setSelectedItem(initialReservation.getSource());
			boardTypeComboBox.setSelectedItem(initialReservation.getBoard());
			paymentTypeComboBox.setSelectedItem(initialReservation.getPaid());
			animalsTextField.setText(String.valueOf(initialReservation.getNumberOfPets()));
			roomsJList.setSelectedIndices(findRooms());
			if(reservation.getDeposit().intValue() != 0) {
				depositRadioButton.setSelected(true);
				depositPaidTextField.setText(initialReservation.getDeposit().toString());
			} else {
				depositRadioButton.setSelected(false);
				depositPaidTextField.setText("");
			}
			calculatePaymentLabel.setText("\u20AC. " + initialReservation.getTotalCost().toString());
			
			customerList = new DefaultListModel<>();
			customerList.addAll(initialReservation.getCustomers());
			refreshCustomers();
		} else {
			phoneNumberTextField.setText("");
			emailAddressTextField.setText("");
			referenceNameTextField.setText("");
			depositPaidTextField.setText("");
			checkinDateChooser.setDate(null);
			checkoutDateChooser.setDate(null);
			sourceComboBox.setSelectedIndex(-1);
			boardTypeComboBox.setSelectedIndex(-1);
			paymentTypeComboBox.setSelectedIndex(-1);
			animalsTextField.setText("");
			roomsJList.clearSelection();
			depositRadioButton.setSelected(false);
			depositPaidTextField.setText("");
			calculatePaymentLabel.setText("\u20AC. ");
			
			this.customerList = new DefaultListModel<>();

			refreshCustomers();
		}
	}

	private Reservation createBooking() {
		if(checkFields()) {
			String surname = referenceNameTextField.getText();
			String email = emailAddressTextField.getText();
			String phoneNumber = phoneNumberTextField.getText();
			LocalDate startDate = DateUtils.convertDateToLocalDate(checkinDateChooser.getDate());
			LocalDate endDate = DateUtils.convertDateToLocalDate(checkoutDateChooser.getDate());
			int pets = Integer.valueOf(animalsTextField.getText());
			Paid paid = (Paid) paymentTypeComboBox.getSelectedItem();
			Board board = (Board) boardTypeComboBox.getSelectedItem();
			Source source = (Source) sourceComboBox.getSelectedItem();
			Set<Customer> customers = Arrays.stream(customerList.toArray()).map(x -> (Customer) x).collect(Collectors.toSet());
			Set<Room> rooms = new HashSet<>(roomsJList.getSelectedValuesList());
			BigDecimal deposit = BigDecimal.ZERO;
			if(depositRadioButton.isSelected())
				if(checkDeposit())
					deposit = BigDecimal.valueOf(Double.parseDouble(depositPaidTextField.getText()));
				else
					return null;
			if(reservation != null) {
				reservation.setSurname(surname);
				reservation.setEmail(email);
				reservation.setPhoneNumber(phoneNumber);
				reservation.setStartDate(startDate);
				reservation.setEndDate(endDate);
				reservation.setNumberOfPets(pets);
				reservation.setPaid(paid);
				reservation.setDeposit(deposit);
				reservation.setBoard(board);
				reservation.setSource(source);
				reservation.setCustomers(customers);
				reservation.setRooms(rooms);
			} else
				reservation = new Reservation(surname, email, phoneNumber, startDate, endDate,
						pets, paid, deposit, board, source, customers, rooms);
			return reservation;
		}
		return null;
	}
	
	private void addBooking(Reservation reservation, ReservationService reservationService) {
		if(reservation != null) {
			reservationService.saveReservation(reservation);
			JOptionPane.showMessageDialog(null, "Reservation saved correctly");
			dispose();
		}
		else
			JOptionPane.showMessageDialog(null, "An error occurred");
	}
	
	public int[] findRooms() {
		List<Integer> roomIndicies = new ArrayList<>();
		for (int i = 0; i < rooms.size(); i++) {
			if(reservation.getRooms().contains(rooms.get(i)))
				roomIndicies.add(i);
		}
		return roomIndicies.stream().mapToInt(i -> i).toArray();
	}
	
	private boolean checkPhoneNumber() {
		if(!phoneNumberTextField.getText().isBlank()) return true;
		else {
			JOptionPane.showMessageDialog(null, "Enter a valid phone number");
			phoneNumberTextField.setText("");
		}
		return false;
	}
	
	private boolean checkEmail() {
		if(!emailAddressTextField.getText().isBlank()) return true;
		else {
			JOptionPane.showMessageDialog(null, "Enter a valid email");
			emailAddressTextField.setText("");
		}
		return false;
	}
	
	private boolean checkReference() {
		if(!referenceNameTextField.getText().isBlank()) return true;
		else {
			JOptionPane.showMessageDialog(null, "Enter a valid name");
			referenceNameTextField.setText("");
		}
		return false;
	}
	
	private boolean checkDeposit() {
		if(!depositPaidTextField.getText().isBlank()) return true;
		else {
			JOptionPane.showMessageDialog(null, "Enter a valid amount for the deposit");
			depositPaidTextField.setText("");
		}
		return false;
	}
	
	private boolean checkSource() {
		if(sourceComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "Select the source of the reservation");
		return false;
	}
	
	private boolean checkBoardType() {
		if(boardTypeComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "Select the type of board");
		return false;
	}
	
	private boolean checkPaymentType() {
		if(paymentTypeComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "Select the type of payment");
		return false;
	}
	
	private boolean roomsCheck() {
		if(roomsJList.getSelectedValuesList().size() > 0) return true;
		JOptionPane.showMessageDialog(null, "Select at least one room");
		return false;
	}
	
	private boolean checkCheckInDate() {
		if(checkinDateChooser.getDate() != null) return true;
//		if(DateUtils.convertDateToLocalDate(checkinDateChooser.getDate()).isAfter(LocalDate.now().minusDays(1)))
//			return true;
		JOptionPane.showMessageDialog(null, "Select a valid start date");
		return false;
	}
	
	private boolean checkCheckOutDate() {
		if(checkoutDateChooser.getDate() != null) return true;
//		if(DateUtils.convertDateToLocalDate(checkinDateChooser.getDate()).isBefore(DateUtils.convertDateToLocalDate(checkoutDateChooser.getDate())))
//			return true;
		JOptionPane.showMessageDialog(null, "Select a valid end date");
		return false;
	}
	
	private boolean checkPets() {
		if(Integer.valueOf(animalsTextField.getText()) >= 0) return true;
		JOptionPane.showMessageDialog(null, "Select a valid number of pets");
		return false;
	}
	
	private boolean checkFields() {
		return checkBoardType() && checkEmail() && checkPaymentType()
				&& checkPhoneNumber() && checkReference() && checkSource()
				&& roomsCheck() && checkCheckInDate() && checkCheckOutDate()
				&& checkPets();
	}
	
	private void refreshCustomers() {
		customerJList.setModel(customerList);
		customerScrollPane.setViewportView(customerJList);
	}
	
	private void refreshRooms(RoomService roomService, Date start, Date end) {
		rooms = new DefaultListModel<>();
		
		if(start != null && end != null) {
			LocalDate localDateStart = DateUtils.convertDateToLocalDate(start);
			LocalDate localDateEnd = DateUtils.convertDateToLocalDate(end);
			Set<Room> availableRooms = roomService.findAvailableRooms(localDateStart, localDateEnd);
			if(initialReservation != null && initialReservation.getStartDate().equals(localDateStart)
					&& initialReservation.getEndDate().equals(localDateEnd))
				availableRooms.addAll(reservation.getRooms());
			rooms.addAll(availableRooms.stream()
					.toList().stream()
					.sorted(Comparator.comparingInt(c -> c.getRoomType().getType().getPeople())).toList());
		}
		
		roomsJList.setModel(rooms);
		scrollPane.setViewportView(roomsJList);
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