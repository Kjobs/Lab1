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

public class CarNewsCrawl extends ActionSupport {

	/**
	 * VersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private String OrderType = "news_date";
	
	public List<NewsModel> CarNewsList = new ArrayList<NewsModel>();
	
	
	//定义一个成员变量
	private String Guser = LoginAction.Guser;
	     
	//提供get/set方法
	public String getGuser() {
	    return Guser;
	}
	public void setGuser(String Guser) {
	    this.Guser = Guser;
	}
	
	/**
	 * Get news of car from some websites.
	 * @return result
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://auto.sina.com.cn/";
		List<NewsModel> CarNews = new ArrayList<NewsModel>();
		List<NewsModel> NECarNews = new ArrayList<NewsModel>();
		try {
			String htmlStr = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(htmlStr);
			
			//获取新浪汽车新闻列表
			Elements elements = doc.select("ul.J-news-moudle").select("div.info").select("h4.title");
			CarNews = GetNewsList.NewsList(elements);
			
			//NetEase car main news.
			String url1 = "http://auto.163.com/";
			String htmlStr1 = GetHtml.getHtmlResourceByUrl(url1, "gbk");
			Document doc1 = Jsoup.parse(htmlStr1);
			Elements item1 = doc1.select("div#s-p2").select("div.m").select(".m-newcar").select("ul.list-news");
			NECarNews = GetNewsList.NewsList(item1);
			CarNews.addAll(NECarNews);
			
			//将获得新闻列表存入数据库
			for(NewsModel news : NECarNews) {
				news.setType("car_main");
//				System.out.println(news.getTitle());
//				System.out.println(news.getDate());
				SQLConnection.NewsAdd(news, "car_news");
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Action--display news of CarNews.
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
			
			CarNewsList = SQLConnection.NewsQuery("car_news", "car_main", OrderType);
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