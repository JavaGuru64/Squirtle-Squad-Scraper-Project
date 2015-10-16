package edu.mansfield.squirtle_squad.delegates;


public interface ScanDelegate {
	public boolean incrementScanPercentage(WebScannerDelegate source, int amountToIncrement);
	public boolean setScanPercentage(WebScannerDelegate source, int percentage);
	public boolean setTimeUntilFinished(WebScannerDelegate source, int timeInSeconds);
	public boolean setStatusText(WebScannerDelegate source, String text);
}
