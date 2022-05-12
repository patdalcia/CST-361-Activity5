package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Album;
import business.MusicManager;
import util.AlbumNotFoundException;
import util.TracksNotFoundException;

@ManagedBean(name = "albumController")
@ViewScoped
public class AlbumController {

	public String onSubmit(Album album){
		
		MusicManager manager = new MusicManager();
	
		try{
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", manager.addAlbum(album));
			
			return "AddAlbumResponse";
		}catch(Exception e){
			System.out.println("ERROR: Could not CREATE Album");
			
			//throw new AlbumNotFoundException("ERROR: Could not CREATE album");
			return "/AddAlbumError.xhtml?faces-redirect=true";
		}
	}
	
	public String onFind(Album album)
	{
		MusicManager manager = new MusicManager();
		
		try
		{
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", manager.getAlbum(album));
			return "AddAlbumResponse";
		}
		catch(Exception e)
		{
			return "/AddAlbumError.xhtml?faces-redirect=true";
		}
	}
}
