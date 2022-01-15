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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBorder(new LineBorder(new Color(224, 255, 255), 5, true));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(224, 255, 255));
		
		JLabel lblNewLabel = new JLabel("Register Orders");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		
		JLabel batchNumberLabel = new JLabel("Batch Number");
		batchNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
		batchNumberLabel.setForeground(new Color(224, 255, 255));
		batchNumberLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		batchId = new JTextField();
		batchId.setFont(new Font("Tahoma", Font.BOLD, 17));
		batchId.setColumns(10);
		
		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setHorizontalAlignment(SwingConstants.LEFT);
		lblSupplier.setForeground(new Color(224, 255, 255));
		lblSupplier.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		supplier = new JTextField();
		supplier.setFont(new Font("Tahoma", Font.BOLD, 17));
		supplier.setColumns(10);
		
		deliveryDate = new JDateChooser();
		((JTextFieldDateEditor) deliveryDate.getDateEditor()).setEditable(false);
		deliveryDate.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblDelivery = new JLabel("Delivery date");
		lblDelivery.setHorizontalAlignment(SwingConstants.LEFT);
		lblDelivery.setForeground(new Color(224, 255, 255));
		lblDelivery.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduct.setForeground(new Color(224, 255, 255));
		lblProduct.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		productName = new JTextField();
		productName.setFont(new Font("Tahoma", Font.BOLD, 17));
		productName.setColumns(10);
		
		amount = new JTextField();
		amount.setDocument(SwingComponentUtil.numberPlainDocument());
		amount.setFont(new Font("Tahoma", Font.BOLD, 17));
		amount.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantity.setForeground(new Color(224, 255, 255));
		lblQuantity.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		JLabel lblExpiration = new JLabel("Expiration date");
		lblExpiration.setHorizontalAlignment(SwingConstants.LEFT);
		lblExpiration.setForeground(new Color(224, 255, 255));
		lblExpiration.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		expireDate = new JDateChooser();
		((JTextFieldDateEditor) expireDate.getDateEditor()).setEditable(false);
		expireDate.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JLabel lblPrice = new JLabel("Total price");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(new Color(224, 255, 255));
		lblPrice.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		
		price = new JTextField();
		price.setDocument(SwingComponentUtil.financesPlainDocument());
		price.setFont(new Font("Tahoma", Font.BOLD, 17));
		price.setColumns(10);
		
		JButton addOrdersButton = new JButton("Add Order");
		addOrdersButton.addActionListener(e -> addOrder(orderService, productService));
		addOrdersButton.setBackground(new Color(224, 255, 255));
		addOrdersButton.setForeground(new Color(0, 128, 128));
		addOrdersButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		
		JButton btnEditOrder = new JButton("Edit order");
		btnEditOrder.addActionListener(e -> modifyOrder());
		btnEditOrder.setForeground(new Color(0, 128, 128));
		btnEditOrder.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnEditOrder.setBackground(new Color(224, 255, 255));
		
		JButton btnDeleteOrder = new JButton("Delete order");
		btnDeleteOrder.addActionListener(e -> deleteOrder(orderService));
		btnDeleteOrder.setForeground(new Color(0, 128, 128));
		btnDeleteOrder.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnDeleteOrder.setBackground(new Color(224, 255, 255));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(224, 255, 255));
		
		JLabel lblNewLabel_1 = new JLabel("Inventory section");
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 40));
		
		scrollPane = new JScrollPane();
		
		orders.addAll(orderService.getAll());
		ordersJList = new JList<>(orders);
		ordersJList.setSelectionForeground(new Color(0, 139, 139));
		ordersJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ordersJList.setForeground(new Color(0, 128, 128));
		ordersJList.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		ordersJList.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(ordersJList);
		
		JLabel lblOrderList = new JLabel("Order list");
		lblOrderList.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderList.setForeground(new Color(0, 128, 128));
		lblOrderList.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 35));
		scrollPane.setColumnHeaderView(lblOrderList);
		
		JButton btnShowProductsIn = new JButton("Show products about to expire");
		btnShowProductsIn.addActionListener(e -> expiresPage = new ExpiresPage(orderService));
		btnShowProductsIn.setForeground(new Color(0, 128, 128));
		btnShowProductsIn.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		btnShowProductsIn.setBackground(new Color(224, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 1523, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(326)
							.addComponent(btnShowProductsIn, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
							.addGap(19)
							.addComponent(btnShowProductsIn, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(8))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
					.addGap(0))
		);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 1503, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(10, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSupplier, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPrice, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addComponent(lblExpiration, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addComponent(batchNumberLabel, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addComponent(lblDelivery, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addComponent(lblProduct, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addComponent(lblQuantity, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))))
					.addGap(63)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(expireDate, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(deliveryDate, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
										.addComponent(batchId, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
										.addComponent(supplier, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
								.addComponent(productName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
							.addComponent(amount, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
						.addComponent(price, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
					.addGap(21))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(75, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDeleteOrder, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
						.addComponent(addOrdersButton, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditOrder, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE))
					.addGap(63))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(batchId, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(batchNumberLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(supplier, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSupplier, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(deliveryDate, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDelivery, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(11)
							.addComponent(productName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(lblProduct, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(amount, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(7)
							.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(expireDate, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExpiration, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(price, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addComponent(addOrdersButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnEditOrder, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDeleteOrder, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
		);
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
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
			yn = JOptionPane.showConfirmDialog(null, "Do you want to delete the selected order?", "Delete order", JOptionPane.YES_NO_OPTION);
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