package com.hotel.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.border.TitledBorder;

import com.hotel.entity.Room;
import com.hotel.entity.Room.Status;
import com.hotel.entity.Room.Floor;
import com.hotel.service.RoomService;
import com.hotel.util.SwingComponentUtil;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GraphicsConfiguration;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CleanRooms extends JFrame {

	@Serial
	private static final long serialVersionUID = -3755273086723639091L;
	private CleaningMenu cleaningMenu;

	public CleanRooms(RoomService roomService) {
		SwingComponentUtil.addHotelIcons(this);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Map<Floor, JPanel> floorPanels = Arrays.stream(Floor.values())
										.collect(Collectors.toMap(Function.identity(), this::floorPanel));
		addRooms(roomService.getAll(), floorPanels, roomService);
		
		JPanel titlePanel = new JPanel(new GridBagLayout());
		titlePanel.setBackground(new Color(224, 255, 255));
		titlePanel.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 30000));
		titlePanel.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 30000));
		
		JLabel titleLabel = new JLabel("Room cleanliness status");
		titleLabel.setForeground(new Color(0, 128, 128));
		titleLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		titlePanel.add(titleLabel);
		
		JPanel container = new JPanel();
		container.setBackground(new Color(0, 139, 139));
		container.setBorder(new LineBorder(new Color(224, 255, 255), 3, true));
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		container.add(titlePanel);
		floorPanels.entrySet().stream()
				.sorted((o1, o2) -> Integer.compare(o2.getKey().getFloor(), o1.getKey().getFloor()))
				.map(Map.Entry::getValue)
				.forEach(container::add);
		
		this.getContentPane().add(container);
		this.pack();
		this.setVisible(true);
	}
	
	private void addRooms(List<Room> rooms, Map<Floor, JPanel> floorPanels, RoomService roomService) {
		for(Room room : rooms) {
			String number = room.getNumber() + "";
			JButton button = new JButton(number);
			button.setFont(new Font("Tahoma", Font.BOLD, 20));
			button.addActionListener(e -> {
				if(CleanRooms.this.cleaningMenu != null) CleanRooms.this.cleaningMenu.dispose();
				Point mousePosition = MouseInfo.getPointerInfo().getLocation();
				CleanRooms.this.cleaningMenu = new CleaningMenu(button, roomService, room, (int) mousePosition.getX(), (int) mousePosition.getY() - (button.getHeight()/2));
			});

			setButtonColours(button, room);
			
			floorPanels.get(room.getFloor()).add(button);
		}
	}
	
	private void setButtonColours(JButton button, Room room) {
		if(room.getStatus() == Status.TO_CLEAN)
			button.setBackground(new Color(204, 0, 0));
		else 
			button.setBackground(new Color(51, 204, 51));
		
		if(!room.getNote().isBlank())
			button.setForeground(Color.YELLOW);
	}

	private JPanel floorPanel(Floor floor) {
		JPanel floorPanel = new JPanel(new GridLayout(1, 0, 10, 0));
		floorPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(224, 255, 255), new Color(224, 255, 255)), "Floor " + floor.getFloor(), TitledBorder.CENTER, TitledBorder.TOP, null, new Color(224, 255, 255)));
		floorPanel.setBackground(new Color(0, 128, 128));
		return floorPanel;
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
