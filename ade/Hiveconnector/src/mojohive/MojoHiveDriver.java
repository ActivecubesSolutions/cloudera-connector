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
import java.sql.SQLException;
import java.sql.Statement;

import mojohive.StackTraceUtil;

public class MojoHiveDriver implements IMojoHiveDriver {

	private Exception _lastException;
	private ResultSet  res;

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
	public boolean QueryResultSet(String driver, String url, String queuename, String username, String password, String query)
	{
		
		boolean hit_error = false;

		_lastException = null;

		// Load the JDBC driver class
		try {
			//logUtil.LogMsg("Loading driver: " + driver);
			Class.forName(driver);
			hit_error = true;
		} catch (ClassNotFoundException e) {
			this._lastException = e;
			return hit_error;
			
			//e.printStackTrace();

		}	

		if( hit_error)	{
			try{

				//
				//logUtil.LogMsg("Opening connection to: " + url);
				Connection con = DriverManager.getConnection(url, username, password);
			
				
				//logUtil.LogMsg("Connection successful. " );
				
				// Run query
				//logUtil.LogMsg("Creating statement. " );
				Statement stmt = con.createStatement();			    
				
				logUtil.LogMsg("Key1: Running SQL (query): " + query);
				//runInitializationQueries(con,stmt);
			    res = stmt.executeQuery(query);
				res.setFetchSize(1);
				logUtil.LogMsg("Key2: Ending JSON convertion");
				hit_error = true;
			}
			catch (Exception e) {
			this._lastException = e;
			
			hit_error = false;

			}
		}


		return hit_error;
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
	
	
	static void runInitializationQueries(Connection connection,Statement stmt) throws SQLException
	 {
	      
	    
	         //TODO Get the queries from a .hiverc file
	         String[] args= new String[2];
	         args[0]="add jar /home/bluestar/connector/UDF.jar"; 
	         args[1]="CREATE TEMPORARY FUNCTION collect_all AS 'javaclient.CollectAll'";
	         //args[2]="set hive.exec.reducers.bytes.per.reducer=100000";
	         
	         for (String query:args)
	         {
	             stmt.execute(query);
	         }
	   
	    

	 }

	@Override
	public String Chunkprocess() throws SQLException {
		 while(res.next()) {
			 //System.out.println(res.getCursorName());
		return res.getCursorName();
		}
		return "false";
	}

	@Override
	public String getcloumn(String ff) throws SQLException {
		return res.getNString("");
	}

}
