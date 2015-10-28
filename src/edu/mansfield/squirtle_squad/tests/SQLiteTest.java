package edu.mansfield.squirtle_squad.tests;

import java.sql.Connection;
import java.sql.SQLException;

import edu.mansfield.squirtle_squad.database.DatabaseInteractions;
import edu.mansfield.squirtle_squad.model.Item;

public class SQLiteTest {

	public static void main(String[] args) throws SQLException {
		Item item1 = new Item(2914, "'Item Test'", 3.14, 1251, true);
		Item item2 = new Item(291204, "'Item Test'", 3.4, 1232412351, true);
		Item item3 = new Item(2912344, "'Item Test'", 1.6181, 12123491, false);
		Item item4 = new Item(2914, "'Replacement Item'", .14, 9818694, true);
		DatabaseInteractions DI = new DatabaseInteractions();
		Connection conn = DI.dbConnect();
		DI.dropTable(conn);
		DI.makeTable(conn);
		DI.insertData(conn, item1);
		DI.insertData(conn, item2);
		DI.insertData(conn, item3);
		Item[] itemSet = DI.getData(conn);
		for (int i = 0; i < itemSet.length; i++) {
			System.out.println(itemSet[i].toString() + "\n");
		}
		DI.deleteData(conn, 2914);
		itemSet = DI.getData(conn);
		System.out.println("After Delete:\n\n");
		for (int i = 0; i < itemSet.length; i++) {
			System.out.println(itemSet[i].toString() + "\n");
		}
		DI.updateData(conn, item4, 291204);
		itemSet = DI.getData(conn);
		System.out.println("After Update:\n\n");
		for (int i = 0; i < itemSet.length; i++) {
			System.out.println(itemSet[i].toString() + "\n");
		}

	}

}
