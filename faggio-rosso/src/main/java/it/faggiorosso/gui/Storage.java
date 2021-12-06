package it.faggiorosso.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;


import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import it.faggiorosso.entity.Ordine;
import it.faggiorosso.service.OrdineService;
import it.faggiorosso.service.ProdottoService;
import it.faggiorosso.util.Checks;
import it.faggiorosso.util.DateUtils;
import it.faggiorosso.util.SwingComponentUtil;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class Storage extends JFrame {

	@Serial
	private static final long serialVersionUID = 2115309755903869630L;
	private JTextField batchId;
	private JTextField supplier;
	private JTextField productName;
	private JTextField amount;
	private JTextField price;
	private JDateChooser deliveryDate;
	private JDateChooser expireDate;
	private JScrollPane scrollPane = new JScrollPane();
	
	private ExpiresPage expiresPage;
	
	private DefaultListModel<Ordine> orders = new DefaultListModel<>();
	private JList<Ordine> ordersJList;

	public Storage(OrdineService ordineService, ProdottoService prodottoService) {
		SwingComponentUtil.addHotelIcons(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1537, 820);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		panel.setBounds(21, 104, 446, 669);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setBounds(0, 0, 446, 80);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registra Ordini");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 10, 426, 60);
		panel_2.add(lblNewLabel);
		
		JLabel numeroLottoLabel = new JLabel("Numero lotto");
		numeroLottoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		numeroLottoLabel.setForeground(new Color(224, 255, 255));
		numeroLottoLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		numeroLottoLabel.setBounds(11, 96, 161, 30);
		panel.add(numeroLottoLabel);
		
		batchId = new JTextField();
		batchId.setFont(new Font("Tahoma", Font.BOLD, 17));
		batchId.setColumns(10);
		batchId.setBounds(262, 96, 174, 30);
		panel.add(batchId);
		
		JLabel lblFornitore = new JLabel("Fornitore");
		lblFornitore.setHorizontalAlignment(SwingConstants.LEFT);
		lblFornitore.setForeground(new Color(224, 255, 255));
		lblFornitore.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblFornitore.setBounds(10, 136, 140, 30);
		panel.add(lblFornitore);
		
		supplier = new JTextField();
		supplier.setFont(new Font("Tahoma", Font.BOLD, 17));
		supplier.setColumns(10);
		supplier.setBounds(262, 136, 174, 30);
		panel.add(supplier);
		
		deliveryDate = new JDateChooser();
		((JTextFieldDateEditor) deliveryDate.getDateEditor()).setEditable(false);
		deliveryDate.setFont(new Font("Tahoma", Font.BOLD, 17));
		deliveryDate.setBounds(262, 179, 174, 30);
		panel.add(deliveryDate);
		
		JLabel lblConsegna = new JLabel("Data consegna");
		lblConsegna.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsegna.setForeground(new Color(224, 255, 255));
		lblConsegna.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblConsegna.setBounds(11, 179, 180, 30);
		panel.add(lblConsegna);
		
		JLabel lblProdotto = new JLabel("Prodotto");
		lblProdotto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProdotto.setForeground(new Color(224, 255, 255));
		lblProdotto.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblProdotto.setBounds(12, 217, 132, 30);
		panel.add(lblProdotto);
		
		productName = new JTextField();
		productName.setFont(new Font("Tahoma", Font.BOLD, 17));
		productName.setColumns(10);
		productName.setBounds(262, 220, 174, 30);
		panel.add(productName);
		
		amount = new JTextField();
		amount.setDocument(SwingComponentUtil.numberPlainDocument());
		amount.setFont(new Font("Tahoma", Font.BOLD, 17));
		amount.setColumns(10);
		amount.setBounds(262, 260, 174, 30);
		panel.add(amount);
		
		JLabel lblQuantita = new JLabel("Quantit\u00E0");
		lblQuantita.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantita.setForeground(new Color(224, 255, 255));
		lblQuantita.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblQuantita.setBounds(10, 257, 117, 30);
		panel.add(lblQuantita);
		
		JLabel lblScadenza = new JLabel("Data scadenza");
		lblScadenza.setHorizontalAlignment(SwingConstants.LEFT);
		lblScadenza.setForeground(new Color(224, 255, 255));
		lblScadenza.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblScadenza.setBounds(10, 299, 181, 30);
		panel.add(lblScadenza);
		
		expireDate = new JDateChooser();
		((JTextFieldDateEditor) expireDate.getDateEditor()).setEditable(false);
		expireDate.setFont(new Font("Tahoma", Font.BOLD, 17));
		expireDate.setBounds(262, 299, 174, 30);
		panel.add(expireDate);
		
		JLabel lblPrezzo = new JLabel("Costo totale              \u20AC");
		lblPrezzo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrezzo.setForeground(new Color(224, 255, 255));
		lblPrezzo.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblPrezzo.setBounds(10, 339, 248, 30);
		panel.add(lblPrezzo);
		
		price = new JTextField();
		price.setDocument(SwingComponentUtil.financesPlainDocument());
		price.setFont(new Font("Tahoma", Font.BOLD, 17));
		price.setColumns(10);
		price.setBounds(262, 339, 174, 30);
		panel.add(price);
		
		JButton addOrdersButton = new JButton("Aggiungi Ordine");
		addOrdersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOrder(ordineService, prodottoService);
			}
		});
		addOrdersButton.setBackground(new Color(224, 255, 255));
		addOrdersButton.setForeground(new Color(0, 128, 128));
		addOrdersButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		addOrdersButton.setBounds(58, 422, 319, 57);
		panel.add(addOrdersButton);
		
		JButton btnModificaOrdine = new JButton("Modifica Ordine");
		btnModificaOrdine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyOrder();
			}
		});
		btnModificaOrdine.setForeground(new Color(0, 128, 128));
		btnModificaOrdine.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnModificaOrdine.setBackground(new Color(224, 255, 255));
		btnModificaOrdine.setBounds(58, 496, 319, 57);
		panel.add(btnModificaOrdine);
		
		JButton btnEliminaOrdine = new JButton("Elimina Ordine");
		btnEliminaOrdine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteOrder(ordineService);
			}
		});
		btnEliminaOrdine.setForeground(new Color(0, 128, 128));
		btnEliminaOrdine.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnEliminaOrdine.setBackground(new Color(224, 255, 255));
		btnEliminaOrdine.setBounds(58, 570, 319, 57);
		panel.add(btnEliminaOrdine);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		panel_1.setBounds(10, 104, 10, 669);
		contentPane.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(224, 255, 255));
		panel_3.setBounds(0, 0, 1523, 94);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Sezione Inventario");
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(10, 10, 1503, 74);
		panel_3.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(477, 104, 1036, 585);
		contentPane.add(scrollPane);
		
		orders.addAll(ordineService.getAll());
		ordersJList = new JList<>(orders);
		ordersJList.setSelectionForeground(new Color(0, 139, 139));
		ordersJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ordersJList.setForeground(new Color(0, 128, 128));
		ordersJList.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		ordersJList.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(ordersJList);
		
		JLabel lblListaOrdini = new JLabel("Elenco Ordini");
		lblListaOrdini.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaOrdini.setForeground(new Color(0, 128, 128));
		lblListaOrdini.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 35));
		scrollPane.setColumnHeaderView(lblListaOrdini);
		
		JButton btnMostraProdottiIn = new JButton("Mostra Prodotti in Scadenza");
		btnMostraProdottiIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expiresPage = new ExpiresPage(ordineService);
			}
		});
		btnMostraProdottiIn.setForeground(new Color(0, 128, 128));
		btnMostraProdottiIn.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnMostraProdottiIn.setBackground(new Color(224, 255, 255));
		btnMostraProdottiIn.setBounds(803, 708, 456, 57);
		contentPane.add(btnMostraProdottiIn);
		
		setVisible(true);
	}
	
	private void addOrder(OrdineService ordineService, ProdottoService prodottoService) {
		if(checkInputFields()) {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "VUOI AGGIUNGERE L'ORDINE IN INVENTARIO?", "AGGIUNGI ORDINE", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				Ordine order = new Ordine(batchId.getText(), supplier.getText(), DateUtils.convertDateToLocalDate(deliveryDate.getDate()),
						DateUtils.convertDateToLocalDate(expireDate.getDate()), productName.getText(),
						Integer.valueOf(amount.getText()), BigDecimal.valueOf(Double.valueOf(price.getText())));
					
					ordineService.saveOrdine(order);
					orders.addElement(order);
					refreshOrdersList();
						
					JOptionPane.showMessageDialog(null, "ORDINE CORRETTAMENTE AGGIUNTO ALL'INVENTARIO");
				}
				else JOptionPane.showMessageDialog(null, "OPERAZIONE ANNULLATA");
			}
		else JOptionPane.showMessageDialog(null, "COMPILA TUTTI GLI ATTRIBUTI PER AGGIUNGERE UN ORDINE");
	}
	
	private void deleteOrder(OrdineService ordineService) {
		Ordine ordine = ordersJList.getSelectedValue();
		if (ordine != null) {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "VUOI ELIMINARE L'ORDINE SELEZIONATO?", "ELIMINA ORDINE", JOptionPane.YES_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
					ordineService.deleteOrdine(ordine);
					orders.removeElement(ordine);
					refreshOrdersList();
					
					JOptionPane.showMessageDialog(null, "ORDINE CANCELLATO");
			}
			JOptionPane.showMessageDialog(null, "OPERAZIONE ANNULLATA");
		}
		else JOptionPane.showMessageDialog(null, "SELEZIONA L'ORDINE CHE VUOI ELIMINARE");
	}
	
	private void modifyOrder() {
		if (ordersJList.getSelectedValue() != null) {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "VUOI MODIFICARE l'ORDINE SELEZIONATO?", "MODIFICA ORDINE", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				Ordine ordine = ordersJList.getSelectedValue();
				
				batchId.setText(ordine.getNumeroLotto());
				supplier.setText(ordine.getFornitore());
				deliveryDate.setDate(DateUtils.convertLocalDateToDate(ordine.getDataConsegna()));
				productName.setText(ordine.getNomeProdotto());
				amount.setText(ordine.getQuantita() + "");
				expireDate.setDate(DateUtils.convertLocalDateToDate(ordine.getDataScadenza()));
				price.setText(Double.toString(ordine.getPrezzo().doubleValue()));
				
				JOptionPane.showMessageDialog(null, "MODIFICA ABILITATA");
			}
			else JOptionPane.showMessageDialog(null, "OPERAZIONE ANNULLATA");
		}
		else JOptionPane.showMessageDialog(null, "SELEZIONA L'ORDINE CHE VUOI MODIFICARE");
	}
	
	private void refreshOrdersList() {
		ordersJList.setModel(orders);
		scrollPane.setViewportView(ordersJList);
	}
	
	@Override
	public void dispose() {
		int yn;
		yn = JOptionPane.showConfirmDialog(null, "SEI SICURO DI VOLER CHIUDERE LA PAGINA?\nATTENZIONE:\nTUTTI I DATI INSERITI NON SALVATI ANDRANNO PERSI.\nVUOI PROCEDERE?", "EXIT", JOptionPane.YES_NO_OPTION);
		if(yn == JOptionPane.YES_OPTION) {
			if(expiresPage != null)
				expiresPage.dispose();
			super.dispose();
		}
	}
	
	private boolean checkInputFields() {
		return checkBatchId() && checkSupplier() && checkName() && checkAmount() && checkPrice();
	}
	
	private boolean checkBatchId() {
		if(!batchId.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI UN IDENTIFICATIVO LOTTO DEL PRODOTTO VALIDO");
		return false;
	}
	
	private boolean checkSupplier() {
		if(!supplier.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI UN NOME FORNITORE VALIDO");
		return false;
	}
	
	private boolean checkName() {
		if(!productName.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI UN NOME PRODOTTO VALIDO");
		return false;
	}
	
	private boolean checkAmount() {
		if(!amount.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI LA QUANTITA' DEL PRODOTTO");
		return false;
	}
	
	private boolean checkPrice() {
		if (Checks.isDouble(price.getText())) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI UN PREZZO VALIDO PER L'ORDINE");
		return false;
	}
}