 package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import com.hotel.entity.Camera;
import com.hotel.entity.Prenotazione;
import com.hotel.entity.Prenotazione.Pensione;
import com.hotel.service.PrenotazioneService;
import com.hotel.util.SwingComponentUtil;

import javax.swing.JLabel;

import java.awt.Font;
import java.time.LocalDate;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import java.io.Serial;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ElenchiRistorazione extends JFrame {

	@Serial
	private static final long serialVersionUID = 6640704140627543254L;
	private DefaultListModel<String> mezzePensioni = new DefaultListModel<>();
	private DefaultListModel<String> pensioniComplete = new DefaultListModel<>();
	private DefaultListModel<String> bnb = new DefaultListModel<>();
	private JList<String> mezzaPensioneList;
	private JList<String> pensioneCompletaList;
	private JList<String> bnbList;
	private JLabel ospitiColazione;
	private JLabel ospitiPranzo;
	private JLabel ospitiCena;

	public ElenchiRistorazione(PrenotazioneService prenotazioneService) {
		SwingComponentUtil.addHotelIcons(this);
		//TODO Implementa in maniera più efficiente
		List<Prenotazione> prenotazioni = prenotazioneService.getAll();
		int A_bnb = 0;
		int b_bnb = 0;
		int A_mp = 0;
		int b_mp = 0;
		int A_pc = 0;
		int b_pc = 0;
		
		for(Prenotazione p : prenotazioni) {
			if (p.getDataInizio().isBefore(LocalDate.now()) || p.getDataInizio().isEqual(LocalDate.now())) {
				if (p.getDataFine().isAfter(LocalDate.now())) {
					String s = "";
					Camera c = p.getCamere().toArray(new Camera[p.getCamere().size()])[0];
					int numeroAdulti = p.getNumeroDiAdulti();
					int numeroMinori = p.getNumeroDiMinori();
					int numeroBambini = p.getNumeroDiBambini();
					
					s += "CAMERA " + c.getNumero() + "   |   ADULTI:  "+ numeroAdulti;
					
					if (numeroMinori > 0)
						s += ",  MINORI:  " + numeroMinori;
					if (numeroBambini > 0)
						s += ",  BAMBINI:  " + numeroBambini;
					if(p.getPensione() == Pensione.BNB)
					{
						bnb.addElement(s);
						A_bnb += numeroAdulti;
						b_bnb += numeroMinori;
						b_bnb += numeroBambini;
					}
					else if(p.getPensione() == Pensione.MEZZA_PENSIONE)
					{
						mezzePensioni.addElement(s);
						A_mp += numeroAdulti;
						b_mp += numeroMinori;
						b_mp += numeroBambini;
					}
					else
					{
						pensioniComplete.addElement(s);
						A_pc += numeroAdulti;
						b_pc += numeroMinori;
						b_pc += numeroBambini;
					}
				}
			}
		}
		
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
		
		JLabel titleLabel = new JLabel("Panoramica Ospiti");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(0, 128, 128));
		titleLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 40));
		titleLabel.setBounds(10, 10, 1513, 61);
		panel.add(titleLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 139, 139));
		panel_1.setBorder(new LineBorder(new Color(224, 255, 255), 3));
		panel_1.setBounds(515, 91, 491, 497);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane MPscrollPane = new JScrollPane();
		MPscrollPane.setBounds(10, 91, 471, 396);
		panel_1.add(MPscrollPane);
		
		mezzaPensioneList = new JList<>(mezzePensioni);
		mezzaPensioneList.setForeground(new Color(0, 139, 139));
		mezzaPensioneList.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		mezzaPensioneList.setBackground(new Color(224, 255, 255));
		MPscrollPane.setViewportView(mezzaPensioneList);
		
		JLabel lblNewLabel = new JLabel("Ospiti a Mezza Pensione");
		lblNewLabel.setForeground(new Color(224, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		lblNewLabel.setBounds(10, 10, 471, 76);
		panel_1.add(lblNewLabel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(224, 255, 255), 3));
		panel_1_1.setBackground(new Color(0, 139, 139));
		panel_1_1.setBounds(1022, 91, 491, 497);
		contentPane.add(panel_1_1);
		
		JScrollPane PCscrollPane = new JScrollPane();
		PCscrollPane.setBounds(10, 91, 471, 396);
		panel_1_1.add(PCscrollPane);
		
		pensioneCompletaList = new JList<>(pensioniComplete);
		pensioneCompletaList.setForeground(new Color(0, 139, 139));
		pensioneCompletaList.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		pensioneCompletaList.setBackground(new Color(224, 255, 255));
		PCscrollPane.setViewportView(pensioneCompletaList);
		
		JLabel lblOspitiAPensione = new JLabel("Ospiti a Pensione Completa");
		lblOspitiAPensione.setHorizontalAlignment(SwingConstants.CENTER);
		lblOspitiAPensione.setForeground(new Color(224, 255, 255));
		lblOspitiAPensione.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		lblOspitiAPensione.setBounds(10, 10, 471, 76);
		panel_1_1.add(lblOspitiAPensione);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBorder(new LineBorder(new Color(224, 255, 255), 3));
		panel_1_2.setBackground(new Color(0, 139, 139));
		panel_1_2.setBounds(10, 91, 491, 497);
		contentPane.add(panel_1_2);
		
		JScrollPane BNBscrollPane = new JScrollPane();
		BNBscrollPane.setBounds(10, 91, 471, 396);
		panel_1_2.add(BNBscrollPane);
		
		bnbList = new JList<>(bnb);
		bnbList.setForeground(new Color(0, 139, 139));
		bnbList.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		bnbList.setBackground(new Color(224, 255, 255));
		BNBscrollPane.setViewportView(bnbList);
		
		JLabel lblOspitiAFormula = new JLabel("Ospiti a formula B&B");
		lblOspitiAFormula.setHorizontalAlignment(SwingConstants.CENTER);
		lblOspitiAFormula.setForeground(new Color(224, 255, 255));
		lblOspitiAFormula.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		lblOspitiAFormula.setBounds(10, 10, 471, 76);
		panel_1_2.add(lblOspitiAFormula);
		
		JLabel lblOspiti = new JLabel("OSPITI COLAZIONE\r\n");
		lblOspiti.setHorizontalAlignment(SwingConstants.LEFT);
		lblOspiti.setForeground(new Color(224, 255, 255));
		lblOspiti.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		lblOspiti.setBounds(10, 598, 491, 38);
		contentPane.add(lblOspiti);
		
		JLabel lblOspitiAFormula_1_1 = new JLabel("OSPITI PRANZO");
		lblOspitiAFormula_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblOspitiAFormula_1_1.setForeground(new Color(224, 255, 255));
		lblOspitiAFormula_1_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		lblOspitiAFormula_1_1.setBounds(10, 646, 491, 38);
		contentPane.add(lblOspitiAFormula_1_1);
		
		JLabel lblOspitiAFormula_1_1_1 = new JLabel("OSPITI CENA");
		lblOspitiAFormula_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblOspitiAFormula_1_1_1.setForeground(new Color(224, 255, 255));
		lblOspitiAFormula_1_1_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		lblOspitiAFormula_1_1_1.setBounds(10, 694, 491, 38);
		contentPane.add(lblOspitiAFormula_1_1_1);
		
		ospitiColazione = new JLabel("");
		ospitiColazione.setHorizontalAlignment(SwingConstants.LEFT);
		ospitiColazione.setForeground(new Color(224, 255, 255));
		ospitiColazione.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		ospitiColazione.setBounds(515, 598, 998, 38);
		contentPane.add(ospitiColazione);
		
		ospitiPranzo = new JLabel("");
		ospitiPranzo.setHorizontalAlignment(SwingConstants.LEFT);
		ospitiPranzo.setForeground(new Color(224, 255, 255));
		ospitiPranzo.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		ospitiPranzo.setBounds(515, 646, 998, 38);
		contentPane.add(ospitiPranzo);
		
		ospitiCena = new JLabel("");
		ospitiCena.setHorizontalAlignment(SwingConstants.LEFT);
		ospitiCena.setForeground(new Color(224, 255, 255));
		ospitiCena.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		ospitiCena.setBounds(515, 694, 998, 38);
		contentPane.add(ospitiCena);
		
		int A_C = A_bnb + A_mp + A_pc;
		int b_c = b_bnb + b_mp + b_pc;
		int A_MP = A_mp + A_pc;
		int b_MP = b_mp + b_pc;
		
		ospitiColazione.setText("ADULTI: " + A_C + "    |    BAMBINI: " + b_c + "    |    TOT. " + (A_C + b_c));
		ospitiPranzo.setText("ADULTI: " + A_pc + "    |    BAMBINI: " + b_pc + "    |    TOT. " + (A_pc + b_pc));
		ospitiCena.setText("ADULTI: " + A_MP + "    |    BAMBINI: " + b_MP + "    |    TOT. " + (A_MP + b_MP));
		
		setVisible(true);
	} 
}
