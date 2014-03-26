package mojohive;

import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jooq.impl.DSL;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import mojohive.StackTraceUtil;

public class MojoHiveDriver implements IMojoHiveDriver {

	private Exception _lastException;

	public MojoHiveDriver()
	{
		_lastException = null;
	}

	public int GetLastErrorCode()
	{
		if (_lastException != null) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public String GetLastExceptionMessage()
	{
		if (_lastException != null) {
			return _lastException.getMessage();
		}
		else {
			return "";
		}
	}

	public String GetLastExceptionStackTrace()
	{ 
		if (_lastException != null) {
			return StackTraceUtil.getStackTrace(_lastException);
		}
		else {
			return "";
		}
	}

	public int TestNamedPipeWrite(String pipename, String dataFilePath) 
	{
		int bytes_written = 0;
		try {

			logUtil.LogMsg("Opening pipe for writing: " + pipename);
			//RandomAccessFile pipe = null; // WARNING: RandomAccessFile cannot be opened as "w" (write-only) which QlikView requires.
			FileOutputStream filestrm = null;
			try {
				//pipe = new RandomAccessFile(pipename, "rw"); 
				filestrm = new FileOutputStream(pipename); 
			}
			catch(Exception e) {
				// TODO Auto-generated catch block
				//logUtil.LogMsg("Fatal exception follows!");
				this._lastException = e;
				e.printStackTrace();
			}
			//if( pipe != null ) {
			if( filestrm != null ) {
				logUtil.LogMsg("Pipe connection successful. " );
				// Read the data file
				Path path = Paths.get(dataFilePath);
				byte[] data = Files.readAllBytes(path);

				//pipe.write(data);
				filestrm.write(data);
				bytes_written = data.length;
				filestrm.close();

				//pipe.close();
			}



		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			//logUtil.LogMsg("Fatal exception follows!");
			this._lastException = e;
			e.printStackTrace();
		}
		return bytes_written;
	}

	public String TestNamedPipeRead(String pipename)
	{
		String result = "";
		_lastException = null;

		try{

			// TO DO: re-write using java.io.FileInputStream

			logUtil.LogMsg("Opening pipe for reading: " + pipename);
			RandomAccessFile pipe = new RandomAccessFile(pipename, "rw"); 
			logUtil.LogMsg("Pipe connection successful. " );


			StringBuffer fullString = new StringBuffer();
			int i = 0;
			while( i < 100 ){
				int charCode = pipe.read();
				if(charCode == 0) {
					//break;
				}
				else {
					//aChar = new Character((char)charCode).toString();
					fullString.append((char)charCode);
				}
				i++;
			}
			//System.out.println("Response: " + fullString);		    
			result = fullString.toString();



			//line = pipe.readLine();
			//result = line;
			//pipe.close();
			//return result;

			/*
		    pipe.readLine()
		    while ( (line = pipe.readLine()) != null )
		    {
		    	System.out.println(line);
		    	result += line;
		    }

		    pipe.close();
		    //logUtil.LogMsg("Pipe closed. " );

			 */

			pipe.close();
			return result;

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			//logUtil.LogMsg("Fatal exception follows!");
			this._lastException = e;
			e.printStackTrace();
		}

		return result;
	}

	public int TestConnection(String driver, String url, String queuename, String username, String password)
	{
		logUtil.LogMsg("Inside Java Land:TestConnection:" + driver);
		int result = -1000;

		_lastException = null;

		// Load the JDBC driver class
		try {
			logUtil.LogMsg("Loading driver: " + driver);
			//Class.forName(driver);
			Driver driverObj=new org.apache.hive.jdbc.HiveDriver();
			DriverManager.registerDriver(driverObj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


		try{

			//
			logUtil.LogMsg("Opening connection to: " + url);
			Connection con = DriverManager.getConnection(url, username, password);
			logUtil.LogMsg("Connection successful. " );

			con.close();
			logUtil.LogMsg("Connection closed. " );

			result = 0;

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			//logUtil.LogMsg("Fatal exception follows!");
			this._lastException = e;
			//e.printStackTrace();
		}


		return result;
	}
	public String TestStringOutput(String author)
	{
		logUtil.LogMsg("Shantanu from Java Land: " +"Entering QueryResultSetAsXML");
		String temp_result = author+" from Java Land: " +"Testing String Output";
		return temp_result;
	}
	public String QueryResultSetAsXML(String driver, String url, String queuename, String username, String password, String query)
	{
		String xml_result = null;
		boolean hit_error = false;

		_lastException = null;

		// Load the JDBC driver class
		try {
			//logUtil.LogMsg("Loading driver: " + driver);
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			hit_error = true;
			this._lastException = e;
			//e.printStackTrace();

		}	

		if( !hit_error)	{
			try{

				//
				//logUtil.LogMsg("Opening connection to: " + url);
				Connection con = DriverManager.getConnection(url, username, password);
				//logUtil.LogMsg("Connection successful. " );
				
				// Run query
				//logUtil.LogMsg("Creating statement. " );
				Statement stmt = con.createStatement();			    
				stmt.setFetchSize(2);
				stmt.execute("set hive.fetch.output.serde = org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe");
				logUtil.LogMsg("Running SQL (query): " + query);
				
				//String addfile="add file/home/bluestar/connector/bsilconn.py"
				
				xmlUtil.runInitializationQueries(con,stmt);
				logUtil.LogMsg("Key1: Running SQL (query): " + query);
				ResultSet  res = stmt.executeQuery(query);
				//logUtil.LogMsg("Key1: Ending SQL (query): " + query);

				//				logUtil.LogMsg("Key2: Starting XML convertion");
				//				xml_result = xmlUtil.ConvertResultSetToXML(res);
				//				logUtil.LogMsg("Key2: Ending XML convertion");

				//logUtil.LogMsg("Key2: Starting JSON convertion");
				xml_result = getHashMap(res).toString();
				//xml_result = DSL.using(con).fetch(res).formatJSON();
				//xml_result = getHashMapImproved(res).toString();
				//xml_result =MojoHiveMultiThreadedDriver.getMultiThreadedResults(query, stmt);
				//logUtil.LogMsg(xml_result);
				logUtil.LogMsg("Key2: Ending JSON convertion");
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				//logUtil.LogMsg("Fatal exception follows!");
				this._lastException = e;
				//e.printStackTrace();


			}
		}


		return xml_result;
	}

	private JSONArray getHashMapImproved(ResultSet rs) throws SQLException,
	JSONException {
		JSONArray json = new JSONArray();
		ResultSetMetaData rsmd = rs.getMetaData();
		int numColumns = rsmd.getColumnCount();
		while (rs.next()) {

			JSONObject obj = new JSONObject();

			for (int i = 1; i < numColumns + 1; i++) {
				String column_name = rsmd.getColumnName(i);

				if (rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
					obj.put(column_name, rs.getArray(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
					obj.put(column_name, rs.getLong(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.REAL) {
					obj.put(column_name, rs.getFloat(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
					obj.put(column_name, rs.getBoolean(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {
					obj.put(column_name, rs.getBlob(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
					obj.put(column_name, rs.getDouble(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
					obj.put(column_name, rs.getDouble(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
					obj.put(column_name, rs.getInt(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
					obj.put(column_name, rs.getNString(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
					obj.put(column_name, rs.getString(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.CHAR) {
					obj.put(column_name, rs.getString(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.NCHAR) {
					obj.put(column_name, rs.getNString(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.LONGNVARCHAR) {
					obj.put(column_name, rs.getNString(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.LONGVARCHAR) {
					obj.put(column_name, rs.getString(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.TINYINT) {
					obj.put(column_name, rs.getByte(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
					obj.put(column_name, rs.getShort(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
					obj.put(column_name, rs.getDate(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.TIME) {
					obj.put(column_name, rs.getTime(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
					obj.put(column_name, rs.getTimestamp(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BINARY) {
					obj.put(column_name, rs.getBytes(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.VARBINARY) {
					obj.put(column_name, rs.getBytes(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.LONGVARBINARY) {
					obj.put(column_name, rs.getBinaryStream(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.BIT) {
					obj.put(column_name, rs.getBoolean(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.CLOB) {
					obj.put(column_name, rs.getClob(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.NUMERIC) {
					obj.put(column_name, rs.getBigDecimal(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.DECIMAL) {
					obj.put(column_name, rs.getBigDecimal(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.DATALINK) {
					obj.put(column_name, rs.getURL(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.REF) {
					obj.put(column_name, rs.getRef(column_name));
				} else if (rsmd.getColumnType(i) == java.sql.Types.STRUCT) {
					obj.put(column_name, rs.getObject(column_name)); // must be a custom mapping consists of a class that implements the interface SQLData and an entry in a java.util.Map object.
				} else if (rsmd.getColumnType(i) == java.sql.Types.DISTINCT) {
					obj.put(column_name, rs.getObject(column_name)); // must be a custom mapping consists of a class that implements the interface SQLData and an entry in a java.util.Map object.
				} else if (rsmd.getColumnType(i) == java.sql.Types.JAVA_OBJECT) {
					obj.put(column_name, rs.getObject(column_name));
				} else {
					obj.put(column_name, rs.getString(i));
				}
			}
			json.add(obj);
		}

		return json;
	}

	private JSONArray getHashMap(ResultSet rs_SubItemType) throws SQLException {
		ResultSetMetaData metaData = rs_SubItemType.getMetaData();
		int colCount = metaData.getColumnCount();
		List<Map<String, Object>> row =new ArrayList<Map<String, Object>>();
		while (rs_SubItemType.next()) {
			Map<String, Object> columns = new HashMap<String, Object>();
			for (int i = 1; i <= colCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs_SubItemType.getObject(i));
			}

			row.add(columns);
		}
		JSONArray jsonA = JSONArray.fromObject(row); 
		// System.out.println(jsonA);
		return jsonA;
	}

	public int QueryDebugResultSetToPipe(String driver, String url, String queuename, String username, String password, String query, String datapipeName)
	{
		// WARNING: this is a hard-coded path to a local file with QVX formatted data.
		String local_input_file = "C:\\Users\\mojotech\\Projects\\Code\\Java\\MojoHiveTest\\CurrencyExchangeRate_debug.qvx";

		// Run the named pipe write function.
		// Note: the datapipeName here is the local output file path.
		return this.TestNamedPipeWrite(datapipeName, local_input_file) ;
	}

	public int QueryResultSetToPipe(String driver, String url, String queuename, String username, String password, String query, String datapipeName)
	{
		int result = 0;   	

		_lastException = null;

		// Load the JDBC driver class
		try 
		{
			logUtil.LogMsg("Loading driver: " + driver);
			Class.forName(driver);
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			result = -1;
			this._lastException = e;
			//e.printStackTrace();
		}	

		if( result >= 0)	
		{

			try {

				// Open the JDBC connection
				logUtil.LogMsg("Opening connection to: " + url);
				Connection con = DriverManager.getConnection(url, username, password);
				logUtil.LogMsg("Connection successful. " );

				// Create the statement
				logUtil.LogMsg("Creating statement. " );
				Statement stmt = con.createStatement();			    

				// Run the SQL query
				logUtil.LogMsg("Running SQL (query): " + query);
				ResultSet  res = stmt.executeQuery(query);

				// We must generate an XML description of the ResultSet schema in the
				// QlikView "QvxTableHeader" XML format.
				// TO DO: This is a stub method, not implemented!
				String xml_table_header = this.GenerateQvxTableHeaderXML(res);

				// Write the result set to the pipe in QlikView data format.
				// TO DO: This is a stub method, not implemented!
				result = WriteResultSetToPipe(res, xml_table_header, datapipeName );


			}
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				//logUtil.LogMsg("Fatal exception follows!");
				this._lastException = e;
				//e.printStackTrace();
			}


		}

		return result;

	}


	private String GenerateQvxTableHeaderXML(ResultSet res)
	{
		String xml = null;

		// Get table name 

		// Walk through columns of first row to get column names and data types.	


		return xml;
	}

	private int WriteResultSetToPipe(ResultSet res, String xmlTableHeader, String datapipeName) 
	{
		int result = 0;



		return result;   	
	}

}
