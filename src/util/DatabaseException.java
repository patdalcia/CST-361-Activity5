package util;

import java.sql.SQLException;

public class DatabaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseException(SQLException errorMessage){
		super(errorMessage);
	}
}
