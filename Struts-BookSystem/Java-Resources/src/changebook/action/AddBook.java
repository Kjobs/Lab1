/**
 * @author KobsJieH
 * Add a book
 */

package changebook.action;

import book.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class AddBook extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	
	private String AuthorID = null;
	public static BookModel bookCache = new BookModel();
	
	private String ISBN = null;
	private String Title = null;
	private String Name = null;
	private String Publisher = null;
	private String PublishDate = null;
	private float Price = 0;
	
	public List<String> AuthorNameList = new ArrayList<String>();
	public List<String> BookTitleList = new ArrayList<String>();
	
	public String getISBN()
	{
		return ISBN;
	}
	
	public void setISBN(String ISBN)
	{
		this.ISBN = ISBN;
	}
	
	public String getTitle()
	{
		return Title;
	}
	
	public void setTitle(String Title)
	{
		this.Title = Title;
	}
	
	public String getName()
	{
		return Name;
	}
	
	public void setName(String Name)
	{
		this.Name = Name;
	}
	
	public String getPublisher()
	{
		return Publisher;
	}
	
	public void setPublisher(String Publisher)
	{
		this.Publisher = Publisher;
	}
	
	public String getPublishDate()
	{
		return PublishDate;
	}
	
	public void setPublishDate(String PublishDate)
	{
		this.PublishDate = PublishDate;
	}
	
	public float getPrice()
	{
		return Price;
	}
	
	public void setPrice(float Price)
	{
		this.Price = Price;
	}
	
	public String execute() throws Exception
	{
		String Aret = ERROR;
		Connection AddBConn = null;
		
		String driver = "com.mysql.jdbc.Driver";
		String username = MySQL_user;
		String password = MySQL_pass;
		String dbUrl = String.format("jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=true", MYSQL_HOST, MYSQL_PORT, MYSQL_DB);
		
		try {
		    Class.forName(driver).newInstance();
		    AddBConn = DriverManager.getConnection(dbUrl, username, password);
			
			String aISB = this.ISBN;
			String aTit = this.Title;
			String aNam = this.Name;
			String aPer = this.Publisher;
			String aPDa = this.PublishDate;
			float aPri = this.Price;
			if(aISB.equals("") || aTit.equals("") || aNam.equals("") || aPer.equals("") || aPDa.equals("") || aPri == 0)
			{
				return ERROR;
			}
			
			//判断添加图书是否已经存在，存在则添加失败
			String QTitleList = "select Title from book;";
			PreparedStatement QTLps = AddBConn.prepareStatement(QTitleList);
			ResultSet QTLrs = QTLps.executeQuery();
			while(QTLrs.next())
				BookTitleList.add(QTLrs.getString(1));
			int flagTitleExist = 0;
			for(int i=0;i<BookTitleList.size();i++)
				if(aTit.equals(BookTitleList.get(i)))
					flagTitleExist = 1;
			if(flagTitleExist == 1)
			{
				return "titleExist";
			}
			
			String QNameList = "select Name from author;";
			PreparedStatement QNLps = AddBConn.prepareStatement(QNameList);
			ResultSet QNLrs = QNLps.executeQuery();			
			while(QNLrs.next())
			{
				AuthorNameList.add(QNLrs.getString(1));
			}			
			//判断作者是否存在，不存在即添加作者信息
			int flagNameExist = 0;
			for(int j=0;j<AuthorNameList.size();j++)
				if(aNam.equals(AuthorNameList.get(j)))
					flagNameExist = 1;	
			if(flagNameExist == 0)
			{
				bookCache.setISBN(aISB);
				bookCache.setTitle(aTit);
				//bookCache.setAuthorID(null);
				bookCache.setPublisher(aPer);
				bookCache.setPublishDate(aPDa);
				bookCache.setPrice(aPri);
				return "addAuthor";
			}
			
			//查找作者姓名对应的AuthorID
			String QauthorID = "select AuthorID from author where Name = ?;";
			PreparedStatement Qidps = AddBConn.prepareStatement(QauthorID);
			Qidps.setString(1, aNam);
			ResultSet Qidrs = Qidps.executeQuery();
			while(Qidrs.next())
				AuthorID = Qidrs.getString(1);
			
			String sql2 = "insert into book values(?,?,?,?,?,?);";
			PreparedStatement Addps = AddBConn.prepareStatement(sql2);
			Addps.setString(1, aISB);
			Addps.setString(2, aTit);
			Addps.setString(3, AuthorID);
			Addps.setString(4, aPer);
			Addps.setString(5, aPDa);
			Addps.setFloat(6, aPri);
			Addps.executeUpdate();
			Addps.close();			
			return SUCCESS;
		}catch (Exception e){
			Aret = ERROR;
		}
		return Aret;
	}
	
}
