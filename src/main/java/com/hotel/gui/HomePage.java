package com.hotel.gui;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.hotel.service.RoomService;
import com.hotel.service.CustomerService;
import com.hotel.service.ConsumptionService;
import com.hotel.service.DocumentService;
import com.hotel.service.OrderService;
import com.hotel.service.ReservationService;
import com.hotel.service.ProductService;
import com.hotel.util.FileUtils;
import com.hotel.util.SwingComponentUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;

public class HomePage extends JFrame {

	@Serial
	private static final long serialVersionUID = 5263091083750632811L;
	private static final String BOOKING_URL = "https://www.booking.com";
	private static final String WHATSAPP_URL = "https://web.whatsapp.com";
	private static final String EMAIL_URL = "https://gmail.com";
	
	private final Desktop desktop = Desktop.getDesktop();

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		EventQueue.invokeLater(() -> {
			try {
				HomePage frame = new HomePage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public HomePage() {
		SwingComponentUtil.addHotelIcons(this);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
//		JLabel lblNewLabel_1 = new JLabel("Gestionale");
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 60));
//		lblNewLabel_1.setForeground(new Color(255, 255, 204));
//		lblNewLabel_1.setBounds(400, 556, 512, 75);
//		contentPane.add(lblNewLabel_1);
//
//		JLabel lblNewLabel_1_1 = new JLabel("Multifunzionale");
//		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1_1.setForeground(new Color(255, 255, 204));
//		lblNewLabel_1_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 60));
//		lblNewLabel_1_1.setBounds(400, 628, 512, 58);
//		contentPane.add(lblNewLabel_1_1);
//
//		JLabel lblNewLabel_1_1_1 = new JLabel("per Hotel");
//		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 204));
//		lblNewLabel_1_1_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 60));
//		lblNewLabel_1_1_1.setBounds(400, 685, 512, 58);
//		contentPane.add(lblNewLabel_1_1_1);

		setBackground();
		setLeftMenuPanel();
		setMiddleSpacingBoxesForLayout();
		setRightMenuPanel();

		this.pack();
	}

	private void setRightMenuPanel() {
		JPanel rightSidePanel = new JPanel(new GridLayout(2, 1));
		rightSidePanel.setOpaque(false);
		getContentPane().add(rightSidePanel);

		JLabel emptyLabel = new JLabel();
		rightSidePanel.add(emptyLabel);

		JPanel bottomRightSidePanel = new JPanel(new GridLayout(0, 1, 10, 25));
		bottomRightSidePanel.setOpaque(false);
		rightSidePanel.add(bottomRightSidePanel);

		JButton bookingButton = new JButton("Booking.com");
		bookingButton.addActionListener(e -> {
			try {
				desktop.browse(new URI(BOOKING_URL));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		bookingButton.setForeground(new Color(255, 255, 255));
		bookingButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		bookingButton.setBackground(new Color(255, 204, 102));
		bottomRightSidePanel.add(bookingButton);

		JButton mailButton = new JButton("Mail");
		mailButton.addActionListener(e -> {
			try {
				desktop.browse(new URI(EMAIL_URL));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		mailButton.setForeground(new Color(255, 255, 255));
		mailButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		mailButton.setBackground(new Color(255, 204, 102));
		bottomRightSidePanel.add(mailButton);

		JButton whatsappButton = new JButton("Whatsapp");
		whatsappButton.addActionListener(e -> {
			try {
				desktop.browse(new URI(WHATSAPP_URL));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		});
		whatsappButton.setForeground(Color.WHITE);
		whatsappButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		whatsappButton.setBackground(new Color(255, 204, 102));
		bottomRightSidePanel.add(whatsappButton);
	}

	private void setMiddleSpacingBoxesForLayout() {
		JLabel one = new JLabel();
		JLabel two = new JLabel();

		getContentPane().add(one);
		getContentPane().add(two);
	}

	private void setLeftMenuPanel() {
		JPanel menuPanel = new JPanel(new GridLayout(0, 1, 0, 10));
		menuPanel.setOpaque(false);

		JButton btnNewButton = new JButton("Plan");
		btnNewButton.addActionListener(e -> new Planner(LocalDate.now(), new ReservationService(), new RoomService(), new ConsumptionService(), new CustomerService(), new DocumentService()));
		btnNewButton.setForeground(new Color(224, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		menuPanel.add(btnNewButton);

		JButton btnNewReservation = new JButton("New reservation");
		btnNewReservation.addActionListener(e -> new NewReservation(new ReservationService(), new RoomService(), new CustomerService(), new DocumentService()));
		btnNewReservation.setForeground(new Color(224, 255, 255));
		btnNewReservation.setBackground(new Color(0, 128, 128));
		btnNewReservation.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		menuPanel.add(btnNewReservation);

		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(e -> new Storage(new OrderService(), new ProductService()));
		btnInventory.setForeground(new Color(224, 255, 255));
		btnInventory.setBackground(new Color(0, 128, 128));
		btnInventory.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		menuPanel.add(btnInventory);

		JButton btnReservationRegister = new JButton("Reservation register");
		btnReservationRegister.addActionListener(e -> new ReservationRegister(new ReservationService()));
		btnReservationRegister.setForeground(new Color(224, 255, 255));
		btnReservationRegister.setBackground(new Color(0, 128, 128));
		btnReservationRegister.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		menuPanel.add(btnReservationRegister);

		JButton btnCustomerRegister = new JButton("Customer register");
		btnCustomerRegister.addActionListener(e -> new CustomerRegister(new CustomerService()));
		btnCustomerRegister.setForeground(new Color(224, 255, 255));
		btnCustomerRegister.setBackground(new Color(0, 128, 128));
		btnCustomerRegister.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		menuPanel.add(btnCustomerRegister);

		JButton btnRoomSection = new JButton("Room section");
		btnRoomSection.addActionListener(e -> new RoomPrices());
		btnRoomSection.setForeground(new Color(224, 255, 255));
		btnRoomSection.setBackground(new Color(0, 128, 128));
		btnRoomSection.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		menuPanel.add(btnRoomSection);

		JButton btnCleaningSection = new JButton("Cleaning section");
		btnCleaningSection.addActionListener(e -> new CleanRooms(new RoomService()));
		btnCleaningSection.setForeground(new Color(224, 255, 255));
		btnCleaningSection.setBackground(new Color(0, 128, 128));
		btnCleaningSection.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		menuPanel.add(btnCleaningSection);

		JButton btnProducts = new JButton("Products");
		btnProducts.addActionListener(e -> new ProductRegister(new ProductService()));
		btnProducts.setForeground(new Color(224, 255, 255));
		btnProducts.setBackground(new Color(0, 128, 128));
		btnProducts.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		menuPanel.add(btnProducts);

		JButton btnSitra = new JButton("Sitra page");
		btnSitra.addActionListener(e -> FileUtils.writeSitraAndOpen(LocalDate.now()));
		btnSitra.setForeground(new Color(224, 255, 255));
		btnSitra.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnSitra.setBackground(new Color(0, 128, 128));
		menuPanel.add(btnSitra);

		JButton cateringButton = new JButton("Catering");
		cateringButton.addActionListener(e -> new Catering(new ReservationService()));
		cateringButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		cateringButton.setForeground(new Color(224, 255, 255));
		cateringButton.setBackground(new Color(0, 139, 139));
		menuPanel.add(cateringButton);

		getContentPane().add(menuPanel);
	}

	private void setBackground() {
		JLabel backgroundLabel = new JLabel();
		try {
			backgroundLabel.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("imgs/wallPaper.jpg"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		backgroundLabel.setLayout(new GridLayout(1, 4));
		this.setContentPane(backgroundLabel);
	}

	@Override
	public void dispose() {
		int yn;
		yn = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?", "Exit", JOptionPane.YES_NO_OPTION);
		if (yn == JOptionPane.YES_OPTION)
			System.exit(0);
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