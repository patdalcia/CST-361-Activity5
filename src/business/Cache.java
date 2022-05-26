package business;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import beans.Album;

@Startup
@Singleton
public class Cache {
	
	HashMap<String, Album> cache;
	
	@PostConstruct
	public void init(){
		cache = new HashMap<String, Album>();
	}
	
	public Album getObject(Album album){
		
		System.out.println("Getting key....");
		String key = album.title.trim() + album.artist.trim() + album.year;
		System.out.println("Key retrieved! Key is: " + key);
		System.out.println("Checking if key exists within cache....");
		
		if(cache.get(key) == null)
		{
			System.out.println("ERROR: Key was not found");
			return null;
		}
		else
		{
			System.out.println("SUCCESS: Key was found. Retrieving records....");
			return cache.get(key);
		}
	}
	
	public void putObject(Album album){
		
		System.out.println("Inside 'putObject()'...Attempting to insert record into cache");
		System.out.println("Generating key...");
		
		String key = album.getTitle().trim() + album.getArtist().trim() + album.getYear();
		
		System.out.println("Key has been generated, key is: " + key);
		System.out.println("Attempting to insert record into cache using generated key....");
		
		cache.put(key, album);
		
		System.out.println("Record has been inserted!");
	}

}
