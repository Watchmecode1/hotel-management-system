package com.hotel.gui;

import java.awt.*;
import java.io.Serial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hotel.entity.Customer;
import com.hotel.entity.Document;
import com.hotel.entity.Reservation;
import com.hotel.entity.Customer.Housed;
import com.hotel.entity.Customer.Gender;
import com.hotel.entity.Document.Release;
import com.hotel.entity.Document.DocumentType;
import com.hotel.service.CustomerService;
import com.hotel.service.DocumentService;
import com.hotel.service.ReservationService;
import com.hotel.util.DateUtils;
import com.hotel.util.FileUtils;
import com.hotel.util.SwingComponentUtil;
import com.jidesoft.swing.ComboBoxSearchable;
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
import javax.swing.JSeparator;

public class newCustomer extends JFrame {
	
	@Serial
	private static final long serialVersionUID = 5512828180724892976L;
	private JPanel newCustomerPanel;
	private JTextField surnameTextField;
	private JTextField nameTextField;
	private JTextField idNumberTextField;
	private JDateChooser dateOfBirthChooser;
	private JComboBox<Gender> genderComboBox;
	private JComboBox<DocumentType> idTypeComboBox;
	private JComboBox<Release> idSourceBox;
	private JComboBox<Housed> gerarchyComboBox;
	private JComboBox<String> citizenshipComboBox;
	private JComboBox<String> birthplaceBox;
	private JComboBox<String> countyOfBirthBox;
	private JComboBox<String> idReleasePlaceBox;
	private JComboBox<String> stateOfBirthComboBox;
	private JComboBox<String> idCountyOfBirthComboBox;
	private JDateChooser idReleaseDateChooser;
	private JDateChooser idExpirationDateChooser;
	private Customer customer;
	
	public newCustomer(Customer customer, Reservation reservation, ReservationService reservationService, CustomerService customerService, DocumentService documentService, DefaultListModel<Customer> customerList) {
		setResizable(false);
		setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		SwingComponentUtil.addHotelIcons(this);
		
		this.customer = customer;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1537, 820);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		newCustomerPanel = new JPanel();
		newCustomerPanel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		newCustomerPanel.setBackground(new Color(0, 128, 128));
		newCustomerPanel.setBounds(33, 10, 1480, 763);
		contentPane.add(newCustomerPanel);
		newCustomerPanel.setLayout(null);
		
		setLabels();
		
		surnameTextField = new JTextField();
		surnameTextField.setBackground(new Color(224, 255, 255));
		surnameTextField.setForeground(new Color(0, 128, 128));
		surnameTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		surnameTextField.setBounds(314, 145, 416, 36);
		newCustomerPanel.add(surnameTextField);
		surnameTextField.setColumns(10);
		
		nameTextField = new JTextField();
		nameTextField.setBackground(new Color(224, 255, 255));
		nameTextField.setForeground(new Color(0, 128, 128));
		nameTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		nameTextField.setColumns(10);
		nameTextField.setBounds(314, 100, 416, 36);
		newCustomerPanel.add(nameTextField);
		
		genderComboBox = new JComboBox<>();
		genderComboBox.setModel(new DefaultComboBoxModel<>(new Gender[]{Gender.MALE, Gender.FEMALE}));
		genderComboBox.setSelectedIndex(-1);
		genderComboBox.setForeground(new Color(0, 128, 128));
		genderComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		genderComboBox.setBackground(new Color(224, 255, 255));
		genderComboBox.setBounds(314, 190, 416, 36);
		newCustomerPanel.add(genderComboBox);
		
		dateOfBirthChooser = new JDateChooser();
		((JTextFieldDateEditor) dateOfBirthChooser.getDateEditor()).setEditable(false);
		dateOfBirthChooser.setBackground(new Color(224, 255, 255));
		dateOfBirthChooser.setForeground(new Color(0, 128, 128));
		dateOfBirthChooser.setFont(new Font("Tahoma", Font.BOLD, 17));
		dateOfBirthChooser.setBounds(314, 415, 417, 36);
		newCustomerPanel.add(dateOfBirthChooser);
		
