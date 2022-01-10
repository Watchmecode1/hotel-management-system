package com.hotel.gui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hotel.service.OrderService;
import com.hotel.util.SwingComponentUtil;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.Serial;

import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;

public class ExpiresPage extends JFrame {

	@Serial
	private static final long serialVersionUID = 6129205272549677652L;

	public ExpiresPage(OrderService orderService) {
		SwingComponentUtil.addHotelIcons(this);
		setResizable(false);
		setBackground(new Color(224, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 20, 739, 755);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 715, 707);
		contentPane.add(scrollPane);
		
		DefaultListModel<String> expired = new DefaultListModel<>();
		expired.addAll(orderService.getExpiringOrders().stream().map(order -> order.getProductName() + " " + "expiry:" + " " + order.getExpirationDate()).toList());
		JList<String> list = new JList<>(expired);
		list.setForeground(new Color(0, 128, 128));
		list.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		list.setSelectionForeground(new Color(0, 139, 139));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("Products expiring this week");
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		setVisible(true);
	}
}