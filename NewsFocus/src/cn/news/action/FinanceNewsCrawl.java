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

public class FinanceNewsCrawl extends ActionSupport {

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
	
	public List<NewsModel> FinanceNewsList = new ArrayList<NewsModel>();
	
	/**
	 * Get news of finance from some websites.
	 * @return
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://finance.sina.com.cn/";
		List<NewsModel> FinanceNews = new ArrayList<NewsModel>();
		List<NewsModel> MlistFinance = new ArrayList<NewsModel>();
		try {
			String TechHtml = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(TechHtml);
//			//NetEase finance htmldoc.
//			String NEurl = "http://money.163.com/";
//			String NETechHtml = GetHtml.getHtmlResourceByUrl(NEurl, "gbk");
//			Document NEdoc = Jsoup.parse(NETechHtml);
			
			//过滤掉一些不需要的链接
			doc.select("div.fin_bd_w").remove();
			doc.select("div.m-title").select("div.m-title-deep").select("div.udv-clearfix").remove();
			doc.select("div.m-p1-mb2-list").select("div.m-list-container").remove();
			doc.select("a[href~=.+(guba|sax|fund|licaishi).*]").remove();
			
			//获取新浪财经新闻列表
			Elements item = doc.select("h3");
			FinanceNews = GetNewsList.NewsList(item);
			//获取另外一些要闻
			Elements item1 = doc.select("div.m-p1-mb1-list").select(".m-list-container");
			MlistFinance = GetNewsList.NewsList(item1);
			FinanceNews.addAll(MlistFinance);
			
//			//网易财经热点新闻
//			Elements item2 = NEdoc.select("ul.topnews_list").select(".mb20");
//			FinanceNews.addAll(GetNewsList.NewsList(item2));
			
			//将获得新闻列表存入数据库
			for(NewsModel news : FinanceNews) {
				news.setType("finance_main");
//				System.out.println(news.getTitle());
//				System.out.println(news.getDate());
				SQLConnection.NewsAdd(news, "finance_news");
			}			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * Action--display news of finance.
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
			FinanceNewsList = SQLConnection.NewsQuery("finance_news", "finance_main", OrderType);
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