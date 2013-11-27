package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetProcessor.java
 * Created on 10/11/2013 by Nikolay Chorniy
 */
public interface ResultSetProcessor {
    void process(ResultSet resultSet) throws SQLException;
}
