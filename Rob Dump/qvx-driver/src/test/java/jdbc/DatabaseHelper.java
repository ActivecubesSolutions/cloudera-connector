package jdbc;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DatabaseHelper.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class DatabaseHelper {
    public static void executeQuery(String sql, ResultSetProcessor resultSetProcessor) throws SQLException {
        Statement statement = ConnectionFactory.getConnection().createStatement();
        ResultSet set = null;
        try {
            set = statement.executeQuery(sql);
            if (resultSetProcessor != null) {
                resultSetProcessor.process(set);
            }
        } finally {
            if (set != null) {
                set.close();
            }
            statement.close();
        }
    }

    public static Class getDefaultEntityClass() {
        return DefaultEntity.class;
    }

    public static void createDefaultEntityData() throws ParseException {
        DatabaseHelper.DefaultEntity entity = new DatabaseHelper.DefaultEntity("Hello Guys", null);
        ConnectionFactory.getSession().save(entity);
        entity = new DatabaseHelper.DefaultEntity("Robert Smith", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("2013-01-01 12:00:05"));
        ConnectionFactory.getSession().save(entity);
    }

    @Entity
    @Table(name = "ontometrics_user")
    public static class DefaultEntity implements Serializable {
        @Id
        @Column(columnDefinition = "BIGINT NOT NULL")
        @GeneratedValue()
        private Long id;

        @Column(columnDefinition = "TEXT")
        private String text;

        @Temporal(TemporalType.TIMESTAMP)
        private Date date;

        public DefaultEntity() {
        }

        public DefaultEntity(String text, Date date) {
            this.text = text;
            this.date = date;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
