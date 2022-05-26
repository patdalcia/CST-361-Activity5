package beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@ManagedBean
@ViewScoped

public class Album implements Serializable{

	@NotNull()
	@Size(min=5, max=50)
	public String title;
	
	@NotNull()
	@Size(min= 5, max=25)
	public String artist;
	
	@Min(1920)
	@Max(2022)
	public int year;
	public List<Track> tracks;
	
	public Album(String title, String artist, int year)
	{
		this.title = title;
		this.artist = artist;
		this.year = year;
		this.tracks = Collections.<Track>emptyList();
	}
	
	public Album()
	{
		this.title = "";
		this.artist = "";
		this.year = 1920;
		this.tracks = null;
	}
	
	public int getNumberTracks()
	{
		return this.tracks.size();
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getArtist()
	{
		return this.artist;
	}
	
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}
	
	public List<Track> getTracks()
	{
		return this.tracks;
	}
	
	public void setTracks(List<Track> tracks)
	{
		this.tracks = tracks;
	}
	
}