		gerarchyComboBox = new JComboBox<>();
		gerarchyComboBox.setModel(new DefaultComboBoxModel<>(new Housed[]{Housed.SINGLE_GUEST, Housed.HOUSEHOLDER, Housed.GROUP_LEADER, Housed.RELATIVE, Housed.GROUP_MEMBER}));
		gerarchyComboBox.setSelectedIndex(-1);
		gerarchyComboBox.setForeground(new Color(0, 128, 128));
		gerarchyComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		gerarchyComboBox.setBackground(new Color(224, 255, 255));
		gerarchyComboBox.setBounds(314, 460, 416, 36);
		newCustomerPanel.add(gerarchyComboBox);
		
		citizenshipComboBox = new JComboBox<>();
		citizenshipComboBox.setModel(FileUtils.getStates());
		citizenshipComboBox.setSelectedIndex(-1);
		citizenshipComboBox.setForeground(new Color(0, 128, 128));
		citizenshipComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		citizenshipComboBox.setBackground(new Color(224, 255, 255));
		citizenshipComboBox.setBounds(314, 235, 416, 36);
		@SuppressWarnings("unused")
		ComboBoxSearchable stateSearch = new ComboBoxSearchable(citizenshipComboBox);
		newCustomerPanel.add(citizenshipComboBox);
		
		birthplaceBox = new JComboBox<>();
		birthplaceBox.setEditable(false);
		birthplaceBox.setEnabled(false);
		birthplaceBox.setModel(FileUtils.getMunicipals());
		birthplaceBox.setSelectedIndex(-1);
		birthplaceBox.setForeground(new Color(0, 128, 128));
		birthplaceBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		birthplaceBox.setBackground(new Color(224, 255, 255));
		birthplaceBox.setBounds(314, 325, 416, 36);
		@SuppressWarnings("unused")
		ComboBoxSearchable searchPlacesOfBirth = new ComboBoxSearchable(birthplaceBox);
		newCustomerPanel.add(birthplaceBox);
		
		countyOfBirthBox = new JComboBox<>();
		countyOfBirthBox.setEditable(false);
		countyOfBirthBox.setEnabled(false);
		countyOfBirthBox.setModel(FileUtils.getProvinces());
		countyOfBirthBox.setSelectedIndex(-1);
		countyOfBirthBox.setForeground(new Color(0, 128, 128));
		countyOfBirthBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		countyOfBirthBox.setBackground(new Color(224, 255, 255));
		countyOfBirthBox.setBounds(314, 370, 416, 36);
		@SuppressWarnings("unused")
		ComboBoxSearchable searchResidence = new ComboBoxSearchable(countyOfBirthBox);
		newCustomerPanel.add(countyOfBirthBox);
		
		idNumberTextField = new JTextField();
		idNumberTextField.setEditable(false);
		idNumberTextField.setForeground(new Color(0, 128, 128));
		idNumberTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		idNumberTextField.setColumns(10);
		idNumberTextField.setBackground(new Color(224, 255, 255));
		idNumberTextField.setBounds(1047, 145, 416, 36);
		newCustomerPanel.add(idNumberTextField);
		
		idReleaseDateChooser = new JDateChooser();
		((JTextFieldDateEditor) idReleaseDateChooser.getDateEditor()).setEditable(false);
		idReleaseDateChooser.setEnabled(false);
		idReleaseDateChooser.setForeground(new Color(0, 128, 128));
		idReleaseDateChooser.setFont(new Font("Tahoma", Font.BOLD, 17));
		idReleaseDateChooser.setBounds(1047, 325, 417, 36);
		newCustomerPanel.add(idReleaseDateChooser);
		
		idExpirationDateChooser = new JDateChooser();
		((JTextFieldDateEditor) idExpirationDateChooser.getDateEditor()).setEditable(false);
		idExpirationDateChooser.setEnabled(false);
		idExpirationDateChooser.setForeground(new Color(0, 128, 128));
		idExpirationDateChooser.setFont(new Font("Tahoma", Font.BOLD, 17));
		idExpirationDateChooser.setBounds(1047, 370, 417, 36);
		newCustomerPanel.add(idExpirationDateChooser);
		
