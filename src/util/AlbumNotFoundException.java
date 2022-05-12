package util;

import java.sql.SQLException;

public class AlbumNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AlbumNotFoundException(String errorMessage){
		super(errorMessage);
	}

}
