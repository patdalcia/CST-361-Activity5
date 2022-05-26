package controllers;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import beans.Album;
import business.MusicManager;
import business.MusicManagerInterface;
import util.AlbumNotFoundException;
import util.LoggingInterceptor;
import util.TracksNotFoundException;

@Named
@ViewScoped
@Interceptors(LoggingInterceptor.class)
public class AlbumController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private MusicManagerInterface mgr;

	public String onSubmit(Album album){
		
		//MusicManager manager = new MusicManager();
	
		try{
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", mgr.addAlbum(album));
			
			return "AddAlbumResponse";
		}catch(Exception e){
			System.out.println("ERROR: Could not CREATE Album");
			
			//throw new AlbumNotFoundException("ERROR: Could not CREATE album");
			return "/AddAlbumError.xhtml?faces-redirect=true";
		}
	}
	
	public String onFind(Album album)
	{
		//MusicManager manager = new MusicManager();
		
		try
		{
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", mgr.getAlbum(album));
			return "AddAlbumResponse";
		}
		catch(Exception e)
		{
			return "/AddAlbumError.xhtml?faces-redirect=true";
		}
	}
}
