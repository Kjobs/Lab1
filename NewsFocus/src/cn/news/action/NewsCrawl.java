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

public class NewsCrawl extends ActionSupport {

	/**
	 * VersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private String OrderType = "news_date";
	
	public List<NewsModel> MainNewsList = new ArrayList<NewsModel>();
	
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
	 * Get news of Main from some websites.
	 * @return result
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://news.sina.com.cn/";
		List<NewsModel> MainNews = new ArrayList<NewsModel>();
		List<NewsModel> NEMainNews = new ArrayList<NewsModel>();
		try {
			String htmlStr = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(htmlStr);
			
			//Sina main news.
			Elements elements = doc.select("div#syncad_1");
			MainNews = GetNewsList.NewsList(elements);
			
			//NetEase main news.
			String url1 = "http://news.163.com/";
			String htmlStr1 = GetHtml.getHtmlResourceByUrl(url1, "gbk");
			Document doc1 = Jsoup.parse(htmlStr1);
			Elements elements1 = doc1.select("div.mod_top_news2");
			NEMainNews = GetNewsList.NewsList(elements1);
			MainNews.addAll(NEMainNews);
			
			//Baidu main news.
			String url2 = "Http://news.baidu.com/";
			String htmlStr2 = GetHtml.getHtmlResourceByUrl(url2, "gb2312");
			Document doc2 = Jsoup.parse(htmlStr2);
			Elements elements2 = doc2.select("div.hotnews").select("strong");
			MainNews.addAll(GetNewsList.NewsList(elements2));
			
			//Huanqiu china news
			String url3 = "http://china.huanqiu.com/";
			String htmlStr3 = GetHtml.getHtmlResourceByUrl(url3,"utf-8");
			Document doc3 = Jsoup.parse(htmlStr3);
			Elements elements3 = doc3.select("ul.listBoxT14");
			MainNews.addAll(GetNewsList.NewsList(elements3));
			
			for(int i=0; i<MainNews.size(); i++) {
				MainNews.get(i).setType("mainlist");
//				System.out.println(MainNews.get(i).getTitle());
//				System.out.println(MainNews.get(i).getDate());
				SQLConnection.NewsAdd(MainNews.get(i), "mainnews");				
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Action--display main news of these website.
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
			
			MainNewsList = SQLConnection.NewsQuery("mainnews", "mainlist", OrderType);
			
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