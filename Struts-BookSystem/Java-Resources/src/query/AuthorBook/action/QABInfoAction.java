/**
 * @author Kobs
 * Query information of author and book by click the title of book
 * Query Author & Book Information->QABInfoAction
 */
package query.AuthorBook.action;

import book.model.*;
import author.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class QABInfoAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	
	public String Title;
	private String AuthorID;
	
	public List<BookModel> book = new ArrayList<BookModel>();
	public List<AuthorModel> author = new ArrayList<AuthorModel>();
	
	public String execute() throws Exception
	{
		String ret = ERROR;
		Connection QueryABconn = null;
		
		String driver = "com.mysql.jdbc.Driver";
		String username = MySQL_user;
		String password = MySQL_pass;
		String dbUrl = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=true", MYSQL_HOST, MYSQL_PORT, MYSQL_DB);
    
		try {
		    Class.forName(driver).newInstance();
		    QueryABconn = DriverManager.getConnection(dbUrl, username, password);
			
			String sql1 = "select * from book where Title = ?;";
			PreparedStatement Qbookps = QueryABconn.prepareStatement(sql1);
			Qbookps.setString(1, this.Title);
			ResultSet Qbookrs = Qbookps.executeQuery();
			
			while(Qbookrs.next())
			{
				BookModel bookInfo = new BookModel();
				bookInfo.setISBN(Qbookrs.getString(1));
				bookInfo.setTitle(Qbookrs.getString(2));
				bookInfo.setAuthorID(Qbookrs.getString(3));
				bookInfo.setPublisher(Qbookrs.getString(4));
				bookInfo.setPublishDate(Qbookrs.getString(5));
				bookInfo.setPrice(Qbookrs.getFloat(6));
				AuthorID = bookInfo.getAuthorID();
				book.add(bookInfo);
			}
			
			String sql2 = "select * from author where AuthorID = ?;";
			PreparedStatement Qauthorps = QueryABconn.prepareStatement(sql2);
			Qauthorps.setString(1, AuthorID);
			ResultSet Qauthorrs = Qauthorps.executeQuery();
			
			while(Qauthorrs.next())
			{
				AuthorModel authorInfo = new AuthorModel();
				authorInfo.setAuthorID(Qauthorrs.getString(1));
				authorInfo.setName(Qauthorrs.getString(2));
				authorInfo.setAge(Qauthorrs.getInt(3));
				authorInfo.setCountry(Qauthorrs.getString(4));
				author.add(authorInfo);
			}
			return SUCCESS;
		}
		catch (Exception e){
			ret = ERROR;
		}
		finally{
			if(QueryABconn != null)
			{
				try{
					QueryABconn.close();
				}catch (Exception e){
				}
			}
		}
		return ret;
	}
}
