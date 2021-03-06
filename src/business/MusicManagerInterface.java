package business;

import java.util.List;

import javax.ejb.Local;

import beans.Album;
import beans.Track;

@Local
public interface MusicManagerInterface {

	public Album addAlbum(Album model);
	
	public List<Track> getTracks(Album album);
	
	public Album getAlbum(Album album);
}
