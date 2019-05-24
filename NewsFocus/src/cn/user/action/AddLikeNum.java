package cn.user.action;


import java.sql.Connection;
import java.sql.PreparedStatement;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import cn.news.consql.*;

public class AddLikeNum extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	public String table = "";
	public String title = "";
	public int publicity = 0;
	
	/**
	 * Action--update likely number. 
	 */
	public String execute() throws Exception {
		String result = ERROR;
		Connection Conn = SQLDemo.getConnection("newsfocus");
		
		try {
			String QueryStr = "update "+this.table+" set publicity = ? where title = ?";
			PreparedStatement LnumPS = Conn.prepareStatement(QueryStr);
			System.out.println(this.table);
			System.out.println(this.title);
			System.out.println(this.publicity);
			LnumPS.setInt(1, this.publicity);
			LnumPS.setString(2, this.title);
			LnumPS.executeQuery();
			LnumPS.close();
			return Action.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			result = ERROR;
		}
		return result;
	}
}