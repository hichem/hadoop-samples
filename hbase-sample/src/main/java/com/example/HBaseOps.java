package com.example;

import java.io.IOException;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;

public class HBaseOps {

	public static void createTable(Connection conn, String tableName, String... families) throws IOException {

		Admin admin = conn.getAdmin();
		HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));

		for(String family : families) {
			tableDescriptor.addFamily(new HColumnDescriptor(family));
		}
		admin.createTable(tableDescriptor);
	}

	public static void insertData(Connection conn, String tableName) {
		
	}
	
}
