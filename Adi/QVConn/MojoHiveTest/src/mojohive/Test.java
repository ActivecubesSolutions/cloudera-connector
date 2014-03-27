package mojohive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;

import net.sf.json.JSONArray;

import org.apache.hadoop.hive.jdbc.HiveStatement;
import org.apache.hadoop.hive.service.HiveClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Test {
	
	
		    
		    TTransport transport;
		    TProtocol protocol;
		    HiveClient client;
		    
		    public Test() {
		      transport = new TSocket("192.168.1.249", 10000);
		      TProtocol protocol = new TBinaryProtocol(transport);
		      client = new HiveClient(protocol);
		    }

		    public  String call() throws Exception {
		      transport.open();
		       client.execute("SELECT id FROM emp ");
		      List<String> row = client.fetchAll();
		      transport.close();
		      JSONArray jsonA = JSONArray.fromObject(row);
		      return jsonA.toString() ;
		    }
		    
	
		   
	private Exception _lastException;

	public String QueryResultSetAsXML(String driver, String url,
			String queuename, String username, String password, String query) {
		String xml_result = null;
		boolean hit_error = false;

		

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
				
				//String addfile="add file/home/bluestar/connector/bsilconn.py"
				ResultSet res = stmt.executeQuery(query);
				
			
				
				
			
				

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
