package com.revature.projectreimbursement.dl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
	//private static ConnectionFactory connectionFactory;  //use this for lazy loading
	private static final ConnectionFactory connectionFactory = new ConnectionFactory();  //for eager loading
	private Properties prop = new Properties(); //hol d s  db config stuff
	private final Logger logger = LogManager.getLogger(this.getClass());
	//We want to force load the postgresql driver.  static members get loaded into memory
	//at the start of program runtime and get run at start of program
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// this is a Singleton - due to this constructor being final.
	private ConnectionFactory() {
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			prop.load(loader.getResourceAsStream("db.properties"));
		} catch(IOException e) {	//in case the connection does not exist
			e.printStackTrace();
			logger.error("Can't find db.props file");
		}
	}
	
	public static ConnectionFactory getInstance() {
		 //if conn doesn't exist, get it!
		//if(connectionFactory == null) connectionFactory = new ConnectionFactory();//this is part of the lazy loading,
		return connectionFactory;
	}
	
	//factories are characterized by some method that contains logic for oblect creation
	public Connection getConnection() {
		Connection conn = null;
		//try to get connection using db creds
		try {
			conn = DriverManager.getConnection(prop.getProperty("url"), 
					prop.getProperty("username"), 
					prop.getProperty("password"));
		} catch(SQLException e) {
			e.printStackTrace();
			logger.error("Can't get connection.");
		}
		return conn;
	}
}
