package edu.mansfield.squirtle_squad.delegates;

import edu.mansfield.squirtle_squad.controller.WebScanner;

public interface ScanDelegate {
	public boolean incrementScanPercentage(WebScanner source, int amountToIncrement);
	public boolean setScanPercentage(WebScanner source, int percentage);
	public boolean setTimeUntilFinished(WebScanner source, int timeInSeconds);
}
