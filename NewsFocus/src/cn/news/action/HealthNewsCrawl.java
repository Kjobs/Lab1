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

public class HealthNewsCrawl extends ActionSupport {

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
	
	public List<NewsModel> HealthNewsList = new ArrayList<NewsModel>();
	
	/**
	 * Get news of health from some websites. 
	 * @return
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://health.sina.com.cn/";
		List<NewsModel> HealthNews = new ArrayList<NewsModel>();
		try {
			String HealthHtml = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(HealthHtml);
			//NetEase health htmldoc
			String NEurl = "http://jiankang.163.com/";
			String NEHealthHtml = GetHtml.getHtmlResourceByUrl(NEurl, "gbk");
			Document NEdoc = Jsoup.parse(NEHealthHtml);
			
			//过滤掉不需要的链接
			doc.select("h2").remove();
			doc.select("span.more").remove();
			doc.select("div.pic").remove();
			//NetEase health
			NEdoc.select("div.focus_ad").remove();
			
			//获取新浪健康热点新闻
			Elements item1 = doc.select("div.blk_01");
			HealthNews = GetNewsList.NewsList(item1);
			//获取网易健康热点新闻
			Elements NEitem1 = NEdoc.select("div.focus_wrap");
			HealthNews.addAll(GetNewsList.NewsList(NEitem1));
			
			//将获得新闻列表存入数据库
			for(NewsModel news1 : HealthNews) {
				news1.setType("health_main");
//				System.out.println(news1.getTitle());
//				System.out.println(news1.getSource());
				SQLConnection.NewsAdd(news1, "health_news");
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * Action--display news of health.
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
			
			HealthNewsList = SQLConnection.NewsQuery("health_news", "health_main", OrderType);
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