package edu.mansfield.squirtle_squad.user_interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import edu.mansfield.squirtle_squad.delegates.StartGUIDelegate;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartGUI implements StartGUIDelegate {

	private File timeStamp = new File("rsrc/TimeStamp");
	private JFrame frmScraperBot;
	private JLabel lblTimeSinceLast;

	ScanningGUI scanGUI;

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
		final StartGUI startGUIReference = this;
		frmScraperBot = new JFrame();
		frmScraperBot.setTitle("Scraper Bot");
		frmScraperBot.setBounds(100, 100, 450, 295);
		frmScraperBot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmScraperBot.getContentPane().setLayout(null);

		JButton btnSubmit = new JButton("Scan");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmScraperBot.setVisible(false);
				// scanGUI = new ScanningGUI();
				new ScanningGUI(startGUIReference);
			}
		});
		btnSubmit.setBounds(108, 202, 65, 23);
		frmScraperBot.getContentPane().add(btnSubmit);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(282, 202, 89, 23);
		frmScraperBot.getContentPane().add(btnCancel);

		JLabel lblWelcomeToThe = new JLabel(
				"Welcome to the site scraper bot, for all your scraping needs!");
		lblWelcomeToThe.setBounds(10, 11, 400, 14);
		frmScraperBot.getContentPane().add(lblWelcomeToThe);

		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HelpGUI.main(null);
			}
		});
		btnHelp.setBounds(21, 36, 89, 23);
		frmScraperBot.getContentPane().add(btnHelp);

		lblTimeSinceLast = new JLabel(getLastTimeStamp());
		lblTimeSinceLast.setBounds(10, 122, 361, 14);
		frmScraperBot.getContentPane().add(lblTimeSinceLast);

		JButton btnViewdb = new JButton("ViewDB");
		btnViewdb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmScraperBot.setVisible(false);
				new DBGUI(startGUIReference);
			}
		});
		btnViewdb.setBounds(183, 202, 89, 23);
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

	@Override
	public void releaseSubGUI(SubGUI subGUI) {
		subGUI = null;
	}

	@Override
	public void makeVisable(SubGUI scanningGUI) {
		lblTimeSinceLast.setText(getLastTimeStamp());
		frmScraperBot.setVisible(true);
	}

	private String getLastTimeStamp() {
		String timeStampText = "Last scan on: ";
		try {
			Scanner sc = new Scanner(timeStamp);
			while (sc.hasNext()) {
				timeStampText += sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (timeStampText.equals("Last scan on: ")) {
			timeStampText += "NEVER!!!";
		}

		return timeStampText;
	}
}
