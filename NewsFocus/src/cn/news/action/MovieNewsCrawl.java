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

public class MovieNewsCrawl extends ActionSupport {

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
	
	public List<NewsModel> TvNewsList = new ArrayList<NewsModel>();
	public List<NewsModel> MovieTopList = new ArrayList<NewsModel>();
	
	/**
	 * Get news of Movie from some wensites.
	 * @return result
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://ent.sina.com.cn/tv/";
		List<NewsModel> TvNews = new ArrayList<NewsModel>();
		List<NewsModel> TvNews1 = new ArrayList<NewsModel>();
		List<NewsModel> MovieTop = new ArrayList<NewsModel>();
		try {
			String MovieHtml = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(MovieHtml);
			
			//获取新浪电视热点新闻	
			Elements item1 = doc.select("div.box02");
			TvNews = GetNewsList.NewsList(item1);
			Elements item2 = doc.select("div.box03");
			TvNews1 = GetNewsList.NewsList(item2);
			TvNews.addAll(TvNews1);
			for(NewsModel news1 : TvNews) {
				news1.setType("tvnews_main");
//				System.out.println(news1.getTitle());
//				System.out.println(news1.getDate());
				SQLConnection.NewsAdd(news1, "movie_news");
			}
			
			//获取新浪影视热点排行
			Elements item3 = doc.select("ul.box23-p1");
			MovieTop = GetNewsList.NewsList(item3);
			
			//将获得新闻列表存入数据库
			for(NewsModel news2 : MovieTop) {
				news2.setType("movie_top");
//				System.out.println(news2.getTitle());
//				System.out.println(news2.getDate());
				SQLConnection.NewsAdd(news2, "movie_news");
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * Action--display news of movie.
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
			
			TvNewsList = SQLConnection.NewsQuery("movie_news", "tvnews_main", OrderType);
			MovieTopList = SQLConnection.NewsQuery("movie_news", "movie_top", OrderType);
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