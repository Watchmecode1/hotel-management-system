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
import javax.swing.JSpinner;
import javax.swing.JRadioButton;

import javax.swing.ListSelectionModel;

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
	private JSpinner animalsNumberSpinner;
	private JRadioButton depositRadioButton;
	private JLabel calculatePaymentLabel;
	
	private final Reservation initialReservation;
	private Reservation reservation;
	
	private DefaultListModel<Room> rooms = new DefaultListModel<>();
	private JList<Room> roomsJList = new JList<>(rooms);
	private JScrollPane scrollPane = new JScrollPane();
	private DefaultListModel<Customer> customerList = new DefaultListModel<>();
	private JList<Customer> customerJList;
	private JScrollPane customerScrollPane;
	

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
		contentPane.setLayout(null);
		
		JPanel newBookingLabel = new JPanel();
		newBookingLabel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		newBookingLabel.setBackground(new Color(0, 128, 128));
		newBookingLabel.setBounds(33, 20, 1480, 741);
		contentPane.add(newBookingLabel);
		newBookingLabel.setLayout(null);
		
		JLabel checkinLabel = new JLabel("Check-in date");
		checkinLabel.setForeground(new Color(224, 255, 255));
		checkinLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		checkinLabel.setBounds(10, 210, 250, 36);
		newBookingLabel.add(checkinLabel);
		
		JLabel checkoutLabel = new JLabel("Check-out date");
		checkoutLabel.setForeground(new Color(224, 255, 255));
		checkoutLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		checkoutLabel.setBounds(11, 260, 250, 36);
		newBookingLabel.add(checkoutLabel);
		
		checkoutDateChooser = new JDateChooser();
		((JTextFieldDateEditor) checkoutDateChooser.getDateEditor()).setEditable(false);
		checkoutDateChooser.setBackground(new Color(224, 255, 255));
		checkoutDateChooser.setForeground(new Color(0, 128, 128));
		checkoutDateChooser.setFont(new Font("Tahoma", Font.BOLD, 20));
		checkoutDateChooser.setBounds(300, 260, 417, 36);
		checkoutDateChooser.getDateEditor().addPropertyChangeListener(evt -> {
			if(evt.getPropertyName().equals("date"))
				refreshRooms(roomService,checkinDateChooser.getDate(), checkoutDateChooser.getDate());
		});
		newBookingLabel.add(checkoutDateChooser);
		
		JLabel sourceSpinner = new JLabel("Reservation source");
		sourceSpinner.setForeground(new Color(224, 255, 255));
		sourceSpinner.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		sourceSpinner.setBounds(10, 60, 295, 36);
		newBookingLabel.add(sourceSpinner);
		
		sourceComboBox = new JComboBox<>();
		sourceComboBox.setModel(new DefaultComboBoxModel<>(new Source[]{Source.BOOKING, Source.HOTEL}));
		sourceComboBox.setSelectedIndex(-1);
		sourceComboBox.setForeground(new Color(0, 128, 128));
		sourceComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		sourceComboBox.setBackground(new Color(224, 255, 255));
		sourceComboBox.setBounds(300, 60, 416, 36);
		newBookingLabel.add(sourceComboBox);
		
		JLabel boardTypeLabel = new JLabel("Pension type");
		boardTypeLabel.setForeground(new Color(224, 255, 255));
		boardTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		boardTypeLabel.setBounds(10, 110, 280, 36);
		newBookingLabel.add(boardTypeLabel);
		
		boardTypeComboBox = new JComboBox<>();
		boardTypeComboBox.setModel(new DefaultComboBoxModel<>(new Board[]{Board.FULL_BOARD, Board.HALF_BOARD, Board.BNB}));
		boardTypeComboBox.setSelectedIndex(-1);
		boardTypeComboBox.setForeground(new Color(0, 128, 128));
		boardTypeComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		boardTypeComboBox.setBackground(new Color(224, 255, 255));
		boardTypeComboBox.setBounds(300, 110, 416, 36);
		newBookingLabel.add(boardTypeComboBox);
		
		JLabel phoneNumberLabel = new JLabel("Phone");
		phoneNumberLabel.setForeground(new Color(224, 255, 255));
		phoneNumberLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		phoneNumberLabel.setBounds(12, 360, 250, 36);
		newBookingLabel.add(phoneNumberLabel);
		
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setDocument(SwingComponentUtil.numberPlainDocument());
		phoneNumberTextField.setForeground(new Color(0, 128, 128));
		phoneNumberTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		phoneNumberTextField.setColumns(10);
		phoneNumberTextField.setBackground(new Color(224, 255, 255));
		phoneNumberTextField.setBounds(300, 360, 416, 36);
		newBookingLabel.add(phoneNumberTextField);
		
		JButton saveReservationButton = new JButton("Save reservation");
		saveReservationButton.addActionListener(e -> addBooking(createBooking(), reservationService));
		saveReservationButton.setBounds(940, 663, 366, 48);
		newBookingLabel.add(saveReservationButton);
		saveReservationButton.setForeground(new Color(0, 128, 128));
		saveReservationButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		saveReservationButton.setBackground(new Color(224, 255, 255));
		
		JPanel upPanel = new JPanel();
		upPanel.setBackground(new Color(224, 255, 255));
		upPanel.setBounds(0, 0, 1480, 48);
		newBookingLabel.add(upPanel);
		upPanel.setLayout(null);
		
		JLabel addBookingLabel = new JLabel("New reservation");
		addBookingLabel.setBounds(0, 0, 1480, 46);
		upPanel.add(addBookingLabel);
		addBookingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addBookingLabel.setForeground(new Color(0, 128, 128));
		addBookingLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 40));
		
		JButton refreshButton = new JButton("Reset");
		refreshButton.addActionListener(e -> refreshFields());
		refreshButton.setForeground(new Color(0, 128, 128));
		refreshButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		refreshButton.setBackground(new Color(224, 255, 255));
		refreshButton.setBounds(145, 663, 366, 48);
		newBookingLabel.add(refreshButton);
		
		checkinDateChooser = new JDateChooser();
		((JTextFieldDateEditor) checkinDateChooser.getDateEditor()).setEditable(false);
		checkinDateChooser.setForeground(new Color(0, 128, 128));
		checkinDateChooser.setFont(new Font("Tahoma", Font.BOLD, 20));
		checkinDateChooser.setBounds(300, 210, 417, 36);
		checkinDateChooser.getDateEditor().addPropertyChangeListener(evt -> {
			if(evt.getPropertyName().equals("date"))
				refreshRooms(roomService, checkinDateChooser.getDate(), checkoutDateChooser.getDate());
		});
		newBookingLabel.add(checkinDateChooser);
		
		emailAddressTextField = new JTextField();
		emailAddressTextField.setForeground(new Color(0, 128, 128));
		emailAddressTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBackground(new Color(224, 255, 255));
		emailAddressTextField.setBounds(300, 410, 416, 36);
		newBookingLabel.add(emailAddressTextField);
		
		JLabel emailAddressLabel = new JLabel("Email");
		emailAddressLabel.setForeground(new Color(224, 255, 255));
		emailAddressLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		emailAddressLabel.setBounds(12, 410, 250, 36);
		newBookingLabel.add(emailAddressLabel);
		
		JButton calculatePaymentButton = new JButton("Calculate total");
		calculatePaymentButton.addActionListener(e -> {
			NewReservation.this.reservation = createBooking();
			if(NewReservation.this.reservation != null)
				calculatePaymentLabel.setText("\u20AC. " + NewReservation.this.reservation.getTotalCost().toString());
		});
		calculatePaymentButton.setBounds(755, 340, 271, 36);
		newBookingLabel.add(calculatePaymentButton);
		calculatePaymentButton.setForeground(new Color(0, 128, 128));
		calculatePaymentButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		calculatePaymentButton.setBackground(new Color(224, 255, 255));
		
		JPanel panel = new JPanel();
		panel.setBounds(1090, 340, 380, 36);
		newBookingLabel.add(panel);
		panel.setLayout(null);
		
		calculatePaymentLabel = new JLabel("\u20AC. ");
		calculatePaymentLabel.setForeground(new Color(0, 128, 128));
		calculatePaymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		calculatePaymentLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		calculatePaymentLabel.setBounds(0, 0, 417, 36);
		panel.add(calculatePaymentLabel);
		
		JLabel paymentTypeLabel = new JLabel("Payment type");
		paymentTypeLabel.setForeground(new Color(224, 255, 255));
		paymentTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		paymentTypeLabel.setBounds(755, 390, 309, 36);
		newBookingLabel.add(paymentTypeLabel);
		
		paymentTypeComboBox = new JComboBox<>();
		paymentTypeComboBox.setModel(new DefaultComboBoxModel<>(new Paid[]{Paid.PAID, Paid.NOT_PAID, Paid.DEPOSIT}));
		paymentTypeComboBox.setSelectedIndex(-1);
		paymentTypeComboBox.setForeground(new Color(0, 128, 128));
		paymentTypeComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		paymentTypeComboBox.setBackground(new Color(224, 255, 255));
		paymentTypeComboBox.setBounds(1090, 390, 380, 36);
		newBookingLabel.add(paymentTypeComboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(224, 255, 255));
		scrollPane.setBounds(755, 63, 715, 255);
		newBookingLabel.add(scrollPane);
		
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
		referenceNameTextField.setBounds(300, 160, 418, 36);
		newBookingLabel.add(referenceNameTextField);
		
		JLabel referenceNameLabel = new JLabel("Nominative");
		referenceNameLabel.setForeground(new Color(224, 255, 255));
		referenceNameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		referenceNameLabel.setBounds(10, 160, 250, 36);
		newBookingLabel.add(referenceNameLabel);
		
		depositPaidTextField = new JTextField();
		depositPaidTextField.setDocument(SwingComponentUtil.financesPlainDocument());
		depositPaidTextField.setEnabled(false);
		depositPaidTextField.setEditable(false);
		depositPaidTextField.setForeground(new Color(0, 128, 128));
		depositPaidTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		depositPaidTextField.setColumns(10);
		depositPaidTextField.setBackground(new Color(224, 255, 255));
		depositPaidTextField.setBounds(1090, 440, 380, 36);
		newBookingLabel.add(depositPaidTextField);
		
		JLabel depositPaidLabel = new JLabel("Deposit paid");
		depositPaidLabel.setForeground(new Color(224, 255, 255));
		depositPaidLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		depositPaidLabel.setBounds(755, 440, 242, 36);
		newBookingLabel.add(depositPaidLabel);
		
		depositRadioButton = new JRadioButton("Deposit");
		depositRadioButton.setBackground(new Color(0, 128, 128));
		depositRadioButton.setBounds(1003, 450, 22, 21);
		depositRadioButton.addActionListener(e -> {
			if(depositRadioButton.isSelected()) {
				depositPaidTextField.setEnabled(true);
				depositPaidTextField.setEditable(true);
			} else {
				depositPaidTextField.setEnabled(false);
				depositPaidTextField.setEditable(false);
			}
		});
		newBookingLabel.add(depositRadioButton);
		
		JPanel euroPanel = new JPanel();
		euroPanel.setBackground(new Color(224, 255, 255));
		euroPanel.setBounds(1054, 440, 37, 36);
		newBookingLabel.add(euroPanel);
		euroPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\u20AC");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 0, 26, 36);
		euroPanel.add(lblNewLabel_1);
		
		JLabel animalsNumberLabel = new JLabel("Pets");
		animalsNumberLabel.setForeground(new Color(224, 255, 255));
		animalsNumberLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		animalsNumberLabel.setBounds(10, 310, 250, 36);
		newBookingLabel.add(animalsNumberLabel);
		
		animalsNumberSpinner = SwingComponentUtil.numberJSpinner();
		animalsNumberSpinner.setForeground(new Color(0, 128, 128));
		animalsNumberSpinner.setFont(new Font("Tahoma", Font.BOLD, 17));
		animalsNumberSpinner.setBounds(300, 310, 415, 36);
		newBookingLabel.add(animalsNumberSpinner);
		
		customerJList = new JList<>(customerList);
		customerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerJList.setFont(new Font("Dialog", Font.PLAIN, 23));
		customerJList.setForeground(new Color(0, 128, 128));
		customerJList.setBackground(new Color(224, 255, 255));
		customerJList.setBounds(755, 523, 479, 130);
		customerScrollPane = new JScrollPane(customerJList);
		customerScrollPane.setBounds(755, 510, 479, 140);
		newBookingLabel.add(customerScrollPane);
		
		JButton addCustomer = new JButton("Add customer");
		addCustomer.setForeground(new Color(0, 128, 128));
		addCustomer.setFont(new Font("Dialog", Font.PLAIN, 33));
		addCustomer.setBackground(new Color(224, 255, 255));
		addCustomer.setBounds(351, 510, 366, 65);
		addCustomer.addActionListener(e -> {
			new newCustomer(NewReservation.this.reservation, reservationService, customerService, documentService, customerList);
			refreshCustomers();
		});
		newBookingLabel.add(addCustomer);
		
		JButton deleteCustomerButton = new JButton("Delete customer");
		deleteCustomerButton.setBackground(new Color(224, 255, 255));
		deleteCustomerButton.setForeground(new Color(0, 128, 128));
		deleteCustomerButton.setFont(new Font("Dialog", Font.PLAIN, 33));
		deleteCustomerButton.setBounds(351, 585, 366, 65);
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
		newBookingLabel.add(deleteCustomerButton);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(224, 255, 255));
		leftPanel.setBounds(10, 20, 24, 741);
		contentPane.add(leftPanel);
		
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
			animalsNumberSpinner.setValue(initialReservation.getNumberOfPets());
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
			animalsNumberSpinner.setValue(0);
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
			int pets = (int) animalsNumberSpinner.getValue();
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
		if((int) animalsNumberSpinner.getValue() >= 0) return true;
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