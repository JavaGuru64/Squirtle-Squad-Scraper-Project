package edu.mansfield.squirtle_squad.user_interface;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
		frmHelpMe = new JFrame();
		frmHelpMe.setTitle("Help Me!");
		frmHelpMe.setBounds(100, 100, 450, 340);
		frmHelpMe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHelpMe.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 11, 414, 240);
		frmHelpMe.getContentPane().add(textPane);
		
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
