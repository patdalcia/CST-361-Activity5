package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBtest {
	
	public static void main(String[] args){
		String DBurl = "jdbc:mysql://localhost:3306/test?useSSL=false&allowPublicKeyRetrieval=true";
		String Username = "root";
		String password = "password";
		Connection conn = null;
		
		try{
			System.out.println("Attempting a Database Connection........" + DBurl);
			conn = DriverManager.getConnection(DBurl, Username, password);
			System.out.println("Connection is Successful!!!!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
