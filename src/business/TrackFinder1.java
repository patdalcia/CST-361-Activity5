package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.enterprise.inject.Alternative;

import beans.Album;
import beans.Track;
import util.TracksNotFoundException;



@Local
public class TrackFinder1 implements TrackFinderInterface{

	public HashMap<String, List<Track>> trackList;
	public List<Track> tList;
	
	public TrackFinder1(){
		
		this.trackList = new HashMap<String, List<Track>>();
		this.tList = new ArrayList<Track>();
		
		
		/* Generating Mock TrackLists */
		List<Track> tracks1 = new ArrayList<Track>();
		for(int i = 0; i <11; i++){
			Track track = new Track("Title " + i, i);
			tracks1.add(track);
			this.tList.add(track);
		}
		this.trackList.put("Album1 || Artist1 || 2000", tracks1);
		
		List<Track> tracks2 = new ArrayList<Track>();
		for(int i = 0; i <11; i++){
			Track track = new Track("Title " + i, i);
			tracks2.add(track);
		}
		this.trackList.put("Album2 || Artist2 || 2000", tracks2);
		
		List<Track> tracks3 = new ArrayList<Track>();
		for(int i = 0; i <11; i++){
			Track track = new Track("Title " + i, i);
			tracks3.add(track);
		}
		this.trackList.put("Album3 || Artist3 || 2000", tracks3);
		
		System.out.println(this.trackList);
		
	}
	
	@Override
	public List<Track> getTracks(Album album) {
		String title = album.getTitle().trim() + " || ";
		String artist = album.getArtist().trim() + " || ";
		String year = Integer.toString(album.getYear()).trim();
		
		String key = title + artist + year;
		
		try{
			List<Track> list = new ArrayList<>(this.trackList.get(key));
			
			if(list.isEmpty()){
				throw new Exception("ERROR: Tracks could not be found");
			}
			else{
				return list;
			}
		}catch(Exception e){
			throw new TracksNotFoundException("ERROR: Track could not be found");
		}
	}

}
