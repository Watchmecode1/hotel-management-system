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
	private JComboBox<String> provinciaDiNascitaBox;
	private JComboBox<String> idReleasePlaceBox;
	private JComboBox<String> statoDiNascitaComboBox;
	private JComboBox<String> idProvinciaDiRilascioComboBox;
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
		ComboBoxSearchable statiSearch = new ComboBoxSearchable(citizenshipComboBox);
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
		
		provinciaDiNascitaBox = new JComboBox<>();
		provinciaDiNascitaBox.setEditable(false);
		provinciaDiNascitaBox.setEnabled(false);
		provinciaDiNascitaBox.setModel(FileUtils.getProvinces());
		provinciaDiNascitaBox.setSelectedIndex(-1);
		provinciaDiNascitaBox.setForeground(new Color(0, 128, 128));
		provinciaDiNascitaBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		provinciaDiNascitaBox.setBackground(new Color(224, 255, 255));
		provinciaDiNascitaBox.setBounds(314, 370, 416, 36);
		@SuppressWarnings("unused")
		ComboBoxSearchable searchResidence = new ComboBoxSearchable(provinciaDiNascitaBox);
		newCustomerPanel.add(provinciaDiNascitaBox);
		
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
		
		idProvinciaDiRilascioComboBox = new JComboBox<>();
		idProvinciaDiRilascioComboBox.setBackground(new Color(224, 255, 255));
		idProvinciaDiRilascioComboBox.setForeground(new Color(224, 255, 255));
		idProvinciaDiRilascioComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		idProvinciaDiRilascioComboBox.setModel(FileUtils.getProvinces());
		idProvinciaDiRilascioComboBox.setEnabled(false);
		idProvinciaDiRilascioComboBox.setEditable(false);
		idProvinciaDiRilascioComboBox.setSelectedIndex(-1);
		idProvinciaDiRilascioComboBox.setBounds(1047, 280, 416, 36);
		@SuppressWarnings("unused")
		ComboBoxSearchable searchProvinciaDiRilascio = new ComboBoxSearchable(idProvinciaDiRilascioComboBox);
		newCustomerPanel.add(idProvinciaDiRilascioComboBox);
		
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
			idProvinciaDiRilascioComboBox.setEnabled(releasePlace != null && !FileUtils.STATE_CODES.containsKey(releasePlace));
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
				idProvinciaDiRilascioComboBox.setEnabled(false);
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
		
		statoDiNascitaComboBox = new JComboBox<>();
		statoDiNascitaComboBox.setModel(FileUtils.getStates());
		statoDiNascitaComboBox.setSelectedIndex(-1);
		statoDiNascitaComboBox.setForeground(new Color(0, 128, 128));
		statoDiNascitaComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		statoDiNascitaComboBox.setBackground(new Color(224, 255, 255));
		statoDiNascitaComboBox.setBounds(314, 280, 416, 36);
		statoDiNascitaComboBox.addActionListener(e -> {
			String state = (String) statoDiNascitaComboBox.getSelectedItem();
			if(state != null && state.equals("ITALIA")) {
				birthplaceBox.setEnabled(true);
				provinciaDiNascitaBox.setEnabled(true);
			}
			else {
				birthplaceBox.setEnabled(false);
				provinciaDiNascitaBox.setEnabled(false);
			}
		});
		newCustomerPanel.add(statoDiNascitaComboBox);
		
		JButton addNewCustomerButton = new JButton("Aggiungi");
		addNewCustomerButton.addActionListener(e -> {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "VUOI AGGIUNGERE IL NUOVO CLIENTE?", "AGGIUNGI CLIENTE", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				if(checkClientFields()) {
					Customer customer1 = null;
					Document document = null;
					boolean correctFields = true;
					if(statoDiNascitaComboBox.getSelectedItem().equals("ITALIA")) {
						if(checkClientItalianFields()) {
							customer1 = new Customer(nameTextField.getText(),
									surnameTextField.getText(),
									(Gender) genderComboBox.getSelectedItem(),
									DateUtils.convertDateToLocalDate(dateOfBirthChooser.getDate()),
									(String) statoDiNascitaComboBox.getSelectedItem(),
									(String) birthplaceBox.getSelectedItem(),
									(String) provinciaDiNascitaBox.getSelectedItem(),
									(String) citizenshipComboBox.getSelectedItem(),
									(Housed) gerarchyComboBox.getSelectedItem());
						}
					} else {
						customer1 = new Customer(nameTextField.getText(),
								surnameTextField.getText(),
								(Gender) genderComboBox.getSelectedItem(),
								DateUtils.convertDateToLocalDate(dateOfBirthChooser.getDate()),
								(String) statoDiNascitaComboBox.getSelectedItem(),
								null,
								null,
								(String) citizenshipComboBox.getSelectedItem(),
								(Housed) gerarchyComboBox.getSelectedItem());
					}
					if(checkIdType()) {
						if(checkDocumentFields()) {
							if(checkIfItalianDocument()) {
								if(checkIdProvincia()) {
									document = new Document(customer1,
											idNumberTextField.getText(),
											(DocumentType) idTypeComboBox.getSelectedItem(),
											(Release) idSourceBox.getSelectedItem(),
											DateUtils.convertDateToLocalDate(idReleaseDateChooser.getDate()),
											DateUtils.convertDateToLocalDate(idExpirationDateChooser.getDate()),
											(String) idReleasePlaceBox.getSelectedItem(),
											(String) idProvinciaDiRilascioComboBox.getSelectedItem());
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
			} else JOptionPane.showMessageDialog(null, "OPERAZIONE ANNULLATA");
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
		
		JLabel addCustomerLabel = new JLabel("Nuovo Cliente");
		addCustomerLabel.setBounds(0, 0, 1496, 86);
		upPanel.add(addCustomerLabel);
		addCustomerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addCustomerLabel.setForeground(new Color(0, 128, 128));
		addCustomerLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 40));
		
		JButton refreshButton = new JButton("Reimposta");
		refreshButton.addActionListener(e -> refreshAttributes());
		refreshButton.setForeground(new Color(0, 128, 128));
		refreshButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		refreshButton.setBackground(new Color(224, 255, 255));
		refreshButton.setBounds(180, 600, 303, 51);
		newCustomerPanel.add(refreshButton);
		
		JLabel provinciaDiRilascioLabel = new JLabel("Provincia di rilascio");
		provinciaDiRilascioLabel.setLabelFor(idProvinciaDiRilascioComboBox);
		provinciaDiRilascioLabel.setForeground(new Color(224, 255, 255));
		provinciaDiRilascioLabel.setFont(new Font("Dialog", Font.PLAIN, 33));
		provinciaDiRilascioLabel.setBackground(new Color(0, 128, 128));
		provinciaDiRilascioLabel.setBounds(754, 281, 281, 36);
		newCustomerPanel.add(provinciaDiRilascioLabel);
		
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
		JLabel surnameLabel = new JLabel("Cognome");
		surnameLabel.setForeground(new Color(224, 255, 255));
		surnameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		surnameLabel.setBounds(10, 145, 250, 36);
		newCustomerPanel.add(surnameLabel);
		
		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setForeground(new Color(224, 255, 255));
		nameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		nameLabel.setBounds(10, 100, 250, 36);
		newCustomerPanel.add(nameLabel);
		
		JLabel dateOfBirthLabel = new JLabel("Data di nascita");
		dateOfBirthLabel.setForeground(new Color(224, 255, 255));
		dateOfBirthLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		dateOfBirthLabel.setBounds(10, 415, 250, 36);
		newCustomerPanel.add(dateOfBirthLabel);
		
		JLabel placeOfBirthLabel = new JLabel("Comune di nascita");
		placeOfBirthLabel.setForeground(new Color(224, 255, 255));
		placeOfBirthLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		placeOfBirthLabel.setBounds(10, 325, 281, 36);
		newCustomerPanel.add(placeOfBirthLabel);
		
		JLabel provinciaDiNascitaLabel = new JLabel("Provincia di nascita");
		provinciaDiNascitaLabel.setForeground(new Color(224, 255, 255));
		provinciaDiNascitaLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		provinciaDiNascitaLabel.setBounds(10, 370, 294, 36);
		newCustomerPanel.add(provinciaDiNascitaLabel);
		
		JLabel genderLabel = new JLabel("Genere");
		genderLabel.setForeground(new Color(224, 255, 255));
		genderLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		genderLabel.setBounds(10, 190, 250, 36);
		newCustomerPanel.add(genderLabel);
		
		JLabel idTypeLabel = new JLabel("Tipo documento");
		idTypeLabel.setForeground(new Color(224, 255, 255));
		idTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idTypeLabel.setBounds(754, 100, 250, 36);
		newCustomerPanel.add(idTypeLabel);
		
		JLabel idNumberLabel = new JLabel("Numero documento");
		idNumberLabel.setForeground(new Color(224, 255, 255));
		idNumberLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idNumberLabel.setBounds(754, 145, 294, 36);
		newCustomerPanel.add(idNumberLabel);
		
		JLabel idReleaseDateLabel = new JLabel("Data rilascio");
		idReleaseDateLabel.setForeground(new Color(224, 255, 255));
		idReleaseDateLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idReleaseDateLabel.setBounds(754, 325, 250, 36);
		newCustomerPanel.add(idReleaseDateLabel);
		
		JLabel idReleasePlaceLabel = new JLabel("Luogo di rilascio");
		idReleasePlaceLabel.setForeground(new Color(224, 255, 255));
		idReleasePlaceLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idReleasePlaceLabel.setBounds(754, 235, 250, 36);
		newCustomerPanel.add(idReleasePlaceLabel);
		
		JLabel idReleaseSource = new JLabel("Rilasciato da");
		idReleaseSource.setForeground(new Color(224, 255, 255));
		idReleaseSource.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idReleaseSource.setBounds(754, 190, 250, 36);
		newCustomerPanel.add(idReleaseSource);
		
		JLabel idGerarchyLabel = new JLabel("Tipo ospite");
		idGerarchyLabel.setForeground(new Color(224, 255, 255));
		idGerarchyLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		idGerarchyLabel.setBounds(10, 460, 250, 36);
		newCustomerPanel.add(idGerarchyLabel);
		
		JLabel statoDiNascitaLabel = new JLabel("Stato di nascita");
		statoDiNascitaLabel.setFont(new Font("Dialog", Font.PLAIN, 33));
		statoDiNascitaLabel.setForeground(new Color(224, 255, 255));
		statoDiNascitaLabel.setBounds(10, 280, 294, 36);
		newCustomerPanel.add(statoDiNascitaLabel);
		
		JLabel citizenship = new JLabel("Cittadinanza");
		citizenship.setForeground(new Color(224, 255, 255));
		citizenship.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		citizenship.setBounds(10, 235, 250, 36);
		newCustomerPanel.add(citizenship);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(0, 499, 1480, 13);
		newCustomerPanel.add(separator);
		
		JLabel expirationDateLabel = new JLabel("Data di scadenza");
		expirationDateLabel.setForeground(new Color(224, 255, 255));
		expirationDateLabel.setFont(new Font("Dialog", Font.PLAIN, 33));
		expirationDateLabel.setBackground(new Color(0, 128, 128));
		expirationDateLabel.setBounds(754, 370, 281, 34);
		newCustomerPanel.add(expirationDateLabel);
	}

	private boolean checkClientFields() {
		return checkName() && checkSurname() && checkGender() && checkDateOfBirth() && checkStateOfBirth()
				 && checkCitizenship() && checkGerarchy() && checkStatoDiNascita();
	}
	
	private boolean checkClientItalianFields() {
		return checkBirthplace() && checkCountyOfBirth();
	}
	
	private boolean checkDocumentFields() {
		return checkDateOfRelease() && checkIdNumber() && checkIdPlaceRelease() && checkSourceId() && checkExpirationDate();
	}
	
	private void refreshAttributes(boolean flag) {
		if(flag) {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "VUOI REIMPOSTARE I DATI INSERITI FIN'ORA?", "REIMPOSTA DATI", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION)
				refreshFields();
		} else
			refreshFields();
	}

	private void refreshAttributes() {
		refreshAttributes(false);
	}
	
	private void refreshFields() {
		if(customer == null) {
			nameTextField.setText("");
			surnameTextField.setText("");
			citizenshipComboBox.setSelectedIndex(-1);
			statoDiNascitaComboBox.setSelectedIndex(-1);
			gerarchyComboBox.setSelectedIndex(-1);
			birthplaceBox.setSelectedIndex(-1);
			provinciaDiNascitaBox.setSelectedIndex(-1);
			genderComboBox.setSelectedIndex(-1);
			dateOfBirthChooser.setDate(null);
			
			idTypeComboBox.setSelectedIndex(-1);
			idNumberTextField.setText("");
			idReleasePlaceBox.setSelectedIndex(-1);
			idProvinciaDiRilascioComboBox.setSelectedIndex(-1);
			idSourceBox.setSelectedIndex(-1);
			idExpirationDateChooser.setDate(null);
			idReleaseDateChooser.setDate(null);
		} else {
			nameTextField.setText(customer.getName());
			surnameTextField.setText(customer.getSurname());
			citizenshipComboBox.setSelectedItem(customer.getCitizenship());
			statoDiNascitaComboBox.setSelectedItem(customer.getStateOfBirth());
			gerarchyComboBox.setSelectedItem(customer.getHoused());
			birthplaceBox.setSelectedItem(customer.getBirthplace());
			provinciaDiNascitaBox.setSelectedItem(customer.getCountyOfBirth());
			genderComboBox.setSelectedItem(customer.getGender());
			dateOfBirthChooser.setDate(DateUtils.convertLocalDateToDate(customer.getDateOfBirth()));
			
			Document documentCliente = customer.getDocument();
			if(documentCliente == null) {
				idTypeComboBox.setSelectedIndex(-1);
				idNumberTextField.setText("");
				idReleasePlaceBox.setSelectedIndex(-1);
				idSourceBox.setSelectedIndex(-1);
				idExpirationDateChooser.setDate(null);
				idReleaseDateChooser.setDate(null);
			} else {
				idTypeComboBox.setSelectedItem(documentCliente.getDocumentType());
				idNumberTextField.setText(documentCliente.getNumber());
				idReleasePlaceBox.setSelectedItem(documentCliente.getPlaceOfIssue());
				idSourceBox.setSelectedItem(documentCliente.getReleasedBy());
				String provinciaDiRilascio = documentCliente.getProvinceOfIssue();
				if(provinciaDiRilascio == null)
					idProvinciaDiRilascioComboBox.setSelectedIndex(-1);
				else
					idProvinciaDiRilascioComboBox.setSelectedItem(provinciaDiRilascio);
				idExpirationDateChooser.setDate(null);
				idReleaseDateChooser.setDate(null);
			}
		}
	}
	
	private boolean checkName() {
		if(!nameTextField.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI UN NOME");
		nameTextField.setText("");
		return false;
	}
	private boolean checkSurname() {
		if(!surnameTextField.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI UN COGNOME");
		surnameTextField.setText("");
		return false;
	}
	
	private boolean checkIdNumber() {
		if(!idNumberTextField.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI UN NUMERO DOCUMENTO");
		idNumberTextField.setText("");
		return false;
	}
	
	private boolean checkGender() {
		if(genderComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA IL GENERE");
		return false;
	}
	
	private boolean checkIdType() {
		return idTypeComboBox.getSelectedIndex() != -1;
	}
	
	private boolean checkSourceId() {
		if(idSourceBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA L'ENTE DI RILASCIO DEL DOCUMENTO");
		return false;
	}
	
	private boolean checkGerarchy() {
		if(gerarchyComboBox.getSelectedIndex() != -1) {
			Housed tipologiaHoused = (Housed) gerarchyComboBox.getSelectedItem();
			if(tipologiaHoused == Housed.RELATIVE || tipologiaHoused == Housed.GROUP_MEMBER)
				return true;
			if(idTypeComboBox.getSelectedIndex() != -1)
				return true;
			JOptionPane.showMessageDialog(null, "IL TIPO DI ALLOGGIATO DEVE AVERE UN DOCUMENTO ASSOCIATO");
		} else
			JOptionPane.showMessageDialog(null, "SELEZIONA LA TIPOLOGIA DI ALLOGGIATO");
		return false;
	}
	
	private boolean checkStateOfBirth() {
		if(citizenshipComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA LA CITTADINANZA");
		return false;
	}
	
	private boolean checkBirthplace() {
		if(birthplaceBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA IL COMUNE DI NASCITA");
		return false;
	}
	
	private boolean checkCitizenship() {
		if(citizenshipComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA LA CITTADINANZA");
		return false;
	}
	
	private boolean checkStatoDiNascita() {
		if(statoDiNascitaComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA LO STATO DI NASCITA");
		return false;
	}
	
	private boolean checkCountyOfBirth() {
		if(provinciaDiNascitaBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA LA PROVINCIA DI NASCITA");
		return false;
	}
	
	private boolean checkIdPlaceRelease() {
		if(idReleasePlaceBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA IL LUOGO DI RILASCIO DEL DOCUMENTO, IL COMUNE O  LO STATO SE ESTERO");
		return false;
	}
	
	private boolean checkDateOfRelease() {
		if(idReleaseDateChooser.getDate() != null) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI LA DATA DI RILASCIO DEL DOCUMENTO");
		return false;
	}
	
	private boolean checkIfItalianDocument() {
		String releasePlace = (String) idReleasePlaceBox.getSelectedItem();
		return releasePlace != null && !FileUtils.STATE_CODES.containsKey(releasePlace);
	}
	
	private boolean checkIdProvincia() {
		if(idProvinciaDiRilascioComboBox.getSelectedItem() != null) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA LA PROVINCIA DI RILASCIO DEL DOCUMENTO");
		return false;
	}
	
	private boolean checkDateOfBirth() {
		if(dateOfBirthChooser.getDate() != null) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI LA DATA DI NASCITA");
		return false;
	}
	
	private boolean checkExpirationDate() {
		if(idExpirationDateChooser.getDate() != null) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI LA DATA DI SCADENZA DEL DOCUMENTO");
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