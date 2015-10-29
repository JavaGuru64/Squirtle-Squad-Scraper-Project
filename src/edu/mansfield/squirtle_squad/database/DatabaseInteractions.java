package edu.mansfield.squirtle_squad.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.mansfield.squirtle_squad.model.Item;
import edu.mansfield.squirtle_squad.utilities.FileHandler;

public class DatabaseInteractions {
	public Connection dbConnect() {
		Connection conn = null;
		try {
			//getClass().getClassLoader().getResource("Scraper.db");
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager
					.getConnection("jdbc:sqlite:" + FileHandler.getScraperFilePath());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;

	}

	public void makeTable(Connection conn) throws SQLException {
		Statement stmt = null;
		String sql = "CREATE TABLE EbayData\n"
				+ "(id long NOT NULL PRIMARY KEY,"
				+ "title varchar(255) NOT NULL,"
				+ "price DOUBLE PRECISION NOT NULL," + "bidTime long NOT NULL,"
				+ "isAuction boolean);";
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();

	}

	public void dropTable(Connection conn) throws SQLException {
		Statement stmt = null;
		String sql = "DROP TABLE EbayData";
		stmt = conn.createStatement();
		stmt.execute(sql);
		stmt.close();
	}

	public void insertData(Connection conn, Item item) throws SQLException {
		// Sanitize item title.
		String newTitle = item.getTitle();
		newTitle = newTitle.replaceAll("\"", "");

		Statement stmt = null;
		stmt = conn.createStatement();
		int boolValue = 0;
		if (item.isAuction()) {
			boolValue = 1;
		}
		String sql = "INSERT INTO EbayData (id, title, price, bidTime, isAuction)"
				+ " VALUES ("
				+ Long.toString(item.getId())
				+ ", \""
				+ newTitle
				+ "\", "
				+ Double.toString(item.getPrice())
				+ ", "
				+ Long.toString(item.getBidTime())
				+ ", "
				+ Integer.toString(boolValue) + ");";
		stmt.execute(sql);
		stmt.close();
	}

	public Item[] getData(Connection conn) throws SQLException {
		Item[] itemSet = null;
		Statement stmt = null;
		int results = 0;
		stmt = conn.createStatement();
		String sql = "SELECT COUNT (*) FROM EbayData;";
		ResultSet ct = stmt.executeQuery(sql);
		if (ct.next()) {
			results = ct.getInt(1);
		}

		itemSet = new Item[results];
		sql = "SELECT * FROM EbayData;";

		ResultSet rs = stmt.executeQuery(sql);
		if (rs != null) {
			int i = 0;
			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				double price = rs.getDouble("price");
				long bidTime = rs.getLong("bidTIme");
				boolean isAuction = rs.getBoolean("isAuction");
				Item item = new Item(id, title, price, bidTime, isAuction);
				itemSet[i] = item;
				i++;
			}
		}
		stmt.close();
		return itemSet;
	}

	public void deleteData(Connection conn, long deleteID) throws SQLException {
		Statement stmt = null;
		String sql = "DELETE FROM EbayData WHERE id =" + deleteID + ";";
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public void deleteExpiredAuctions(Connection conn) throws SQLException {
		Statement stmt = null;
		String sql = "DELETE FROM EbayData WHERE bidTime < " + System.currentTimeMillis() + " AND bidTime != 0;";
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	public void updateData(Connection conn, Item item, long replaceID)
			throws SQLException {
		deleteData(conn, replaceID);
		insertData(conn, item);

	}

	public String searchSelect(Connection conn, String searchTerm,
			String searchOrder, String searchType) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM EbayData ORDER BY id LIMIT 10000;";
		// WHERE title LIKE " + searchTerm + " AND isAuction ORDER BY " + searchOrder;
		
		ResultSet rs = stmt.executeQuery(sql);
		String returnString = "";

		if (rs != null) {
			while (rs.next()) {
				returnString += Long.toString(rs.getLong("id")) + ", "
						+ rs.getString("title")
						+ Double.toString(rs.getDouble("price")) + ", "
						+ Long.toString(rs.getLong("bidTime")) + ", "
						+ Boolean.toString(rs.getBoolean("isAuction")) + "\n";

			}
		}
		stmt.close();
		return returnString;
	}

	public void addOrUpdateData(Connection conn, Item item) throws SQLException {
		String newTitle = item.getTitle();
		newTitle = newTitle.replaceAll("\"", "");

		int boolValue = 0;
		if (item.isAuction()) {
			boolValue = 1;
		}

		String sqlTest = "INSERT OR REPLACE INTO EbayData (id, title, price, bidTime, isAuction)"
				+ " VALUES ("
				+ Long.toString(item.getId())
				+ ", \""
				+ newTitle
				+ "\", "
				+ Double.toString(item.getPrice())
				+ ", "
				+ Long.toString(item.getBidTime())
				+ ", "
				+ Integer.toString(boolValue) + ");";

		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sqlTest);
		stmt.close();

	}
	
}
