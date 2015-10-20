package edu.mansfield.squirtle_squad.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.mansfield.squirtle_squad.model.Item;

public class DatabaseInteractions {
	public Connection dbConnect() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:Scraper.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		return conn;

	}

	public void makeTable(Connection conn) throws SQLException {
		Statement stmt = null;
		String sql = "CREATE TABLE EbayData\n" + "(id long NOT NULL PRIMARY KEY," + "title varchar(255) NOT NULL,"
				+ "price DOUBLE PRECISION NOT NULL," + "bidTime long NOT NULL," + "isAuction boolean);";
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
		String sql = "INSERT INTO EbayData (id, title, price, bidTime, isAuction)" + " VALUES ("
				+ Long.toString(item.getId()) + ", \"" + newTitle + "\", " + Double.toString(item.getPrice()) + ", "
				+ Long.toString(item.getBidTime()) + ", " + Integer.toString(boolValue) + ");";
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

	public void updateData(Connection conn, Item item, long replaceID) throws SQLException {
		deleteData(conn, replaceID);
		insertData(conn, item);

	}

	public String searchSelect(Connection conn, String searchTerm, String searchOrder, String searchType)
			throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM EbayData ORDER BY id";// WHERE title LIKE " + searchTerm + " AND isAuction ORDER BY " + searchOrder;
		ResultSet rs = stmt.executeQuery(sql);
		String returnString = "";

		if (rs != null) {
			while (rs.next()) {
				returnString += Long.toString(rs.getLong("id")) + ", " +
						rs.getString("title") + Double.toString(rs.getDouble("price")) +", " +
						Long.toString(rs.getLong("bidTime")) +", " +
						Boolean.toString(rs.getBoolean("isAuction")) + "\n";

			}
		}

		return returnString;
	}

	public void addOrUpdateData(Connection conn, Item item) throws SQLException {
		// -TODO change to the CASE WHEN sql statement to get rid of thread
		// conflicts.
		Statement stmt = conn.createStatement();
		String newTitle = item.getTitle();
		newTitle = newTitle.replaceAll("\"", "");

		// String sqlTest = "CASE WHEN (SELECT id FROM EbayData WHERE id=" +
		// item.getID() + ") != NULL"
		// + " THEN (UPDATE EbayData SET"
		// + " title=" + item.getTitle() + ","
		// + " price=" + item.getPrice() + ","
		// + " bidTime=" + item.getBidTime() + ","
		// + " isAuction=" + item.isAuction()
		// + " WHERE id=" + item.getID()
		// + " ELSE (INSERT INTO EbayData (id, title, price, bidTime,
		// isAuction)"
		// + " VALUES ("
		// + Long.toString(item.getId())
		// + ", \""
		// + newTitle
		// + "\", "
		// + Double.toString(item.getPrice())
		// + ", "
		// + Long.toString(item.getBidTime())
		// + ", "
		// + Integer.toString(boolValue) + ")) END;"

		String sqlTest = "SELECT id FROM EbayData WHERE id =" + item.getId() + ";";

		ResultSet testResultSet = stmt.executeQuery(sqlTest);

		if (testResultSet.next()) {
			updateData(conn, item, item.getId());
		} else {
			insertData(conn, item);
		}
	}
}
