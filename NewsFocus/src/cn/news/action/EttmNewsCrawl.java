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

public class EttmNewsCrawl extends ActionSupport {

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
	
	public List<NewsModel> EntToutiaoList = new ArrayList<NewsModel>();
	public List<NewsModel> EntYaowenList = new ArrayList<NewsModel>();
	
	/**
	 * Get news of entertainment from some websites.
	 * @return result
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://ent.sina.com.cn/";
		//Sina entertainment news of toutiao.
		List<NewsModel> EntToutiaoList = new ArrayList<NewsModel>();
		//Sina entertainment news of yaowen.
		List<NewsModel> EntYaowenList = new ArrayList<NewsModel>();
		try {
			String TechHtml = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(TechHtml);
			//NetEase ent Toutiao.
			String url1 = "http://ent.163.com/";
			String TechHtml1 = GetHtml.getHtmlResourceByUrl(url1, "gbk");
			Document doc1 = Jsoup.parse(TechHtml1);
			
			//过滤不需要的新闻
			doc.select("a:contains(论坛实录)").remove();
			doc.select("a:contains(直播回顾)").remove();
			doc.select("a:contains(微博-[)").remove();
			doc.select("a:contains(联播-[)").remove();
			//NE ent filter
			doc1.select("a[class='pic']").remove();
			
			//获取新浪娱乐头条新闻列表
			Elements item1 = doc.select("div#mainText").select("div#toutiao");
			EntToutiaoList = GetNewsList.NewsList(item1);
			//网易娱乐头条新闻列表
			Elements NEitem1 = doc1.select("div.top_news");
			EntToutiaoList.addAll(GetNewsList.NewsList(NEitem1));
			
			//将获得新闻列表存入数据库
			for(NewsModel news1 : EntToutiaoList) {
				news1.setType("ent_toutiao");
//				System.out.println(news1.getTitle());
//				System.out.println(news1.getDate());
				SQLConnection.NewsAdd(news1, "ent_news");
			}
			
			//获取新浪娱乐要闻列表
			Elements item2 = doc.select("div#mainText").select("div.yaowen");
			EntYaowenList = GetNewsList.NewsList(item2);
			
			//将获得新闻列表存入数据库
			for(NewsModel news2 : EntYaowenList) {
				news2.setType("ent_yaowen");
				SQLConnection.NewsAdd(news2, "ent_news");
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * Action--display news of entertainment. 
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
			
			EntToutiaoList = SQLConnection.NewsQuery("ent_news", "ent_toutiao", OrderType);
			EntYaowenList = SQLConnection.NewsQuery("ent_news", "ent_yaowen", OrderType);
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