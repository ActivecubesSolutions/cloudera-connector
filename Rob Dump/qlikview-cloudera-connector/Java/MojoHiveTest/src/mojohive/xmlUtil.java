package mojohive;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import mojohive.StackTraceUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class xmlUtil {
	public static String ConvertResultSetToXML(ResultSet rs) {
		StringWriter sw = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element results = doc.createElement("Results");
			doc.appendChild(results);

			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			if(rs!=null){
			while (rs.next()) {
				if(rs!=null){
				Element row = doc.createElement("Row");
				results.appendChild(row);
				for (int i = 1; i <= colCount; i++) {
					String columnName = rsmd.getColumnName(i);
					Object value = rs.getObject(i);
					Element node = doc.createElement(columnName);
					
					
										
					if(value!=null){
					node.appendChild(doc.createTextNode(value.toString()));
					row.appendChild(node);
					}
					else{
						node.appendChild(doc.createTextNode("null"));
						row.appendChild(node);
					}
				}
				}
			}
			}
			
			DOMSource domSource = new DOMSource(doc);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					"yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			transformer.transform(domSource, sr);

			// System.out.println(sw.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(StackTraceUtil.getStackTrace(e));
		}
		return sw.toString();
	}

	public static void runInitializationQueries(Connection con, Statement stmt) throws SQLException {
		 String[] args= new String[2];
         args[0]="add jar /home/cm/connector/UDF.jar"; 
         args[1]="CREATE TEMPORARY FUNCTION collect_all AS 'javaclient.CollectAll'";
         //args[2]="set hive.exec.reducers.bytes.per.reducer=100000";
         
         for (String query:args)
         {
             stmt.execute(query);
         }

	}
}
