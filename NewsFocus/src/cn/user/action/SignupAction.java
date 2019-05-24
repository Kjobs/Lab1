package cn.user.action;

import com.opensymphony.xwork2.ActionSupport;

import cn.news.consql.SQLConnection;

public class SignupAction extends ActionSupport {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	//拿到表单上的数据  
	private String t_UserName;  
	private String t_UserPass;  
	     
	public String gett_UserName() {  
		return t_UserName;  
	}  
	   
	public void sett_UserName(String t_UserName) {  
		this.t_UserName= t_UserName;  
	}  
	   
	public String gett_UserPass() {  
		return t_UserPass;  
	}  
	   
	public void sett_UserPass(String t_UserPass) {  
		this.t_UserPass= t_UserPass;  
	}  
	   
	public String execute() throws Exception{
		String info = "";
		String result = ERROR;
		info = SQLConnection.UserSignup(t_UserName, t_UserPass);
		if(info == "UserHasExist") {
			result = ERROR;
			return result;
		}		
		result = SUCCESS;
		return result;
	}	   
} 