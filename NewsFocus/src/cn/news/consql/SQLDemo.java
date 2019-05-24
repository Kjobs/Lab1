package cn.news.consql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class SQLDemo {
    public static Connection getConnection(String database) throws InstantiationException, IllegalAccessException {
    	String driver = "com.mysql.jdbc.Driver";
		String username = `SQL_Username`;
		String password = `SQL_Password`;
		String dbUrl = String.format("jdbc:mysql://localhost:3366/"+database+"?characterEncoding=utf8&useSSL=true");
    try {
    	//Load the driver for connection	
    	Class.forName(driver).newInstance();
    	//Database Connection
        Connection connection = DriverManager.getConnection(dbUrl, username, password);            
        return connection;
        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
 
}
