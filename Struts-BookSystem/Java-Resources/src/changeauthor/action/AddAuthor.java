/**
 * @author KobsJieH
 * Add a Author
 */

package changeauthor.action;

import book.model.*;
import changebook.action.AddBook;

import java.sql.*;

import com.opensymphony.xwork2.ActionSupport;

public class AddAuthor extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	
	private String AuthorID = null;
	private String Name = null;
	private int Age;
	private String Country = null;
	
	AddBook temp = new AddBook();
	private BookModel book = AddBook.bookCache;
	
	public String getName()
	{
		return Name;
	}
	
	public void setName(String Name)
	{
		this.Name = Name;
	}
	
	public int getAge()
	{
		return Age;
	}
	
	public void setAge(int Age)
	{
		this.Age = Age;
	}
	
	public String getCountry()
	{
		return Country;
	}
	
	public void setCountry(String Country)
	{
		this.Country = Country;
	}
	
	public String execute() throws Exception
	{
		String Aret = ERROR;
		Connection AddAConn = null;
		
		String driver = "com.mysql.jdbc.Driver";
		String username = "MySQL_user";
		String password = "MySQL_pass";
		String dbUrl = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=true", MYSQL_HOST, MYSQL_PORT, MYSQL_DB);
		
		try {
		    Class.forName(driver).newInstance();
		    AddAConn = DriverManager.getConnection(dbUrl, username, password);
			
		    String QAuthorID = "select AuthorID from author order by AuthorID desc limit 1;";
			PreparedStatement QAIDps = AddAConn.prepareStatement(QAuthorID);
			ResultSet QAIDrs = QAIDps.executeQuery();
			String LastID = null;
			while(QAIDrs.next())
			{
				LastID = QAIDrs.getString(1); 
			}
			int MidAID;
			MidAID = Integer.parseInt(LastID)+1;
			AuthorID = String.format("%05d", MidAID);
			
			String sql2 = "insert into author values(?,?,?,?);";
			PreparedStatement Adaps = AddAConn.prepareStatement(sql2);
			Adaps.setString(1, AuthorID);
			Adaps.setString(2, Name);
			Adaps.setInt(3, Age);
			Adaps.setString(4, Country);
			Adaps.executeUpdate();
			
			String sql3 = "insert into book values(?,?,?,?,?,?);";
			PreparedStatement adbps = AddAConn.prepareStatement(sql3);
			adbps.setString(1, book.getISBN());
			adbps.setString(2, book.getTitle());
			adbps.setString(3, AuthorID);
			adbps.setString(4, book.getPublisher());
			adbps.setString(5, book.getPublishDate());
			adbps.setFloat(6, book.getPrice());
			adbps.executeUpdate();
			adbps.close();			
			return SUCCESS;
		}catch (Exception e){
			Aret = ERROR;
		}
		return Aret;
	}	
}
