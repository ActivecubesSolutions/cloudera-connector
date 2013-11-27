package mojomysql;


import java.sql.*;
import java.io.StringWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class SimpleMySQLDriver implements ISimpleMySQLDriver {


		
	    public void RunStaticQuery(String query)	
	    {

	     try {
	     Statement stmt;

	     //Register the JDBC driver for MySQL.
	     Class.forName("com.mysql.jdbc.Driver");

	     //Define URL of database server for
	     // database named sakila on the localhost
	     // with the default port number 5510.
	     String url =
	     "jdbc:mysql://localhost:5510/sakila";

	     //Get a connection to the database for a
	     // user named root with a blank password.
	     // This user is the default administrator
	     // having full privileges to do anything.
	     Connection con =
	     DriverManager.getConnection(
	     url,"root", "irvine09");

	     //Display URL and connection information
	     System.out.println("URL: " + url);
	     System.out.println("Connection: " + con);

	     //Get a Statement object
	     stmt = con.createStatement();

	     // Execute the SQL statement that was passed in.
	     stmt.executeUpdate(
	     query
	     );
	     


	     con.close();
	     }catch( Exception e ) {
	     e.printStackTrace();
	     System.out.println( StackTraceUtil.getStackTrace(e) );
	     
	     }//end catch
	     
	    } // end RunStaticQuery()	    
	    
	    
	    public String QueryResultSetAsString(String driver, String url, String username, String password, String query)	
	    {

	    	String str_res = null;
	     try {
	     Statement stmt;

	     //Register the JDBC driver for MySQL.
	     //Class.forName("com.mysql.jdbc.Driver");
	     Class.forName(driver);

	     //Define URL of database server for
	     // database named sakila on the localhost
	     // with the default port number 5510.
	     //String url = "jdbc:mysql://localhost:5510/sakila";

	     //Get a connection to the database for a
	     // user named root with a blank password.
	     // This user is the default administrator
	     // having full privileges to do anything.
	     Connection con =
	     DriverManager.getConnection(url, username, password);
	     //DriverManager.getConnection(url,"root", "irvine09");

	     //Display URL and connection information
	     System.out.println("URL: " + url);
	     System.out.println("Connection: " + con);

	     //Get a Statement object
	     stmt = con.createStatement();

	     // Execute the SQL statement that was passed in.
	     ResultSet res = stmt.executeQuery(query);
	     
	     str_res = ConvertResultSetToXML(res);
	     
	     res.close();

	     con.close();
	     }catch( Exception e ) {
	     e.printStackTrace();
	     System.out.println( StackTraceUtil.getStackTrace(e) );
	     
	     }//end catch
	     
	     return str_res;
	    } // end GetResultSet()
	    
	    public String ConvertResultSetToXML(ResultSet rs)
	    {
	    	StringWriter sw = null;
	    	try 
	    	{
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.newDocument();
	        Element results = doc.createElement("Results");
	        doc.appendChild(results);
	        
	        
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int colCount = rsmd.getColumnCount();

	        while (rs.next()) {
	          Element row = doc.createElement("Row");
	          results.appendChild(row);
	          for (int i = 1; i <= colCount; i++) {
	            String columnName = rsmd.getColumnName(i);
	            Object value = rs.getObject(i);
	            Element node = doc.createElement(columnName);
	            node.appendChild(doc.createTextNode(value.toString()));
	            row.appendChild(node);
	          }
	        }
	        DOMSource domSource = new DOMSource(doc);
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
	        sw = new StringWriter();
	        StreamResult sr = new StreamResult(sw);
	        transformer.transform(domSource, sr);

	        System.out.println(sw.toString());
		     }catch( Exception e ) {
			     e.printStackTrace();
			     System.out.println( StackTraceUtil.getStackTrace(e) );
			     
			 }	       
	    	
	        return sw.toString();

	    }
	    
}


