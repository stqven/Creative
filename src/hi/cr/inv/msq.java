package hi.cr.inv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class msq {
  public static Connection Conn;
  
  public static boolean isClosed() {
    return Conn == null;
  }
  
  public static boolean isConnected() {
    return Conn != null;
  }
  
  public static void connect() {
    try {
      Conn = DriverManager.getConnection("jdbc:mysql://eu.sql.titannodes.com:3306/" + "s6510_utilts" + "?autoReconnect=true", "u6510_IXbBvWzXNf", "weU!Iub@f+gz4pjjc2ozoj=o");
      System.out.println("MySQL2 Connected!");
    } catch (SQLException e) {
      Conn = null;
      System.out.println("MySQL2 Error!");
    }
  }
  
  public static void close() {
    try {
      Conn.close();
      System.out.println("MySQL2 Connection closed successfuly");
    } catch (SQLException e) {
      Conn = null;
      System.out.println("Failed to close MySQL2 connection");
    }
  }
  
  public static void update(String Query) {
    try {
      PreparedStatement P = Conn.prepareStatement(Query);
      P.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public static ResultSet getResult(String Query) {
    try {
      PreparedStatement P = Conn.prepareStatement(Query);
      return P.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
