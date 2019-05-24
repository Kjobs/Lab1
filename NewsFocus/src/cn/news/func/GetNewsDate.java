package cn.news.func;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetNewsDate {
	/**
	 * function to search date in the string.
	 * @param str
	 * @return DateResult
	 */
	public static String StrDate(String str) {
		String DateResult = "";
		//regex start
		String regexSina = "(\\d{4})[-|\\/|年|\\.|\\\\](0[1-9]|1[1-2])[-|\\/|月|\\.|\\\\](0[1-9]|[1-2][0-9]|3[0-1])";
		Pattern p1 = Pattern.compile(regexSina);
		Matcher m1 = p1.matcher(str);
		if(m1.find()) {
			DateResult = m1.group(0);
			for(int i=0;i<=m1.groupCount();i++) {
				//Get year number. 
				String DateYear1 = m1.group(1);
				//Get month number.
				String DateMonth1 = m1.group(2);
				//Get date number.
				String DateDay1 = m1.group(3);
				DateResult = DateYear1+'-'+DateMonth1+'-'+DateDay1;
			}
			return DateResult;
		}
		String regexNE163 = "(\\d{2})[\\/|-|年|\\.](\\d{3,4})[\\/]";
		Pattern p2 = Pattern.compile(regexNE163);
		Matcher m2 = p2.matcher(str);
		if(m2.find()) {
			DateResult = m2.group(0);
			//Get year number.
			String DateYear2 = "20"+m2.group(1);
			//Get month number.
			int num = m2.group(2).length()==4?2:1;
			String DateMonth2 = m2.group(2).substring(0, num);
			//Get date number.
			String DateDay2 = m2.group(2).substring(num);
			DateResult = DateYear2+'-'+DateMonth2+'-'+DateDay2;
		}
		else
			return null;
		return DateResult;
	}
	
	/**
	 * To know when the news created.
	 * @author Kobs
	 * @param url
	 * @return date
	 */
	public static String NewsDate(String url) {
		String result = "";
		
		//date of news from url
		String URLDate = StrDate(url); 
		if(URLDate != null)
			return URLDate;
		else {
			Calendar c = Calendar.getInstance();
			String htmlStr = GetHtml.getHtmlResourceByUrl(url, "UTF-8");
			Document doc = Jsoup.parse(htmlStr);
			
			//date of sina video
			String DateInfo = doc.select("span.tm_wrap").select(".m_hd").text();
			String InfoDate = StrDate(DateInfo);
			if(InfoDate != null)
				return InfoDate;
			
			//date in the page content
			String PageDate = StrDate(htmlStr);
			if(PageDate != null)
				return PageDate;
			else {
				String year = Integer.toString(c.get(Calendar.YEAR)); 
				String month = Integer.toString(c.get(Calendar.MONTH)); 
				String day = Integer.toString(c.get(Calendar.DATE)); 
				String date = year+"-"+month+"-"+day;
				result = date;
			}
		}
		return result;
	}
}