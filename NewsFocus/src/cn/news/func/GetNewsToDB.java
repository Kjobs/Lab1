package cn.news.func;

import java.util.TimerTask;

import cn.news.action.CarNewsCrawl;
import cn.news.action.EduNewsCrawl;
import cn.news.action.EttmNewsCrawl;
import cn.news.action.FinanceNewsCrawl;
import cn.news.action.GameNewsCrawl;
import cn.news.action.HealthNewsCrawl;
import cn.news.action.MovieNewsCrawl;
import cn.news.action.NewsCrawl;
import cn.news.action.SportsNewsCrawl;
import cn.news.action.TechNewsCrawl;
import cn.news.action.VideoNewsCrawl;
import cn.news.action.WarNewsCrawl;

public class GetNewsToDB {
	/**
	 * function get news from the websites.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("execute");
		CarNewsCrawl.RunCrawl();
//		EduNewsCrawl.RunCrawl();
		EttmNewsCrawl.RunCrawl();
		FinanceNewsCrawl.RunCrawl();
		GameNewsCrawl.RunCrawl();
		HealthNewsCrawl.RunCrawl();
		MovieNewsCrawl.RunCrawl();
		NewsCrawl.RunCrawl();
		SportsNewsCrawl.RunCrawl();
		TechNewsCrawl.RunCrawl();
		VideoNewsCrawl.RunCrawl();
		WarNewsCrawl.RunCrawl();
		System.out.println("Done!");
//		Timer timer = new Timer(false); //参数false保证,主进程结束后，timer任务不会被取消，依然可以定期循环执行。 
//		CollectionTask task = new CollectionTask(); 
//		Calendar cal = Calendar.getInstance(); 
//		cal.set(2016, 11, 24, 17, 18, 0);  
//		Date firstTime = cal.getTime(); 
//		int rate = 24 * 60 * 60 * 1000; // 每24小时执行一次 
//		timer.scheduleAtFixedRate(task, firstTime, rate);
	}
	 
	public static class CollectionTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("execute");
			try {
				CarNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				EduNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				EttmNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				FinanceNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				GameNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				HealthNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				MovieNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				NewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				SportsNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				TechNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				VideoNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				WarNewsCrawl.RunCrawl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Done!");
		}		
	}
}