package cn.user.action;

import com.opensymphony.xwork2.ActionSupport;

import cn.news.consql.SQLConnection;
import cn.news.model.UserModel;

public class LoginAction extends ActionSupport {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	public static String Guser = new String();
	//拿到表单上的数据  
	private String username;  
	private String password;  
	     
	public String getUsername() {  
		return username;  
	}  
	   
	public void setUsername(String username) {  
		this.username= username;  
	}  
	   
	public String getPassword() {  
		return password;  
	}  
	   
	public void setPassword(String password) {  
		this.password= password;  
	}  
	   
	public String execute() throws Exception{
		UserModel User = new UserModel();
		String result = ERROR;
		User = SQLConnection.UserLogin(username);
		if(User == null) {
			result = ERROR;
			return result;
		}		
		String usr = User.getUsername();
		String pass = User.getPassword();
		Guser = usr;
		if(usr.equals(username) && pass.equals(password)) {
			result = SUCCESS;
		}
		return result;
	}	   
} 