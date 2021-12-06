package it.faggiorosso.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import it.faggiorosso.entity.Camera;
import it.faggiorosso.entity.Prenotazione;
import it.faggiorosso.service.PrenotazioneService;
import it.faggiorosso.util.SwingComponentUtil;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroPrenotazioni extends JFrame {

	private static final long serialVersionUID = -4144532333900919080L;
	private JPanel contentPane;
	private JTextField research;
	private DefaultListModel<Prenotazione> prenotazioni = new DefaultListModel<>();
	private JList<Prenotazione> prenotazioniJList;
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
	
	public RegistroPrenotazioni(PrenotazioneService prenotazioneService) {
		SwingComponentUtil.addHotelIcons(this);
		prenotazioni.addAll(prenotazioneService.getAll());
		prenotazioniJList = new JList<>(prenotazioni);
		scrollPane.setViewportView(prenotazioniJList);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 1537, 820);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JLabel registerLabel = new JLabel("Registro Prenotazioni");
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
		
		JButton btnNewButton = new JButton("ricerca");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchResults(research.getText());
			}
		});
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
		
		JLabel emailAddressLabel = new JLabel("indirizzo e-mail");
		emailAddressLabel.setForeground(new Color(224, 255, 255));
		emailAddressLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		emailAddressLabel.setBounds(1014, 337, 205, 28);
		resultPanel.add(emailAddressLabel);
		
		JLabel boardTypeLabel = new JLabel("tipologia pensione");
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
		
		JLabel bookingLabel_1 = new JLabel("id prenotazione");
		bookingLabel_1.setForeground(new Color(224, 255, 255));
		bookingLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		bookingLabel_1.setBounds(1012, 10, 162, 28);
		resultPanel.add(bookingLabel_1);
		
		JLabel referenceNameLabel_1 = new JLabel("nominativo");
		referenceNameLabel_1.setForeground(new Color(224, 255, 255));
		referenceNameLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		referenceNameLabel_1.setBounds(1014, 43, 162, 28);
		resultPanel.add(referenceNameLabel_1);
		
		JLabel checkinLabel_1 = new JLabel("data checkin");
		checkinLabel_1.setForeground(new Color(224, 255, 255));
		checkinLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		checkinLabel_1.setBounds(1012, 81, 162, 28);
		resultPanel.add(checkinLabel_1);
		
		JLabel checkoutLabel_1 = new JLabel("data checkout");
		checkoutLabel_1.setForeground(new Color(224, 255, 255));
		checkoutLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		checkoutLabel_1.setBounds(1012, 120, 162, 28);
		resultPanel.add(checkoutLabel_1);
		
		JLabel childrenNumberLabel_1 = new JLabel("numero bambini 0-3");
		childrenNumberLabel_1.setForeground(new Color(224, 255, 255));
		childrenNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		childrenNumberLabel_1.setBounds(1012, 229, 205, 28);
		resultPanel.add(childrenNumberLabel_1);
		
		JLabel teenagersNumberLabel_1 = new JLabel("numero minori 14");
		teenagersNumberLabel_1.setForeground(new Color(224, 255, 255));
		teenagersNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		teenagersNumberLabel_1.setBounds(1012, 193, 204, 28);
		resultPanel.add(teenagersNumberLabel_1);
		
		JLabel adultsNumberLabel_1 = new JLabel("numero adulti");
		adultsNumberLabel_1.setForeground(new Color(224, 255, 255));
		adultsNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		adultsNumberLabel_1.setBounds(1013, 155, 161, 28);
		resultPanel.add(adultsNumberLabel_1);
		
		JLabel animalsNumberLabel_1 = new JLabel("numero animali");
		animalsNumberLabel_1.setForeground(new Color(224, 255, 255));
		animalsNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		animalsNumberLabel_1.setBounds(1012, 261, 205, 28);
		resultPanel.add(animalsNumberLabel_1);
		
		JLabel phoneNumberLabel_1 = new JLabel("numero cellulare");
		phoneNumberLabel_1.setForeground(new Color(224, 255, 255));
		phoneNumberLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		phoneNumberLabel_1.setBounds(1012, 299, 205, 28);
		resultPanel.add(phoneNumberLabel_1);
		
		JLabel sourceSpinner_1 = new JLabel("fonte prenotazione");
		sourceSpinner_1.setForeground(new Color(224, 255, 255));
		sourceSpinner_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		sourceSpinner_1.setBounds(1014, 413, 205, 28);
		resultPanel.add(sourceSpinner_1);
		
		JLabel paymentTypeLabel_1 = new JLabel("tipologia pagamento");
		paymentTypeLabel_1.setForeground(new Color(224, 255, 255));
		paymentTypeLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		paymentTypeLabel_1.setBounds(1014, 490, 205, 28);
		resultPanel.add(paymentTypeLabel_1);
		
		JLabel depositPaidLabel_1 = new JLabel("acconto versato");
		depositPaidLabel_1.setForeground(new Color(224, 255, 255));
		depositPaidLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		depositPaidLabel_1.setBounds(1014, 528, 204, 28);
		resultPanel.add(depositPaidLabel_1);
		
		id = new JLabel("");
		id.setForeground(new Color(224, 255, 255));
		id.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		id.setBounds(1228, 10, 185, 28);
		resultPanel.add(id);
		
		JLabel totalPaymentLabel = new JLabel("totale dovuto");
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
	
		prenotazioniJList = new JList<>(prenotazioni);
		prenotazioniJList.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(prenotazioniJList);
		
		JLabel titleJListLabel = new JLabel("Lista Prenotazioni");
		titleJListLabel.setForeground(new Color(0, 128, 128));
		titleJListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleJListLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		scrollPane.setColumnHeaderView(titleJListLabel);
		
		JButton btnNewButton_1_1 = new JButton("mostra dati");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSelectedBookingData();
				
			}
		});
		btnNewButton_1_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnNewButton_1_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1_1.setForeground(new Color(0, 128, 128));
		btnNewButton_1_1.setBounds(341, 557, 272, 37);
		resultPanel.add(btnNewButton_1_1);
		
		JLabel roomsLabel = new JLabel("camere occupate");
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
		
		JOptionPane.showMessageDialog(null, "AVVIO RICERCA");
		
		DefaultListModel<Prenotazione> prenotazioniFiltrate = new DefaultListModel<>();
		
		for (int i = 0; i < prenotazioni.size(); i++)
		{
			if (charSeq.length() != 0)
			{
				if (prenotazioni.get(i).toString().contains(charSeq))
				{
					prenotazioniFiltrate.addElement(prenotazioni.get(i));
				}
			}
		}
		if (prenotazioniFiltrate.size() < 1)
		{
			JOptionPane.showMessageDialog(null, "NESSUN RISULTATO TROVATO");
			research.setText("");
			
		}
		else
		{
			prenotazioniJList.setModel(prenotazioniFiltrate);
			scrollPane.setViewportView(prenotazioniJList);
			research.setText("");
		
			JOptionPane.showMessageDialog(null, "RICERCA COMPLETATA");
		}
	}
	
