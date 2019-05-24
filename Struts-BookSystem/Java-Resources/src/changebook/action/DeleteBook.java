/**
 * @author Kobs
 * Delete a book
 */

package changebook.action;

import java.sql.*;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteBook extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	
	public String Title;
	
	public String execute() throws Exception
	{
		String ret = ERROR;
		Connection DeleteConn = null;
		
		String driver = "com.mysql.jdbc.Driver";
		String username = MySQL_user;
		String password = MySQL_pass;
		String dbUrl = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=true", MYSQL_HOST, MYSQL_PORT, MYSQL_DB);
		
		try {
		    Class.forName(driver).newInstance();
		    DeleteConn = DriverManager.getConnection(dbUrl, username, password);
		    
			String sql = "delete from book where Title = ?;";
			PreparedStatement Deleteps = DeleteConn.prepareStatement(sql);
			Deleteps.setString(1, this.Title);
			Deleteps.executeUpdate();
			Deleteps.close();
			return SUCCESS;
		}catch (Exception e){
			ret = ERROR;
		}
		return ret;
	}
	
}
