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

public class WarNewsCrawl extends ActionSupport {

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
	
	public List<NewsModel> WarNewsList = new ArrayList<NewsModel>();
	public List<NewsModel> ChinaMilList = new ArrayList<NewsModel>();
	
	/**
	 * Get news of war from some websites.
	 * @return result
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://mil.news.sina.com.cn/";
		List<NewsModel> WarMain = new ArrayList<NewsModel>();
		List<NewsModel> ChinaMil = new ArrayList<NewsModel>();
		try {
			String WarHtml = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(WarHtml);
			//Huanqiu mil htmldoc
			String HQurl = "http://mil.huanqiu.com/";
			String HQHtml = GetHtml.getHtmlResourceByUrl(HQurl, "UTF-8");
			Document HQdoc = Jsoup.parse(HQHtml);
			//ifeng mil htmldoc
			String IFurl = "http://news.ifeng.com/mil/";
			String IFHtml = GetHtml.getHtmlResourceByUrl(IFurl, "utf-8");
			Document IFdoc = Jsoup.parse(IFHtml);
			
			//过滤掉不需要的链接
			doc.select("div.blk1").remove();
			
			//获取新浪热点军事新闻
			Elements item = doc.select("div.col360");
			WarMain = GetNewsList.NewsList(item);
			//获取环球军事新闻
			Elements HQitem1 = HQdoc.select("div.newsFir");
			WarMain.addAll(GetNewsList.NewsList(HQitem1));
			
			//将获得新闻列表存入数据库
			for(NewsModel news1 : WarMain) {
				news1.setType("war_main");
//				System.out.println(news1.getTitle());
//				System.out.println(news1.getDate());
				SQLConnection.NewsAdd(news1, "war_news");
			}
			
			//获取凤凰网中国军情新闻
			Elements IFitem1 = IFdoc.select("ul.list01").select(".mt14");
			ChinaMil = GetNewsList.NewsList(IFitem1);
			
			//将获得新闻列表存入数据库
			for(NewsModel news2 : ChinaMil) {
				news2.setType("china_mil");
				SQLConnection.NewsAdd(news2, "war_news");
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * Action--display news of War.
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
			
			WarNewsList = SQLConnection.NewsQuery("war_news", "war_main", OrderType);
			ChinaMilList = SQLConnection.NewsQuery("war_news", "china_mil", OrderType);
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