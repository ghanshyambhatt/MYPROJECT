package com.java.dbconnection;

import java.sql.*;

import org.apache.log4j.Logger;

public class DBUtil {

	final static Logger logger = Logger.getLogger(DBUtil.class);
	private Connection connection = null;

	public Connection getConnection() {
		if (connection != null)
			return connection;
		else {

			// step1 load the driver class
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// step2 create the connection object
				connection = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe", "aashish",
						"aashu");
			} catch (ClassNotFoundException e) {
				logger.error("Error:Database Connection:Driver Loader", e);
			} catch (SQLException e) {
				logger.error("Error:Database Connection:Failed to Get Connection Object.", e);
			}

			return connection;
		}
	}
}
