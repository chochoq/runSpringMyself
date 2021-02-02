package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
   public static Connection CON;
   static {
      try {
         //오라클 jdbc 드라이버설정
         Class.forName("oracle.jdbc.driver.OracleDriver");
         CON=DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.24:1521:xe", "cho", "1234");
         System.out.println("접속성공");
      }catch(Exception e) {
         System.out.println(e.toString());
      }
   }
}