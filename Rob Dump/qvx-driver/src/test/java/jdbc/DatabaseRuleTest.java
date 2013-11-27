package jdbc;

import org.hibernate.Session;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * DatabaseRuleTest.java
 * Created on 10/10/2013 by Andrey Chorniy / Nikolay Chorniy
 */
public class DatabaseRuleTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule(DatabaseHelper.getDefaultEntityClass(), EntityExample.class);

    @Test
    public void testConnection() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        assertThat(connection, notNullValue());
        assertThat(connection.isClosed(), is(false));
    }

    @Test
    public void testCreateTables() throws SQLException {
        DatabaseHelper.executeQuery("SELECT id, date, text FROM ontometrics_user", new ResultSetProcessor() {
            @Override
            public void process(ResultSet resultSet) throws SQLException {
                assertThat(resultSet, notNullValue());
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                assertThat(resultSetMetaData, notNullValue());
                assertThat(resultSetMetaData.getColumnCount(), equalTo(3));
                assertThat(resultSetMetaData.getTableName(1), equalToIgnoringCase("ontometrics_user"));
                assertThat(resultSetMetaData.getTableName(2), equalToIgnoringCase("ontometrics_user"));
                assertThat(resultSetMetaData.getColumnName(1), equalToIgnoringCase("ID"));
                assertThat(resultSetMetaData.getColumnName(2), equalToIgnoringCase("date"));
                assertThat(resultSetMetaData.getColumnName(3), equalToIgnoringCase("text"));

                assertThat(resultSetMetaData.getColumnType(1), equalTo(Types.BIGINT));
                assertThat(resultSetMetaData.getColumnType(2), equalTo(Types.TIMESTAMP));
                assertThat(resultSetMetaData.getColumnType(3), equalTo(Types.CLOB));
            }
        });
    }

    @Test
    public void testInsertData() throws SQLException, ParseException {
        DatabaseHelper.createDefaultEntityData();
        DatabaseHelper.executeQuery("SELECT text, id, date FROM ontometrics_user ORDER BY text DESC", new ResultSetProcessor() {
            @Override
            public void process(ResultSet resultSet) throws SQLException {
                assertThat(resultSet.next(), is(true));
                assertThat(resultSet.getString("text"), equalTo("Robert Smith"));
                assertThat(resultSet.getLong("id"), is(greaterThan(0l)));
                assertThat(resultSet.getDate("date"), notNullValue());
                assertThat(resultSet.next(), is(true));
                assertThat(resultSet.getString("text"), equalTo("Hello Guys"));
                assertThat(resultSet.getLong("id"), is(greaterThan(0l)));
                assertThat(resultSet.getDate("date"), nullValue());
                assertThat(resultSet.next(), is(false));
            }
        });
    }

    @Test
    public void testHibernateSession(){
        Session session = ConnectionFactory.getSession();
        session.save(new EntityExample());
        @SuppressWarnings("unchecked")
        List<EntityExample> fromDb = session.createCriteria(EntityExample.class).list();
        assertThat(fromDb, notNullValue());
        assertThat(fromDb.size(), is(1));
        assertThat(fromDb.get(0).pk, notNullValue());
    }

    @Entity
    public static class EntityExample {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long pk;
    }
}
