package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.border.LineBorder;

import com.hotel.entity.Product;
import com.hotel.service.ProductService;
import com.hotel.util.Checks;
import com.hotel.util.SwingComponentUtil;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.io.Serial;
import java.math.BigDecimal;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ProductRegister extends JFrame {

	@Serial
	private static final long serialVersionUID = 2115309755903869630L;
	private JTextField productName;
	private JTextField price;
	private JScrollPane scrollPane = new JScrollPane();
	
	private DefaultListModel<Product> products = new DefaultListModel<>();
	private JList<Product> productsJList;

	public ProductRegister(ProductService productService) {
		SwingComponentUtil.addHotelIcons(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setLayout(null);
		
		JLabel productsLabel = new JLabel("Register products");
		productsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productsLabel.setForeground(new Color(0, 128, 128));
		productsLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		productsLabel.setBounds(10, 10, 436, 60);
		panel_2.add(productsLabel);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduct.setForeground(new Color(224, 255, 255));
		lblProduct.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		productName = new JTextField();
		productName.setFont(new Font("Tahoma", Font.BOLD, 17));
		productName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price each  \u20AC");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(new Color(224, 255, 255));
		lblPrice.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		price = new JTextField();
		price.setDocument(SwingComponentUtil.financesPlainDocument());
		price.setFont(new Font("Tahoma", Font.BOLD, 17));
		price.setColumns(10);
		
		JButton addProductsButton = new JButton("Add product");
		addProductsButton.addActionListener(e -> addProduct(productService));
		addProductsButton.setBackground(new Color(224, 255, 255));
		addProductsButton.setForeground(new Color(0, 128, 128));
		addProductsButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		
		JButton btnEditProduct = new JButton("Edit product");
		btnEditProduct.addActionListener(e -> modifyProduct());
		btnEditProduct.setForeground(new Color(0, 128, 128));
		btnEditProduct.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnEditProduct.setBackground(new Color(224, 255, 255));
		
		JPanel panel_3 = new JPanel(new GridBagLayout());
		panel_3.setBackground(new Color(224, 255, 255));
		
		JLabel lblNewLabel_1 = new JLabel("Inventory section");
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 40));
		
		scrollPane = new JScrollPane();
		
		products.addAll(productService.getAll());
		productsJList = new JList<>(products);
		productsJList.setSelectionForeground(new Color(0, 139, 139));
		productsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productsJList.setForeground(new Color(0, 128, 128));
		productsJList.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		productsJList.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(productsJList);
		
		JLabel productsListLabel = new JLabel("Product list");
		productsListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productsListLabel.setForeground(new Color(0, 128, 128));
		productsListLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 35));
		scrollPane.setColumnHeaderView(productsListLabel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE))
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 1513, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
						.addComponent(scrollPane))
					.addGap(0))
		);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 1503, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
		);
		panel_3.setLayout(gl_panel_3);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addComponent(lblProduct, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(productName, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(price, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(58)
					.addComponent(addProductsButton, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(58)
					.addComponent(btnEditProduct, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(140)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProduct, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(productName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(price, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(112)
					.addComponent(addProductsButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(btnEditProduct, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		setVisible(true);
	}
	
	private void addProduct(ProductService productService) {
		if(checkInputFields()) {
			Product product = new Product(productName.getText(), BigDecimal.valueOf(Double.parseDouble(price.getText())));
					
			productService.saveProduct(product);
			products.addElement(product);
			refreshProductsList();
						
			JOptionPane.showMessageDialog(null, "Product correctly added to the inventory");
		}
		else JOptionPane.showMessageDialog(null, "Fill out all the fields to add a product");
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
			yn = JOptionPane.showConfirmDialog(null, "Do you want to modify the selected product?", "Edit product", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				Product product = productsJList.getSelectedValue();
				
				productName.setText(product.getName());
				price.setText(Double.toString(product.getPrice().doubleValue()));
			}
		}
		else JOptionPane.showMessageDialog(null, "Select the product you want to edit");
	}
	
	private void refreshProductsList() {
		productsJList.setModel(products);
		scrollPane.setViewportView(productsJList);
	}
	
	private boolean checkInputFields() {
		return checkName() && checkPrice();
	}
	
	private boolean checkName() {
		if(!productName.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "Enter a valid product name");
		return false;
	}
	
	private boolean checkPrice() {
		if (Checks.isDouble(price.getText())) return true;
		JOptionPane.showMessageDialog(null, "Enter a valid price for the product");
		return false;
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
}