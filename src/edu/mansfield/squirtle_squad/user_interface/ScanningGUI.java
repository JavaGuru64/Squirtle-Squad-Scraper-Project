package edu.mansfield.squirtle_squad.user_interface;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;

import edu.mansfield.squirtle_squad.controller.EbayScanController;
import edu.mansfield.squirtle_squad.delegates.ScanDelegate;
import edu.mansfield.squirtle_squad.delegates.StartGUIDelegate;
import edu.mansfield.squirtle_squad.delegates.WebScannerDelegate;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ScanningGUI implements ScanDelegate{

	private JFrame frmScanInProgress;
	private StartGUIDelegate delegate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScanningGUI window = new ScanningGUI();
					window.frmScanInProgress.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScanningGUI() {
		initialize();
		EbayScanController scanController = new EbayScanController(this);
		scanController.scan();
	}
	
	public ScanningGUI(StartGUIDelegate delegate) {
		this.delegate = delegate;
		initialize();
		EbayScanController scanController = new EbayScanController(this);
		scanController.scan();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmScanInProgress = new JFrame();
		frmScanInProgress.setTitle("Scan in progress");
		frmScanInProgress.setBounds(100, 100, 400, 150);
		frmScanInProgress.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmScanInProgress.getContentPane().setLayout(null);
		
		JLabel lblScanning = new JLabel("Scanning...");
		lblScanning.setBounds(80, 11, 69, 14);
		frmScanInProgress.getContentPane().add(lblScanning);
		
		JSlider slider = new JSlider();
		slider.setValue(0);
		slider.setBounds(10, 36, 200, 23);
		slider.setFocusable(false);
		slider.setEnabled(false);
		frmScanInProgress.getContentPane().add(slider);
		
		JLabel label = new JLabel("%%%");
		label.setBounds(80, 70, 46, 14);
		frmScanInProgress.getContentPane().add(label);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmScanInProgress.setVisible(false);
			}
		});
		btnCancel.setBounds(285, 78, 89, 23);
		frmScanInProgress.getContentPane().add(btnCancel);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setBounds(285, 49, 89, 23);
		frmScanInProgress.getContentPane().add(btnNewButton);
	}

	@Override
	public boolean incrementScanPercentage(WebScannerDelegate source, int amountToIncrement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setScanPercentage(WebScannerDelegate source, int percentage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setTimeUntilFinished(WebScannerDelegate source, int timeInSeconds) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setStatusText(WebScannerDelegate source, String text) {
		// TODO Auto-generated method stub
		return false;
	}

}
