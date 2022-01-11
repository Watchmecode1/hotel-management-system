 package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.border.LineBorder;

import com.hotel.entity.Reservation;
import com.hotel.entity.Room;
import com.hotel.entity.Reservation.Board;
import com.hotel.service.ReservationService;
import com.hotel.util.SwingComponentUtil;

import javax.swing.JLabel;

import java.time.LocalDate;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import java.io.Serial;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class Catering extends JFrame {

	@Serial
	private static final long serialVersionUID = 6640704140627543254L;

	public Catering(ReservationService reservationService) {
		SwingComponentUtil.addHotelIcons(this);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//TODO Implementa in maniera piu efficiente
		List<Reservation> reservations = reservationService.getAll();
		int adultsBNB = 0;
		int childrenBNB = 0;
		int adultsHalfBoard = 0;
		int childrenHalfBoard = 0;
		int adultsFullBoard = 0;
		int childrenFullBoard = 0;

		DefaultListModel<String> halfBoards = new DefaultListModel<>();
		DefaultListModel<String> fullBoards = new DefaultListModel<>();
		DefaultListModel<String> bnb = new DefaultListModel<>();
		for (Reservation p : reservations) {
			if (p.getStartDate().isBefore(LocalDate.now()) || p.getStartDate().isEqual(LocalDate.now())) {
				if (p.getEndDate().isAfter(LocalDate.now())) {
					String s = "";
					Room c = p.getRooms().toArray(new Room[0])[0];
					int numberOfAdults = p.getNumberOfAdults();
					int numberOfMinors = p.getNumberOfMinors();
					int numberOfChildren = p.getNumberOfChilds();

					s += "ROOM" + " " + c.getNumber() + "   |   " + "ADULTS:" + "  " + numberOfAdults;

					if (numberOfMinors > 0)
						s += ",  " + "MINORS:" + "  " + numberOfMinors;
					if (numberOfChildren > 0)
						s += ",  " + "CHILDREN" + "  " + numberOfChildren;
					if (p.getBoard() == Board.BNB) {
						bnb.addElement(s);
						adultsBNB += numberOfAdults;
						childrenBNB += numberOfMinors;
						childrenBNB += numberOfChildren;
					} else if (p.getBoard() == Board.HALF_BOARD) {
						halfBoards.addElement(s);
						adultsHalfBoard += numberOfAdults;
						childrenHalfBoard += numberOfMinors;
						childrenHalfBoard += numberOfChildren;
					} else {
						fullBoards.addElement(s);
						adultsFullBoard += numberOfAdults;
						childrenFullBoard += numberOfMinors;
						childrenFullBoard += numberOfChildren;
					}
				}
			}
		}

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1537, 820);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(224, 255, 255), 3, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(0, 0, 1533, 81);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel titleLabel = new JLabel("Guests overview");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(0, 128, 128));
		titleLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 40));
		titleLabel.setBounds(10, 10, 1513, 61);
		panel.add(titleLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 139, 139));
		panel_1.setBorder(new LineBorder(new Color(224, 255, 255), 3));
		panel_1.setBounds(515, 91, 491, 497);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane halfBoardScrollPane = new JScrollPane();
		halfBoardScrollPane.setBounds(10, 91, 471, 396);
		panel_1.add(halfBoardScrollPane);

		JList<String> halfBoardList = new JList<>(halfBoards);
		halfBoardList.setForeground(new Color(0, 139, 139));
		halfBoardList.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		halfBoardList.setBackground(new Color(224, 255, 255));
		halfBoardScrollPane.setViewportView(halfBoardList);

		JLabel lblNewLabel = new JLabel("Half board guests");
		lblNewLabel.setForeground(new Color(224, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		lblNewLabel.setBounds(10, 10, 471, 76);
		panel_1.add(lblNewLabel);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(224, 255, 255), 3));
		panel_1_1.setBackground(new Color(0, 139, 139));
		panel_1_1.setBounds(1022, 91, 491, 497);
		contentPane.add(panel_1_1);

		JScrollPane fullBoardScrollPane = new JScrollPane();
		fullBoardScrollPane.setBounds(10, 91, 471, 396);
		panel_1_1.add(fullBoardScrollPane);

		JList<String> fullBoardList = new JList<>(fullBoards);
		fullBoardList.setForeground(new Color(0, 139, 139));
		fullBoardList.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		fullBoardList.setBackground(new Color(224, 255, 255));
		fullBoardScrollPane.setViewportView(fullBoardList);

		JLabel fullBoardGuestsLabel = new JLabel("Full board guests");
		fullBoardGuestsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fullBoardGuestsLabel.setForeground(new Color(224, 255, 255));
		fullBoardGuestsLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		fullBoardGuestsLabel.setBounds(10, 10, 471, 76);
		panel_1_1.add(fullBoardGuestsLabel);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBorder(new LineBorder(new Color(224, 255, 255), 3));
		panel_1_2.setBackground(new Color(0, 139, 139));
		panel_1_2.setBounds(10, 91, 491, 497);
		contentPane.add(panel_1_2);

		JScrollPane BNBScrollPane = new JScrollPane();
		BNBScrollPane.setBounds(10, 91, 471, 396);
		panel_1_2.add(BNBScrollPane);

		JList<String> bnbList = new JList<>(bnb);
		bnbList.setForeground(new Color(0, 139, 139));
		bnbList.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		bnbList.setBackground(new Color(224, 255, 255));
		BNBScrollPane.setViewportView(bnbList);

		JLabel BNBGuests = new JLabel("B&B guests");
		BNBGuests.setHorizontalAlignment(SwingConstants.CENTER);
		BNBGuests.setForeground(new Color(224, 255, 255));
		BNBGuests.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		BNBGuests.setBounds(10, 10, 471, 76);
		panel_1_2.add(BNBGuests);

		JLabel guestsAtBreakfastLabel = new JLabel("Guests at breakfast");
		guestsAtBreakfastLabel.setHorizontalAlignment(SwingConstants.LEFT);
		guestsAtBreakfastLabel.setForeground(new Color(224, 255, 255));
		guestsAtBreakfastLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		guestsAtBreakfastLabel.setBounds(10, 598, 491, 38);
		contentPane.add(guestsAtBreakfastLabel);

		JLabel guestsAtLunchLabel = new JLabel("Guests at lunch");
		guestsAtLunchLabel.setHorizontalAlignment(SwingConstants.LEFT);
		guestsAtLunchLabel.setForeground(new Color(224, 255, 255));
		guestsAtLunchLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		guestsAtLunchLabel.setBounds(10, 646, 491, 38);
		contentPane.add(guestsAtLunchLabel);

		JLabel guestsAtDinnerLabel = new JLabel("Guests at dinner");
		guestsAtDinnerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		guestsAtDinnerLabel.setForeground(new Color(224, 255, 255));
		guestsAtDinnerLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		guestsAtDinnerLabel.setBounds(10, 694, 491, 38);
		contentPane.add(guestsAtDinnerLabel);

		JLabel guestsBreakfast = new JLabel("");
		guestsBreakfast.setHorizontalAlignment(SwingConstants.LEFT);
		guestsBreakfast.setForeground(new Color(224, 255, 255));
		guestsBreakfast.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		guestsBreakfast.setBounds(515, 598, 998, 38);
		contentPane.add(guestsBreakfast);

		JLabel guestsLunch = new JLabel("");
		guestsLunch.setHorizontalAlignment(SwingConstants.LEFT);
		guestsLunch.setForeground(new Color(224, 255, 255));
		guestsLunch.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		guestsLunch.setBounds(515, 646, 998, 38);
		contentPane.add(guestsLunch);

		JLabel guestsDinner = new JLabel("");
		guestsDinner.setHorizontalAlignment(SwingConstants.LEFT);
		guestsDinner.setForeground(new Color(224, 255, 255));
		guestsDinner.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));
		guestsDinner.setBounds(515, 694, 998, 38);
		contentPane.add(guestsDinner);

		int adultsBreakfast = adultsBNB + adultsHalfBoard + adultsFullBoard;
		int childrenBreakfast = childrenBNB + childrenHalfBoard + childrenFullBoard;
		int adultsLunch = adultsHalfBoard + adultsFullBoard;
		int childrenLunch = childrenHalfBoard + childrenFullBoard;

		guestsBreakfast.setText("ADULTS:" + " " + adultsBreakfast + "    |    " + "CHILDREN:" + " " + childrenBreakfast + "    |    TOT. " + (adultsBreakfast + childrenBreakfast));
		guestsLunch.setText("ADULTS:" + " " + adultsFullBoard + "    |    " + "CHILDREN:" + " " + childrenFullBoard + "    |    TOT. " + (adultsFullBoard + childrenFullBoard));
		guestsDinner.setText("ADULTS:" + " " + adultsLunch + "    |    " + "CHILDREN:" + " " + childrenLunch + "    |    TOT. " + (adultsLunch + childrenLunch));

		setVisible(true);
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