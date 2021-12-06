package it.faggiorosso.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import it.faggiorosso.entity.Cliente;
import it.faggiorosso.service.ClienteService;
import it.faggiorosso.util.SwingComponentUtil;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.awt.event.ActionEvent;

public class RegistroClienti extends JFrame {

	@Serial
	private static final long serialVersionUID = -5496041474637489808L;
	private JPanel contentPane;
	private JTextField research;
	
	private DefaultListModel<Cliente> customers = new DefaultListModel<>();
	private JList<Cliente> customersJList;
	private JScrollPane scrollPane = new JScrollPane();
	
	private JLabel bookingResult;
	private JLabel surname;
	private JLabel name;
	private JLabel dateOfBirth;
	private JLabel stateOfBirth;
	private JLabel placeOfBirth;
	private JLabel citizenship;
	private JLabel ageType;
	private JLabel gender;
	private JLabel idType;
	private JLabel idNumber;
	private JLabel releaseIdDate;
	private JLabel expireIdDate;
	private JLabel idReleaseSource;
	private JLabel idReleasePlace;

	public RegistroClienti(ClienteService clienteService) {
		SwingComponentUtil.addHotelIcons(this);
		customers.addAll(clienteService.getAll());
		customersJList = new JList<>(customers);
		scrollPane.setViewportView(customersJList);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel idTypeLabel = new JLabel("documento");
		idTypeLabel.setForeground(new Color(224, 255, 255));
		idTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		idTypeLabel.setBounds(1014, 337, 205, 28);
		resultPanel.add(idTypeLabel);
		
		JLabel idNumberLabel = new JLabel("numero documento");
		idNumberLabel.setForeground(new Color(224, 255, 255));
		idNumberLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		idNumberLabel.setBounds(1014, 375, 205, 28);
		resultPanel.add(idNumberLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u20AC");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(1204, 531, 26, 28);
		resultPanel.add(lblNewLabel_1);
		
		JLabel bookingLabel_1 = new JLabel("prenotazione");
		bookingLabel_1.setForeground(new Color(224, 255, 255));
		bookingLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		bookingLabel_1.setBounds(1012, 10, 162, 28);
		resultPanel.add(bookingLabel_1);
		
		JLabel surnameLabel = new JLabel("cognome");
		surnameLabel.setForeground(new Color(224, 255, 255));
		surnameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		surnameLabel.setBounds(1014, 43, 162, 28);
		resultPanel.add(surnameLabel);
		
		JLabel nameLabel = new JLabel("nome");
		nameLabel.setForeground(new Color(224, 255, 255));
		nameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		nameLabel.setBounds(1012, 81, 162, 28);
		resultPanel.add(nameLabel);
		
		JLabel birthDateLabel = new JLabel("data di nascita");
		birthDateLabel.setForeground(new Color(224, 255, 255));
		birthDateLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		birthDateLabel.setBounds(1012, 120, 162, 28);
		resultPanel.add(birthDateLabel);
		
		JLabel residenceLabel = new JLabel("residenza");
		residenceLabel.setForeground(new Color(224, 255, 255));
		residenceLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		residenceLabel.setBounds(1012, 229, 205, 28);
		resultPanel.add(residenceLabel);
		
		JLabel placeOfBirthLabel = new JLabel("luogo di nascita");
		placeOfBirthLabel.setForeground(new Color(224, 255, 255));
		placeOfBirthLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		placeOfBirthLabel.setBounds(1012, 193, 204, 28);
		resultPanel.add(placeOfBirthLabel);
		
		JLabel stateOfBirthLabel = new JLabel("stato di nascita");
		stateOfBirthLabel.setForeground(new Color(224, 255, 255));
		stateOfBirthLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		stateOfBirthLabel.setBounds(1013, 155, 161, 28);
		resultPanel.add(stateOfBirthLabel);
		
		JLabel typeLabel = new JLabel("tipologia");
		typeLabel.setForeground(new Color(224, 255, 255));
		typeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		typeLabel.setBounds(1012, 261, 205, 28);
		resultPanel.add(typeLabel);
		
		JLabel genderLabel = new JLabel("genere");
		genderLabel.setForeground(new Color(224, 255, 255));
		genderLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		genderLabel.setBounds(1012, 299, 205, 28);
		resultPanel.add(genderLabel);
		
		JLabel dreleaseIdLabel = new JLabel("data rilascio");
		dreleaseIdLabel.setForeground(new Color(224, 255, 255));
		dreleaseIdLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		dreleaseIdLabel.setBounds(1014, 413, 205, 28);
		resultPanel.add(dreleaseIdLabel);
		
		JLabel idExpireDatelabel = new JLabel("data scadenza");
		idExpireDatelabel.setForeground(new Color(224, 255, 255));
		idExpireDatelabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		idExpireDatelabel.setBounds(1014, 451, 205, 28);
		resultPanel.add(idExpireDatelabel);
		
		JLabel releaseSourceId = new JLabel("rilasciato da");
		releaseSourceId.setForeground(new Color(224, 255, 255));
		releaseSourceId.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		releaseSourceId.setBounds(1014, 489, 204, 28);
		resultPanel.add(releaseSourceId);
		
		bookingResult = new JLabel("");
		bookingResult.setForeground(new Color(224, 255, 255));
		bookingResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		bookingResult.setBounds(1228, 10, 185, 28);
		resultPanel.add(bookingResult);
		
		JLabel placeOfReleaseLabel = new JLabel("luogo di rilascio");
		placeOfReleaseLabel.setForeground(new Color(224, 255, 255));
		placeOfReleaseLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		placeOfReleaseLabel.setBounds(1014, 527, 204, 28);
		resultPanel.add(placeOfReleaseLabel);
		
		surname = new JLabel("");
		surname.setForeground(new Color(224, 255, 255));
		surname.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		surname.setBounds(1228, 43, 185, 28);
		resultPanel.add(surname);
		
		dateOfBirth = new JLabel("");
		dateOfBirth.setForeground(new Color(224, 255, 255));
		dateOfBirth.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		dateOfBirth.setBounds(1228, 114, 185, 28);
		resultPanel.add(dateOfBirth);
		
		name = new JLabel("");
		name.setForeground(new Color(224, 255, 255));
		name.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		name.setBounds(1228, 81, 185, 28);
		resultPanel.add(name);
		
		ageType = new JLabel("");
		ageType.setForeground(new Color(224, 255, 255));
		ageType.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		ageType.setBounds(1227, 261, 185, 28);
		resultPanel.add(ageType);
		
		citizenship = new JLabel("");
		citizenship.setForeground(new Color(224, 255, 255));
		citizenship.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		citizenship.setBounds(1227, 223, 185, 28);
		resultPanel.add(citizenship);
		
		placeOfBirth = new JLabel("");
		placeOfBirth.setForeground(new Color(224, 255, 255));
		placeOfBirth.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		placeOfBirth.setBounds(1227, 185, 185, 28);
		resultPanel.add(placeOfBirth);
		
		stateOfBirth = new JLabel("");
		stateOfBirth.setForeground(new Color(224, 255, 255));
		stateOfBirth.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		stateOfBirth.setBounds(1227, 152, 185, 28);
		resultPanel.add(stateOfBirth);
		
		idReleasePlace = new JLabel("");
		idReleasePlace.setForeground(new Color(224, 255, 255));
		idReleasePlace.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		idReleasePlace.setBounds(1228, 527, 185, 28);
		resultPanel.add(idReleasePlace);
		
		idReleaseSource = new JLabel("");
		idReleaseSource.setForeground(new Color(224, 255, 255));
		idReleaseSource.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		idReleaseSource.setBounds(1228, 489, 185, 28);
		resultPanel.add(idReleaseSource);
		
		expireIdDate = new JLabel("");
		expireIdDate.setForeground(new Color(224, 255, 255));
		expireIdDate.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		expireIdDate.setBounds(1228, 451, 185, 28);
		resultPanel.add(expireIdDate);
		
		releaseIdDate = new JLabel("");
		releaseIdDate.setForeground(new Color(224, 255, 255));
		releaseIdDate.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		releaseIdDate.setBounds(1227, 408, 185, 28);
		resultPanel.add(releaseIdDate);
		
		idNumber = new JLabel("");
		idNumber.setForeground(new Color(224, 255, 255));
		idNumber.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		idNumber.setBounds(1228, 370, 185, 28);
		resultPanel.add(idNumber);
		
		idType = new JLabel("");
		idType.setForeground(new Color(224, 255, 255));
		idType.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		idType.setBounds(1228, 337, 185, 28);
		resultPanel.add(idType);
		
		gender = new JLabel("");
		gender.setForeground(new Color(224, 255, 255));
		gender.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		gender.setBounds(1228, 299, 185, 28);
		resultPanel.add(gender);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(994, 0, 5, 604);
		resultPanel.add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(0, 128, 128));
		scrollPane.setBounds(10, 10, 979, 537);
		resultPanel.add(scrollPane);
		
	
		customersJList = new JList<>(customers);
		customersJList.setSelectionBackground(new Color(0, 128, 128));
		customersJList.setForeground(new Color(0, 128, 128));
		customersJList.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		customersJList.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(customersJList);
		
		JLabel titleJListLabel = new JLabel("Lista Clienti");
		titleJListLabel.setForeground(new Color(0, 128, 128));
		titleJListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleJListLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		scrollPane.setColumnHeaderView(titleJListLabel);
		
		JButton btnNewButton_1_1 = new JButton("mostra dati");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCustomerSelectedData();
			}
		});
		btnNewButton_1_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnNewButton_1_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1_1.setForeground(new Color(0, 128, 128));
		btnNewButton_1_1.setBounds(358, 557, 272, 37);
		resultPanel.add(btnNewButton_1_1);
		
		JPanel leftRegisterPanel = new JPanel();
		leftRegisterPanel.setBackground(new Color(224, 255, 255));
		leftRegisterPanel.setBounds(10, 10, 51, 763);
		contentPane.add(leftRegisterPanel);
		
		setVisible(true);
	}
	
	private void showCustomerSelectedData() {
		if (customersJList.getSelectedValue() != null)
		{
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "VUOI VEDERE LE INFORMAZIONI RELATIVI AL CLIENTE SELEZIONATO?","MOSTRA INFO CLIENTE", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION)
			{
				Cliente cliente = customersJList.getSelectedValue();
				surname.setText(cliente.getCognome());
				name.setText(cliente.getNome());
				dateOfBirth.setText(cliente.getDataDiNascita().toString());
				stateOfBirth.setText(cliente.getStatoDiNascita());
				placeOfBirth.setText(cliente.getComuneDiNascita());
				citizenship.setText(cliente.getCittadinanza());
				ageType.setText(cliente.getEta().name());
				gender.setText(cliente.getSesso().name());
				idType.setText(cliente.getDocumento().getTipoDocumento().name());
				idNumber.setText(cliente.getDocumento().getNumero());
				releaseIdDate.setText(cliente.getDocumento().getDataDiRilascio().toString());
				expireIdDate.setText(cliente.getDocumento().getDataDiScadenza().toString());
				idReleaseSource.setText(cliente.getDocumento().getRilascio().name());
				idReleasePlace.setText(cliente.getDocumento().getLuogoDiRilascio());
				
				JOptionPane.showMessageDialog(null, "DATI CARICATI");
			}
			
		}
		else JOptionPane.showMessageDialog(null, "SELEZIONA IL CLIENTE DI CUI SI VOGLIONE VEDERE LE INFORMAZIONI");
	}
	
private void searchResults(String charSeq) {
		
		JOptionPane.showMessageDialog(null, "AVVIO RICERCA");
		
		DefaultListModel<Cliente> clientiFiltrati = new DefaultListModel<>();
		
		for (int i = 0; i < customers.size(); i++)
		{
			if (charSeq.length() != 0)
			{
				if (customers.get(i).toString().contains(charSeq))
				{
					clientiFiltrati.addElement(customers.get(i));
				}
			}
		}
		if (clientiFiltrati.size() < 1)
		{
			JOptionPane.showMessageDialog(null, "NESSUN RISULTATO TROVATO");
			research.setText("");
			
		}
		else
		{
			customersJList.setModel(clientiFiltrati);
			scrollPane.setViewportView(customersJList);
			research.setText("");
		
			JOptionPane.showMessageDialog(null, "RICERCA COMPLETATA");
		}
	}
	
//	private void refreshCustomers() {
//		customersJList.setModel(customers);
//		scrollPane.setViewportView(customersJList);
//	}
}