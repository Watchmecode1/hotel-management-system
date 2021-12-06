package it.faggiorosso.gui;

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
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GraphicsConfiguration;

import it.faggiorosso.entity.Camera;
import it.faggiorosso.entity.Camera.Condizione;
import it.faggiorosso.entity.Camera.Piano;
import it.faggiorosso.service.CameraService;
import it.faggiorosso.util.SwingComponentUtil;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class CleanRooms extends JFrame {

	@Serial
	private static final long serialVersionUID = -3755273086723639091L;
	private MenuPulizia menuPulizia;

	public CleanRooms(CameraService cameraService) {
		SwingComponentUtil.addHotelIcons(this);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Map<Piano, JPanel> floorPanels = Arrays.stream(Piano.values())
										.collect(Collectors.toMap(Function.identity(), this::floorPanel));
		addRooms(cameraService.getAll(), floorPanels, cameraService);
		
		JPanel titlePanel = new JPanel(new GridBagLayout());
		titlePanel.setBackground(new Color(224, 255, 255));
		titlePanel.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 30000));
		titlePanel.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 30000));
		
		JLabel titleLabel = new JLabel("Stato Pulizia Camere");
		titleLabel.setForeground(new Color(0, 128, 128));
		titleLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 30));
		titlePanel.add(titleLabel);
		
		JPanel container = new JPanel();
		container.setBackground(new Color(0, 139, 139));
		container.setBorder(new LineBorder(new Color(224, 255, 255), 3, true));
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		container.add(titlePanel);
		floorPanels.entrySet().stream()
				.sorted((o1, o2) -> Integer.compare(o2.getKey().getPiano(), o1.getKey().getPiano()))
				.map(x -> x.getValue())
				.forEach(container::add);
		
		this.getContentPane().add(container);
		this.pack();
		this.setVisible(true);
	}
	
	private void addRooms(List<Camera> camere, Map<Piano, JPanel> floorPanels, CameraService cameraService) {		
		for(Camera camera : camere) {
			String number = camera.getNumero() + "";
			JButton button = new JButton(number);
			button.setFont(new Font("Tahoma", Font.BOLD, 20));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(CleanRooms.this.menuPulizia != null) CleanRooms.this.menuPulizia.dispose();
					Point mousePosition = MouseInfo.getPointerInfo().getLocation();
					CleanRooms.this.menuPulizia = new MenuPulizia(button, cameraService, camera, (int) mousePosition.getX(), (int) mousePosition.getY() - (button.getHeight()/2));
				}
			});

			setButtonColours(button, camera);
			
			floorPanels.get(camera.getPiano()).add(button);
		}
	}
	
	private void setButtonColours(JButton button, Camera camera) {
		if(camera.getCondizione() == Condizione.DA_PULIRE)
			button.setBackground(new Color(204, 0, 0));
		else 
			button.setBackground(new Color(51, 204, 51));
		
		if(!camera.getNote().isBlank())
			button.setForeground(Color.YELLOW);
	}

	private JPanel floorPanel(Piano piano) {
		JPanel floorPanel = new JPanel(new GridLayout(1, 0, 10, 0));
		floorPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(224, 255, 255), new Color(224, 255, 255)), "Piano " + piano.getPiano(), TitledBorder.CENTER, TitledBorder.TOP, null, new Color(224, 255, 255)));
		floorPanel.setBackground(new Color(0, 128, 128));
		return floorPanel;
	}
	
	@Override
	public void dispose() {
		int yn;
		yn = JOptionPane.showConfirmDialog(null, "SEI SICURO DI VOLER CHIUDERE LA PAGINA?\nATTENZIONE:\nTUTTI I DATI INSERITI NON SALVATI ANDRANNO PERSI.\nVUOI PROCEDERE?", "EXIT", JOptionPane.YES_NO_OPTION);
		if (yn == JOptionPane.YES_OPTION) {
			if(menuPulizia != null) menuPulizia.dispose();
			super.dispose();
		}
	}
	
	@Override
	  public synchronized void setExtendedState(final int state) {
	    if ((state & Planner.MAXIMIZED_BOTH) == Planner.MAXIMIZED_BOTH) {
	      final GraphicsConfiguration cfg = getGraphicsConfiguration();
	      final Insets screenInsets = getToolkit().getScreenInsets(cfg);
	      final Rectangle screenBounds = cfg.getBounds();
	      final int x = screenInsets.left + screenBounds.x * 0;
	      final int y = screenInsets.top + screenBounds.y * 0;
	      final int w = screenBounds.width - screenInsets.right - screenInsets.left;
	      final int h = screenBounds.height - screenInsets.bottom - screenInsets.top;
	      final Rectangle maximizedBounds = new Rectangle(x, y, w, h);
	      super.setMaximizedBounds(maximizedBounds);
	    }
	    super.setExtendedState(state);
	  }
}
