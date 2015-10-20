package edu.mansfield.squirtle_squad.tests;

import java.sql.Connection;
import java.sql.SQLException;

import edu.mansfield.squirtle_squad.database.DatabaseInteractions;
import edu.mansfield.squirtle_squad.model.Item;

public class SQLiteTestData {
	//DO NOT RUN THIS IF YOU ALREADY HAVE REAL DATA IN THE DATABASE, YOU WILL DO SERIOUS HARAM.
	public static void main(String[] args) throws SQLException {
		DatabaseInteractions DI = new DatabaseInteractions();
		Connection conn = DI.dbConnect();
		DI.dropTable(conn);
		DI.makeTable(conn);
		int numberOfItems = 10;
		Item[] items = new Item[numberOfItems];
		for(int i = 0; i < items.length; i++){
			boolean testbool = false;
			if(i % 2 == 0){
				testbool = true;
			}
			Item insertItem = new Item(i * (System.currentTimeMillis()/3), "TestItem" + Integer.toString(i), 2.25 * i, System.currentTimeMillis(), testbool);
			items[i] = insertItem;
		}
		
		for(Item item: items){
			System.out.println(item.toString() + "\n");
			DI.insertData(conn, item);
		}
		
	}

}
