package cn.news.consql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.news.model.NewsModel;
import cn.news.model.UserModel;

/**
 * @author Kobs
 * class for database connection;
 */
public class SQLConnection {
	/**
	 * Add a News object.
	 * @param NewsObj
	 * @return result
	 * @throws Exception
	 */
	public static String NewsAdd(NewsModel NewsObj, String NewsType) throws Exception {
		String result = "";
		Connection Conn1 = SQLDemo.getConnection("newsfocus");
		
		try {
			//Query mysql that the news we will insert added or not.
			String QueryExistStr = "select title from "+NewsType+" where title = ? limit 1";
			PreparedStatement QueryExistPS = Conn1.prepareStatement(QueryExistStr);
			QueryExistPS.setString(1, NewsObj.getTitle());
			ResultSet QErs = QueryExistPS.executeQuery();
			//if the news exist, stop insert and return.
			if(QErs.next()) {
				result = "error";
				return result;
			}			
			else {
				//MySQL insert news sentence
				String InsertStr = "insert into "+NewsType+"(title,news_url,news_date,source,type,publicity) value(?,?,?,?,?,0)";
				PreparedStatement AddPS = Conn1.prepareStatement(InsertStr);
				AddPS.setString(1, NewsObj.getTitle());
				AddPS.setString(2, NewsObj.getLink());
				AddPS.setString(3, NewsObj.getDate());
				AddPS.setString(4, NewsObj.getSource());
				AddPS.setString(5, NewsObj.getType());
				AddPS.executeUpdate();
				AddPS.close();			
				//Add successfully, return success.
				result = "success";
			}
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		} finally {
			if(Conn1 != null)
				try {
					Conn1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return result;
	}
	
	/**
	 * Query NewsList.
	 * @param NewsType
	 * @return NewsList
	 * @throws Exception
	 */
	public static List<NewsModel> NewsQuery(String NewsType, String ListType, String OrderType) throws Exception {
		List<NewsModel> NewsList = new ArrayList<NewsModel>();
		Connection Conn2 = SQLDemo.getConnection("newsfocus");
		try {
			//MySQL query sentence
			String QueryStr = "select * from "+NewsType+" where type = ? order by "+OrderType+" desc limit 30";
			PreparedStatement QueryPS = Conn2.prepareStatement(QueryStr);
			QueryPS.setString(1, ListType);
			ResultSet QueryRS = QueryPS.executeQuery();
			
			while(QueryRS.next()) {
				NewsModel news = new NewsModel();
				news.setTitle(QueryRS.getString(2));
				news.setLink(QueryRS.getString(3));
				news.setDate(QueryRS.getString(4));
				news.setSource(QueryRS.getString(5));
				news.setType(QueryRS.getString(6));
				news.setLikeNum(QueryRS.getInt(7));
				NewsList.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(Conn2 != null) {
				try {
					Conn2.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return NewsList;
	}
	
	public static String UserSignup(String username, String password) throws Exception {
		String result = "";
		Connection Conn3 = SQLDemo.getConnection("news_user");
		try {
			String QueryExistStr = "select username from user where username = ?";
			PreparedStatement QueryExistPS = Conn3.prepareStatement(QueryExistStr);
			QueryExistPS.setString(1, username);
			ResultSet QErs = QueryExistPS.executeQuery();
			if(QErs.next()) {
				result = "UserHasExist";
				return result;
			}
			else {
				String AddStr = "Insert into user (username,password) value(?,?)";
				PreparedStatement AddPS = Conn3.prepareStatement(AddStr);
				AddPS.setString(1, username);
				AddPS.setString(2, password);
				AddPS.executeQuery();
				AddPS.close();
				return "success";
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(Conn3 != null) {
				try {
					Conn3.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "success";
	}
	
	public static UserModel UserLogin(String username) throws Exception {
		UserModel user = new UserModel();
		Connection Conn4 = SQLDemo.getConnection("news_user");
		try {
			String QueryStr = "select * from user where username=?";
			PreparedStatement LogPS = Conn4.prepareStatement(QueryStr);
			LogPS.setString(1, username);
			ResultSet LogRS = LogPS.executeQuery();
			while(LogRS.next()) {
				user.setUsername(LogRS.getString(1));
				user.setPassword(LogRS.getString(2));				
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(Conn4 != null) {
				try {
					Conn4.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}
}