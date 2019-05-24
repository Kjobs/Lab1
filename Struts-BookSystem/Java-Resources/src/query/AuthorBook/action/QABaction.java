/**
 * @author Kobs
 * Query author's books by name of the author
 * Auery Author'Book->QABaction
 */
package query.AuthorBook.action;

import author.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class QABaction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	
	public List<String> AuthorBookList = new ArrayList<String>();
	public List<AuthorModel> AuthorList = new ArrayList<AuthorModel>();
	
	private String AuthorName;
	private String AuthorID;
	
	public String getAuthorName()
	{
		return AuthorName;
	}
	
	public void setAuthorName(String AuthorName)
	{
		this.AuthorName = AuthorName;
	}
	
	public String execute() throws Exception
	{
		String ret = ERROR;
		Connection QueryAuthorConn = null;
		
		String driver = "com.mysql.jdbc.Driver";
		String username = MySQL_user;
		String password = MySQL_pass;
		String dbUrl = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=true", MYSQL_HOST, MYSQL_PORT, MYSQL_DB);
		
		try {
		    Class.forName(driver).newInstance();
		    QueryAuthorConn = DriverManager.getConnection(dbUrl, username, password);
			
			String StrAuthorName = this.AuthorName;
			if(StrAuthorName.equals(""))
			{
				String sql2 = "select AuthorID,Name from author;";
				PreparedStatement nps = QueryAuthorConn.prepareStatement(sql2);
				ResultSet nrs = nps.executeQuery();
				
				while(nrs.next())
				{
					AuthorModel authors = new AuthorModel();
					authors.setAuthorID(nrs.getString(1));
					authors.setName(nrs.getString(2));
					AuthorList.add(authors);
				}
				return "author";
			}
			
			String sql1 = "select AuthorID from author where Name = ?;";
			PreparedStatement AuthorIDps = QueryAuthorConn.prepareStatement(sql1);
			AuthorIDps.setString(1, this.AuthorName);
			ResultSet rs = AuthorIDps.executeQuery();
			
			while(rs.next())
				AuthorID = rs.getString(1);
			
			if(AuthorID == null)
				return ERROR;
			
			String sql2 = "select Title from book where AuthorID = ?;";
			PreparedStatement Bookps = QueryAuthorConn.prepareStatement(sql2);
			Bookps.setString(1, this.AuthorID);
			ResultSet Bookrs = Bookps.executeQuery();
			
			while(Bookrs.next())
			{
				AuthorBookList.add(Bookrs.getString(1));
			}
			return SUCCESS;
		}
		catch (Exception e){
			ret = ERROR;
		}
		finally{
			if(QueryAuthorConn != null)
			{
				try{
					QueryAuthorConn.close();
				}
				catch (Exception e){
				}
			}
		}
		return ret;
	}
}
