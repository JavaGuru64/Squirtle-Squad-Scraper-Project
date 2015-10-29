package edu.mansfield.squirtle_squad.user_interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.util.Scanner;

public class HelpGUI {

	private JFrame frmHelpMe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpGUI window = new HelpGUI();
					window.frmHelpMe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HelpGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		InputStream ipst = getClass().getClassLoader().getResourceAsStream("README.md");
		Scanner sc = new Scanner(ipst);
		
		String readMeText = "";
		while (sc.hasNext()) {
			readMeText += sc.nextLine() + "\n";
		}
		sc.close();
		
		frmHelpMe = new JFrame();
		frmHelpMe.setTitle("Help Me!");
		frmHelpMe.setBounds(100, 100, 450, 340);
		// frmHelpMe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHelpMe.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 240);
		frmHelpMe.getContentPane().add(scrollPane);

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setText(readMeText);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmHelpMe.setVisible(false);
			}
		});
		btnOk.setBounds(335, 268, 89, 23);
		frmHelpMe.getContentPane().add(btnOk);
	}
}
