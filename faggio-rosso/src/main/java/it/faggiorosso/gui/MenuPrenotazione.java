package it.faggiorosso.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.faggiorosso.entity.Prenotazione;
import it.faggiorosso.service.CameraService;
import it.faggiorosso.service.ClienteService;
import it.faggiorosso.service.ConsumazioneService;
import it.faggiorosso.service.DocumentoService;
import it.faggiorosso.service.PrenotazioneService;
import it.faggiorosso.service.ProdottoService;
import it.faggiorosso.util.FileUtils;
import it.faggiorosso.util.SwingComponentUtil;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serial;
import java.awt.event.ActionEvent;

public class MenuPrenotazione extends JFrame {

	@Serial
	private static final long serialVersionUID = -7515500934140028996L;
	
	private NuovaConsumazione nuovaConsumazione;

	public MenuPrenotazione(int x, int y, Prenotazione prenotazione, PrenotazioneService prenotazioneService, 
			ConsumazioneService consumazioneService, CameraService cameraService,
			ClienteService clienteService, DocumentoService documentoService) {
		SwingComponentUtil.addHotelIcons(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 358, 222);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addConsumazioneButton = new JButton("Aggiungi Consumazione");
		addConsumazioneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				nuovaConsumazione = new NuovaConsumazione(x, y, prenotazione, consumazioneService, new ProdottoService());
			}
		});
		addConsumazioneButton.setForeground(Color.BLACK);
		addConsumazioneButton.setBackground(new Color(0, 191, 255));
		addConsumazioneButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		addConsumazioneButton.setBounds(10, 55, 331, 35);
		contentPane.add(addConsumazioneButton);
		
		JButton btnSalvaPdf = new JButton("Genera PDF");
		btnSalvaPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileUtils.writePDFAndOpen(prenotazione);
				} catch (IOException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "SI E' VERIFICATO UN ERRORE, IMPOSSIBILE GENERARE IL PDF");
				}
			}		
		});
		btnSalvaPdf.setForeground(Color.BLACK);
		btnSalvaPdf.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		btnSalvaPdf.setBackground(new Color(0, 191, 255));
		btnSalvaPdf.setBounds(10, 100, 331, 35);
		contentPane.add(btnSalvaPdf);
		
		JButton changeReservationButton = new JButton("Modifica Prenotazione");
		changeReservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new NuovaPrenotazione(prenotazione, prenotazioneService, cameraService, clienteService, documentoService);
			}
		});
		changeReservationButton.setForeground(Color.BLACK);
		changeReservationButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		changeReservationButton.setBackground(new Color(0, 191, 255));
		changeReservationButton.setBounds(10, 10, 331, 35);
		contentPane.add(changeReservationButton);
		
		JButton eliminaPrenotazioneButton = new JButton("Elimina Prenotazione");
		eliminaPrenotazioneButton.setForeground(Color.BLACK);
		eliminaPrenotazioneButton.setBackground(new Color(0, 191, 255));
		eliminaPrenotazioneButton.setFont(new Font("Dialog", Font.PLAIN, 25));
		eliminaPrenotazioneButton.setBounds(10, 145, 331, 35);
		eliminaPrenotazioneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int yn;
				yn = JOptionPane.showConfirmDialog(null, "CONFERMI DI VOLER ELIMINARE LA PRENOTAZIONE?", "ELIMINA PRENOTAZIONE", JOptionPane.YES_NO_OPTION);
				if (yn == JOptionPane.YES_OPTION) {
					prenotazioneService.deletePrenotazione(prenotazione);
					JOptionPane.showMessageDialog(null, "LA PRENOTAZIONE E' STATA ELIMINATA CORRETTAMENTE");
					dispose();
				}
			}
		});
		contentPane.add(eliminaPrenotazioneButton);
		
		this.setVisible(true);
	}
	
	@Override
	public void dispose() {
		if(nuovaConsumazione != null) nuovaConsumazione.dispose();
		super.dispose();
	}
}