package com.hotel.gui;

import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import com.hotel.service.CustomerService;
import com.hotel.service.ConsumptionService;
import com.hotel.service.DocumentService;
import com.hotel.service.ReservationService;
import com.hotel.util.SwingComponentUtil;

import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;

public class Planner extends JFrame {

	@Serial
	private static final long serialVersionUID = -3819752603187204888L;
	private MenuPrenotazione menuPrenotazione;

	public Planner(LocalDate data, ReservationService reservationService, RoomService roomService, ConsumptionService consumptionService, CustomerService customerService, DocumentService documentService) {
		SwingComponentUtil.addHotelIcons(this);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		List<Room> camere = roomService.getAll().stream()
								.sorted(Comparator.comparingInt(Room::getNumber))
								.toList();
		int numeroCamere = camere.size();
		int numeroGiorni = data.lengthOfMonth();
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(new Color(0, 139, 139));
		container.setBorder(new LineBorder(new Color(224, 255, 255), 3, true));
		getContentPane().add(container);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(0, 139, 139));
		JButton leftButton = new JButton();
		try {
			ImageIcon img = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("imgs/leftArrow.png")));
			leftButton.setIcon(img);
		} catch(IOException e) {
			e.printStackTrace();
		}
		leftButton.addActionListener(e -> {
			//TODO transizione
			new Planner(data.withDayOfMonth(1).minusMonths(1), reservationService, roomService, consumptionService, customerService, documentService);
			Planner.this.dispose();
		});
		leftButton.setBackground(container.getBackground());
		leftButton.setBorder(null);
		topPanel.add(leftButton);
		
		JLabel monthYear = new JLabel(getMonthFromMonthValue(data.getMonthValue()) + "/" + data.getYear());
		monthYear.setHorizontalAlignment(SwingConstants.CENTER);
		monthYear.setForeground(new Color(224, 255, 255));
		monthYear.setFont(new Font("Dialog", Font.PLAIN, 33));
		topPanel.add(monthYear);
		
		JButton rightButton = new JButton();
		try {
			ImageIcon img = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("imgs/rightArrow.png")));
			rightButton.setIcon(img);
		} catch(IOException e) {
			e.printStackTrace();
		}
		rightButton.addActionListener(e -> {
			new Planner(data.withDayOfMonth(1).plusMonths(1), reservationService, roomService, consumptionService, customerService, documentService);
			Planner.this.dispose();
		});
		rightButton.setBackground(container.getBackground());
		rightButton.setBorder(null);
		topPanel.add(rightButton);
		topPanel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 70));
		topPanel.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 70));
		topPanel.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 70));
		container.add(topPanel);
		
		JPanel planPanel = new JPanel();
		GridBagLayout gridLayout = new GridBagLayout();
		planPanel.setLayout(gridLayout);
		
		boolean previousMonthBookingIsPossible;
		for(int row = 0; row <= numeroCamere; row++) {
			previousMonthBookingIsPossible = true;
			for(int column = 0; column <= numeroGiorni; column++) {
				/////////////////////////
				JLabel halfLabel = new JLabel();
				GridBagConstraints halfLabelConstraints = new GridBagConstraints();
				halfLabelConstraints.gridx = column * 2;
				halfLabelConstraints.gridy = row;
				halfLabelConstraints.fill = GridBagConstraints.BOTH;
				halfLabelConstraints.weightx = 0.5;
				halfLabelConstraints.weighty = 0.5;
				JLabel halfLabel2 = new JLabel();
				GridBagConstraints halfLabelConstraints2 = new GridBagConstraints();
				halfLabelConstraints2.gridx = column * 2 + 1;
				halfLabelConstraints2.gridy = row;
				halfLabelConstraints2.fill = GridBagConstraints.BOTH;
				halfLabelConstraints2.weightx = 0.5;
				halfLabelConstraints2.weighty = 0.5;
				planPanel.add(halfLabel, halfLabelConstraints);
				planPanel.add(halfLabel2, halfLabelConstraints2);
				//////////////////
				if(row == 0) {
					if(column > 0) {
						JLabel label = new JLabel(String.valueOf(column));
						label.setHorizontalAlignment(SwingConstants.CENTER);
						label.setVerticalAlignment(SwingConstants.BOTTOM);
						GridBagConstraints constraints = new GridBagConstraints();
						constraints.gridx = column * 2;
						constraints.gridy = row;
						constraints.anchor = GridBagConstraints.PAGE_END;
						constraints.gridwidth = 2;
						constraints.weightx = 0.5;
						constraints.weighty = 0.5;
						
						planPanel.add(label, constraints);
					} else {
						JLabel label = new JLabel();
						GridBagConstraints constraints = new GridBagConstraints();
						constraints.gridx = column * 2;
						constraints.gridy = row;
						constraints.gridwidth = 2;
						constraints.weightx = 0.5;
						constraints.weighty = 0.5;
						planPanel.add(label, constraints);
					}
				} else {
					if(column == 0) {
						JLabel label = new JLabel(String.valueOf(camere.get(row - 1).getNumber()));
						label.setHorizontalAlignment(SwingConstants.CENTER);
						label.setVerticalAlignment(SwingConstants.CENTER);
						GridBagConstraints constraints = new GridBagConstraints();
						constraints.gridx = 0;
						constraints.gridy = row;
						constraints.gridwidth = 2;
						constraints.weightx = 0.5;
						constraints.weighty = 0.5;
						planPanel.add(label, constraints);
					} else {
						Room room = camere.get(row - 1);
						LocalDate cellLocalDate = data.withDayOfMonth(column);
						Reservation reservation = reservationService.findByRoomAndStartDate(room, cellLocalDate);
						
						//month before bookings
						if(reservation != null) previousMonthBookingIsPossible = false;
						if(previousMonthBookingIsPossible)
							reservation = reservationService.findByRoomAndStartDateInPreviousMonth(room, cellLocalDate);
						
						if(reservation != null) {
							JLabel label = new JLabel();
							label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
							GridBagConstraints constraints = new GridBagConstraints();
							constraints.gridx = column * 2;
							constraints.gridy = row;
							constraints.gridwidth = 2;
							constraints.fill = GridBagConstraints.BOTH;
							constraints.weightx = 0.5;
							constraints.weighty = 0.5;
							
							GridBagConstraints constraintsButton = new GridBagConstraints();
							constraintsButton.gridx = previousMonthBookingIsPossible ? 2 : column * 2 + 1;
							constraintsButton.gridy = row;
							constraintsButton.fill = GridBagConstraints.BOTH;
							constraintsButton.weightx = 0.5;
							constraintsButton.weighty = 0.5;
							constraintsButton.gridwidth = previousMonthBookingIsPossible ? reservation.getEndDate().getDayOfMonth() * 2 - 1 : (int) reservation.getStartDate().until(reservation.getEndDate(), ChronoUnit.DAYS) * 2;

							JButton button = new JButton(reservation.getSurname());
							button.setFont(new Font("Tahoma", Font.BOLD, 20));
							setButtonBackground(button, reservation);
							button.setMaximumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / (numeroGiorni + 1), 15));
							button.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / (numeroGiorni + 1), 15));
							final Reservation reservationFinal = reservation;
							button.addActionListener(e -> {
								if(Planner.this.menuPrenotazione != null) Planner.this.menuPrenotazione.dispose();
								Point mousePosition = MouseInfo.getPointerInfo().getLocation();
								Planner.this.menuPrenotazione = new MenuPrenotazione((int) mousePosition.getX(), (int) mousePosition.getY() - (button.getHeight()/2), reservationFinal, reservationService, consumptionService, roomService, customerService, documentService);
							});
							planPanel.add(button, constraintsButton);
							planPanel.add(label, constraints);
							previousMonthBookingIsPossible = false;
						} else {
							JLabel label = new JLabel();
							label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
							GridBagConstraints constraints = new GridBagConstraints();
							constraints.gridx = column * 2;
							constraints.gridy = row;
							constraints.gridwidth = 2;
							constraints.fill = GridBagConstraints.BOTH;
							constraints.weightx = 0.5;
							constraints.weighty = 0.5;
							planPanel.add(label, constraints);
						}
					}
				}
			}
			container.add(planPanel);
		}

		this.pack();
		this.setVisible(true);
	}
	
	private void setButtonBackground(JButton button, Reservation reservation) {
		LocalDate today = LocalDate.now();
		if(today.isAfter(reservation.getStartDate().minusDays(1)) && today.isBefore(reservation.getEndDate()))
			button.setBackground(Color.YELLOW);
		else if(today.isAfter(reservation.getEndDate().minusDays(1)))
			button.setBackground(Color.RED);
		else
			button.setBackground(Color.GREEN);
	}

	private String getMonthFromMonthValue(int monthValue) {
		return switch(monthValue) {
			case 1 	-> "GENNAIO";
			case 2 	-> "FEBBRAIO";
			case 3 	-> "MARZO";
			case 4 	-> "APRILE";
			case 5 	-> "MAGGIO";
			case 6 	-> "GIUGNO";
			case 7 	-> "LUGLIO";
			case 8 	-> "AGOSTO";
			case 9 	-> "SETTEMBRE";
			case 10 -> "OTTOBRE";
			case 11 -> "NOVEMBRE";
			case 12 -> "DICEMBRE";
			default -> throw new AssertionError("Invalid month");
		};
	}

	@Override
	public void dispose() {
		if(menuPrenotazione != null) menuPrenotazione.dispose();
		super.dispose();
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