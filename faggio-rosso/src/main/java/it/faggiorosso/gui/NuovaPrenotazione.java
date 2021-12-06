package it.faggiorosso.gui;

import java.awt.Font;
import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;

import it.faggiorosso.entity.Camera;
import it.faggiorosso.entity.Cliente;
import it.faggiorosso.entity.Prenotazione;
import it.faggiorosso.entity.Prenotazione.Fonte;
import it.faggiorosso.entity.Prenotazione.Pagato;
import it.faggiorosso.entity.Prenotazione.Pensione;
import it.faggiorosso.service.CameraService;
import it.faggiorosso.service.ClienteService;
import it.faggiorosso.service.DocumentoService;
import it.faggiorosso.service.PrenotazioneService;
import it.faggiorosso.util.DateUtils;
import it.faggiorosso.util.SwingComponentUtil;
import javax.swing.ListSelectionModel;

public class NuovaPrenotazione extends JFrame {
	
	@Serial
	private static final long serialVersionUID = 2654050621459550287L;
	private JTextField phoneNumberTextField;
	private JTextField emailAddressTextField;
	private JTextField referenceNameTextField;
	private JTextField depositPaidTextField;
	private JDateChooser checkoutDateChooser;
	private JDateChooser checkinDateChooser;
	private JComboBox<Fonte> sourceComboBox;
	private JComboBox<Pensione> boardTypeComboBox;
	private JComboBox<Pagato> paymentTypeComboBox;
	private JSpinner animalsNumberSpinner;
	private JRadioButton depositRadioButton;
	private JLabel calculatePaymentLabel;
	
	private final Prenotazione initialPrenotazione;
	private Prenotazione prenotazione;
	
	private DefaultListModel<Camera> camere = new DefaultListModel<>();
	private JList<Camera> camereJList = new JList<>(camere);;
	private JScrollPane scrollPane = new JScrollPane();
	private DefaultListModel<Cliente> customerList = new DefaultListModel<>();
	private JList<Cliente> customerJList;
	private JScrollPane customerScrollPane;
	

