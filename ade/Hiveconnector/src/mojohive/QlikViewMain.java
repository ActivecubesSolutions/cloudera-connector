package mojohive;

import java.io.StringWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import mojohive.MojoHiveDriver;


public class QlikViewMain {


	// Correct driver name for Hive Server 2 is org.apache.hive.jdbc.HiveDriver
	private static String _drivername = "org.apache.hive.jdbc.HiveDriver";

	// Connection string to online instance is "jdbc:hive2://54.218.97.70:21050/;auth=noSasl"
	private static String _url = "jdbc:hive2://54.218.97.70:21050/;auth=noSasl";
	
	// We need a placeholder for some connection parameters 
	private static String _username = "";
	private static String _password = "";
	private static String _queuename = "";
	private static String _sqlquery = "SELECT * FROM billtocodes WHERE billtocode='3DAKE'";
	// TO DO: the output file path here must be edited for a given local environment.
	// This is just the full path to a local disk file.
	private static String _outputfile = "C:\\Users\\mojotech\\Projects\\Code\\Java\\MojoHiveTest\\MojoHiveTest_output.qvx";
	
	public static void main(String[] args) {

		logUtil.LogMsg("Hello World from mojohive.QlikViewMain.");

		int result = -1;
		   
		MojoHiveDriver hive_driver;
		hive_driver = new MojoHiveDriver();
		
		// For now do not bother to test the JDBC connection, we are just using local data.
		boolean test_connection = false;
		if(test_connection) {
			result = hive_driver.TestConnection(_drivername, _url, _queuename, _username, _password);
			
			if( result != 0 ) {
				logUtil.LogMsg("TestConnection() failed: " + hive_driver.GetLastExceptionMessage());
				logUtil.LogMsg("Stack trace: " + hive_driver.GetLastExceptionStackTrace());
			}
			else {
				logUtil.LogMsg("TestConnection() successful.");
			}
		}

		// Call the actual query function.
		
		logUtil.LogMsg("Running SQL (query): " + _sqlquery);
		
		// IMPORTANT: For Java-only testing we just use an ordinary local disk file name as the pipe name.
		//            The driver object just writes the output data stream to the file, which can then be  
		//            examined in a binary editor or opened directly from QlikView via File | Open (*.qvx).
		String dataPipeName = _outputfile;
		
		// Run the actual query and write the output stream to a file.
		//hive_driver.QueryResultSetToPipe(_drivername, _url, _queuename, _username, _password, _sqlquery, dataPipeName);
		hive_driver.QueryDebugResultSetToPipe(_drivername, _url, _queuename, _username, _password, _sqlquery, dataPipeName);
		if(hive_driver.GetLastErrorCode() != 0 ) {
			logUtil.LogMsg("QueryResultSetToPipe() failed: " + hive_driver.GetLastExceptionMessage());
			logUtil.LogMsg("Full Stack trace: " + hive_driver.GetLastExceptionStackTrace());
		}
		else {
		    logUtil.LogMsg("SUCCESS: QlikView QVX format data written to file " + dataPipeName);
		}
				
		
		return;

	}

}