		idSourceBox = new JComboBox<>();
		idSourceBox.setEditable(false);
		idSourceBox.setEnabled(false);
		idSourceBox.setModel(new DefaultComboBoxModel<>(new Release[]{Release.QUESTURA, Release.PREFETTURA, Release.MIT_UCO}));
		idSourceBox.setSelectedIndex(-1);
		idSourceBox.setForeground(new Color(0, 128, 128));
		idSourceBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		idSourceBox.setBackground(new Color(224, 255, 255));
		idSourceBox.setBounds(1047, 190, 416, 36);
		newCustomerPanel.add(idSourceBox);
		
		idCountyOfBirthComboBox = new JComboBox<>();
		idCountyOfBirthComboBox.setBackground(new Color(224, 255, 255));
		idCountyOfBirthComboBox.setForeground(new Color(224, 255, 255));
		idCountyOfBirthComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		idCountyOfBirthComboBox.setModel(FileUtils.getProvinces());
		idCountyOfBirthComboBox.setEnabled(false);
		idCountyOfBirthComboBox.setEditable(false);
		idCountyOfBirthComboBox.setSelectedIndex(-1);
		idCountyOfBirthComboBox.setBounds(1047, 280, 416, 36);
		@SuppressWarnings("unused")
		ComboBoxSearchable searchProvinceOfIssue = new ComboBoxSearchable(idCountyOfBirthComboBox);
		newCustomerPanel.add(idCountyOfBirthComboBox);
		
		idReleasePlaceBox = new JComboBox<>();
		idReleasePlaceBox.setEditable(false);
		idReleasePlaceBox.setEnabled(false);
		idReleasePlaceBox.setModel(FileUtils.getStatesAndMunicipals());
		idReleasePlaceBox.setSelectedIndex(-1);
		idReleasePlaceBox.setForeground(new Color(0, 128, 128));
		idReleasePlaceBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		idReleasePlaceBox.setBackground(new Color(224, 255, 255));
		idReleasePlaceBox.setBounds(1047, 235, 416, 36);
		@SuppressWarnings("unused")
		ComboBoxSearchable searchRelease = new ComboBoxSearchable(idReleasePlaceBox);
		newCustomerPanel.add(idReleasePlaceBox);
		idReleasePlaceBox.addActionListener(e -> {
			String releasePlace = (String) idReleasePlaceBox.getSelectedItem();
			idCountyOfBirthComboBox.setEnabled(releasePlace != null && !FileUtils.STATE_CODES.containsKey(releasePlace));
		});
		
		idTypeComboBox = new JComboBox<>();
		idTypeComboBox.addActionListener(e -> {
			if(idTypeComboBox.getSelectedIndex() != -1) {
				idNumberTextField.setEditable(true);
				idSourceBox.setEnabled(true);
				idReleasePlaceBox.setEnabled(true);
				idReleaseDateChooser.setEnabled(true);
				idExpirationDateChooser.setEnabled(true);
			} else {
				idNumberTextField.setEditable(false);
				idSourceBox.setEnabled(false);
				idReleasePlaceBox.setEnabled(false);
				idCountyOfBirthComboBox.setEnabled(false);
				idReleaseDateChooser.setEnabled(false);
				idExpirationDateChooser.setEnabled(false);
			}
		});
		idTypeComboBox.setModel(new DefaultComboBoxModel<>(new DocumentType[]{DocumentType.CARTA_IDENTITA, DocumentType.CARTA_IDENTITA_ELETTRONICA, DocumentType.PATENTE_DI_GUIDA, DocumentType.PASSAPORTO}));
		idTypeComboBox.setSelectedIndex(-1);
		idTypeComboBox.setForeground(new Color(0, 128, 128));
		idTypeComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		idTypeComboBox.setBackground(new Color(224, 255, 255));
		idTypeComboBox.setBounds(1047, 100, 416, 36);
		newCustomerPanel.add(idTypeComboBox);
		
