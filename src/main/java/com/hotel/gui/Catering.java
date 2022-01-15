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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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

		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));

		JLabel titleLabel = new JLabel("Guests overview");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(new Color(0, 128, 128));
		titleLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 40));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 139, 139));
		panel_1.setBorder(new LineBorder(new Color(224, 255, 255), 3));

		JScrollPane halfBoardScrollPane = new JScrollPane();

		JList<String> halfBoardList = new JList<>(halfBoards);
		halfBoardList.setForeground(new Color(0, 139, 139));
		halfBoardList.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		halfBoardList.setBackground(new Color(224, 255, 255));
		halfBoardScrollPane.setViewportView(halfBoardList);

		JLabel lblNewLabel = new JLabel("Half board guests");
		lblNewLabel.setForeground(new Color(224, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(224, 255, 255), 3));
		panel_1_1.setBackground(new Color(0, 139, 139));

		JScrollPane fullBoardScrollPane = new JScrollPane();

		JList<String> fullBoardList = new JList<>(fullBoards);
		fullBoardList.setForeground(new Color(0, 139, 139));
		fullBoardList.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		fullBoardList.setBackground(new Color(224, 255, 255));
		fullBoardScrollPane.setViewportView(fullBoardList);

		JLabel fullBoardGuestsLabel = new JLabel("Full board guests");
		fullBoardGuestsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fullBoardGuestsLabel.setForeground(new Color(224, 255, 255));
		fullBoardGuestsLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBorder(new LineBorder(new Color(224, 255, 255), 3));
		panel_1_2.setBackground(new Color(0, 139, 139));

		JScrollPane BNBScrollPane = new JScrollPane();

		JList<String> bnbList = new JList<>(bnb);
		bnbList.setForeground(new Color(0, 139, 139));
		bnbList.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		bnbList.setBackground(new Color(224, 255, 255));
		BNBScrollPane.setViewportView(bnbList);

		JLabel BNBGuests = new JLabel("B&B guests");
		BNBGuests.setHorizontalAlignment(SwingConstants.CENTER);
		BNBGuests.setForeground(new Color(224, 255, 255));
		BNBGuests.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

		JLabel guestsAtBreakfastLabel = new JLabel("Guests at breakfast");
		guestsAtBreakfastLabel.setHorizontalAlignment(SwingConstants.LEFT);
		guestsAtBreakfastLabel.setForeground(new Color(224, 255, 255));
		guestsAtBreakfastLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

		JLabel guestsAtLunchLabel = new JLabel("Guests at lunch");
		guestsAtLunchLabel.setHorizontalAlignment(SwingConstants.LEFT);
		guestsAtLunchLabel.setForeground(new Color(224, 255, 255));
		guestsAtLunchLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

		JLabel guestsAtDinnerLabel = new JLabel("Guests at dinner");
		guestsAtDinnerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		guestsAtDinnerLabel.setForeground(new Color(224, 255, 255));
		guestsAtDinnerLabel.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

		JLabel guestsBreakfast = new JLabel("");
		guestsBreakfast.setHorizontalAlignment(SwingConstants.LEFT);
		guestsBreakfast.setForeground(new Color(224, 255, 255));
		guestsBreakfast.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

		JLabel guestsLunch = new JLabel("");
		guestsLunch.setHorizontalAlignment(SwingConstants.LEFT);
		guestsLunch.setForeground(new Color(224, 255, 255));
		guestsLunch.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

		JLabel guestsDinner = new JLabel("");
		guestsDinner.setHorizontalAlignment(SwingConstants.LEFT);
		guestsDinner.setForeground(new Color(224, 255, 255));
		guestsDinner.setFont(new Font("Haettenschweiler", Font.PLAIN, 35));

		int adultsBreakfast = adultsBNB + adultsHalfBoard + adultsFullBoard;
		int childrenBreakfast = childrenBNB + childrenHalfBoard + childrenFullBoard;
		int adultsLunch = adultsHalfBoard + adultsFullBoard;
		int childrenLunch = childrenHalfBoard + childrenFullBoard;

		guestsBreakfast.setText("ADULTS:" + " " + adultsBreakfast + "    |    " + "CHILDREN:" + " " + childrenBreakfast + "    |    TOT. " + (adultsBreakfast + childrenBreakfast));
		guestsLunch.setText("ADULTS:" + " " + adultsFullBoard + "    |    " + "CHILDREN:" + " " + childrenFullBoard + "    |    TOT. " + (adultsFullBoard + childrenFullBoard));
		guestsDinner.setText("ADULTS:" + " " + adultsLunch + "    |    " + "CHILDREN:" + " " + childrenLunch + "    |    TOT. " + (adultsLunch + childrenLunch));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1_2, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
					.addGap(16)
					.addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(guestsAtBreakfastLabel, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
					.addGap(14)
					.addComponent(guestsBreakfast, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
					.addGap(4))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(guestsAtLunchLabel, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(guestsLunch, GroupLayout.PREFERRED_SIZE, 998, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(guestsAtDinnerLabel, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(guestsDinner, GroupLayout.PREFERRED_SIZE, 998, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
						.addComponent(panel_1_1, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
						.addComponent(panel_1_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(guestsAtBreakfastLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(guestsBreakfast, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(guestsAtLunchLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(guestsLunch, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(guestsAtDinnerLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(guestsDinner, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(45))
		);
		GroupLayout gl_panel_1_2 = new GroupLayout(panel_1_2);
		gl_panel_1_2.setHorizontalGroup(
			gl_panel_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel_1_2.createParallelGroup(Alignment.LEADING)
						.addComponent(BNBGuests, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
						.addComponent(BNBScrollPane, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
					.addGap(7))
		);
		gl_panel_1_2.setVerticalGroup(
			gl_panel_1_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_2.createSequentialGroup()
					.addGap(7)
					.addComponent(BNBGuests, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(BNBScrollPane, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addGap(7))
		);
		panel_1_2.setLayout(gl_panel_1_2);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addComponent(fullBoardScrollPane, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(fullBoardGuestsLabel, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)))
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(7)
					.addComponent(fullBoardGuestsLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(fullBoardScrollPane, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addGap(7))
		);
		panel_1_1.setLayout(gl_panel_1_1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
						.addComponent(halfBoardScrollPane, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
					.addGap(7))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(7)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(halfBoardScrollPane, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addGap(7))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 1517, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

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