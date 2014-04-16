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



public class MojoHiveMain {
	
	// WARNING: BAD DRIVER NAME org.apache.hadoop.hive.jdbc.HiveDriver !!! 
	//private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
	
	// Correct driver name for Hive Server 2 is org.apache.hive.jdbc.HiveDriver
	private static String drivername = "org.apache.hive.jdbc.HiveDriver";

	// Connection string to online instance is jdbc:hive2://54.218.97.70:21050/;auth=noSasl
	//private static String url = "jdbc:hive2://54.218.97.70:21050/;auth=noSasl";
	//private static String url = "jdbc:hive2://54.251.109.167:10000/default";
	private static String url = "jdbc:hive2://192.168.1.249:10000/default";
	//private static String url = "jdbc:hive://54.218.97.70:10000/default";
	//private static String url = "jdbc:hive://54.218.97.70:21050/default";
	//private static String url = "jdbc:hive2://54.218.97.70:21050/default";
	
	//private static String url = "jdbc:hive://localhost:10000/default";
	//private static String url = "jdbc:hive://54.218.97.70:10000/default";
	//private static String url = "jdbc:hive2://myhost.example.com:21050/;auth=noSasl";
	//private static String url = "jdbc:hive://54.218.97.70:21050/;auth=HS2NoSasl";

	//private static String url = "jdbc:hive2://54.218.97.70:21050/default;auth=HS2NoSasl";
	
	//private static String url = "jdbc:hive://54.218.97.70:21050/;auth=HS2NoSasl";
	//private static String url = "jdbc:hive2://54.218.97.70:21050/default;auth=HS2NoSasl";
	//private static String url = "jdbc:hive://54.218.97.70:21050/;auth=HS2NoSasl";
	//private static String url = "jdbc:hive://54.218.97.70:21050/";
	
	// Connection refused:
	//private static String url = "jdbc:hive://54.218.97.70:21050/default";
	//private static String url = "jdbc:hive://54.218.97.70:21050/default;auth=HS2NoSasl";
	//private static String url = "jdbc:hive://54.218.97.70:21050/default;auth=noSasl";
	//private static String url = "jdbc:hive://54.218.97.70:21050/;auth=noSasl";
	//private static String url = "jdbc:hive://54.218.97.70:21050/;auth=HS2NoSasl";
	
	// Invalid URL error (the 'hive2' appears to cause the problem):
	//private static String url = "jdbc:hive2://54.218.97.70:21050/;auth=HS2NoSasl";
	//private static String url = "jdbc:hive2://54.218.97.70:21050/default;auth=noSasl";
	//private static String url = "jdbc:hive2://54.218.97.70:21050/;auth=noSasl";
	//private static String url = "jdbc:hive2://54.218.97.70:10000/default";
	
	// Timeout error:
	
	
	// We need a placeholder for some connection parameters 
	private static String username = "hive";
	private static String password = "hive";
	private static String queuename = "";

	
	public static void main(String[] args) throws SQLException {
		

		logUtil.LogMsg("Hello World from mojohive.MojoHiveMain");

		   
		MojoHiveDriver hive_driver;
		hive_driver = new MojoHiveDriver();
		int result = hive_driver.TestConnection(drivername, url, queuename, username, password);
		
		if( result != 0 ) {
			logUtil.LogMsg("TestConnection() failed: " + hive_driver.GetLastExceptionMessage());
			logUtil.LogMsg("Stack trace: " + hive_driver.GetLastExceptionStackTrace());
		}
		else {
			logUtil.LogMsg("TestConnection() successful.");
		}
		
		//String sql_query ="from (select priceandinventoryprimaryid,productprimaryid from priceandinventory ) a select collect_all(a.priceandinventoryprimaryid) as priceandinventoryprimaryid, collect_all(a.productprimaryid) as productprimaryid";
		// Call the actual query function.
		//int sampleSize[]={10,1000,50000,100000,500000,1000000};
		//int sampleSize[]={10,500000,1000000};
		
			String sql_query = "select * from emp ";
			String xml_result = null;
			logUtil.LogMsg("Running SQL (query): " + sql_query);
			logUtil.LogMsg("Start");
			boolean test= hive_driver.QueryResultSet(drivername, url,  queuename,  username, password, sql_query);
			if(test){
				
				String ff=" ";
				System.out.println(hive_driver.getcloumn(ff));
				System.out.println(hive_driver.Chunkprocess());
				
			}
			
			logUtil.LogMsg("End of Execution");
			//System.out.println(hive_driver.TestStringOutput("<Results><Row><billtocode>abc</billtocode></Row></Results>"));
			if(hive_driver.GetLastErrorCode() != 0 ) {
				logUtil.LogMsg("QueryResultSetAsXML() failed: " + hive_driver.GetLastExceptionMessage());
				logUtil.LogMsg("Stack trace: " + hive_driver.GetLastExceptionStackTrace());
			}
			else {
			    //logUtil.LogMsg(xml_result);
				logUtil.LogMsg("Query Execution done");
			}
			
		return;
		
		


	}
	
    

}