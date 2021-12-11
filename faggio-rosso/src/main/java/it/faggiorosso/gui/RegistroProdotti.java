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

import it.faggiorosso.entity.Prodotto;
import it.faggiorosso.service.ProdottoService;
import it.faggiorosso.util.Checks;
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

public class RegistroProdotti extends JFrame {

	@Serial
	private static final long serialVersionUID = 2115309755903869630L;
	private JTextField productName;
	private JTextField price;
	private JScrollPane scrollPane = new JScrollPane();
	
	private DefaultListModel<Prodotto> products = new DefaultListModel<>();
	private JList<Prodotto> productsJList;

	public RegistroProdotti( ProdottoService prodottoService) {
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
		
		JLabel productsLabel = new JLabel("Registra Prodotti");
		productsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productsLabel.setForeground(new Color(0, 128, 128));
		productsLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		productsLabel.setBounds(10, 10, 426, 60);
		panel_2.add(productsLabel);
		
		JLabel lblProdotto = new JLabel("Prodotto");
		lblProdotto.setHorizontalAlignment(SwingConstants.LEFT);
		lblProdotto.setForeground(new Color(224, 255, 255));
		lblProdotto.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblProdotto.setBounds(35, 220, 132, 30);
		panel.add(lblProdotto);
		
		productName = new JTextField();
		productName.setFont(new Font("Tahoma", Font.BOLD, 17));
		productName.setColumns(10);
		productName.setBounds(206, 220, 230, 30);
		panel.add(productName);
		
		JLabel lblPrezzo = new JLabel("Prezzo Cad.  \u20AC");
		lblPrezzo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrezzo.setForeground(new Color(224, 255, 255));
		lblPrezzo.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblPrezzo.setBounds(35, 280, 161, 30);
		panel.add(lblPrezzo);
		
		price = new JTextField();
		price.setDocument(SwingComponentUtil.financesPlainDocument());
		price.setFont(new Font("Tahoma", Font.BOLD, 17));
		price.setColumns(10);
		price.setBounds(206, 280, 230, 30);
		panel.add(price);
		
		JButton addProductsButton = new JButton("Aggiungi Prodotto");
		addProductsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProduct(prodottoService);
			}
		});
		addProductsButton.setBackground(new Color(224, 255, 255));
		addProductsButton.setForeground(new Color(0, 128, 128));
		addProductsButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		addProductsButton.setBounds(58, 422, 319, 57);
		panel.add(addProductsButton);
		
		JButton btnModificaProduct = new JButton("Modifica Prodotto");
		btnModificaProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyProduct();
			}
		});
		btnModificaProduct.setForeground(new Color(0, 128, 128));
		btnModificaProduct.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnModificaProduct.setBackground(new Color(224, 255, 255));
		btnModificaProduct.setBounds(58, 496, 319, 57);
		panel.add(btnModificaProduct);
		
//		JButton btnEliminaProdotto = new JButton("Elimina Prodotto");
//		btnEliminaProdotto.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				deleteProduct(prodottoService);
//			}
//		});
//		btnEliminaProdotto.setForeground(new Color(0, 128, 128));
//		btnEliminaProdotto.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
//		btnEliminaProdotto.setBackground(new Color(224, 255, 255));
//		btnEliminaProdotto.setBounds(58, 570, 319, 57);
//		panel.add(btnEliminaProdotto);
		
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
		
		products.addAll(prodottoService.getAll());
		productsJList = new JList<>(products);
		productsJList.setSelectionForeground(new Color(0, 139, 139));
		productsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productsJList.setForeground(new Color(0, 128, 128));
		productsJList.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		productsJList.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(productsJList);
		
		JLabel productsListLabel = new JLabel("Elenco Prodotti");
		productsListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productsListLabel.setForeground(new Color(0, 128, 128));
		productsListLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 35));
		scrollPane.setColumnHeaderView(productsListLabel);
		
		setVisible(true);
	}
	
	private void addProduct(ProdottoService prodottoService) {
		if(checkInputFields()) {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "VUOI AGGIUNGERE L'ORDINE IN INVENTARIO?", "AGGIUNGI ORDINE", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				Prodotto prodotto = new Prodotto(productName.getText(), BigDecimal.valueOf(Double.valueOf(price.getText())));
					
					prodottoService.saveProdotto(prodotto);
					products.addElement(prodotto);
					refreshProductsList();
						
					JOptionPane.showMessageDialog(null, "ORDINE CORRETTAMENTE AGGIUNTO ALL'INVENTARIO");
				}
			}
		else JOptionPane.showMessageDialog(null, "COMPILA TUTTI GLI ATTRIBUTI PER AGGIUNGERE UN ORDINE");
	}
	
//	private void deleteProduct(ProdottoService prodottoService) {
//		Prodotto prodotto = productsJList.getSelectedValue();
//		if (prodotto != null) {
//			int yn;
//			yn = JOptionPane.showConfirmDialog(null, "VUOI ELIMINARE IL PRODOTTO SELEZIONATO?", "ELIMINA PRODOTTO", JOptionPane.YES_OPTION);
//			if (yn == JOptionPane.YES_OPTION) {
//					prodottoService.deleteProdotto(prodotto);
//					products.removeElement(prodotto);
//					refreshProductsList();
//					
//					JOptionPane.showMessageDialog(null, "ORDINE CANCELLATO");
//			}
//		}
//		else JOptionPane.showMessageDialog(null, "SELEZIONA IL PRODOTTO CHE VUOI ELIMINARE");
//	}
	
	private void modifyProduct() {
		if (productsJList.getSelectedValue() != null) {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "VUOI MODIFICARE IL PRODOTTO SELEZIONATO?", "MODIFICA PRODOTTO", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				Prodotto prodotto = productsJList.getSelectedValue();
				
				productName.setText(prodotto.getNome());
				price.setText(Double.toString(prodotto.getPrezzo().doubleValue()));
				
				JOptionPane.showMessageDialog(null, "MODIFICA ABILITATA");
			}
		}
		else JOptionPane.showMessageDialog(null, "SELEZIONA IL PRODOTTO CHE VUOI MODIFICARE");
	}
	
	private void refreshProductsList() {
		productsJList.setModel(products);
		scrollPane.setViewportView(productsJList);
	}
	
	@Override
	public void dispose() {
		int yn;
		yn = JOptionPane.showConfirmDialog(null, "SEI SICURO DI VOLER CHIUDERE LA PAGINA?\nATTENZIONE:\nTUTTI I DATI INSERITI NON SALVATI ANDRANNO PERSI.\nVUOI PROCEDERE?", "EXIT", JOptionPane.YES_NO_OPTION);
		if(yn == JOptionPane.YES_OPTION)
			super.dispose();
	}
	
	private boolean checkInputFields() {
		return checkName() && checkPrice();
	}
	
	private boolean checkName() {
		if(!productName.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI UN NOME PRODOTTO VALIDO");
		return false;
	}
	
	private boolean checkPrice() {
		if (Checks.isDouble(price.getText())) return true;
		JOptionPane.showMessageDialog(null, "INSERISCI UN PREZZO VALIDO PER IL PRODOTTO");
		return false;
	}
}