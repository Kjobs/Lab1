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

public class VideoNewsCrawl extends ActionSupport {

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
		
	public List<NewsModel> VideoList = new ArrayList<NewsModel>();
	public List<NewsModel> EntList = new ArrayList<NewsModel>();
	
	/**
	 * Get news of video from some websites.
	 * @return result
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://ent.sina.com.cn/video/";
		List<NewsModel> VideoHot = new ArrayList<NewsModel>();
		List<NewsModel> VideoHign = new ArrayList<NewsModel>();
		try {
			String TechHtml = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(TechHtml);
			//Tencent video htmldoc
			String TenVurl = "https://v.qq.com/";
			String TenVHtml = GetHtml.getHtmlResourceByUrl(TenVurl, "UTF-8");
			Document TenVdoc = Jsoup.parse(TenVHtml);
			
			//过滤掉不需要的链接
			doc.select("span.rank-num").remove();
			
			//获取新浪热点视频新闻
			Elements item1 = doc.select("h3.title");
			VideoHot = GetNewsList.NewsList(item1);
			//获取腾讯热点视频
			Elements TenVitem = TenVdoc.select("div.slider_nav");
			VideoHot.addAll(GetNewsList.NewsList(TenVitem));
			
			//将获得新闻列表存入数据库
			for(NewsModel news1 : VideoHot) {
				news1.setType("video_hot");
//				System.out.println(news1.getTitle());
//				System.out.println(news1.getDate());
				SQLConnection.NewsAdd(news1, "video_news");
			}
			
			//获取新浪视频排行榜
			Elements item2 = doc.select("div.bd");
			VideoHign = GetNewsList.NewsList(item2);
			
			//将获得新闻列表存入数据库
			for(NewsModel news2 : VideoHign) {
				news2.setType("video_hign");
				SQLConnection.NewsAdd(news2, "video_news");
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * Action--display news of video.
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
			
			 EntList = SQLConnection.NewsQuery("video_news", "video_hot", OrderType);
			 VideoList = SQLConnection.NewsQuery("video_news", "video_hign", OrderType);
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