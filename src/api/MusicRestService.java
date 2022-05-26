package api;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Album;
import beans.ResponseDataModel;
import business.MusicManager;
import business.MusicManagerInterface;
import util.AlbumNotFoundException;

@Path("/music")
public class MusicRestService {
	
	@EJB
	private MusicManagerInterface mgr;
	
	public MusicRestService(){}

	@GET
	@Path("/getAlbumj/{title}/{artist}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAlbumj(
			@PathParam("title") String title, 
			@PathParam("artist") String artist, 
			@PathParam("year") String year)
	{
		//MusicManager manager = new MusicManager();
		Album album = new Album();
		
		try
		{
			album.setTitle(title);
			album.setArtist(artist);
			album.setYear(Integer.parseInt(year));
		}
		catch(NumberFormatException e)
		{
			System.out.println("ERROR: " + e);
		}
		try
		{
			//album = manager.getAlbum(album);
			album = mgr.getAlbum(album);
			
			//ResponseDataModel response = new ResponseDataModel(0, "", album);
			
			return Response.ok(album).build();
		}
		catch(AlbumNotFoundException e)
		{
			System.out.println("Errror" + e);
			return null;
		}
	}
	
	@GET
	@Path("/getAlbumx/{title}/{artist}/{year}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAlbumx(
			@PathParam("title") String title, 
			@PathParam("artist") String artist, 
			@PathParam("year") String year)
	{
		
		MusicManager manager = new MusicManager();
		Album album = new Album();
		
		try
		{
			album.setTitle(title);
			album.setArtist(artist);
			album.setYear(Integer.parseInt(year));
		}
		catch(NumberFormatException e)
		{
			System.out.println("ERROR: " + e);
		}
		try
		{
			album = manager.getAlbum(album);
			
			ResponseDataModel response = new ResponseDataModel(0, "", album);
			
			//GenericEntity<Collection<Album>> entity = new GenericEntity<Collection<Album>>(album){};
			
			return Response.status(200).entity(album).build();
		}
		catch(AlbumNotFoundException e)
		{
			System.out.println("Errror" + e);
			return null;
		}
		
	}
}
