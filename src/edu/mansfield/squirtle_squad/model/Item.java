package edu.mansfield.squirtle_squad.model;

/*
 * This is our item class that represents what will be added to the database.
 * Should be the same for Ebay items and dealsea items.
 * 
 */

public class Item {

	long id;
	String title;
	double price;
	long bidTime;
	boolean isAuction;

	public Item() {
		id = 0;
		title = "";
		price = 0;
		bidTime = 0;
		isAuction = false;
	}

	public Item(long id, String title, double price, long bidTime,
			boolean isAuction) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.bidTime = bidTime;
		this.isAuction = isAuction;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getBidTime() {
		return bidTime;
	}

	public void setBidTime(long bidTime) {
		this.bidTime = bidTime;
	}

	public boolean isAuction() {
		return isAuction;
	}

	public void setAuction(boolean isAuction) {
		this.isAuction = isAuction;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", price=" + price
				+ ", bidTime=" + bidTime + ", isAuction=" + isAuction + "]";
	}
}