//	private void refreshBookings() {
//		prenotazioniJList.setModel(prenotazioni);
//		scrollPane.setViewportView(prenotazioniJList);
//	}
	
	private void showSelectedBookingData()
	{
		if (prenotazioniJList.getSelectedValue() != null)
		{
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "VUOI VISUALIZZARE LE INFORMAZIONI DELLA PRENOTAZIONE SELEZIONATA?","MOSTRA INFO PRENOTAZIONE",JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION)
			{
				Prenotazione prenotazione = prenotazioniJList.getSelectedValue();
				id.setText(Long.toString(prenotazione.getId()));
				reference.setText(prenotazione.getCognome());
				checkIn.setText(prenotazione.getDataInizio().toString());
				checkout.setText(prenotazione.getDataFine().toString());
				adultNumberResult.setText(Integer.toString(prenotazione.getNumeroDiAdulti()));
				teenagersNumberResult.setText(Integer.toString(prenotazione.getNumeroDiMinori()));
				childrenNumberResult.setText(Integer.toString(prenotazione.getNumeroDiBambini()));
				animalNumberResult.setText(Integer.toString(prenotazione.getNumeroAnimali()));
				phoneNumberResult.setText(prenotazione.getNumeroDiTelefono());
				emailResult.setText(prenotazione.getEmail());
				typeBoardResult.setText(prenotazione.getPensione().name());
				sourceResult.setText(prenotazione.getFonte().name());
				paymentTypeResult.setText(prenotazione.getPagato().name());
				depositPaidResult.setText(prenotazione.getDeposito().toString());
				totalResult.setText(prenotazione.getCostoTotale().toString());
				
				String s = "";
				for (Camera camera : prenotazione.getCamere())
					s += camera.getNumero() + " ";
				
				rooms.setText(s);
				
				JOptionPane.showMessageDialog(null, "DATI CARICATI");
			}
		}
		else JOptionPane.showMessageDialog(null, "SELEZIONA LA PRENOTAZIONE DI CUI SI VOGLIONO VEDERE LE INFORMAZIONI");
	}
	
	public void showRequiredData(Prenotazione prenotazione) {
		id.setText(Long.toString(prenotazione.getId()));
		reference.setText(prenotazione.getCognome());
		checkIn.setText(prenotazione.getDataInizio().toString());
		checkout.setText(prenotazione.getDataFine().toString());
		adultNumberResult.setText(Integer.toString(prenotazione.getNumeroDiAdulti()));
		teenagersNumberResult.setText(Integer.toString(prenotazione.getNumeroDiMinori()));
		childrenNumberResult.setText(Integer.toString(prenotazione.getNumeroDiBambini()));
		animalNumberResult.setText(Integer.toString(prenotazione.getNumeroAnimali()));
		phoneNumberResult.setText(prenotazione.getNumeroDiTelefono());
		emailResult.setText(prenotazione.getEmail());
		typeBoardResult.setText(prenotazione.getPensione().name());
		sourceResult.setText(prenotazione.getFonte().name());
		paymentTypeResult.setText(prenotazione.getPagato().name());
		depositPaidResult.setText(prenotazione.getDeposito().toString());
		totalResult.setText(prenotazione.getCostoTotale().toString());
		
		String s = "";
		for (Camera camera : prenotazione.getCamere())
			s += camera.getNumero() + " ";
		
		rooms.setText(s);
	}
}