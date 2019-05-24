package cn.news.func;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetSource {
	/**
	 * To know where the news source from.
	 * @author Kobs
	 * @param url
	 * @return Source
	 */
	public static String SourceFrom(String url) {
		String result = "";
		
		String regex = "\\.([^\\.]+(\\.com|\\.cn){1,2})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(url);
		if(m.find())
			result = m.group(1);
		else
			result = "none";
		return result;
	}
}