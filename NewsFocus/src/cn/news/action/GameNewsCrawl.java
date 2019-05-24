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

public class GameNewsCrawl extends ActionSupport {

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
	
	public List<NewsModel> GameYaowenList = new ArrayList<NewsModel>();
	public List<NewsModel> HotGamesList = new ArrayList<NewsModel>();
	
	/**
	 * Get news of game from some websites.
	 * @return result
	 * @throws Exception
	 */
	public static String RunCrawl() throws Exception {
		String result = ERROR;
		
		String baseurl = "http://games.sina.com.cn/";
		List<NewsModel> GameYaowen = new ArrayList<NewsModel>();
		List<NewsModel> HotGames = new ArrayList<NewsModel>();
		try {
			String GameHtml = GetHtml.getHtmlResourceByUrl(baseurl, "UTF-8");
			Document doc = Jsoup.parse(GameHtml);
			//NetEase game htmldoc
			String NEurl = "http://play.163.com/";
			String NEGameHtml = GetHtml.getHtmlResourceByUrl(NEurl, "gbk");
			Document NEdoc = Jsoup.parse(NEGameHtml);
			
			//过滤规则
			doc.select("a[href~=.+(e\\.games|ka).+]").remove();
			
			//获取新浪游戏要闻
			Elements item1 = doc.select("div.Headline").select("h2.nowrap");
			GameYaowen = GetNewsList.NewsList(item1);
			//获取网易游戏要闻
			Elements NEitem1 = NEdoc.select("div.m-collist");
			GameYaowen.addAll(GetNewsList.NewsList(NEitem1));
			
			//将获得新闻列表存入数据库
			for(NewsModel news1 : GameYaowen) {
				news1.setType("game_yaowen");
//				System.out.println(news1.getTitle());
//				System.out.println(news1.getSource());
				SQLConnection.NewsAdd(news1, "game_news");
			}
			System.out.println();
			
			//获取新浪游戏热点
			Elements item2 = doc.select("div.hotlist").select("div.hotlist-tab-wrap");
			HotGames = GetNewsList.NewsList(item2);
			for(NewsModel news2 : HotGames) {
				news2.setType("hot_game");
				SQLConnection.NewsAdd(news2, "game_news");
			}
			
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * Action--display news of game.
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
			
			GameYaowenList = SQLConnection.NewsQuery("game_news", "game_yaowen", OrderType);
			HotGamesList = SQLConnection.NewsQuery("game_news", "hot_game", OrderType);
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