package it.faggiorosso.gui;

import java.awt.EventQueue;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

import it.faggiorosso.service.CameraService;
import it.faggiorosso.service.ClienteService;
import it.faggiorosso.service.ConsumazioneService;
import it.faggiorosso.service.DocumentoService;
import it.faggiorosso.service.OrdineService;
import it.faggiorosso.service.PrenotazioneService;
import it.faggiorosso.service.ProdottoService;
import it.faggiorosso.util.FileUtils;
import it.faggiorosso.util.NoScalingIcon;
import it.faggiorosso.util.SwingComponentUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	@Serial
	private static final long serialVersionUID = 5263091083750632811L;
	private static final String BOOKING_URL = "https://www.booking.com";
	private static final String WHATSAPP_URL = "https://web.whatsapp.com";
	private static final String POSTA_URL = "https://mail.ovh.net/roundcube/?_task=login";
	private static final String ALLOGGIATI_URL = "https://alloggiatiweb.poliziadistato.it/PortaleAlloggiati/";
	
	private Desktop desktop = Desktop.getDesktop();

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		JOptionPane.showMessageDialog(null, "LOGIN");
		SwingComponentUtil.addHotelIcons(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1537, 820);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Gestionale");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 60));
		lblNewLabel_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_1.setBounds(400, 556, 512, 75);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Multifunzionale");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_1_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 60));
		lblNewLabel_1_1.setBounds(400, 628, 512, 58);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("per Hotel");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 204));
		lblNewLabel_1_1_1.setFont(new Font("Haettenschweiler", Font.PLAIN, 60));
		lblNewLabel_1_1_1.setBounds(400, 685, 512, 58);
		contentPane.add(lblNewLabel_1_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(0, 0, 400, 792);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Plan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Planner(LocalDate.now(), new PrenotazioneService(), new CameraService(), new ConsumazioneService(), new ClienteService(), new DocumentoService());
			}
		});
		btnNewButton.setForeground(new Color(224, 255, 255));
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnNewButton.setBounds(90, 10, 220, 65);
		panel.add(btnNewButton);
		
		JButton btnNuovaPrenotazione = new JButton("Nuova Prenotazione");
		btnNuovaPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NuovaPrenotazione(new PrenotazioneService(), new CameraService(), new ClienteService(), new DocumentoService());
			}
		});
		btnNuovaPrenotazione.setForeground(new Color(224, 255, 255));
		btnNuovaPrenotazione.setBackground(new Color(0, 128, 128));
		btnNuovaPrenotazione.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnNuovaPrenotazione.setBounds(90, 85, 220, 65);
		panel.add(btnNuovaPrenotazione);
		
		JButton btnInventario = new JButton("Inventario");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Storage(new OrdineService(), new ProdottoService());
			}
		});
		btnInventario.setForeground(new Color(224, 255, 255));
		btnInventario.setBackground(new Color(0, 128, 128));
		btnInventario.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnInventario.setBounds(90, 235, 220, 65);
		panel.add(btnInventario);
		
		JButton btnRegPrenotazioni = new JButton("Reg. Prenotazioni");
		btnRegPrenotazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegistroPrenotazioni(new PrenotazioneService());
			}
		});
		btnRegPrenotazioni.setForeground(new Color(224, 255, 255));
		btnRegPrenotazioni.setBackground(new Color(0, 128, 128));
		btnRegPrenotazioni.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnRegPrenotazioni.setBounds(90, 310, 220, 65);
		panel.add(btnRegPrenotazioni);
		
		JButton btnRegClienti = new JButton("Reg. Clienti");
		btnRegClienti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegistroClienti(new ClienteService());
			}
		});
		btnRegClienti.setForeground(new Color(224, 255, 255));
		btnRegClienti.setBackground(new Color(0, 128, 128));
		btnRegClienti.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnRegClienti.setBounds(90, 385, 220, 65);
		panel.add(btnRegClienti);
		
		JButton btnSezioneCamere = new JButton("Sezione Camere");
		btnSezioneCamere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PrezziCamere();
			}
		});
		btnSezioneCamere.setForeground(new Color(224, 255, 255));
		btnSezioneCamere.setBackground(new Color(0, 128, 128));
		btnSezioneCamere.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnSezioneCamere.setBounds(90, 460, 220, 65);
		panel.add(btnSezioneCamere);
		
		JButton btnSezionePulizie = new JButton("Sezione Pulizie");
		btnSezionePulizie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CleanRooms(new CameraService());
			}
		});
		btnSezionePulizie.setForeground(new Color(224, 255, 255));
		btnSezionePulizie.setBackground(new Color(0, 128, 128));
		btnSezionePulizie.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnSezionePulizie.setBounds(90, 535, 220, 65);
		panel.add(btnSezionePulizie);
		
		JButton btnProdotti = new JButton("Prodotti");
		btnProdotti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegistroProdotti(new ProdottoService());
			}
		});
		btnProdotti.setForeground(new Color(224, 255, 255));
		btnProdotti.setBackground(new Color(0, 128, 128));
		btnProdotti.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnProdotti.setBounds(90, 610, 220, 65);
		panel.add(btnProdotti);
		
		JButton btnSitra = new JButton("Pagina Sitra");
		btnSitra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileUtils.writeSitraAndOpen(LocalDate.now());
			}
		});
		btnSitra.setForeground(new Color(224, 255, 255));
		btnSitra.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnSitra.setBackground(new Color(0, 128, 128));
		btnSitra.setBounds(90, 160, 220, 65);
		panel.add(btnSitra);
		
		JButton ristorazioneButton = new JButton("Ristorazione");
		ristorazioneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO migliora
				new ElenchiRistorazione(new PrenotazioneService());
			}
		});
		ristorazioneButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		ristorazioneButton.setForeground(new Color(224, 255, 255));
		ristorazioneButton.setBackground(new Color(0, 139, 139));
		ristorazioneButton.setBounds(90, 685, 220, 65);
		panel.add(ristorazioneButton);
		
		JButton btnNewButton_1 = new JButton("Booking.com");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.browse(new URI(BOOKING_URL));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(255, 204, 102));
		btnNewButton_1.setBounds(1312, 637, 200, 70);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Posta");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.browse(new URI(POSTA_URL));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnNewButton_2.setBackground(new Color(255, 204, 102));
		btnNewButton_2.setBounds(1312, 712, 200, 70);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("Whatsapp");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.browse(new URI(WHATSAPP_URL));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 20));
		btnNewButton_1_1.setBackground(new Color(255, 204, 102));
		btnNewButton_1_1.setBounds(1312, 561, 200, 70);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnAlloggiati = new JButton("Alloggiati Web");
		btnAlloggiati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.browse(new URI(ALLOGGIATI_URL));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnAlloggiati.setForeground(Color.WHITE);
		btnAlloggiati.setFont(new Font("Haettenschweiler", Font.PLAIN, 25));
		btnAlloggiati.setBackground(new Color(255, 204, 102));
		btnAlloggiati.setBounds(1312, 10, 200, 70);
		contentPane.add(btnAlloggiati);
		
		JLabel lblNewLabel = new JLabel("");
		try {
			lblNewLabel.setIcon(new NoScalingIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("imgs/wallPaper.jpg")))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel.setBounds(198, 0, 1335, 792);
		contentPane.add(lblNewLabel);
	}
	
	@Override
	public void dispose() {
		int yn;
		yn = JOptionPane.showConfirmDialog(null, "SEI SICURO DI VOLER USCIRE DAL GESTIONALE?", "EXIT", JOptionPane.YES_NO_OPTION);
		if (yn == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "LOGOUT");
			System.exit(0);
		}
	}
}
