package edu.mansfield.squirtle_squad.controller;

import edu.mansfield.squirtle_squad.database.DatabaseInteractions;
import edu.mansfield.squirtle_squad.delegates.ScanDelegate;
import edu.mansfield.squirtle_squad.delegates.WebScannerDelegate;
import edu.mansfield.squirtle_squad.model.Item;
import edu.mansfield.squirtle_squad.scraper.EbayScraper;
import edu.mansfield.squirtle_squad.scraper.Ebay_Cat;
import edu.mansfield.squirtle_squad.scraper.Scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/* 
 * http://www.ebay.com/sch/i.html?_png=1&rt=nc&_mPrRngCbx=1&_udlo=0&_udhi=100
 */

/*
 * http://www.ebay.com/sch/i.html?_from=R40&_sacat=0&_png=1&_nkw=all+items&_pgn=16&_skc=3000&rt=nc
 */

public class EbayScanController extends ScanController implements
		WebScannerDelegate {

	final int ITEMS_PER_PAGE = 10000;
	final double ACTUAL_PERCENTAGE_TO_DOWNLOAD_ALMOST = .01;

	private ArrayList<String> categories;
	private int scanLength;
	private double scanPercent;
	private double percentIncrementPerCategory;
	private ScanDelegate delegate;
	private boolean isCanceled, endScan;
	private Connection dbConnect;
	private int catagoriesScanned;

	public EbayScanController(ScanDelegate delegate) {
		this.delegate = delegate;
		initializeScan();
	}

	public EbayScanController() {
		initializeScan();
	}

	public void scan() {

		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (final String categoryURL : categories) {

			Thread aThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						scanCategory(new String(categoryURL));
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (!isCanceled && !endScan) {
						catagoriesScanned++;
						updatePercentages();
					}
				}
			}, categoryURL);

			// aThread.setPriority(Thread.MAX_PRIORITY);
			aThread.start();
			threads.add(aThread);

		}

		boolean threadsAreAlive;

		do {
			threadsAreAlive = false;
			for (Thread thread : threads) {
				// System.out.println(thread.getName()+ ": " +
				// (thread.isAlive()?" is alive": " is dead"));
				if (thread.isAlive()) {
					threadsAreAlive = true;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (threadsAreAlive && !endScan);

		delegate.scanEndedCleanup(this);

		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected boolean initializeScan() {
		catagoriesScanned = 0;
		isCanceled = false;
		endScan = false;
		try {
			categories = new ArrayList<String>(Ebay_Cat.getAllCategories());
			scanLength = categories.size();
			scanPercent = 0;
			percentIncrementPerCategory = 100 / (ACTUAL_PERCENTAGE_TO_DOWNLOAD_ALMOST * scanLength);
			DatabaseInteractions dbInteract = new DatabaseInteractions();
			dbConnect = dbInteract.dbConnect();
		} catch (IOException e) {
			e.printStackTrace();
			endScan();
			showAlertErrorBox("Error Code: " + e.getClass(),
					e.getLocalizedMessage());
			/*
			 * -TODO exit scan and throw message box exclaiming failure may want
			 * to make a delegate
			 */
			return false;
		}
		return true;
	}

	public void scanCategory(String categoryURL) throws Exception {
		EbayScraper scraper = null;
		int numberOfItemsInCategory;
		int itemsDownloaded;
		int minPrice;
		int maxPrice;
		int priceIncrement;

		String url = categoryURL;
		itemsDownloaded = 0;
		minPrice = 0;
		maxPrice = 10;
		priceIncrement = maxPrice - minPrice;

		if (!endScan) {
			scraper = new EbayScraper(this, url);
			numberOfItemsInCategory = scraper.getItemCount();
		} else {
			numberOfItemsInCategory = -1;
		}

		String priceRange = "";
		int maxPriceDividor = 0;

		// Begin the scraping process for the current category
		while (itemsDownloaded < numberOfItemsInCategory
				&& minPrice < 1000000000 && !isCanceled) {

			// System.out.println(categoryURL + " says that isCanceled is " +
			// isCanceled);
			priceRange = "&rt=nc";
			maxPriceDividor = 1;

			if (!endScan) {
				while (numberOfItemsInCategory > 10000
						&& scraper.getItemCount() > 10000
						&& maxPrice < 1000000000
						&& maxPriceDividor < priceIncrement && !isCanceled) {
					maxPrice = minPrice + priceIncrement / maxPriceDividor;
					maxPriceDividor *= 2;

					if (maxPrice <= minPrice) {
						maxPrice = minPrice + 1;
						break;
					}

					priceRange = "&_mPrRngCbx=1&_udlo=" + minPrice + "&_udhi="
							+ maxPrice + "&rt=nc";
					if (!endScan) {
						scraper = new EbayScraper(this, url + "?_png=1"
								+ priceRange);
						// System.out.println(categoryURL +
						// ": Checking Price Range!" + minPrice + "-" +
						// maxPrice);
					}
				}

				if (maxPrice > minPrice) {
					priceIncrement = Math.abs(10 * (maxPrice - minPrice));
				} else {
					priceIncrement = 10;
				}
				minPrice = maxPrice;

				if (!endScan) {
					delegate.setStatusText(this, "Downloading: " + url
							+ "?_png=1" + priceRange);
				}

				if (!pushItemsToDataBase(scraper.getItemsListed()) || endScan) {
					// may want to add a flag for throwing an alert error
					// box stating lost DB Connection if not canceled
					// Do not add here or you could get potentially 460 error
					// boxes
					return;
				}
				itemsDownloaded += scraper.getItemCount();
				// System.out.println(categoryURL + ": Downloaded");
			}
			// System.out.println("ReLoop");

		}// WE WON'T STOP UNTIL ALL YOUR DATUM IS BELONG TO US.

		if (isCanceled) {
			delegate.setStatusText(this, "Canceling...");
		}
	}

	public void alertConnectionTimeOut(Scraper source) {
		showAlertErrorBox(
				"Connection Timeout",
				"Your connection timed out.\n"
						+ "Please, check to make sure you are connected to the internet and try again.");
		System.exit(0);
		// -TODO Alert User of connection timeout. Fail Happy!!!
	}

	private void updatePercentages() {
		scanPercent = percentIncrementPerCategory * catagoriesScanned;
		if (scanPercent >= 100) {
			delegate.setScanPercentage(this, (int) 100 * 100);
			endScan();
		} else {
			delegate.setScanPercentage(this, (int) (scanPercent * 100));
		}
	}

	private boolean pushItemsToDataBase(Collection<Item> items) {

		for (Item item : items) {
			if (!endScan) {
				DatabaseInteractions dbInteract = new DatabaseInteractions();
				try {
					// System.out.println(item);
					dbInteract.addOrUpdateData(dbConnect, item);
				} catch (SQLException e) {
					// Thread conflict usually, but if there is no connection
					// we are probably canceling or failing splendidly
					e.printStackTrace();
					if (dbConnect == null) {
						System.out.println("NULL CONNECTION!!!");
						return false;
					}
				}
			}
		}
		return true;
	}

	private void showAlertErrorBox(String error, String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, error,
				JOptionPane.ERROR_MESSAGE);
	}

	private void endScan() {
		endScan = true;
		dbConnect = null;
	}

	public void kill() {
		isCanceled = true;
		endScan();
	}
}
