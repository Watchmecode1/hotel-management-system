package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import com.hotel.entity.Customer;
import com.hotel.service.CustomerService;
import com.hotel.util.SwingComponentUtil;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.io.Serial;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CustomerRegister extends JFrame {

	@Serial
	private static final long serialVersionUID = -5496041474637489808L;
	private JTextField research;
	
	private DefaultListModel<Customer> customers = new DefaultListModel<>();
	private JList<Customer> customersJList;
	private JScrollPane scrollPane = new JScrollPane();

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

	public CustomerRegister(CustomerService customerService) {
		SwingComponentUtil.addHotelIcons(this);
		customers.addAll(customerService.getAll());
		customersJList = new JList<>(customers);
		scrollPane.setViewportView(customersJList);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1537, 820);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		registerPanel.setBackground(new Color(0, 128, 128));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(224, 255, 255));
		
		JLabel registerLabel = new JLabel("Customer register");
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
		
		JLabel idTypeLabel = new JLabel("Document");
		idTypeLabel.setForeground(new Color(224, 255, 255));
		idTypeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel idNumberLabel = new JLabel("Document number");
		idNumberLabel.setForeground(new Color(224, 255, 255));
		idNumberLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel lblNewLabel_1 = new JLabel("\u20AC");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel bookingLabel_1 = new JLabel("Reservation");
		bookingLabel_1.setForeground(new Color(224, 255, 255));
		bookingLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel surnameLabel = new JLabel("Surname");
		surnameLabel.setForeground(new Color(224, 255, 255));
		surnameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setForeground(new Color(224, 255, 255));
		nameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel birthDateLabel = new JLabel("Date of birth");
		birthDateLabel.setForeground(new Color(224, 255, 255));
		birthDateLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel residenceLabel = new JLabel("Residence");
		residenceLabel.setForeground(new Color(224, 255, 255));
		residenceLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel placeOfBirthLabel = new JLabel("Birth place");
		placeOfBirthLabel.setForeground(new Color(224, 255, 255));
		placeOfBirthLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel stateOfBirthLabel = new JLabel("State of birth");
		stateOfBirthLabel.setForeground(new Color(224, 255, 255));
		stateOfBirthLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel typeLabel = new JLabel("Housed");
		typeLabel.setForeground(new Color(224, 255, 255));
		typeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setForeground(new Color(224, 255, 255));
		genderLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel releaseDateLabel = new JLabel("Release date");
		releaseDateLabel.setForeground(new Color(224, 255, 255));
		releaseDateLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel idExpireDateLabel = new JLabel("Expiration date");
		idExpireDateLabel.setForeground(new Color(224, 255, 255));
		idExpireDateLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel releaseSourceId = new JLabel("Released by");
		releaseSourceId.setForeground(new Color(224, 255, 255));
		releaseSourceId.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));

		JLabel bookingResult = new JLabel("");
		bookingResult.setForeground(new Color(224, 255, 255));
		bookingResult.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel placeOfReleaseLabel = new JLabel("Place of issue");
		placeOfReleaseLabel.setForeground(new Color(224, 255, 255));
		placeOfReleaseLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		surname = new JLabel("");
		surname.setForeground(new Color(224, 255, 255));
		surname.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		dateOfBirth = new JLabel("");
		dateOfBirth.setForeground(new Color(224, 255, 255));
		dateOfBirth.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		name = new JLabel("");
		name.setForeground(new Color(224, 255, 255));
		name.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		ageType = new JLabel("");
		ageType.setForeground(new Color(224, 255, 255));
		ageType.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		citizenship = new JLabel("");
		citizenship.setForeground(new Color(224, 255, 255));
		citizenship.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		placeOfBirth = new JLabel("");
		placeOfBirth.setForeground(new Color(224, 255, 255));
		placeOfBirth.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		stateOfBirth = new JLabel("");
		stateOfBirth.setForeground(new Color(224, 255, 255));
		stateOfBirth.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		idReleasePlace = new JLabel("");
		idReleasePlace.setForeground(new Color(224, 255, 255));
		idReleasePlace.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		idReleaseSource = new JLabel("");
		idReleaseSource.setForeground(new Color(224, 255, 255));
		idReleaseSource.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		expireIdDate = new JLabel("");
		expireIdDate.setForeground(new Color(224, 255, 255));
		expireIdDate.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		releaseIdDate = new JLabel("");
		releaseIdDate.setForeground(new Color(224, 255, 255));
		releaseIdDate.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		idNumber = new JLabel("");
		idNumber.setForeground(new Color(224, 255, 255));
		idNumber.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		idType = new JLabel("");
		idType.setForeground(new Color(224, 255, 255));
		idType.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		gender = new JLabel("");
		gender.setForeground(new Color(224, 255, 255));
		gender.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(0, 128, 128));
		
	
		customersJList = new JList<>(customers);
		customersJList.setSelectionBackground(new Color(0, 128, 128));
		customersJList.setForeground(new Color(0, 128, 128));
		customersJList.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		customersJList.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(customersJList);
		
		JLabel titleJListLabel = new JLabel("Customers");
		titleJListLabel.setForeground(new Color(0, 128, 128));
		titleJListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleJListLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		scrollPane.setColumnHeaderView(titleJListLabel);
		
		JButton btnNewButton_1_1 = new JButton("Show data");
		btnNewButton_1_1.addActionListener(e -> showCustomerSelectedData());
		btnNewButton_1_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnNewButton_1_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1_1.setForeground(new Color(0, 128, 128));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(registerPanel, GroupLayout.DEFAULT_SIZE, 1508, Short.MAX_VALUE)
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(registerPanel, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE))
		);
		GroupLayout gl_registerPanel = new GroupLayout(registerPanel);
		gl_registerPanel.setHorizontalGroup(
			gl_registerPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, 1498, Short.MAX_VALUE)
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
					.addComponent(resultPanel, GroupLayout.PREFERRED_SIZE, 604, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
		);
		GroupLayout gl_resultPanel = new GroupLayout(resultPanel);
		gl_resultPanel.setHorizontalGroup(
			gl_resultPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_resultPanel.createSequentialGroup()
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(354)
							.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
							.addGap(358)))
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(bookingLabel_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(bookingResult, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(surnameLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(surname, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(name, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(birthDateLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(dateOfBirth, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(1)
							.addComponent(stateOfBirthLabel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(stateOfBirth, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(placeOfBirthLabel, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(placeOfBirth, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(residenceLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(citizenship, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(typeLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(ageType, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addComponent(genderLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(gender, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(idTypeLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(idType, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(idNumberLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(idNumber, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(releaseDateLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(releaseIdDate, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(idExpireDateLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(expireIdDate, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(releaseSourceId, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(idReleaseSource, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_resultPanel.createSequentialGroup()
									.addGap(214)
									.addComponent(idReleasePlace, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_resultPanel.createSequentialGroup()
									.addGap(190)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
								.addComponent(placeOfReleaseLabel, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))))
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
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
				.addGroup(gl_resultPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(bookingLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookingResult, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(surnameLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(surname, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(birthDateLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(dateOfBirth, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(stateOfBirthLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(stateOfBirth, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(8)
							.addComponent(placeOfBirthLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(placeOfBirth, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(residenceLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(citizenship, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(typeLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(ageType, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(genderLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(gender, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(idTypeLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(idType, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(idNumberLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(idNumber, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(releaseDateLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(releaseIdDate, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(idExpireDateLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(expireIdDate, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(releaseSourceId, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(idReleaseSource, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_resultPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(idReleasePlace, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_resultPanel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(placeOfReleaseLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
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
	
	private void showCustomerSelectedData() {
		if (customersJList.getSelectedValue() != null) {
			Customer customer = customersJList.getSelectedValue();
			surname.setText(customer.getSurname());
			name.setText(customer.getName());
			dateOfBirth.setText(customer.getDateOfBirth().toString());
			stateOfBirth.setText(customer.getStateOfBirth());
			placeOfBirth.setText(customer.getBirthplace());
			citizenship.setText(customer.getCitizenship());
			ageType.setText(customer.getAge().name());
			gender.setText(customer.getGender().name());
			idType.setText(customer.getDocument().getDocumentType().name());
			idNumber.setText(customer.getDocument().getNumber());
			releaseIdDate.setText(customer.getDocument().getReleaseDate().toString());
			expireIdDate.setText(customer.getDocument().getExpirationDate().toString());
			idReleaseSource.setText(customer.getDocument().getReleasedBy().name());
			idReleasePlace.setText(customer.getDocument().getPlaceOfIssue());
		}
		else JOptionPane.showMessageDialog(null, "Select the customer whose information you want to see");
	}
	
	private void searchResults(String charSeq) {
		DefaultListModel<Customer> customers = new DefaultListModel<>();
		
		for (int i = 0; i < this.customers.size(); i++) {
			if (charSeq.length() != 0) {
				if (this.customers.get(i).toString().contains(charSeq))
					customers.addElement(this.customers.get(i));
			}
		}
		if(!customers.isEmpty()) {
			customersJList.setModel(customers);
			scrollPane.setViewportView(customersJList);
		}
		research.setText("");
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
	
//	private void refreshCustomers() {
//		customersJList.setModel(customers);
//		scrollPane.setViewportView(customersJList);
//	}
}