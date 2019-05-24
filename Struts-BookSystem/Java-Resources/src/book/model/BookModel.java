package book.model;

public class BookModel
{
	private String ISBN = null;
	private String Title = null;
	private String BoAuthorID = null;
	private String Publisher = null;
	private String PublishDate = null;
	private float Price;
	
	public String getISBN()
	{
		return ISBN;
	}
	
	public void setISBN(String ISBN)
	{
		this.ISBN = ISBN;
	}
	
	public String getTitle()
	{
		return Title;
	}
	
	public void setTitle(String Title)
	{
		this.Title = Title;
	}
	
	public String getAuthorID()
	{
		return BoAuthorID;
	}
	
	public void setAuthorID(String BoAuthorID)
	{
		this.BoAuthorID = BoAuthorID;
	}
	
	public String getPublisher()
	{
		return Publisher;
	}
	
	public void setPublisher(String Publisher)
	{
		this.Publisher = Publisher;
	}
	
	public String getPublishDate()
	{
		return PublishDate;
	}
	
	public void setPublishDate(String PublishDate)
	{
		this.PublishDate = PublishDate;
	}
	
	public float getPrice()
	{
		return Price;
	}
	
	public void setPrice(float Price)
	{
		this.Price = Price;
	}
}
