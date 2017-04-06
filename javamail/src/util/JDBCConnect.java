package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnect {
	private static Connection conn=null;
	private static final String URL="jdbc:mysql://localhost:3306/bookstore";
	private static final String USER="root";
	private static final String PASS="123456";
   public static Connection getConnection(){
	   try{
	   Class.forName("com.mysql.jdbc.Driver");
	   conn=DriverManager.getConnection(URL,USER,PASS);
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return conn;
   }
}
