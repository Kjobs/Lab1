package cn.news.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.opensymphony.xwork2.ActionSupport;

import cn.news.consql.SQLConnection;
import cn.news.func.GetHtml;
import cn.news.func.GetNewsList;
import cn.news.model.NewsModel;
import cn.user.action.LoginAction;

public class EduNewsCrawl extends ActionSupport {

	/**
	 * versionUID.
	 */
	private static final long serialVersionUID = 1L;

	private String OrderType = "news_date";
	
	//定义一个成员变量
	private String Guser = LoginAction.Guser;
		     
	//提供get/set方法
	public String getGuser() {
		  return Guser;
	}
	public void setGuser(String Guser) {
		  this.Guser = Guser;
	}
	
	public List<NewsModel> EduMainNews = new ArrayList<NewsModel>();
	/**
	 * Get news of education from some websites.
	 * @return result
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://edu.sina.com.cn/";
		List<NewsModel> EduMain = new ArrayList<NewsModel>();
		try {
			String TechHtml = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(TechHtml);
			
			//获取新浪教育要闻
			doc.select("div.mainContent_r_title").remove();
			doc.select("div.mainContent_r_bottom").remove();
			Elements item = doc.select("div.mainContent_r");
			EduMain = GetNewsList.NewsList(item);
			
			//NetEase hot education news.
			String url1 = "http://edu.163.com/";
			String TechHtml1 = GetHtml.getHtmlResourceByUrl(url1, "gbk");
			Document doc1 = Jsoup.parse(TechHtml1);
			Elements item1 = doc1.select("ul.news_list");
			EduMain.addAll(GetNewsList.NewsList(item1));
			
			//将获得新闻列表存入数据库
			for(NewsModel news : EduMain) {
				news.setType("edu_main");
//				System.out.println(news.getTitle());
//				System.out.println(news.getDate());
				SQLConnection.NewsAdd(news, "edu_news");
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * Action--display news of education.
	 */
	public String execute() throws Exception {
		String result = ERROR;
		try {
			//get type for order
			if(OrderType.equals("标题"))
				OrderType = "title";
			else if(OrderType.equals("来源"))
				OrderType = "source";
			else
				OrderType = "news_date";
			
			EduMainNews = SQLConnection.NewsQuery("edu_news", "edu_main", OrderType);
			result = SUCCESS;
		} catch (SQLException e) {
			result = ERROR;
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * geter
	 * @return the ordertype
	 */
	public String getOrdertype() {
		return OrderType;
	}
	
	/**
	 * seter
	 * @param OrderType
	 */
	public void setOrderType(String OrderType) {
		this.OrderType = OrderType;
	}
}