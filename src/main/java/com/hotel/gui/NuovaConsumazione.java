package com.hotel.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.hotel.entity.Consumption;
import com.hotel.entity.Reservation;
import com.hotel.entity.Product;
import com.hotel.service.ConsumptionService;
import com.hotel.service.ProductService;
import com.hotel.util.SwingComponentUtil;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.io.Serial;

public class NuovaConsumazione extends JFrame {

	@Serial
	private static final long serialVersionUID = -2732123696809924171L;
	private JSpinner productAmountSpinner;
	private DefaultListModel<Product> products = new DefaultListModel<>();
	private JList<Product> productsJList;
	private JScrollPane productsScrollPane = new JScrollPane();

	public NuovaConsumazione(int x, int y, Reservation reservation, ConsumptionService consumptionService, ProductService productService) {
		SwingComponentUtil.addHotelIcons(this);
		products.addAll(productService.getAll());
		productsJList  = new JList<>(products);
		productsScrollPane.setViewportView(productsJList);
		
		setResizable(false);
		setFont(new Font("Harlow Solid Italic", Font.BOLD, 20));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 541, 469);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel consummationPanel = new JPanel();
		consummationPanel.setBackground(new Color(0, 128, 128));
		consummationPanel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		consummationPanel.setBounds(10, 10, 516, 419);
		contentPane.add(consummationPanel);
		consummationPanel.setLayout(null);
		
		productsScrollPane = new JScrollPane();
		productsScrollPane.setBackground(new Color(224, 255, 255));
		productsScrollPane.setBounds(10, 10, 496, 315);
		consummationPanel.add(productsScrollPane);
		
		JLabel prodottiLabel = new JLabel("Prodotti");
		prodottiLabel.setForeground(new Color(0, 128, 128));
		prodottiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		prodottiLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		productsScrollPane.setColumnHeaderView(prodottiLabel);
		
		productsJList = new JList<>(products);
		productsJList.setForeground(new Color(0, 128, 128));
		productsJList.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		productsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productsJList.setSelectionForeground(new Color(0, 128, 128));
		productsJList.setBackground(new Color(224, 255, 255));
		productsScrollPane.setViewportView(productsJList);
		
		productAmountSpinner = SwingComponentUtil.numberJSpinner();
		productAmountSpinner.setForeground(new Color(0, 128, 128));
		productAmountSpinner.setFont(new Font("Tahoma", Font.BOLD, 15));
		productAmountSpinner.setBackground(new Color(224, 255, 255));
		productAmountSpinner.setBounds(105, 335, 318, 28);
		consummationPanel.add(productAmountSpinner);
		
		JButton addConsummationButton = new JButton("Aggiungi consumazione");
		addConsummationButton.addActionListener(e -> addConsumazioneToPrenotazione(reservation, consumptionService));
		addConsummationButton.setBackground(new Color(224, 255, 255));
		addConsummationButton.setForeground(new Color(0, 128, 128));
		addConsummationButton.setFont(new Font("Harlow Solid Italic", Font.BOLD, 25));
		addConsummationButton.setBounds(105, 373, 318, 28);
		consummationPanel.add(addConsummationButton);
		
		setVisible(true);
	}
	
	private void addConsumazioneToPrenotazione(Reservation reservation, ConsumptionService consumptionService) {
		Product selectedProduct = productsJList.getSelectedValue();
		int selectedAmount = (int) productAmountSpinner.getValue();
		if(selectedProduct != null) {
			if(selectedAmount <= 0) JOptionPane.showMessageDialog(null, "INSERISCI UNA QUANTITA' VALIDA");
			else {
				//TODO aggiusta
				Consumption consumption = new Consumption(reservation, selectedProduct, selectedAmount);
				
				int yn;
				yn = JOptionPane.showConfirmDialog(null, "VUOI AGGIUNGERE " + selectedAmount + " " + consumption.getProductName().toUpperCase() + " ALLA PRENOTAZIONE?", "AGGIUNGI CONSUMAZIONE", JOptionPane.YES_NO_OPTION);
				if (yn == JOptionPane.YES_OPTION) {
					consumptionService.saveConsumption(consumption);
					JOptionPane.showMessageDialog(null, "LA CONSUMAZIONE E'STATA AGGIUNTA CORRETTAMENTE");
				}
				else
					JOptionPane.showMessageDialog(null, "OPERAZIONE ANNULLATA");
				refreshSelections();
			}
		}
		else JOptionPane.showMessageDialog(null, "SELEZIONA IL PRODOTTO DA AGGIUNGERE ALLA PRENOTAZIONE");
	}
	
	private void refreshSelections() {
		productsJList.clearSelection();
		productAmountSpinner.setValue(0);
	}
}
