package com.hotel.gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

import com.hotel.entity.RoomType;
import com.hotel.service.RoomTypeService;
import com.hotel.util.Checks;
import com.hotel.util.SwingComponentUtil;

import java.math.BigDecimal;

import java.io.Serial;

public class RoomPrices extends JFrame {

	@Serial
	private static final long serialVersionUID = 6640704140627543254L;
	private JTextField doubleTextField;
	private JTextField tripleTextField;
	private JTextField quadrupleTextField;
	private JTextField quintupleTextField;
	private RoomTypeService roomTypeService = new RoomTypeService();

	public RoomPrices() {
		SwingComponentUtil.addHotelIcons(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(0, 139, 139));
		((JPanel) getContentPane()).setBorder(new LineBorder(new Color(224, 255, 255), 3, true));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		setTitlePanel();

		JPanel mainPanel = setMainPanel();

		JLabel one = new JLabel();
		mainPanel.add(one);

		setCenterPricePanel(mainPanel);

		JLabel two = new JLabel();
		mainPanel.add(two);
		
		setVisible(true);
	}

	private JPanel setMainPanel() {
		JPanel mainPanel = new JPanel(new GridLayout(1, 3));
		mainPanel.setBackground(new Color(0, 128, 128));
		mainPanel.setBorder(new LineBorder(new Color(224, 255, 255), 3, true));
		getContentPane().add(mainPanel);
		return mainPanel;
	}

	private void setCenterPricePanel(JPanel mainPanel) {
		JPanel pricePanel = createCenterPricePanelAndSetTitle(mainPanel);
		addRoomTypes(pricePanel);
		addConfirmAndResetButtons(pricePanel);
	}

	private void addConfirmAndResetButtons(JPanel pricePanel) {
		JPanel buttonPanel = createButtonPanel();
		JButton resetButton = createResetButton();
		JButton confirmButton = createConfirmButton();

		GridBagConstraints centerButton = new GridBagConstraints();
		centerButton.weighty = 0.5;
		centerButton.weightx = 0.5;
		centerButton.anchor = GridBagConstraints.CENTER;
		buttonPanel.add(resetButton, centerButton);
		buttonPanel.add(confirmButton, centerButton);

		pricePanel.add(buttonPanel);
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(new Color(0, 128, 128));
		return buttonPanel;
	}

	private JButton createConfirmButton() {
		JButton confirmButton = new JButton("Apply");
		confirmButton.addActionListener(e -> {
			if (!doubleTextField.getText().isBlank()
					&& !tripleTextField.getText().isBlank()
					&& !quadrupleTextField.getText().isBlank()
					&& !quintupleTextField.getText().isBlank()) {
				if (Checks.isDigit(doubleTextField.getText())
						&& Checks.isDigit(tripleTextField.getText())
						&& Checks.isDigit(quadrupleTextField.getText())
						&& Checks.isDigit(quintupleTextField.getText())) {
					setPrices();
				} else {
					JOptionPane.showMessageDialog(null, "Insert valid prices for the rooms");
				}
			} else
				JOptionPane.showMessageDialog(null, "Insert valid prices for the rooms");
		});
		confirmButton.setForeground(new Color(0, 128, 128));
		confirmButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		confirmButton.setBackground(new Color(224, 255, 255));
		return confirmButton;
	}

	private JButton createResetButton() {
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(e -> {
			int yn;
			yn = JOptionPane.showConfirmDialog(null, "Do you want to reset the data entered so far?", "Reset", JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				doubleTextField.setText(roomTypeService.getById(RoomType.Type.DOUBLE).getPrice().toString());
				tripleTextField.setText(roomTypeService.getById(RoomType.Type.TRIPLE).getPrice().toString());
				quadrupleTextField.setText(roomTypeService.getById(RoomType.Type.QUADRUPLE).getPrice().toString());
				quintupleTextField.setText(roomTypeService.getById(RoomType.Type.QUINTUPLE).getPrice().toString());
			} else
				JOptionPane.showMessageDialog(null, "Operation canceled");
		});
		resetButton.setForeground(new Color(0, 128, 128));
		resetButton.setBackground(new Color(224, 255, 255));
		resetButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		return resetButton;
	}

