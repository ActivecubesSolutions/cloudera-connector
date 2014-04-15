package com.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Utility {


    public static String test(ResultSet res) throws SQLException{
        long startTime = System.currentTimeMillis();
        res.setFetchSize(20000);
       
     // res.getNString("");
           while(res.next()) {
            //System.out.println(res.getCursorName());
            return res.getCursorName();
           
        }
        return "flase";
           /* long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("elapsedTime"+elapsedTime);
        */
       
       
    }

}