package jdbc;

import org.hibernate.Session;

import java.sql.Connection;

/**
 * ConnectionFactory.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class ConnectionFactory {
    public static Connection getConnection() {
        return DatabaseRule.getConnection();
    }

    public static Session getSession(){
        return DatabaseRule.getSession();
    }
}
