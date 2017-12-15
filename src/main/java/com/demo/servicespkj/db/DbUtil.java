package com.demo.servicespkj.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DbUtil {
	
    private static DbUtil  datasource;
    private BasicDataSource ds;
	
	private DbUtil() {
		ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("usbw");
        ds.setUrl("jdbc:mysql://localhost:3307/demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        ds.setMinIdle(10);
        ds.setMaxIdle(30);
        ds.setMaxOpenPreparedStatements(20);
        
	}
	
	public static DbUtil getInstance() {
		if(datasource == null) {
			datasource = new DbUtil();
		}
		return datasource;
	}
	
	public Connection getConnection() throws SQLException {
		return this.ds.getConnection();
	}
		
	public DataSource getDataSource() {
		return this.ds;
	}
}
