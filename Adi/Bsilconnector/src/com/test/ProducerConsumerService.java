package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerService {
	 private static String driverName = "org.apache.hive.jdbc.HiveDriver";
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
           String sql = "select * from priceandinventory limit 100000" ;
           System.out.println("Running: " + sql);
          ResultSet res = stmt.executeQuery(sql);
          res.setFetchSize(2000);
         System.out.println( res.getNString(""));
        //Creating BlockingQueue of size 10
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue,res);
        Consumer consumer = new Consumer(queue);
        //starting producer to produce messages in queue
        new Thread(producer).start();
        //starting consumer to consume messages from queue
        new Thread(consumer).start();
        System.out.println("Producer and Consumer has been started");
    }
    
    
 
}