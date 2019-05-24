package cn.news.func;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.news.model.NewsModel;

public class GetNewsList {
	/**
	 * Get list of news by the elements.
	 * @author Kobs
	 * @param elements
	 * @return News
	 */
	public static List<NewsModel> NewsList(Elements elements) {
		List<NewsModel> NewsList = new ArrayList<NewsModel>();
		for (Element element : elements) {
			Elements news = element.select("a[href]");
			for (Element oneObj : news) {
				NewsModel NewsObj = new NewsModel();
				//set news url
				String url = oneObj.attr("href");
				NewsObj.setLink(url);
				//set news title
				String title = GetTitle.NewsTitle(url);
				if(title.equals("error"))
					NewsObj.setTitle(oneObj.text());
				else
					NewsObj.setTitle(title);
				//set news source
				String source = GetSource.SourceFrom(url);
				NewsObj.setSource(source);
				//set news date
				String date = GetNewsDate.NewsDate(url);
				NewsObj.setDate(date);
				NewsList.add(NewsObj);
			}
		}
		return NewsList;
	}
}