package cn.news.func;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetTitle {
	
	public static String NewsTitle(String url) {
		String result = "";
		String CharSet = "";
		
		//judge charset by the url
		String Source = GetSource.SourceFrom(url);
		if(Source.equals("163.com"))
			CharSet = "gb2312";
		else
			CharSet = "UTF-8";
		try {
			String htmlStr = GetHtml.getHtmlResourceByUrl(url, CharSet);
			Document doc = Jsoup.parse(htmlStr);
			
			//some filter
			doc.select("a[href]").remove();
			
			//sina
			String bodyTitle = doc.select("h1#artibodyTitle").text();
			if(!bodyTitle.equals("")) {
				result = bodyTitle;
				return result;
			}
			//sina
			String mainTitle = doc.select("h1#main_title").text();
			if(!mainTitle.equals("")) {
				result = mainTitle;
				return result;
			}
			//netease
			String gameTitle = doc.select("h1.article-h1").text();
			if(!gameTitle.equals("")) {
				result = gameTitle;
				return gameTitle;
			}
			//mostly h1
			String h1Title = doc.select("h1").text();
			if(!h1Title.equals("")) {
				result = h1Title;
				return result;
			}
			else
				return "error";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}