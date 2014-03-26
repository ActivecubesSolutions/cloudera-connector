package mojohive;

import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import net.sf.json.JSONObject;
import mojohive.StackTraceUtil;

public class MojoHiveDriver implements IMojoHiveDriver {

	private Exception _lastException;

	public MojoHiveDriver() {
		_lastException = null;
	}

	public int GetLastErrorCode() {
		if (_lastException != null) {
			return -1;
		} else {
			return 0;
		}
	}

	public String GetLastExceptionMessage() {
		if (_lastException != null) {
			return _lastException.getMessage();
		} else {
			return "";
		}
	}

	public String GetLastExceptionStackTrace() {
		if (_lastException != null) {
			return StackTraceUtil.getStackTrace(_lastException);
		} else {
			return "";
		}
	}

	public int TestNamedPipeWrite(String pipename, String dataFilePath) {
		int bytes_written = 0;
		try {

			logUtil.LogMsg("Opening pipe for writing: " + pipename);
			// RandomAccessFile pipe = null; // WARNING: RandomAccessFile cannot
			// be opened as "w" (write-only) which QlikView requires.
			FileOutputStream filestrm = null;
			try {
				// pipe = new RandomAccessFile(pipename, "rw");
				filestrm = new FileOutputStream(pipename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// logUtil.LogMsg("Fatal exception follows!");
				this._lastException = e;
				e.printStackTrace();
			}
			// if( pipe != null ) {
			if (filestrm != null) {
				logUtil.LogMsg("Pipe connection successful. ");
				// Read the data file
				Path path = Paths.get(dataFilePath);
				byte[] data = Files.readAllBytes(path);

				// pipe.write(data);
				filestrm.write(data);
				bytes_written = data.length;
				filestrm.close();

				// pipe.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// logUtil.LogMsg("Fatal exception follows!");
			this._lastException = e;
			e.printStackTrace();
		}
		return bytes_written;
	}

	public String TestNamedPipeRead(String pipename) {
		String result = "";
		_lastException = null;

		try {

			// TO DO: re-write using java.io.FileInputStream

			logUtil.LogMsg("Opening pipe for reading: " + pipename);
			RandomAccessFile pipe = new RandomAccessFile(pipename, "rw");
			logUtil.LogMsg("Pipe connection successful. ");

			StringBuffer fullString = new StringBuffer();
			int i = 0;
			while (i < 100) {
				int charCode = pipe.read();
				if (charCode == 0) {
					// break;
				} else {
					// aChar = new Character((char)charCode).toString();
					fullString.append((char) charCode);
				}
				i++;
			}
			// System.out.println("Response: " + fullString);
			result = fullString.toString();

			// line = pipe.readLine();
			// result = line;
			// pipe.close();
			// return result;

			/*
			 * pipe.readLine() while ( (line = pipe.readLine()) != null ) {
			 * System.out.println(line); result += line; }
			 * 
			 * pipe.close(); //logUtil.LogMsg("Pipe closed. " );
			 */

			pipe.close();
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// logUtil.LogMsg("Fatal exception follows!");
			this._lastException = e;
			e.printStackTrace();
		}

		return result;
	}

	public int TestConnection(String driver, String url, String queuename,
			String username, String password) {
		int result = -1;

		_lastException = null;

		// Load the JDBC driver class
		try {
			logUtil.LogMsg("Loading driver: " + driver);
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			this._lastException = e;
			e.printStackTrace();
			// System.exit(1);
			return result;

		}

		try {

			//
			logUtil.LogMsg("Opening connection to: " + url);
			Connection con = DriverManager.getConnection(url, username,
					password);
			logUtil.LogMsg("Connection successful. ");

			con.close();
			logUtil.LogMsg("Connection closed. ");

			result = 0;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// logUtil.LogMsg("Fatal exception follows!");
			this._lastException = e;
			// e.printStackTrace();
		}

		return result;
	}

	public String QueryResultSetAsXML(String driver, String url,
			String queuename, String username, String password, String query) {
		String xml_result = null;
		boolean hit_error = false;

		_lastException = null;

		// Load the JDBC driver class
		try {
			logUtil.LogMsg("Loading driver: " + driver);
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			hit_error = true;
			this._lastException = e;
			// e.printStackTrace();

		}

		if (!hit_error) {
			try {

				//
				logUtil.LogMsg("Opening connection to: " + url);
				Connection con = DriverManager.getConnection(url, username,
						password);
				logUtil.LogMsg("Connection successful. ");

				// Run query
				logUtil.LogMsg("Creating statement. ");
				Statement stmt = con.createStatement();
				stmt.setFetchSize(2);
				stmt.execute("set hive.fetch.output.serde = org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe");
				logUtil.LogMsg("Running SQL (query): " + query);
				
				//String addfile="add file/home/bluestar/connector/bsilconn.py"
				
				xmlUtil.runInitializationQueries(con,stmt);
				
				ResultSet res = stmt.executeQuery(query);
				
				//System.out.println(res+"res....");
				
				//System.out.println("before");
				/*List l=query(query,con);
				
				System.out.println(l.get(1)+".."+l.size());*/
				
				/*
				try {
					System.out.println("before");
					while (res.next()) {
			             
											
			            
			        }
					System.out.println("after");
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				*/
				
				
				
				/*logUtil.LogMsg("results:");
				xml_result = xmlUtil.ConvertResultSetToXML(res);*/
				String timeStamp = new SimpleDateFormat("yyyyMMdd:HH:mm:ss").format(Calendar.getInstance().getTime());
				System.out.println("before"+timeStamp);
				//Object[][]ff= xmlUtil.executeQuery(res);
				xmlUtil.getHashMap(res);
				
				//System.out.println(Arrays.deepToString(ff));
				
				String timeStamp1 = new SimpleDateFormat("yyyyMMdd:HH:mm:ss").format(Calendar.getInstance().getTime());
				System.out.println("after"+timeStamp1);
			
				
				/*logUtil.LogMsg("results:before");
				JSONObject oj=xmlUtil.ResultSet2JSONObject(res);
				logUtil.LogMsg("results:after");
				xml_result=oj.toString();*/
				
				//ResultSet2JSONObject1(rs);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				// logUtil.LogMsg("Fatal exception follows!");
				this._lastException = e;
				// e.printStackTrace();
			}
		}

		return xml_result;
	}

	
	public List query(String query,Connection con) {
	    List result = null;
	    try {
	        QueryRunner qrun = new QueryRunner();
	        result = (List) qrun.query(con, query, new MapListHandler());
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return result;
	}
	
	public int QueryDebugResultSetToPipe(String driver, String url,
			String queuename, String username, String password, String query,
			String datapipeName) {
		// WARNING: this is a hard-coded path to a local file with QVX formatted
		// data.
		// String local_input_file =
		// "C:\\Users\\mojotech\\Projects\\Code\\Java\\MojoHiveTest\\CurrencyExchangeRate_debug.qvx";
		String local_input_file = "D:\\CurrencyExchangeRate_debug.qvx";

		// Run the named pipe write function.
		// Note: the datapipeName here is the local output file path.
		return this.TestNamedPipeWrite(datapipeName, local_input_file);
	}

	public int QueryResultSetToPipe(String driver, String url,
			String queuename, String username, String password, String query,
			String datapipeName) {
		int result = 0;

		_lastException = null;
		
		// Load the JDBC driver class
		try {
			logUtil.LogMsg("Loading driver: " + driver);
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			result = -1;
			this._lastException = e;
			// e.printStackTrace();
		}

		if (result >= 0) {

			try {

				// Open the JDBC connection
				logUtil.LogMsg("Opening connection to: " + url);
				Connection con = DriverManager.getConnection(url, username,
						password);
				logUtil.LogMsg("Connection successful. ");

				// Create the statement
				logUtil.LogMsg("Creating statement. ");
				Statement stmt = con.createStatement();

				// Run the SQL query
				logUtil.LogMsg("Running SQL (query): " + query);
				ResultSet res = stmt.executeQuery(query);
				
			

				// We must generate an XML description of the ResultSet schema
				// in the
				// QlikView "QvxTableHeader" XML format.
				// TO DO: This is a stub method, not implemented!
				String xml_table_header = this.GenerateQvxTableHeaderXML(res);

				// Write the result set to the pipe in QlikView data format.
				// TO DO: This is a stub method, not implemented!
				result = WriteResultSetToPipe(res, xml_table_header,
						datapipeName);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				// logUtil.LogMsg("Fatal exception follows!");
				this._lastException = e;
				// e.printStackTrace();
			}

		}

		return result;

	}

	private String GenerateQvxTableHeaderXML(ResultSet res) {
		String xml = null;

		// Get table name

		// Walk through columns of first row to get column names and data types.

		return xml;
	}

	private int WriteResultSetToPipe(ResultSet res, String xmlTableHeader,
			String datapipeName) {
		int result = 0;

		return result;
	}

	@Override
	public String QueryShowTableAsXML(String driver, String url,
			String queuename, String username, String password) {

		String xml_result = null;
		boolean hit_error = false;

		String query = "show tables";

		_lastException = null;

		// Load the JDBC driver class
		try {
			logUtil.LogMsg("Loading driver: " + driver);
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			hit_error = true;
			this._lastException = e;
			// e.printStackTrace();

		}

		if (!hit_error) {
			try {

				//
				logUtil.LogMsg("Opening connection to: " + url);
				Connection con = DriverManager.getConnection(url, username,
						password);
				logUtil.LogMsg("Connection successful. ");

				// Run query
				logUtil.LogMsg("Creating statement. ");
				Statement stmt = con.createStatement();

				logUtil.LogMsg("Running SQL (query): " + query);
				ResultSet res = stmt.executeQuery(query);

				xml_result = xmlUtil.ConvertResultSetToXML(res);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				// logUtil.LogMsg("Fatal exception follows!");
				this._lastException = e;
				// e.printStackTrace();
			}
		}

		return xml_result;

	}

}