	public NuovaPrenotazione(Prenotazione prenotazione, PrenotazioneService prenotazioneService, CameraService cameraService, ClienteService clienteService, DocumentoService documentoService) {
		this.initialPrenotazione = prenotazione;
		this.prenotazione = prenotazione;
		
		SwingComponentUtil.addHotelIcons(this);
		setResizable(false);
		setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		setTitle("HOTEL FAGGIOROSSO - NUOVA PRENOTAZIONE");
		
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
		
		JLabel checkinLabel = new JLabel("Data checkin");
		checkinLabel.setForeground(new Color(224, 255, 255));
		checkinLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		checkinLabel.setBounds(10, 210, 250, 36);
		newBookingLabel.add(checkinLabel);
		
		JLabel checkoutLabel = new JLabel("Data checkout");
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
		checkoutDateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("date"))
					refreshRooms(cameraService,checkinDateChooser.getDate(), checkoutDateChooser.getDate());
			}
		});
		newBookingLabel.add(checkoutDateChooser);
		
		JLabel sourceSpinner = new JLabel("Fonte prenotazione");
		sourceSpinner.setForeground(new Color(224, 255, 255));
		sourceSpinner.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		sourceSpinner.setBounds(10, 60, 295, 36);
		newBookingLabel.add(sourceSpinner);
		
		sourceComboBox = new JComboBox<>();
		sourceComboBox.setModel(new DefaultComboBoxModel<Fonte>(new Fonte[] {Fonte.BOOKING, Fonte.HOTEL}));
		sourceComboBox.setSelectedIndex(-1);
		sourceComboBox.setForeground(new Color(0, 128, 128));
		sourceComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		sourceComboBox.setBackground(new Color(224, 255, 255));
		sourceComboBox.setBounds(300, 60, 416, 36);
		newBookingLabel.add(sourceComboBox);
		
		JLabel boardTypeLabel = new JLabel("Tipologia pensione");
		boardTypeLabel.setForeground(new Color(224, 255, 255));
		boardTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		boardTypeLabel.setBounds(10, 110, 280, 36);
		newBookingLabel.add(boardTypeLabel);
		
		boardTypeComboBox = new JComboBox<>();
		boardTypeComboBox.setModel(new DefaultComboBoxModel<Pensione>(new Pensione[] {Pensione.PENSIONE_COMPLETA, Pensione.MEZZA_PENSIONE, Pensione.BNB}));
		boardTypeComboBox.setSelectedIndex(-1);
		boardTypeComboBox.setForeground(new Color(0, 128, 128));
		boardTypeComboBox.setFont(new Font("Tahoma", Font.BOLD, 17));
		boardTypeComboBox.setBackground(new Color(224, 255, 255));
		boardTypeComboBox.setBounds(300, 110, 416, 36);
		newBookingLabel.add(boardTypeComboBox);
		
		JLabel phoneNumberLabel = new JLabel("Telefono");
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
		
		JButton savePrenotazioneButton = new JButton("Salva prenotazione");
		savePrenotazioneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBooking(createBooking(), prenotazioneService);
			}
		});
		savePrenotazioneButton.setBounds(940, 663, 366, 48);
		newBookingLabel.add(savePrenotazioneButton);
		savePrenotazioneButton.setForeground(new Color(0, 128, 128));
		savePrenotazioneButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		savePrenotazioneButton.setBackground(new Color(224, 255, 255));
		
		JPanel upPanel = new JPanel();
		upPanel.setBackground(new Color(224, 255, 255));
		upPanel.setBounds(0, 0, 1480, 48);
		newBookingLabel.add(upPanel);
		upPanel.setLayout(null);
		
		JLabel addBookingLabel = new JLabel("Nuova Prenotazione");
		addBookingLabel.setBounds(0, 0, 1480, 46);
		upPanel.add(addBookingLabel);
		addBookingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addBookingLabel.setForeground(new Color(0, 128, 128));
		addBookingLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 40));
		
		JButton refreshButton = new JButton("Reimposta");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshFields();
			}
		});
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
		checkinDateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("date"))
					refreshRooms(cameraService, checkinDateChooser.getDate(), checkoutDateChooser.getDate());
			}
		});
		newBookingLabel.add(checkinDateChooser);
		
		emailAddressTextField = new JTextField();
		emailAddressTextField.setForeground(new Color(0, 128, 128));
		emailAddressTextField.setFont(new Font("Tahoma", Font.BOLD, 17));
		emailAddressTextField.setColumns(10);
		emailAddressTextField.setBackground(new Color(224, 255, 255));
		emailAddressTextField.setBounds(300, 410, 416, 36);
		newBookingLabel.add(emailAddressTextField);
		
		JLabel emailAddressLabel = new JLabel("Indirizzo e-mail");
		emailAddressLabel.setForeground(new Color(224, 255, 255));
		emailAddressLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		emailAddressLabel.setBounds(12, 410, 250, 36);
		newBookingLabel.add(emailAddressLabel);
		
		JButton calculatePaymentButton = new JButton("Calcola totale");
		calculatePaymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				NuovaPrenotazione.this.prenotazione = createBooking();
				if(NuovaPrenotazione.this.prenotazione != null)
					calculatePaymentLabel.setText("\u20AC. " + NuovaPrenotazione.this.prenotazione.getCostoTotale().toString());
			}
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
		
		JLabel paymentTypeLabel = new JLabel("Tipologia pagamento");
		paymentTypeLabel.setForeground(new Color(224, 255, 255));
		paymentTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		paymentTypeLabel.setBounds(755, 390, 309, 36);
		newBookingLabel.add(paymentTypeLabel);
		
		paymentTypeComboBox = new JComboBox<>();
		paymentTypeComboBox.setModel(new DefaultComboBoxModel<Pagato>(new Pagato[] {Pagato.PAGATO, Pagato.NON_PAGATO, Pagato.DEPOSITO}));
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
		
		camereJList = new JList<>(camere);
		camereJList.setSelectionForeground(new Color(102, 255, 204));
		camereJList.setForeground(new Color(0, 128, 128));
		camereJList.setFont(new Font("Tahoma", Font.BOLD, 17));
		camereJList.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(camereJList);
		
		JLabel roomsLabel = new JLabel("Camere riservate");
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
		
		JLabel referenceNameLabel = new JLabel("Nominativo");
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
		
		JLabel depositPaidLabel = new JLabel("Acconto versato");
		depositPaidLabel.setForeground(new Color(224, 255, 255));
		depositPaidLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 33));
		depositPaidLabel.setBounds(755, 440, 242, 36);
		newBookingLabel.add(depositPaidLabel);
		
		depositRadioButton = new JRadioButton("deposito");
		depositRadioButton.setBackground(new Color(0, 128, 128));
		depositRadioButton.setBounds(1003, 450, 22, 21);
		depositRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(depositRadioButton.isSelected()) {
					depositPaidTextField.setEnabled(true);
					depositPaidTextField.setEditable(true);
				} else {
					depositPaidTextField.setEnabled(false);
					depositPaidTextField.setEditable(false);
				}
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
		
		JLabel animalsNumberLabel = new JLabel("Numero animali");
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
		
		JButton addCustomer = new JButton("Aggiungi Cliente");
		addCustomer.setForeground(new Color(0, 128, 128));
		addCustomer.setFont(new Font("Dialog", Font.PLAIN, 33));
		addCustomer.setBackground(new Color(224, 255, 255));
		addCustomer.setBounds(351, 510, 366, 65);
		addCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new NuovoCliente(NuovaPrenotazione.this.prenotazione, prenotazioneService, clienteService, documentoService, customerList);
				refreshCustomers();
			}
		});
		newBookingLabel.add(addCustomer);
		
		JButton eliminaClienteButton = new JButton("Elimina Cliente");
		eliminaClienteButton.setBackground(new Color(224, 255, 255));
		eliminaClienteButton.setForeground(new Color(0, 128, 128));
		eliminaClienteButton.setFont(new Font("Dialog", Font.PLAIN, 33));
		eliminaClienteButton.setBounds(351, 585, 366, 65);
		eliminaClienteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente clienteSelezionato = customerJList.getSelectedValue();
				if(clienteSelezionato == null)
					JOptionPane.showMessageDialog(null, "SELEZIONA UN CLIENTE DA ELIMINARE");
				else {
					prenotazione.getClienti().remove(clienteSelezionato);
					prenotazioneService.savePrenotazione(prenotazione);
					clienteService.deleteCliente(clienteSelezionato);
					customerList.removeElement(clienteSelezionato);
					refreshCustomers();
					JOptionPane.showMessageDialog(null, "CLIENTE ELIMINATO");
				}
			}
		});
		newBookingLabel.add(eliminaClienteButton);
		
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
	public NuovaPrenotazione(PrenotazioneService prenotazioneService, CameraService cameraService, ClienteService clienteService, DocumentoService documentoService) {
		this(null, prenotazioneService, cameraService, clienteService, documentoService);
	}
	
	public void refreshFields() {
		if(initialPrenotazione != null) {
			phoneNumberTextField.setText(initialPrenotazione.getNumeroDiTelefono());
			emailAddressTextField.setText(initialPrenotazione.getEmail());
			referenceNameTextField.setText(initialPrenotazione.getCognome());
			depositPaidTextField.setText(initialPrenotazione.getDeposito().toString());
			checkinDateChooser.setDate(DateUtils.convertLocalDateToDate(initialPrenotazione.getDataInizio()));
			checkoutDateChooser.setDate(DateUtils.convertLocalDateToDate(initialPrenotazione.getDataFine()));
			sourceComboBox.setSelectedItem(initialPrenotazione.getFonte());
			boardTypeComboBox.setSelectedItem(initialPrenotazione.getPensione());
			paymentTypeComboBox.setSelectedItem(initialPrenotazione.getPagato());
			animalsNumberSpinner.setValue(initialPrenotazione.getNumeroAnimali());
			camereJList.setSelectedIndices(findRooms());
			if(prenotazione.getDeposito().intValue() != 0) {
				depositRadioButton.setSelected(true);
				depositPaidTextField.setText(initialPrenotazione.getDeposito().toString());
			} else {
				depositRadioButton.setSelected(false);
				depositPaidTextField.setText("");
			}
			calculatePaymentLabel.setText("\u20AC. " + initialPrenotazione.getCostoTotale().toString());
			
			customerList = new DefaultListModel<>();
			customerList.addAll(initialPrenotazione.getClienti());
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
			camereJList.clearSelection();
			depositRadioButton.setSelected(false);
			depositPaidTextField.setText("");
			calculatePaymentLabel.setText("\u20AC. ");
			
			this.customerList = new DefaultListModel<>();
			
			refreshCustomers();
		}
	}

	private Prenotazione createBooking() {
		if(checkFields()) {
			String cognome = referenceNameTextField.getText();
			String email = emailAddressTextField.getText();
			String numeroDiTelefono = phoneNumberTextField.getText();
			LocalDate dataInizio = DateUtils.convertDateToLocalDate(checkinDateChooser.getDate());
			LocalDate dataFine = DateUtils.convertDateToLocalDate(checkoutDateChooser.getDate());
			int numeroAnimali = (int) animalsNumberSpinner.getValue();
			Pagato pagato = (Pagato) paymentTypeComboBox.getSelectedItem();
			Pensione pensione = (Pensione) boardTypeComboBox.getSelectedItem();
			Fonte fonte = (Fonte) sourceComboBox.getSelectedItem();
			Set<Cliente> clienti = Arrays.stream(customerList.toArray()).map(x -> (Cliente) x).collect(Collectors.toSet());
			Set<Camera> camere = new HashSet<>(camereJList.getSelectedValuesList());
			BigDecimal deposito = BigDecimal.ZERO;
			if(depositRadioButton.isSelected())
				if(checkDeposit())
					deposito = BigDecimal.valueOf(Double.valueOf(depositPaidTextField.getText()));
				else
					return null;
			if(prenotazione != null) {
				prenotazione.setCognome(cognome);
				prenotazione.setEmail(email);
				prenotazione.setNumeroDiTelefono(numeroDiTelefono);
				prenotazione.setDataInizio(dataInizio);
				prenotazione.setDataFine(dataFine);
				prenotazione.setNumeroAnimali(numeroAnimali);
				prenotazione.setPagato(pagato);
				prenotazione.setDeposito(deposito);
				prenotazione.setPensione(pensione);
				prenotazione.setFonte(fonte);
				prenotazione.setClienti(clienti);
				prenotazione.setCamere(camere);
			} else
				prenotazione = new Prenotazione(cognome, email, numeroDiTelefono, dataInizio, dataFine, 
						numeroAnimali, pagato, deposito, pensione, fonte, clienti, camere);
			return prenotazione;
		}
		return null;
	}
	
	private void addBooking(Prenotazione prenotazione, PrenotazioneService prenotazioneService) {
		if(prenotazione != null) {
			prenotazioneService.savePrenotazione(prenotazione);
			JOptionPane.showMessageDialog(null, "PRENOTAZIONE SALVATA CORRETTAMENTE");
			dispose();
		}
		else
			JOptionPane.showMessageDialog(null, "SI E' VERIFICATO UN ERRORE, LA PRENOTAZIONE NON E' STATA SALVATA");
	}
	
	public int[] findRooms() {
		List<Integer> roomIndicies = new ArrayList<>();
		for (int i = 0; i < camere.size(); i++) {
			if(prenotazione.getCamere().contains(camere.get(i)))
				roomIndicies.add(i);
		}
		return roomIndicies.stream().mapToInt(i -> i).toArray();
	}
	
	private boolean checkPhoneNumber() {
		if(!phoneNumberTextField.getText().isBlank()) return true;
		else {
			JOptionPane.showMessageDialog(null, "INSERISCI UN NUMERO DI TELEFONO VALIDO");
			phoneNumberTextField.setText("");
		}
		return false;
	}
	
	private boolean checkEmail() {
		if(!emailAddressTextField.getText().isBlank()) return true;
		else {
			JOptionPane.showMessageDialog(null, "INSERISCI UNA MAIL VALIDA");
			emailAddressTextField.setText("");
		}
		return false;
	}
	
	private boolean checkReference() {
		if(!referenceNameTextField.getText().isBlank()) return true;
		else {
			JOptionPane.showMessageDialog(null, "INSERISCI UN NOME VALIDO");
			referenceNameTextField.setText("");
		}
		return false;
	}
	
	private boolean checkDeposit() {
		if(!depositPaidTextField.getText().isBlank()) return true;
		else {
			JOptionPane.showMessageDialog(null, "INSERISCI UN IMPORTO VALIDO PER L'ACCONTO");
			depositPaidTextField.setText("");
		}
		return false;
	}
	
	private boolean checkSource() {
		if(sourceComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA LA FONTE DELLA PRENOTAZIONE");
		return false;
	}
	
	private boolean checkBoardType() {
		if(boardTypeComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA IL TIPO DI PENSIONE");
		return false;
	}
	
	private boolean checkPaymentType() {
		if(paymentTypeComboBox.getSelectedIndex() != -1) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA IL TIPO DI PAGAMENTO");
		return false;
	}
	
	private boolean camereCheck() {
		if(camereJList.getSelectedValuesList().size() > 0) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA ALMENO UNA CAMERA");
		return false;
	}
	
	private boolean checkCheckInDate() {
		if(checkinDateChooser.getDate() != null) return true;
//		if(DateUtils.convertDateToLocalDate(checkinDateChooser.getDate()).isAfter(LocalDate.now().minusDays(1)))
//			return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA UNA DATA DI INIZIO VALIDA");
		return false;
	}
	
	private boolean checkCheckOutDate() {
		if(checkoutDateChooser.getDate() != null) return true;
//		if(DateUtils.convertDateToLocalDate(checkinDateChooser.getDate()).isBefore(DateUtils.convertDateToLocalDate(checkoutDateChooser.getDate())))
//			return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA UNA DATA DI FINE VALIDA");
		return false;
	}
	
	private boolean checkPets() {
		if((int) animalsNumberSpinner.getValue() >= 0) return true;
		JOptionPane.showMessageDialog(null, "SELEZIONA UN NUMERO DI ANIMALI VALIDO");
		return false;
	}
	
	private boolean checkFields() {
		if (checkBoardType() && checkEmail() && checkPaymentType() 
				&& checkPhoneNumber() && checkReference() && checkSource() 
				&& camereCheck() && checkCheckInDate() && checkCheckOutDate()
				&& checkPets()) return true;
		return false;
	}
	
	private void refreshCustomers() {
		customerJList.setModel(customerList);
		customerScrollPane.setViewportView(customerJList);
	}
	
	private void refreshRooms(CameraService cameraService, Date start, Date end) {
		camere = new DefaultListModel<>();
		
		if(start != null && end != null) {
			LocalDate localDateStart = DateUtils.convertDateToLocalDate(start);
			LocalDate localDateEnd = DateUtils.convertDateToLocalDate(end);
			Set<Camera> availableRooms = cameraService.findAvailableRooms(localDateStart, localDateEnd);
			if(initialPrenotazione != null && initialPrenotazione.getDataInizio().equals(localDateStart) 
					&& initialPrenotazione.getDataFine().equals(localDateEnd))
				availableRooms.addAll(prenotazione.getCamere());
			camere.addAll(availableRooms.stream()
					.toList().stream()
					.sorted((c1, c2) -> Integer.compare(c1.getTipoCamera().getTipo().getPersone(),
							c2.getTipoCamera().getTipo().getPersone())).toList());
		}
		
		camereJList.setModel(camere);
		scrollPane.setViewportView(camereJList);
	}
}