	private void addRoomTypes(JPanel pricePanel) {
		addRoomType(pricePanel, doubleTextField, RoomType.Type.DOUBLE);
		addRoomType(pricePanel, tripleTextField, RoomType.Type.TRIPLE);
		addRoomType(pricePanel, quadrupleTextField, RoomType.Type.QUADRUPLE);
		addRoomType(pricePanel, quintupleTextField, RoomType.Type.QUINTUPLE);
	}

	private void addRoomType(JPanel pricePanel, JTextField textField, RoomType.Type roomType) {
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.setBackground(new Color(0, 128, 128));
		pricePanel.add(panel);

		JLabel doubleLabel = new JLabel(roomType.toString() + " price");
		doubleLabel.setForeground(new Color(224, 255, 255));
		doubleLabel.setBackground(new Color(0, 128, 128));
		doubleLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		doubleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(doubleLabel);

		JPanel priceRoomPanel = new JPanel(new GridBagLayout());
		priceRoomPanel.setBackground(new Color(0, 128, 128));
		panel.add(priceRoomPanel);

		JLabel lblNewLabel_1_1_1 = new JLabel("\u20AC");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0;
		c.weighty = 0;
		priceRoomPanel.add(lblNewLabel_1_1_1, c);

		textField = new JTextField();
		GridBagConstraints d = new GridBagConstraints();
		d.gridx = 1;
		d.gridy = 0;
		d.anchor = GridBagConstraints.LINE_START;
		d.fill = GridBagConstraints.HORIZONTAL;
		d.weightx = 0.5;
		d.weighty = 0.5;
		textField.setDocument(SwingComponentUtil.financesPlainDocument());
		textField.setText(roomTypeService.getById(roomType).getPrice().toString());
		priceRoomPanel.add(textField, d);

		JLabel three = new JLabel();
		GridBagConstraints t = new GridBagConstraints();
		t.gridx = 2;
		t.gridy = 0;
		t.weightx = 0.5;
		t.weighty = 0.5;
		priceRoomPanel.add(three, t);
	}

	private JPanel createCenterPricePanelAndSetTitle(JPanel mainPanel) {
		JPanel pricePanel = new JPanel(new GridLayout(0, 1));
		pricePanel.setBackground(new Color(0, 128, 128));
		pricePanel.setBorder(new LineBorder(new Color(224, 255, 255), 3, true));
		mainPanel.add(pricePanel);

		JLabel textLabel = new JLabel("Set prices");
		textLabel.setForeground(new Color(224, 255, 255));
		textLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabel.setVerticalAlignment(SwingConstants.CENTER);
		pricePanel.add(textLabel);
		return pricePanel;
	}

	private void setTitlePanel() {
		JPanel titlePanel = new JPanel(new GridBagLayout());
		titlePanel.setBackground(new Color(224, 255, 255));
		titlePanel.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height / 7);
		getContentPane().add(titlePanel);

		JLabel titleLabel = new JLabel("Room management");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(0, 128, 128));
		titleLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		titlePanel.add(titleLabel);
	}

	private void setPrices() {
		int yn = JOptionPane.showConfirmDialog(null, "Do you want to confirm the new prices for the rooms?", "Update prices", JOptionPane.YES_NO_OPTION);
		if (yn == JOptionPane.YES_OPTION) {
			roomTypeService.saveRoomType(new RoomType(RoomType.Type.DOUBLE, BigDecimal.valueOf(Double.parseDouble(doubleTextField.getText()))));
			roomTypeService.saveRoomType(new RoomType(RoomType.Type.TRIPLE, BigDecimal.valueOf(Double.parseDouble(tripleTextField.getText()))));
			roomTypeService.saveRoomType(new RoomType(RoomType.Type.QUADRUPLE, BigDecimal.valueOf(Double.parseDouble(quadrupleTextField.getText()))));
			roomTypeService.saveRoomType(new RoomType(RoomType.Type.QUINTUPLE, BigDecimal.valueOf(Double.parseDouble(quintupleTextField.getText()))));
		
			JOptionPane.showMessageDialog(null, "Room prices updated correctly");
		}
		else JOptionPane.showMessageDialog(null, "Operation canceled");
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