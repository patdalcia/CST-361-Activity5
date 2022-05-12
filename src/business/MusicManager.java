package business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.el.ELException;

import beans.Album;
import beans.Track;
import data.MusicDataService;
import util.AlbumNotFoundException;
import util.DatabaseException;
import util.TracksNotFoundException;

@Stateless
public class MusicManager implements MusicManagerInterface{

	public HashMap<String, List<Track>> trackList;
	public List<Track> tList;
	
	public MusicManager(){
		this.trackList = new HashMap<String, List<Track>>();
		this.tList = new ArrayList<Track>();
		
		
		/* Generating Mock TrackLists */
		List<Track> tracks1 = new ArrayList<Track>();
		for(int i = 0; i <11; i++){
			Track track = new Track("Title " + i, i);
			tracks1.add(track);
			this.tList.add(track);
		}
		this.trackList.put("Album 1 || Artist 1 || 1920", tracks1);
		
		List<Track> tracks2 = new ArrayList<Track>();
		for(int i = 0; i <11; i++){
			Track track = new Track("Title " + i, i);
			tracks2.add(track);
		}
		this.trackList.put("Album 2 || Artist 2 || 1921", tracks2);
		
		List<Track> tracks3 = new ArrayList<Track>();
		for(int i = 0; i <11; i++){
			Track track = new Track("Title " + i, i);
			tracks3.add(track);
		}
		this.trackList.put("Album 3 || Artist 3 || 1922", tracks3);
		
		System.out.println(this.trackList);
	}
	
	@Override
	public Album addAlbum(Album model) {
		List<Track> tracks = this.getTracks(model);
		//List<Track> tracks = this.tList;
		
		if(tracks.isEmpty() || tracks == null){
			throw new TracksNotFoundException("Track Not Found");
		}
		else{
			model.setTracks(tracks);
			
			MusicDataService service = new MusicDataService();
			service.create(model);
			
			return model;
		}		
	}
	
	private List<Track> getTracks(Album album){
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
	
	public Album getAlbum(Album album){
		
		MusicDataService service = new MusicDataService();
		
		if(service.findBy(album).tracks == null)
		{
			String errorMessage = "ERROR: Album was not found within the Database";
			throw new AlbumNotFoundException(errorMessage);
		}
		else{
			return service.findBy(album);
		}
	}

}