		stateOfBirthComboBox = new JComboBox<>();
		stateOfBirthComboBox.setModel(FileUtils.getStates());
		stateOfBirthComboBox.setSelectedIndex(-1);
		stateOfBirthComboBox.setForeground(new Color(0, 128, 128));
		stateOfBirthComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		stateOfBirthComboBox.setBackground(new Color(224, 255, 255));
		stateOfBirthComboBox.setBounds(314, 280, 416, 36);
		stateOfBirthComboBox.addActionListener(e -> {
			String state = (String) stateOfBirthComboBox.getSelectedItem();
			if(state != null && state.equals("ITALIA")) {
				birthplaceBox.setEnabled(true);
				countyOfBirthBox.setEnabled(true);
			}
			else {
				birthplaceBox.setEnabled(false);
				countyOfBirthBox.setEnabled(false);
			}
		});
		newCustomerPanel.add(stateOfBirthComboBox);
		
		JButton addNewCustomerButton = new JButton("Add");
		addNewCustomerButton.addActionListener(e -> {
			if(checkClientFields()) {
				Customer customer1 = null;
				Document document = null;
				boolean correctFields = true;
				if(stateOfBirthComboBox.getSelectedItem().equals("ITALIA")) {
					if(checkClientItalianFields()) {
						customer1 = new Customer(nameTextField.getText(),
								surnameTextField.getText(),
								(Gender) genderComboBox.getSelectedItem(),
								DateUtils.convertDateToLocalDate(dateOfBirthChooser.getDate()),
								(String) stateOfBirthComboBox.getSelectedItem(),
								(String) birthplaceBox.getSelectedItem(),
								(String) countyOfBirthBox.getSelectedItem(),
								(String) citizenshipComboBox.getSelectedItem(),
								(Housed) gerarchyComboBox.getSelectedItem());
					}
				} else {
					customer1 = new Customer(nameTextField.getText(),
							surnameTextField.getText(),
							(Gender) genderComboBox.getSelectedItem(),
							DateUtils.convertDateToLocalDate(dateOfBirthChooser.getDate()),
							(String) stateOfBirthComboBox.getSelectedItem(),
							null,
							null,
							(String) citizenshipComboBox.getSelectedItem(),
							(Housed) gerarchyComboBox.getSelectedItem());
					}
				if(checkIdType()) {
					if(checkDocumentFields()) {
						if(checkIfItalianDocument()) {
							if(checkIdProvince()) {
								document = new Document(customer1,
										idNumberTextField.getText(),
										(DocumentType) idTypeComboBox.getSelectedItem(),
										(Release) idSourceBox.getSelectedItem(),
										DateUtils.convertDateToLocalDate(idReleaseDateChooser.getDate()),
										DateUtils.convertDateToLocalDate(idExpirationDateChooser.getDate()),
										(String) idReleasePlaceBox.getSelectedItem(),
										(String) idCountyOfBirthComboBox.getSelectedItem());
							} else
								correctFields = false;
						} else
							document = new Document(customer1,
									idNumberTextField.getText(),
									(DocumentType) idTypeComboBox.getSelectedItem(),
									(Release) idSourceBox.getSelectedItem(),
									DateUtils.convertDateToLocalDate(idReleaseDateChooser.getDate()),
									DateUtils.convertDateToLocalDate(idExpirationDateChooser.getDate()),
									(String) idReleasePlaceBox.getSelectedItem(),
									null);
					} else
						correctFields = false;
				}
				if(correctFields) {
					customerService.saveCustomer(customer1);
					if(document != null) documentService.saveDocument(document);
					customerList.addElement(customer1);
					dispose();
				}
			}
		});
		addNewCustomerButton.setBounds(878, 600, 303, 51);
		newCustomerPanel.add(addNewCustomerButton);
		addNewCustomerButton.setForeground(new Color(0, 128, 128));
		addNewCustomerButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		addNewCustomerButton.setBackground(new Color(224, 255, 255));
		
		JPanel upPanel = new JPanel();
		upPanel.setBackground(new Color(224, 255, 255));
		upPanel.setBounds(-22, 0, 1496, 86);
		newCustomerPanel.add(upPanel);
		upPanel.setLayout(null);
		
