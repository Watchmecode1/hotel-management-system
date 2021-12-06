package it.faggiorosso.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.math.BigDecimal;

import javax.swing.SwingConstants;

import it.faggiorosso.entity.TipoCamera;
import it.faggiorosso.entity.TipoCamera.Tipo;
import it.faggiorosso.service.TipoCameraService;
import it.faggiorosso.util.Checks;
import it.faggiorosso.util.SwingComponentUtil;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.awt.event.ActionEvent;

public class PrezziCamere extends JFrame {

	@Serial
	private static final long serialVersionUID = 6640704140627543254L;
	private JTextField singleTextField;
	private JTextField doubleTextField;
	private JTextField tripleTextField;
	private JTextField quadrupleTextField;
	private JTextField quintupleTextField;
	private TipoCameraService tipoCameraService = new TipoCameraService();

	public PrezziCamere() {
		SwingComponentUtil.addHotelIcons(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1537, 820);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(224, 255, 255), 3, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(0, 0, 1533, 81);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel titleLabel = new JLabel("Gestione camere");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(0, 128, 128));
		titleLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		titleLabel.setBounds(10, 10, 1513, 61);
		panel.add(titleLabel);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 128, 128));
		mainPanel.setBorder(new LineBorder(new Color(224, 255, 255), 3, true));
		mainPanel.setBounds(505, 220, 547, 346);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel textLabel = new JLabel("Imposta Prezzi\r\n");
		textLabel.setForeground(new Color(224, 255, 255));
		textLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setBounds(10, 10, 527, 46);
		mainPanel.add(textLabel);
		
		JLabel quadruplaLabel = new JLabel("Prezzo Quadrupla");
		quadruplaLabel.setForeground(new Color(224, 255, 255));
		quadruplaLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		quadruplaLabel.setBounds(10, 212, 211, 26);
		mainPanel.add(quadruplaLabel);
		
		JLabel matrimonialeLabel = new JLabel("Prezzo Matrimoniale");
		matrimonialeLabel.setForeground(new Color(224, 255, 255));
		matrimonialeLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		matrimonialeLabel.setBounds(10, 132, 223, 26);
		mainPanel.add(matrimonialeLabel);
		
		JLabel triplaLabel = new JLabel("Prezzo Tripla");
		triplaLabel.setForeground(new Color(224, 255, 255));
		triplaLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		triplaLabel.setBounds(10, 172, 223, 26);
		mainPanel.add(triplaLabel);
		
		doubleTextField = new JTextField();
		doubleTextField.setDocument(SwingComponentUtil.financesPlainDocument());
		doubleTextField.setText(tipoCameraService.getById(Tipo.DOPPIA).getPrezzo().toString());
		doubleTextField.setColumns(10);
		doubleTextField.setBounds(351, 126, 186, 19);
		mainPanel.add(doubleTextField);
		
		tripleTextField = new JTextField();
		tripleTextField.setDocument(SwingComponentUtil.financesPlainDocument());
		tripleTextField.setText(tipoCameraService.getById(Tipo.TRIPLA).getPrezzo().toString());
		tripleTextField.setColumns(10);
		tripleTextField.setBounds(351, 166, 186, 19);
		mainPanel.add(tripleTextField);
		
		quadrupleTextField = new JTextField();
		quadrupleTextField.setDocument(SwingComponentUtil.financesPlainDocument());
		quadrupleTextField.setText(tipoCameraService.getById(Tipo.QUADRUPLA).getPrezzo().toString());
		quadrupleTextField.setColumns(10);
		quadrupleTextField.setBounds(351, 206, 186, 19);
		mainPanel.add(quadrupleTextField);
		
		JPanel euroPanel_1 = new JPanel();
		euroPanel_1.setLayout(null);
		euroPanel_1.setBackground(new Color(224, 255, 255));
		euroPanel_1.setBounds(326, 126, 26, 19);
		mainPanel.add(euroPanel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u20AC");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(0, 0, 26, 19);
		euroPanel_1.add(lblNewLabel_1_1_1);
		
		JPanel euroPanel_2 = new JPanel();
		euroPanel_2.setLayout(null);
		euroPanel_2.setBackground(new Color(224, 255, 255));
		euroPanel_2.setBounds(327, 166, 26, 19);
		mainPanel.add(euroPanel_2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("\u20AC");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_2.setBounds(0, 0, 26, 19);
		euroPanel_2.add(lblNewLabel_1_1_2);
		
		JPanel euroPanel_3 = new JPanel();
		euroPanel_3.setLayout(null);
		euroPanel_3.setBackground(new Color(224, 255, 255));
		euroPanel_3.setBounds(327, 206, 26, 19);
		mainPanel.add(euroPanel_3);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("\u20AC");
		lblNewLabel_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_3.setForeground(new Color(0, 128, 128));
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_3.setBounds(0, 0, 26, 19);
		euroPanel_3.add(lblNewLabel_1_1_3);
		
		JButton btnNewButton = new JButton("Reimposta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int yn;
				yn  =JOptionPane.showConfirmDialog(null, "VUOI REIMPOSTARE I DATI INSERITI FIN'ORA?", "REIMPOSTA DATI", JOptionPane.YES_NO_OPTION);
				if (yn == JOptionPane.YES_OPTION) {
					doubleTextField.setText(tipoCameraService.getById(Tipo.DOPPIA).getPrezzo().toString());
					tripleTextField.setText(tipoCameraService.getById(Tipo.TRIPLA).getPrezzo().toString());
					quadrupleTextField.setText(tipoCameraService.getById(Tipo.QUADRUPLA).getPrezzo().toString());
					quintupleTextField.setText(tipoCameraService.getById(Tipo.QUINTUPLA).getPrezzo().toString());
					
					JOptionPane.showMessageDialog(null, "DATI REIMPOSTATI");
				} else
					JOptionPane.showMessageDialog(null, "OPERAZIONE ANNULLATA");
			}
		});
		btnNewButton.setForeground(new Color(0, 128, 128));
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		btnNewButton.setBounds(10, 300, 211, 36);
		mainPanel.add(btnNewButton);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!singleTextField.getText().isBlank() 
						&& !doubleTextField.getText().isBlank()
						&& !tripleTextField.getText().isBlank() 
						&& !quadrupleTextField.getText().isBlank() 
						&& !quintupleTextField.getText().isBlank()) {
					if (Checks.isDigit(singleTextField.getText()) 
							&& Checks.isDigit(doubleTextField.getText()) 
							&& Checks.isDigit(tripleTextField.getText()) 
							&& Checks.isDigit(quadrupleTextField.getText())
							&& Checks.isDigit(quintupleTextField.getText())) {
						setPrices();
					} else {
						JOptionPane.showMessageDialog(null, "INSERISCI PREZZI VALIDI PER LE CAMERE");
					}
				} else
					JOptionPane.showMessageDialog(null, "INSERISCI I PREZZI DI TUTTE LE TIPOLOGIE DI CAMERE NEL PERIODO SCELTO");
			}
		});
		btnConferma.setForeground(new Color(0, 128, 128));
		btnConferma.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		btnConferma.setBackground(new Color(224, 255, 255));
		btnConferma.setBounds(326, 300, 211, 36);
		mainPanel.add(btnConferma);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Prezzo Quintupla");
		lblNewLabel_2_2_1_1.setForeground(new Color(224, 255, 255));
		lblNewLabel_2_2_1_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblNewLabel_2_2_1_1.setBounds(10, 248, 211, 26);
		mainPanel.add(lblNewLabel_2_2_1_1);
		
		quintupleTextField = new JTextField();
		quintupleTextField.setDocument(SwingComponentUtil.financesPlainDocument());
		quintupleTextField.setText(tipoCameraService.getById(Tipo.QUINTUPLA).getPrezzo().toString());
		quintupleTextField.setColumns(10);
		quintupleTextField.setBounds(352, 248, 186, 19);
		mainPanel.add(quintupleTextField);
		
		JPanel euroPanel_3_1 = new JPanel();
		euroPanel_3_1.setLayout(null);
		euroPanel_3_1.setBackground(new Color(224, 255, 255));
		euroPanel_3_1.setBounds(326, 248, 26, 19);
		mainPanel.add(euroPanel_3_1);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("\u20AC");
		lblNewLabel_1_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_3_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_3_1.setBounds(0, 0, 26, 19);
		euroPanel_3_1.add(lblNewLabel_1_1_3_1);
		
		setVisible(true);
	}
	
	private void setPrices() {
		int yn;
		yn = JOptionPane.showConfirmDialog(null, "VUOI CONFERMARE I NUOVI PREZZI PER LE CAMERE?", "AGGIORNA PREZZI", JOptionPane.YES_NO_OPTION);
		if (yn == JOptionPane.YES_OPTION) {
			tipoCameraService.saveTipoCamera(new TipoCamera(Tipo.DOPPIA, BigDecimal.valueOf(Double.parseDouble(doubleTextField.getText()))));
			tipoCameraService.saveTipoCamera(new TipoCamera(Tipo.TRIPLA, BigDecimal.valueOf(Double.parseDouble(tripleTextField.getText()))));
			tipoCameraService.saveTipoCamera(new TipoCamera(Tipo.QUADRUPLA, BigDecimal.valueOf(Double.parseDouble(quadrupleTextField.getText()))));
			tipoCameraService.saveTipoCamera(new TipoCamera(Tipo.QUINTUPLA, BigDecimal.valueOf(Double.parseDouble(quintupleTextField.getText()))));
		
			JOptionPane.showMessageDialog(null, "PREZZI CAMERE AGGIORNATI CORRETTAMENTE");	
		}
		else JOptionPane.showMessageDialog(null, "OPERAZIONE ANNULLATA");	
	}
	
	@Override
	public void dispose() {
		int yn;
		yn = JOptionPane.showConfirmDialog(null, "SEI SICURO DI VOLER CHIUDERE LA PAGINA?\nATTENZIONE:\nTUTTI I DATI INSERITI NON SALVATI ANDRANNO PERSI.\nVUOI PROCEDERE?", "EXIT", JOptionPane.YES_NO_OPTION);
		if (yn == JOptionPane.YES_OPTION)
			super.dispose();
	}
}
