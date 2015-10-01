package edu.mansfield.squirtle_squad.user_interface;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JSlider;


public class StartGUI {

	private JFrame frmScraperBot;
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		frmScraperBot.setBounds(100, 100, 407, 328);
		frmScraperBot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmScraperBot.getContentPane().setLayout(null);
		
		JButton btnSubmit = new JButton("Scan");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScanningGUI sg = new ScanningGUI();
				sg.main(null);
			}
		});
		btnSubmit.setBounds(104, 235, 65, 23);
		frmScraperBot.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(292, 235, 89, 23);
		frmScraperBot.getContentPane().add(btnCancel);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the site scraper bot, for all your scraping needs!");
		lblWelcomeToThe.setBounds(10, 11, 371, 14);
		frmScraperBot.getContentPane().add(lblWelcomeToThe);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HelpGUI hg = new HelpGUI();
				hg.main(null);
			}
		});
		btnHelp.setBounds(292, 36, 89, 23);
		frmScraperBot.getContentPane().add(btnHelp);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Scrape Ebay");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(10, 56, 109, 23);
		frmScraperBot.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Scrape Both");
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(10, 108, 109, 23);
		frmScraperBot.getContentPane().add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Scrape DealSea");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(10, 82, 133, 23);
		frmScraperBot.getContentPane().add(rdbtnNewRadioButton_1);
		
		JLabel lblTimeSinceLast = new JLabel("Time since last scan: ");
		lblTimeSinceLast.setBounds(10, 153, 133, 14);
		frmScraperBot.getContentPane().add(lblTimeSinceLast);
		
		JLabel lblTimetag = new JLabel("TimeTag");
		lblTimetag.setBounds(144, 153, 65, 14);
		frmScraperBot.getContentPane().add(lblTimetag);
		
		JSlider slider = new JSlider();
		slider.setBounds(181, 82, 200, 23);
		frmScraperBot.getContentPane().add(slider);
		
		JLabel lblPercentageOfSite = new JLabel("Percentage of site to scrape: ");
		lblPercentageOfSite.setBounds(144, 117, 172, 14);
		frmScraperBot.getContentPane().add(lblPercentageOfSite);
		
		JLabel label = new JLabel("%%%");
		label.setBounds(335, 117, 46, 14);
		frmScraperBot.getContentPane().add(label);
		
		JButton btnViewdb = new JButton("ViewDB");
		btnViewdb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DBGUI dg = new DBGUI();
				dg.main(null);
			}
		});
		btnViewdb.setBounds(188, 235, 89, 23);
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