		JLabel addCustomerLabel = new JLabel("New customer");
		addCustomerLabel.setBounds(0, 0, 1496, 86);
		upPanel.add(addCustomerLabel);
		addCustomerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addCustomerLabel.setForeground(new Color(0, 128, 128));
		addCustomerLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 40));
		
		JButton refreshButton = new JButton("Reset");
		refreshButton.addActionListener(e -> refreshAttributes());
		refreshButton.setForeground(new Color(0, 128, 128));
		refreshButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		refreshButton.setBackground(new Color(224, 255, 255));
		refreshButton.setBounds(180, 600, 303, 51);
		newCustomerPanel.add(refreshButton);
		
		JLabel provinceOfIssueLabel = new JLabel("Province of issue");
		provinceOfIssueLabel.setLabelFor(idCountyOfBirthComboBox);
		provinceOfIssueLabel.setForeground(new Color(224, 255, 255));
		provinceOfIssueLabel.setFont(new Font("Dialog", Font.PLAIN, 33));
		provinceOfIssueLabel.setBackground(new Color(0, 128, 128));
		provinceOfIssueLabel.setBounds(754, 281, 281, 36);
		newCustomerPanel.add(provinceOfIssueLabel);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(224, 255, 255));
		leftPanel.setBounds(10, 10, 24, 763);
		contentPane.add(leftPanel);

		refreshAttributes();
		setVisible(true);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public newCustomer(Reservation reservation, ReservationService reservationService, CustomerService customerService, DocumentService documentService, DefaultListModel<Customer> customerList) {
		this(null, reservation, reservationService, customerService, documentService, customerList);
	}
	
	private void setLabels() {
		JLabel surnameLabel = new JLabel("Surname");
		surnameLabel.setForeground(new Color(224, 255, 255));
		surnameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		surnameLabel.setBounds(10, 145, 250, 36);
		newCustomerPanel.add(surnameLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setForeground(new Color(224, 255, 255));
		nameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		nameLabel.setBounds(10, 100, 250, 36);
		newCustomerPanel.add(nameLabel);
		
		JLabel dateOfBirthLabel = new JLabel("Date of birth");
		dateOfBirthLabel.setForeground(new Color(224, 255, 255));
		dateOfBirthLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		dateOfBirthLabel.setBounds(10, 415, 250, 36);
		newCustomerPanel.add(dateOfBirthLabel);
		
		JLabel placeOfBirthLabel = new JLabel("Birthplace");
		placeOfBirthLabel.setForeground(new Color(224, 255, 255));
		placeOfBirthLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		placeOfBirthLabel.setBounds(10, 325, 281, 36);
		newCustomerPanel.add(placeOfBirthLabel);
		
		JLabel countyOfBirthLabel = new JLabel("County of birth");
		countyOfBirthLabel.setForeground(new Color(224, 255, 255));
		countyOfBirthLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		countyOfBirthLabel.setBounds(10, 370, 294, 36);
		newCustomerPanel.add(countyOfBirthLabel);
		
		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setForeground(new Color(224, 255, 255));
		genderLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		genderLabel.setBounds(10, 190, 250, 36);
		newCustomerPanel.add(genderLabel);
		
		JLabel idTypeLabel = new JLabel("Document type");
		idTypeLabel.setForeground(new Color(224, 255, 255));
		idTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idTypeLabel.setBounds(754, 100, 250, 36);
		newCustomerPanel.add(idTypeLabel);
		
		JLabel idNumberLabel = new JLabel("Document number");
		idNumberLabel.setForeground(new Color(224, 255, 255));
		idNumberLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idNumberLabel.setBounds(754, 145, 294, 36);
		newCustomerPanel.add(idNumberLabel);
		
		JLabel idReleaseDateLabel = new JLabel("Release date");
		idReleaseDateLabel.setForeground(new Color(224, 255, 255));
		idReleaseDateLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idReleaseDateLabel.setBounds(754, 325, 250, 36);
		newCustomerPanel.add(idReleaseDateLabel);
		
		JLabel idReleasePlaceLabel = new JLabel("Place of issue");
		idReleasePlaceLabel.setForeground(new Color(224, 255, 255));
		idReleasePlaceLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idReleasePlaceLabel.setBounds(754, 235, 250, 36);
		newCustomerPanel.add(idReleasePlaceLabel);
		
		JLabel idReleaseSource = new JLabel("Released by");
		idReleaseSource.setForeground(new Color(224, 255, 255));
		idReleaseSource.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idReleaseSource.setBounds(754, 190, 250, 36);
		newCustomerPanel.add(idReleaseSource);
		
		JLabel idHierarchyLabel = new JLabel("Hierarchy");
		idHierarchyLabel.setForeground(new Color(224, 255, 255));
		idHierarchyLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idHierarchyLabel.setBounds(10, 460, 250, 36);
		newCustomerPanel.add(idHierarchyLabel);
		
		JLabel stateOfBirthLabel = new JLabel("State of birth");
		stateOfBirthLabel.setFont(new Font("Dialog", Font.PLAIN, 33));
		stateOfBirthLabel.setForeground(new Color(224, 255, 255));
		stateOfBirthLabel.setBounds(10, 280, 294, 36);
		newCustomerPanel.add(stateOfBirthLabel);
		
		JLabel citizenship = new JLabel("Citizenship");
		citizenship.setForeground(new Color(224, 255, 255));
		citizenship.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		citizenship.setBounds(10, 235, 250, 36);
		newCustomerPanel.add(citizenship);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(0, 499, 1480, 13);
		newCustomerPanel.add(separator);
		
		JLabel expirationDateLabel = new JLabel("Expiration date");
		expirationDateLabel.setForeground(new Color(224, 255, 255));
		expirationDateLabel.setFont(new Font("Dialog", Font.PLAIN, 33));
		expirationDateLabel.setBackground(new Color(0, 128, 128));
		expirationDateLabel.setBounds(754, 370, 281, 34);
		newCustomerPanel.add(expirationDateLabel);
	}

	private boolean checkClientFields() {
		return checkName() && checkSurname() && checkGender() && checkDateOfBirth() && checkStateOfBirth()
				 && checkCitizenship() && checkHierarchy();
	}
	
	private boolean checkClientItalianFields() {
		return checkBirthplace() && checkCountyOfBirth();
	}
	
	private boolean checkDocumentFields() {
		return checkDateOfRelease() && checkIdNumber() && checkIdPlaceRelease() && checkSourceId() && checkExpirationDate();
	}
	
	private void refreshAttributes() {
		refreshFields();
	}
	
	private void refreshFields() {
		if(customer == null) {
			nameTextField.setText("");
			surnameTextField.setText("");
			citizenshipComboBox.setSelectedIndex(-1);
			stateOfBirthComboBox.setSelectedIndex(-1);
			gerarchyComboBox.setSelectedIndex(-1);
			birthplaceBox.setSelectedIndex(-1);
			countyOfBirthBox.setSelectedIndex(-1);
			genderComboBox.setSelectedIndex(-1);
			dateOfBirthChooser.setDate(null);
			
			idTypeComboBox.setSelectedIndex(-1);
			idNumberTextField.setText("");
			idReleasePlaceBox.setSelectedIndex(-1);
			idCountyOfBirthComboBox.setSelectedIndex(-1);
			idSourceBox.setSelectedIndex(-1);
			idExpirationDateChooser.setDate(null);
			idReleaseDateChooser.setDate(null);
		} else {
			nameTextField.setText(customer.getName());
			surnameTextField.setText(customer.getSurname());
			citizenshipComboBox.setSelectedItem(customer.getCitizenship());
			stateOfBirthComboBox.setSelectedItem(customer.getStateOfBirth());
			gerarchyComboBox.setSelectedItem(customer.getHoused());
			birthplaceBox.setSelectedItem(customer.getBirthplace());
			countyOfBirthBox.setSelectedItem(customer.getCountyOfBirth());
			genderComboBox.setSelectedItem(customer.getGender());
			dateOfBirthChooser.setDate(DateUtils.convertLocalDateToDate(customer.getDateOfBirth()));
			
			Document customerDocument = customer.getDocument();
			if(customerDocument == null) {
				idTypeComboBox.setSelectedIndex(-1);
				idNumberTextField.setText("");
				idReleasePlaceBox.setSelectedIndex(-1);
				idSourceBox.setSelectedIndex(-1);
				idExpirationDateChooser.setDate(null);
				idReleaseDateChooser.setDate(null);
			} else {
				idTypeComboBox.setSelectedItem(customerDocument.getDocumentType());
				idNumberTextField.setText(customerDocument.getNumber());
				idReleasePlaceBox.setSelectedItem(customerDocument.getPlaceOfIssue());
				idSourceBox.setSelectedItem(customerDocument.getReleasedBy());
				String countyOfIssue = customerDocument.getProvinceOfIssue();
				if(countyOfIssue == null)
					idCountyOfBirthComboBox.setSelectedIndex(-1);
				else
					idCountyOfBirthComboBox.setSelectedItem(countyOfIssue);
				idExpirationDateChooser.setDate(null);
				idReleaseDateChooser.setDate(null);
			}
		}
	}
	
	private boolean checkName() {
		if(!nameTextField.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "Enter a name");
		nameTextField.setText("");
		return false;
	}
	private boolean checkSurname() {
		if(!surnameTextField.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "Enter a surname");
		surnameTextField.setText("");
		return false;
	}
	
	private boolean checkIdNumber() {
		if(!idNumberTextField.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "Enter a document number");
		idNumberTextField.setText("");
		return false;
	}
	
	private boolean checkGender() {
		if(genderComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "Select the gender");
		return false;
	}
	
	private boolean checkIdType() {
		return idTypeComboBox.getSelectedIndex() != -1;
	}
	
	private boolean checkSourceId() {
		if(idSourceBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "Select the document issue body");
		return false;
	}
	
	private boolean checkHierarchy() {
		if(gerarchyComboBox.getSelectedIndex() != -1) {
			Housed housedType = (Housed) gerarchyComboBox.getSelectedItem();
			if(housedType == Housed.RELATIVE || housedType == Housed.GROUP_MEMBER)
				return true;
			if(idTypeComboBox.getSelectedIndex() != -1)
				return true;
			JOptionPane.showMessageDialog(null, "Accommodation type must have an associated document");
		} else
			JOptionPane.showMessageDialog(null, "Select the type of accommodation");
		return false;
	}
	
	private boolean checkStateOfBirth() {
		if(stateOfBirthComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "Select a state of birth");
		return false;
	}
	
	private boolean checkBirthplace() {
		if(birthplaceBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "Select the birthplace");
		return false;
	}
	
	private boolean checkCitizenship() {
		if(citizenshipComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "Select the citizenship");
		return false;
	}
	
	private boolean checkCountyOfBirth() {
		if(countyOfBirthBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "Select the province of birth");
		return false;
	}
	
	private boolean checkIdPlaceRelease() {
		if(idReleasePlaceBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "Select the place of issue of the document, the municipality or the state if foreign");
		return false;
	}
	
	private boolean checkDateOfRelease() {
		if(idReleaseDateChooser.getDate() != null) return true;
		JOptionPane.showMessageDialog(null, "Enter the document issue date");
		return false;
	}
	
	private boolean checkIfItalianDocument() {
		String releasePlace = (String) idReleasePlaceBox.getSelectedItem();
		return releasePlace != null && !FileUtils.STATE_CODES.containsKey(releasePlace);
	}
	
	private boolean checkIdProvince() {
		if(idCountyOfBirthComboBox.getSelectedItem() != null) return true;
		JOptionPane.showMessageDialog(null, "Select the province of release of the document");
		return false;
	}
	
	private boolean checkDateOfBirth() {
		if(dateOfBirthChooser.getDate() != null) return true;
		JOptionPane.showMessageDialog(null, "Enter the date of birth");
		return false;
	}
	
	private boolean checkExpirationDate() {
		if(idExpirationDateChooser.getDate() != null) return true;
		JOptionPane.showMessageDialog(null, "Enter the expiry date of the document");
		return false;
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