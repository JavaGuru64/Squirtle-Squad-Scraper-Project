package edu.mansfield.squirtle_squad.user_interface;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
//import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import edu.mansfield.squirtle_squad.delegates.StartGUIDelegate;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.util.Date;



public class StartGUI{

	private JFrame frmScraperBot;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JLabel lblTimetag;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGUI window = new StartGUI();
					window.frmScraperBot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmScraperBot = new JFrame();
		frmScraperBot.setTitle("Scraper Bot");
		frmScraperBot.setBounds(100, 100, 340, 242);
		frmScraperBot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmScraperBot.getContentPane().setLayout(null);
		
		JButton btnSubmit = new JButton("Scan");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblTimetag.setText(Date.from(Instant.now()).toString());
				ScanningGUI.main(null);
			}
		});
		btnSubmit.setBounds(21, 120, 65, 23);
		frmScraperBot.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(209, 120, 89, 23);
		frmScraperBot.getContentPane().add(btnCancel);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the site scraper bot, for all your scraping needs!");
		lblWelcomeToThe.setBounds(10, 11, 371, 14);
		frmScraperBot.getContentPane().add(lblWelcomeToThe);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HelpGUI.main(null);
			}
		});
		btnHelp.setBounds(21, 36, 89, 23);
		frmScraperBot.getContentPane().add(btnHelp);
		
		JLabel lblTimeSinceLast = new JLabel("Time since last scan: ");
		lblTimeSinceLast.setBounds(10, 81, 133, 14);
		frmScraperBot.getContentPane().add(lblTimeSinceLast);
		
		lblTimetag = new JLabel("TimeTag");
		lblTimetag.setBounds(144, 81, 65, 14);
		frmScraperBot.getContentPane().add(lblTimetag);
		
		JButton btnViewdb = new JButton("ViewDB");
		btnViewdb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DBGUI.main(null);
			}
		});
		btnViewdb.setBounds(105, 120, 89, 23);
		frmScraperBot.getContentPane().add(btnViewdb);
		
		JMenuBar menuBar = new JMenuBar();
		frmScraperBot.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnFile.add(mntmHelp);
		
		JMenuItem mntmScan = new JMenuItem("Scan");
		mnFile.add(mntmScan);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
	}
}
