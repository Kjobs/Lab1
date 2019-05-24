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

public class SportsNewsCrawl extends ActionSupport {

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
	
	public List<NewsModel> SportsNewsList = new ArrayList<NewsModel>();
	public List<NewsModel> FootballNews = new ArrayList<NewsModel>();
	public List<NewsModel> BasketballNews = new ArrayList<NewsModel>();
	
	/**
	 * Get news of sports from some websites.
	 * @return result
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://sports.sina.com.cn/";
		List<NewsModel> SportsNews = new ArrayList<NewsModel>();
		List<NewsModel> FootballList = new ArrayList<NewsModel>();
		List<NewsModel> Basketball = new ArrayList<NewsModel>();
		try {
			String SportsHtml = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(SportsHtml);
			//NetEase sports htmldoc
			String NEurl = "http://sports.163.com/";
			String NESportsHtml = GetHtml.getHtmlResourceByUrl(NEurl, "gbk");
			Document NEdoc = Jsoup.parse(NESportsHtml);
			//ifeng sports htmldoc
			String IFurl = "http://sports.ifeng.com/";
			String IFSportsHtml = GetHtml.getHtmlResourceByUrl(IFurl, "utf-8");
			Document IFdoc = Jsoup.parse(IFSportsHtml);
			//cctv basketball htmldoc
			String HQurl = "http://sports.huanqiu.com/basketball/";
			String HQhtmlStr = GetHtml.getHtmlResourceByUrl(HQurl,"utf-8");			
			Document HQdoc = Jsoup.parse(HQhtmlStr);
			
			//过滤掉不需要的链接
			doc.select("div#phdnews_p_slide").remove();
			doc.select("a:contains(视频)").remove();
			NEdoc.select("li.first").remove();
			HQdoc.select("h2 p").remove();
			
			//获取新浪体育新闻
			Elements  item = doc.select("div.phdnews_hdline");
			SportsNews = GetNewsList.NewsList(item);
			//获取网易体育热点
			Elements NEitem1 = NEdoc.select("div.topnews_news_r").select(".fl");
			SportsNews.addAll(GetNewsList.NewsList(NEitem1));
			
			//将获得新闻列表存入数据库
			for(NewsModel news1 : SportsNews) {
				news1.setType("sports_main");
//				System.out.println(news1.getTitle());
//				System.out.println(news1.getDate());
				SQLConnection.NewsAdd(news1, "sports_news");
			}
			
			//获取凤凰网足球新闻
			Elements IFitem1 = IFdoc.select("div.box_05").select(".mt03").select("div.list06");
			FootballList = GetNewsList.NewsList(IFitem1);			
			//将获得新闻列表存入数据库
			for(NewsModel news2 : FootballList) {
				news2.setType("football");
				SQLConnection.NewsAdd(news2, "sports_news");
			}
			
			//获取体坛网篮球新闻
			Elements item3 = HQdoc.select("li.dotLightBot").select("h2");
			Basketball = GetNewsList.NewsList(item3);
			//将获得新闻列表存入数据库
			for(NewsModel news3 : Basketball) {
				news3.setType("basketball");
//				System.out.println(news3.getTitle());
//				System.out.println(news3.getDate());
				SQLConnection.NewsAdd(news3, "sports_news");
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * Action--display news of sports.
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
			
			SportsNewsList = SQLConnection.NewsQuery("sports_news", "sports_main", OrderType);
			FootballNews = SQLConnection.NewsQuery("sports_news", "football", OrderType);
			BasketballNews = SQLConnection.NewsQuery("sports_news", "basketball", OrderType);
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