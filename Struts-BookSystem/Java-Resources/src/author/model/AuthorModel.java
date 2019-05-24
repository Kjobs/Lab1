package author.model;

public class AuthorModel
{
	private String AuAuthorID = null;
	private String Name = null;
	private int Age;
	private String Country = null;
	
	public String getAuthorID()
	{
		return AuAuthorID;
	}
	
	public void setAuthorID(String AuAuthorID)
	{
		this.AuAuthorID = AuAuthorID;
	}
	
	public String getName()
	{
		return Name;
	}
	
	public void setName(String Name)
	{
		this.Name = Name;
	}
	
	public int getAge()
	{
		return Age;
	}
	
	public void setAge(int Age)
	{
		this.Age = Age;
	}
	
	public String getCountry()
	{
		return Country;
	}
	
	public void setCountry(String Country)
	{
		this.Country = Country;
	}
}
