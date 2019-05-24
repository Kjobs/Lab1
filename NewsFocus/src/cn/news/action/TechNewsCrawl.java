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

public class TechNewsCrawl extends ActionSupport {

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
	
	public List<NewsModel> TechMainNews = new ArrayList<NewsModel>();
	public List<NewsModel> TechNewsHign = new ArrayList<NewsModel>();
	public List<NewsModel> TechHComNews = new ArrayList<NewsModel>();
	
	/**
	 * Get news of technology from some websites.
	 * @return result
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://tech.sina.com.cn/";
		List<NewsModel> TechMain = new ArrayList<NewsModel>();
		List<NewsModel> TNewsHign = new ArrayList<NewsModel>();
		List<NewsModel> TComsHign = new ArrayList<NewsModel>();
		try {
			String TechHtml = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(TechHtml);
			
			//获取新浪科技要闻
			Elements item1 = doc.select("div.tech_nav").select("div.nr").select("ul");
			TechMain = GetNewsList.NewsList(item1);
			for(NewsModel news1 : TechMain) {
				news1.setType("tech_main");
//				System.out.println(news1.getTitle());
//				System.out.println(news1.getDate());
				SQLConnection.NewsAdd(news1, "tech_news");
			}
			
			//获取新浪新闻排行
			Elements item2 = doc.select("div.tech_main").select("div.wr").select("div.newsRankCon");
			Elements elements1 = item2.select("ul#newsRankTabC1");
			TNewsHign = GetNewsList.NewsList(elements1);
			for(NewsModel news2 : TNewsHign) {
				news2.setType("tech_hign");
				SQLConnection.NewsAdd(news2, "tech_news");
			}
			
			//获取新浪评论排行靠前的新闻
			Elements elements2 = item2.select("ul#newsRankTabC2");
			TComsHign = GetNewsList.NewsList(elements2);
			for(NewsModel news3 : TComsHign) {
				news3.setType("tech_comment_hign");
				SQLConnection.NewsAdd(news3, "tech_news");
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * Action--display news of technology.
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
			
			TechMainNews = SQLConnection.NewsQuery("tech_news", "tech_main", OrderType);
			TechNewsHign = SQLConnection.NewsQuery("tech_news", "tech_hign", OrderType);
			TechHComNews = SQLConnection.NewsQuery("tech_news", "tech_comment_hign", OrderType);
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