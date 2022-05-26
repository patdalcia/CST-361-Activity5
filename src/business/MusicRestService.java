package business;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Album;
import beans.ResponseDataModel;

@Path("music")
public class MusicRestService {
	
	@GET
	@Path("getAlbum/{title}/{artist}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDataModel getAlbum(
			@PathParam("title") String title, 
			@PathParam("artist") String artist, 
			@PathParam("year") String year)
	{
		MusicManager manager = new MusicManager();
		Album album = new Album();
		
		album.setTitle(title);
		album.setArtist(artist);
		album.setYear(Integer.parseInt(year));
		ResponseDataModel rdm = new ResponseDataModel(0, "", manager.getAlbum(album));
		
		return rdm;
	}

}
