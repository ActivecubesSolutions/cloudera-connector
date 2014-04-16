package com.test;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.Date;

 
public class HiveJdbcClient {
     public static final int EXECUTOR_CONSUMER_THREADS = 10;
     public static final int RECORDS_FETCH_LIMIT=100;
     public static final int RECORDS_FETCH_SIZE=10; //this has to be a constant,as the total number of records is not known. 
     public static final int FETCHED_QUEUE_SIZE=10;
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
 
  /**
 * @param args
 * @throws SQLException
   */
  public static void main(String[] args) throws SQLException {
      try {
          Class.forName(driverName);
        } catch (ClassNotFoundException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          System.exit(1);
        }
        final String IMPALAD_HOST = "192.168.1.249";

        // port 21050 is the default impalad JDBC port
        final String IMPALAD_JDBC_PORT = "21050";
        Connection con = DriverManager.getConnection("jdbc:hive2://192.168.1.249:10000/default", "", "");
       //Connection con = DriverManager.getConnection("jdbc:hive2://" + IMPALAD_HOST + ':' + IMPALAD_JDBC_PORT + "/;auth=noSasl");
       
        Statement stmt = con.createStatement();
           // select * query
        String sql = "select * from priceandinventory limit "+RECORDS_FETCH_LIMIT ;
        System.out.println("Running: " + sql);
       final ResultSet res = stmt.executeQuery(sql);
      System.out.println( res.getNString(""));
      
      long lStartTime = new Date().getTime();
     
    new Thread()
    {
       
    public void run()
    {try {
        res.setFetchSize(RECORDS_FETCH_SIZE);
        res.next();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   
    }
    }.start();
   
   
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
boolean last = true;       
int times = 0;
    while(last)
    {
    String output = res.getCursorName();
   
   // System.out.println("output"+output);
    //times++;
    if(output.equalsIgnoreCase("false"))
    {
        last = false;
    }
    }
               
               
    //System.out.println("no of times printed"+times);           
               
           
               
           
           
            //return "";
           
       
     
      /*while (!(Utility.test(res).equalsIgnoreCase("flase"))){
         
                 Utility.test( res);
           
      }*/
      long lEndTime = new Date().getTime();
      double difference = lEndTime - lStartTime;
      System.out.println("Elapsed milliseconds: " + difference/1000);
     
     }
 
 
}