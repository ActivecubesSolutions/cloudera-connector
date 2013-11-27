import com.ontometrics.qvx.jdbc.JdbcQvxDatasource;
import com.ontometrics.qvx.writer.QvxDataSource;
import com.ontometrics.qvx.writer.QvxWriter;
import jdbc.ConnectionFactory;
import jdbc.DatabaseHelper;
import jdbc.DatabaseRule;
import jdbc.ResultSetProcessor;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.*;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import static org.junit.Assert.fail;


/**
 * ResultSetToQvxTransformationTest.java
 * Created on 10 10, 2013 by Andrey Chorniy
 */
public class ResultSetToQvxTransformationTest {
    private static Random random = new Random(System.currentTimeMillis());
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule(NumericEntity.class, DateEntity.class);

    private Properties outputConf;

    @Before
    public void init(){
        try {
            outputConf = ResourceUtils.readDataSet("/qvx-output.properties");
        } catch (IOException e) {
            fail("Failed to read configuration "+e.getMessage());
        }
    }

    @Test
    public void testNumeric () {
        testImpl(new NumericEntityTestDataProvider("numeric_entity", "numerics.output"));
    }

    private void testImpl (final TestDataProvider testDataProvider) {
        OutputStream os = null;

        try {
            os = ResourceUtils.getOutputStream(outputConf, testDataProvider.outputProperty);
        } catch (FileNotFoundException e) {
            fail ("Unable to create output stream");
        }


        Session session = ConnectionFactory.getSession();
        session.setFlushMode(FlushMode.AUTO);
        for (int i = 0; i < 10 ; i++) {
            Object entity = testDataProvider.generateNewEntity();
            session.save(entity);
        }

        final QvxWriterResultSetProcessor resultSetProcessor = new QvxWriterResultSetProcessor(os);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    DatabaseHelper.executeQuery("SELECT * FROM "+testDataProvider.tableName, resultSetProcessor);
                } catch (Exception ex) {
                    fail ("Conversion to QVX file failed");
                }
            }
        });
    }

    private abstract static class TestDataProvider {
        private String tableName;
        private String outputProperty;

        protected TestDataProvider(String tableName, String outputProperty) {
            this.tableName = tableName;
            this.outputProperty = outputProperty;
        }


        public abstract Object generateNewEntity();
    }
    private static class NumericEntityTestDataProvider extends TestDataProvider {
        private NumericEntityTestDataProvider(String tableName, String outputProperty) {
            super(tableName, outputProperty);
        }

        @Override
        public Object generateNewEntity() {
                return new NumericEntity((short)random.nextInt(), random.nextBoolean() ? (short)random.nextInt() : null,
                        random.nextBoolean() ? random.nextLong() : null, random.nextInt(), random.nextDouble());
        }
    }

    private static class DateEntityTestDataProvider extends TestDataProvider {
        private DateEntityTestDataProvider(String tableName, String outputProperty) {
            super(tableName, outputProperty);
        }

        @Override
        public Object generateNewEntity() {
            return new DateEntity(new Date(System.currentTimeMillis() - random.nextInt(100000000)),
                    new Date(System.currentTimeMillis() - random.nextInt(100000000)),
                    new Date(System.currentTimeMillis() - random.nextInt(100000000)));
        }
    }

    @Test
    public void testDates() {
        testImpl(new DateEntityTestDataProvider("date_entity", "dates.output"));
    }


    @Entity
    @Table(name = "numeric_entity")
    public static class NumericEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long pk;
        @Column(name = "short_primitive")
        private short shortPrimitive;
        @Column(name = "short_object")
        private Short shortObject;
        @Column(name = "long_object")
        private Long longObject;
        @Column(name = "int_primitive")
        private int intPrimitive;
        @Column(name = "double_primitive")
        private double doublePrimitive;

        public NumericEntity() {
        }

        public NumericEntity(short shortPrimitive, Short shortObject, Long longObject, int intPrimitive, double doublePrimitive) {
            this.shortPrimitive = shortPrimitive;
            this.shortObject = shortObject;
            this.longObject = longObject;
            this.intPrimitive = intPrimitive;
            this.doublePrimitive = doublePrimitive;
        }
    }

    @Entity
    @Table(name = "date_entity")
    public static class DateEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long pk;

        @Temporal(TemporalType.TIMESTAMP)
        private Date timestamp;

        @Temporal(TemporalType.DATE)
        private Date date;

        @Temporal(TemporalType.TIME)
        private Date time;

        public Date getTimestamp() {
            return timestamp;
        }

        public Date getDate() {
            return date;
        }

        public Date getTime() {
            return time;
        }

        public DateEntity() {
        }

        public DateEntity(Date timestamp, Date date, Date time) {
            this.timestamp = timestamp;
            this.date = date;
            this.time = time;
        }

    }

    private class QvxWriterResultSetProcessor implements ResultSetProcessor {
        private DataOutputStream os;
        private OutputStream originalOs;

        private QvxWriterResultSetProcessor(OutputStream os) {
            this.os = new DataOutputStream(originalOs = os);
        }

        @Override
        public void process(ResultSet resultSet) throws SQLException {
            try {
                QvxDataSource qvxDataSource = new JdbcQvxDatasource(resultSet);
                QvxWriter qvxWriter = new QvxWriter();
                qvxWriter.generateQvx(qvxDataSource, os);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                 if (os != null && originalOs != System.out) {
                     try {
                         os.close();
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
            }
        }
    }
}
