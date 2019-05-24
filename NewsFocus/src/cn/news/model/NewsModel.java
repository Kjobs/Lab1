package cn.news.model;

/**
 * Class of news model.
 * @author Kobs
 */
public class NewsModel {
	private String Title;
	private String Link;
	private String Source;
	private String Date;
	private String Type;
	private int LikeNum;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String Title) {
		this.Title = Title;
	}
	
	public String getLink() {
		return Link;
	}
	public void setLink(String Link) {
		this.Link = Link;
	}
	
	public String getSource() {
		return Source;
	}
	public void setSource(String Source) {
		this.Source = Source;
	}
	
	public String getDate() {
		return Date;
	}
	public void setDate(String Date) {
		this.Date = Date;
	}
	
	public String getType() {
		return Type;
	}
	public void setType(String Type) {
		this.Type = Type;
	}
	
	public int getLikeNum() {
		return LikeNum;
	}
	public void setLikeNum(int LikeNum) {
		this.LikeNum = LikeNum;
	}
}