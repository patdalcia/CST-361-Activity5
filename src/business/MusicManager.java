package business;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.el.ELException;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import beans.Album;
import beans.Track;
import data.DataAccessInterface;
import data.MusicDataService;
import util.AlbumNotFoundException;
import util.DatabaseException;
import util.LoggingInterceptor;
import util.TracksNotFoundException;

@Stateless
@Interceptors(LoggingInterceptor.class)
public class MusicManager implements MusicManagerInterface, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private DataAccessInterface<Album> dao; 
	
	@Inject
	private TrackFinderInterface tf;
	
	@EJB
	private Cache cache;

	public HashMap<String, List<Track>> trackList;
	public List<Track> tList;
	
	public MusicManager(){
		
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
			
			//MusicDataService service = new MusicDataService();
			dao.create(model);
			
			return model;
		}		
	}
	
	public List<Track> getTracks(Album album){
		return tf.getTracks(album);
	}
	
	public Album getAlbum(Album album){
		
		if(cache.getObject(album) != null){
			return cache.getObject(album);
		}
		else
		{
			//MusicDataService service = new MusicDataService();
			Album a = dao.findBy(album);
			if(a.tracks == null)
			{
				String errorMessage = "ERROR: Album was not found within the Database";
				throw new AlbumNotFoundException(errorMessage);
			}
			else{
				cache.putObject(a);
				return a;
			}
		}
	}

}
