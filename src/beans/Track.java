package beans;

import java.util.HashMap;

public class Track {
	public String title;
	public int number;
	public HashMap<String, String> trackInfo;
	
	public Track()
	{
		this.title = "";
		this.number = 0;
		this.trackInfo = new HashMap<String, String>();
	}
	
	public Track(String title, int number){
		this.title = title;
		this.number = number;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setNumber(int number) 
	{
		this.number = number;
	}
	
	public int getNumber()
	{
		return this.number;
	}
}
