package jdbc;

import com.ontometrics.qvx.jdbc.JdbcHeaderConfiguration;
import com.ontometrics.qvx.jdbc.JdbcQvxHeaderConfigurationConverter;
import com.ontometrics.qvx.jdbc.JdbcValueAccessor;
import com.ontometrics.qvx.writer.FieldDescriptor;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * JdbcQvxHeaderConfigurationConverterTest.java
 * Created on 10/10/2013 by Nikolay Chorniy
 */
public class JdbcQvxHeaderConfigurationConverterTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule(DatabaseHelper.getDefaultEntityClass(), BlobEntity.class);

    @Test
    public void testWithTextAndDates() throws SQLException, ParseException {
        DatabaseHelper.createDefaultEntityData();
        DatabaseHelper.executeQuery("SELECT text, id, date FROM ontometrics_user ORDER BY id", new ResultSetProcessor() {
            @Override
            public void process(ResultSet resultSet) throws SQLException {
                JdbcHeaderConfiguration headerConfiguration = new JdbcQvxHeaderConfigurationConverter()
                        .convert(resultSet.getMetaData());
                assertThat(headerConfiguration, notNullValue());
                assertThat(headerConfiguration.getTableName(), equalToIgnoringCase("ontometrics_user"));
                assertThat(headerConfiguration.getValueAccessorList().size(), equalTo(3));
                assertThat(headerConfiguration.getFieldDescriptors().size(), equalTo(3));
                int rowCount = 0;
                while (resultSet.next()) {
                    int i = 0;
                    for (FieldDescriptor fieldDescriptor : headerConfiguration.getFieldDescriptors()) {
                        JdbcValueAccessor valueAccessor = headerConfiguration.getValueAccessorList().get(i++);
                        Object value = valueAccessor.getValue(resultSet);
                        if (fieldDescriptor.getFieldName().equalsIgnoreCase("text")) {
                            assertThat(value, instanceOf(String.class));
                            if (rowCount == 0) {
                                assertThat((String)value, equalTo("Hello Guys"));
                            } else {
                                assertThat((String)value, equalTo("Robert Smith"));
                            }
                        } else if (fieldDescriptor.getFieldName().equalsIgnoreCase("date")) {
                            if (rowCount == 0) {
                                assertThat(value, nullValue());
                            } else {
                                assertThat(value, notNullValue());
                                assertThat(value, instanceOf(Date.class));
                            }
                        }
                    }
                    ++rowCount;
                }
            }
        });
    }

    @Test
    public void testWithBlobs() throws SQLException {
        BlobEntity blobEntity = new BlobEntity();
        blobEntity.setBlob(new SimpleBlob());
        blobEntity.setText("John");
        blobEntity.setInts(new int[10]);
        ConnectionFactory.getSession().save(blobEntity);
        DatabaseHelper.executeQuery("SELECT id, blob, text, ints FROM blobs ORDER BY id", new ResultSetProcessor() {
            @Override
            public void process(ResultSet resultSet) throws SQLException {
                JdbcHeaderConfiguration headerConfiguration = new JdbcQvxHeaderConfigurationConverter()
                        .convert(resultSet.getMetaData());
                assertThat(headerConfiguration, notNullValue());
                assertThat(headerConfiguration.getTableName(), equalToIgnoringCase("blobs"));
                assertThat(headerConfiguration.getValueAccessorList().size(), equalTo(2));
                assertThat(headerConfiguration.getFieldDescriptors().size(), equalTo(2));
                while (resultSet.next()) {
                    int i = 0;
                    for (FieldDescriptor fieldDescriptor : headerConfiguration.getFieldDescriptors()) {
                        JdbcValueAccessor valueAccessor = headerConfiguration.getValueAccessorList().get(i++);
                        Object value = valueAccessor.getValue(resultSet);
                        if (fieldDescriptor.getFieldName().equalsIgnoreCase("text")) {
                            assertThat(value, instanceOf(String.class));
                            assertThat((String)value, equalTo("John"));
                        } else if (fieldDescriptor.getFieldName().equalsIgnoreCase("id")) {
                            assertThat(value, notNullValue());
                            assertThat(value, instanceOf(Long.class));
                        }
                    }
                }
            }
        });

    }

    private static class SimpleBlob implements Blob {
        private byte[] array = new byte[100];

        @Override
        public long length() throws SQLException {
            return array.length;
        }

        @Override
        public byte[] getBytes(long pos, int length) throws SQLException {
            return Arrays.copyOfRange(array, (int)pos, (int)pos + length);
        }

        @Override
        public InputStream getBinaryStream() throws SQLException {
            return new ByteArrayInputStream(array);
        }

        @Override
        public long position(byte[] pattern, long start) throws SQLException {
            return 0;
        }

        @Override
        public long position(Blob pattern, long start) throws SQLException {
            return 0;
        }

        @Override
        public int setBytes(long pos, byte[] bytes) throws SQLException {
            return bytes.length;
        }

        @Override
        public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException {
            return len;
        }

        @Override
        public OutputStream setBinaryStream(long pos) throws SQLException {
            return new ByteArrayOutputStream();
        }

        @Override
        public void truncate(long len) throws SQLException {
        }

        @Override
        public void free() throws SQLException {
        }

        @Override
        public InputStream getBinaryStream(long pos, long length) throws SQLException {
            return new ByteArrayInputStream(getBytes(pos, (int)length));
        }
    }

    @Entity
    @Table(name = "blobs")
    public static class BlobEntity {
        @Id
        @GeneratedValue
        private Long id;

        @Lob
        private SimpleBlob blob;

        private String text;

        private int[] ints;

        public SimpleBlob getBlob() {
            return blob;
        }

        public void setBlob(SimpleBlob blob) {
            this.blob = blob;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int[] getInts() {
            return ints;
        }

        public void setInts(int[] ints) {
            this.ints = ints;
        }
    }
}
