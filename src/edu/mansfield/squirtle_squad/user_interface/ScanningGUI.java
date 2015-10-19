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
import java.time.Instant;
import java.util.Date;


public class ScanningGUI implements ScanDelegate{

	private JFrame frmScanInProgress;
	
	private JLabel percentLabel;
	private JSlider slider;
	private JLabel scanLabel;
	private int sliderValue = 0; 
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
		
		new Thread(new Runnable() {
			public void run() {
				scanController.scan();
			}
		}, "scan_process").start(); 
	}
	
	public ScanningGUI(StartGUIDelegate delegate) {
		initialize();
		
		EbayScanController scanController = new EbayScanController(this);
		
		new Thread(new Runnable() {
			public void run() {
				scanController.scan();
			}
		}, "scan_process").start(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmScanInProgress = new JFrame();
		frmScanInProgress.setTitle("Scan in progress");
		frmScanInProgress.setBounds(100, 100, 600, 150);
		frmScanInProgress.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmScanInProgress.getContentPane().setLayout(null);
		
		scanLabel = new JLabel("Scanning: Initializing Scan...");
		scanLabel.setBounds(10, 11, 550, 14);
		frmScanInProgress.getContentPane().add(scanLabel);
		
		slider = new JSlider();
		slider.setValue(sliderValue);
		slider.setBounds(10, 36, 450, 23);
		slider.setFocusable(false);
		slider.setEnabled(false);
		frmScanInProgress.getContentPane().add(slider);
		
		percentLabel = new JLabel("0%");
		percentLabel.setBounds(200, 70, 46, 14);
		frmScanInProgress.getContentPane().add(percentLabel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmScanInProgress.setVisible(false);
			}
		});
		btnCancel.setBounds(485, 78, 89, 23);
		frmScanInProgress.getContentPane().add(btnCancel);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setBounds(485, 49, 89, 23);
		frmScanInProgress.getContentPane().add(btnNewButton);
	}

	@Override
	public boolean incrementScanPercentage(WebScannerDelegate source, int amountToIncrement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setScanPercentage(WebScannerDelegate source, int percentage) {
		slider.setValue((int) Math.round((percentage/100.00)*4.5));
		//-TODO Check if the second percentage is a single digit and the pre append with a zero if it is.
		percentLabel.setText(percentage/100 + "." + percentage%100 + "%");
		return false;
	}

	// Don't worry about this one. I don't really have a way to implement 
	// a time estimator in my scanner yet and it is way low on my priority list
	@Override
	public boolean setTimeUntilFinished(WebScannerDelegate source, int timeInSeconds) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setStatusText(WebScannerDelegate source, String text) {
		scanLabel.setText(text);
		return false;
	}

}
