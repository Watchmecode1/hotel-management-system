package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.hotel.entity.Order;
import com.hotel.service.OrderService;
import com.hotel.service.ProductService;
import com.hotel.util.Checks;
import com.hotel.util.DateUtils;
import com.hotel.util.SwingComponentUtil;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.io.Serial;
import java.math.BigDecimal;

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
	
	private DefaultListModel<Order> orders = new DefaultListModel<>();
	private JList<Order> ordersJList;

	public Storage(OrderService orderService, ProductService productService) {
		SwingComponentUtil.addHotelIcons(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1537, 820);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
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
		
		JLabel lblNewLabel = new JLabel("Register Orders");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 10, 426, 60);
		panel_2.add(lblNewLabel);
		
		JLabel numeroLottoLabel = new JLabel("Batch Number");
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
		
		JLabel lblFornitore = new JLabel("Supplier");
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
		
		JLabel lblConsegna = new JLabel("Delivery date");
		lblConsegna.setHorizontalAlignment(SwingConstants.LEFT);
		lblConsegna.setForeground(new Color(224, 255, 255));
		lblConsegna.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblConsegna.setBounds(11, 179, 180, 30);
		panel.add(lblConsegna);
		
		JLabel lblProdotto = new JLabel("Product");
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
		
		JLabel lblQuantita = new JLabel("Quantity");
		lblQuantita.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantita.setForeground(new Color(224, 255, 255));
		lblQuantita.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		lblQuantita.setBounds(10, 257, 117, 30);
		panel.add(lblQuantita);
		
		JLabel lblScadenza = new JLabel("Expiration date");
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
		
		JLabel lblPrezzo = new JLabel("Total price              \u20AC");
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
		
		JButton addOrdersButton = new JButton("Add Order");
		addOrdersButton.addActionListener(e -> addOrder(orderService, productService));
		addOrdersButton.setBackground(new Color(224, 255, 255));
		addOrdersButton.setForeground(new Color(0, 128, 128));
		addOrdersButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		addOrdersButton.setBounds(58, 422, 319, 57);
		panel.add(addOrdersButton);
		
		JButton btnModificaOrdine = new JButton("Edit order");
		btnModificaOrdine.addActionListener(e -> modifyOrder());
		btnModificaOrdine.setForeground(new Color(0, 128, 128));
		btnModificaOrdine.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnModificaOrdine.setBackground(new Color(224, 255, 255));
		btnModificaOrdine.setBounds(58, 496, 319, 57);
		panel.add(btnModificaOrdine);
		
		JButton btnEliminaOrdine = new JButton("Delete order");
		btnEliminaOrdine.addActionListener(e -> deleteOrder(orderService));
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
		
		JLabel lblNewLabel_1 = new JLabel("Inventory section");
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(10, 10, 1503, 74);
		panel_3.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(477, 104, 1036, 585);
		contentPane.add(scrollPane);
		
		orders.addAll(orderService.getAll());
		ordersJList = new JList<>(orders);
		ordersJList.setSelectionForeground(new Color(0, 139, 139));
		ordersJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ordersJList.setForeground(new Color(0, 128, 128));
		ordersJList.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		ordersJList.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(ordersJList);
		
		JLabel lblListaOrdini = new JLabel("Order list");
		lblListaOrdini.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaOrdini.setForeground(new Color(0, 128, 128));
		lblListaOrdini.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 35));
		scrollPane.setColumnHeaderView(lblListaOrdini);
		
		JButton btnMostraProdottiIn = new JButton("Show products about to expire");
		btnMostraProdottiIn.addActionListener(e -> expiresPage = new ExpiresPage(orderService));
		btnMostraProdottiIn.setForeground(new Color(0, 128, 128));
		btnMostraProdottiIn.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnMostraProdottiIn.setBackground(new Color(224, 255, 255));
		btnMostraProdottiIn.setBounds(803, 708, 456, 57);
		contentPane.add(btnMostraProdottiIn);
		
		setVisible(true);
	}
	
	private void addOrder(OrderService orderService, ProductService productService) {
		if(checkInputFields()) {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "Do you want to add the order in inventory?", "Add order", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				Order order = new Order(batchId.getText(), supplier.getText(), DateUtils.convertDateToLocalDate(deliveryDate.getDate()),
						DateUtils.convertDateToLocalDate(expireDate.getDate()), productName.getText(),
						Integer.parseInt(amount.getText()), BigDecimal.valueOf(Double.parseDouble(price.getText())));
					
					orderService.saveOrder(order);
					orders.addElement(order);
					refreshOrdersList();
						
					JOptionPane.showMessageDialog(null, "Order successfully added to inventory");
				}
				else JOptionPane.showMessageDialog(null, "Operation canceled");
			}
		else JOptionPane.showMessageDialog(null, "Fill out all the fields to add an order");
	}
	
	private void deleteOrder(OrderService orderService) {
		Order order = ordersJList.getSelectedValue();
		if (order != null) {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "Do you want to delete the selected order?", "Elimina ordine", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
					orderService.deleteOrder(order);
					orders.removeElement(order);
					refreshOrdersList();
					
					JOptionPane.showMessageDialog(null, "Order deleted");
			}
			JOptionPane.showMessageDialog(null, "Operation canceled");
		}
		else JOptionPane.showMessageDialog(null, "Select the order you want to delete");
	}
	
	private void modifyOrder() {
		if (ordersJList.getSelectedValue() != null) {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "Do you want to change the selected order", "Edit order", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				Order order = ordersJList.getSelectedValue();
				
				batchId.setText(order.getBatchNumber());
				supplier.setText(order.getSupplier());
				deliveryDate.setDate(DateUtils.convertLocalDateToDate(order.getDeliveryDate()));
				productName.setText(order.getProductName());
				amount.setText(order.getQuantity() + "");
				expireDate.setDate(DateUtils.convertLocalDateToDate(order.getExpirationDate()));
				price.setText(Double.toString(order.getPrice().doubleValue()));
				
				JOptionPane.showMessageDialog(null, "Editing enabled");
			}
			else JOptionPane.showMessageDialog(null, "Operation canceled");
		}
		else JOptionPane.showMessageDialog(null, "Select the order you want to change");
	}
	
	private void refreshOrdersList() {
		ordersJList.setModel(orders);
		scrollPane.setViewportView(ordersJList);
	}
	
	private boolean checkInputFields() {
		return checkBatchId() && checkSupplier() && checkName() && checkAmount() && checkPrice();
	}
	
	private boolean checkBatchId() {
		if(!batchId.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "Enter a valid product batch id");
		return false;
	}
	
	private boolean checkSupplier() {
		if(!supplier.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "Enter a valid supplier name");
		return false;
	}
	
	private boolean checkName() {
		if(!productName.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "Enter a valid product name");
		return false;
	}
	
	private boolean checkAmount() {
		if(!amount.getText().isBlank()) return true;
		JOptionPane.showMessageDialog(null, "Enter the quantity of the product");
		return false;
	}
	
	private boolean checkPrice() {
		if (Checks.isDouble(price.getText())) return true;
		JOptionPane.showMessageDialog(null, "Enter a valid price for the order");
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