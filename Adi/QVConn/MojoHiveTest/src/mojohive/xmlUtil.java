package mojohive;

import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import mojohive.StackTraceUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.Gson;

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
						node.appendChild(doc.createTextNode("NA"));
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

			System.out.println(sw.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(StackTraceUtil.getStackTrace(e));
		}
		return sw.toString();
	}
	
	public static final JSONObject ResultSet2JSONObject(ResultSet rs) {
        JSONObject element = null;
        JSONArray joa = new JSONArray();
        JSONObject jo = new JSONObject();
        int totalLength = 0;
        ResultSetMetaData rsmd = null;
        String columnName = null;
        String columnValue = null;
        try {
                rsmd = rs.getMetaData();
                while (rs.next()) {
                        element = new JSONObject();
                        for (int i = 0; i < rsmd.getColumnCount(); i++) {
                                columnName = rsmd.getColumnName(i+1);
                                columnValue = rs.getString(columnName);
                                element.accumulate(columnName, columnValue);
                        }
                        joa.add(element);
                        totalLength ++;
                }
                jo.accumulate("result", "success");
                jo.accumulate("rows", totalLength);
                jo.accumulate("data", joa);
        } catch (SQLException e) {
                jo.accumulate("result", "failure");
                jo.accumulate("error", e.getMessage());
        }
        return jo;
}


	 public static final JSONObject ResultSet2JSONObject1(ResultSet resultSet) {
	        JSONObject element;
	        
	        JSONArray jsonArray = new JSONArray();
	        JSONObject jsonObject = new JSONObject();
	       	 int totalLength = 0;
	        ResultSetMetaData resultSetMetaData;
	        String columnName;
	        try {
	            resultSetMetaData = resultSet.getMetaData();
	            while (resultSet.next()) {
	                element = new JSONObject();
	                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
	                    columnName = resultSetMetaData.getColumnName(i);

	                    if (resultSetMetaData.getColumnType(i) == java.sql.Types.ARRAY) {
	                        element.accumulate(columnName, resultSet.getArray(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.BIGINT) {
	                        element.accumulate(columnName, resultSet.getInt(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.BOOLEAN) {
	                        element.accumulate(columnName, resultSet.getBoolean(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.BLOB) {
	                        element.accumulate(columnName, resultSet.getBlob(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.DOUBLE) {
	                        element.accumulate(columnName, resultSet.getDouble(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.FLOAT) {
	                        element.accumulate(columnName, resultSet.getFloat(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.INTEGER) {
	                        element.accumulate(columnName, resultSet.getInt(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.NVARCHAR) {
	                        element.accumulate(columnName, resultSet.getNString(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.VARCHAR) {
	                        element.accumulate(columnName, resultSet.getString(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.TINYINT) {
	                        element.accumulate(columnName, resultSet.getInt(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.SMALLINT) {
	                        element.accumulate(columnName, resultSet.getInt(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.DATE) {
	                        element.accumulate(columnName, resultSet.getDate(columnName));
	                    } else if (resultSetMetaData.getColumnType(i) == java.sql.Types.TIMESTAMP) {
	                        element.accumulate(columnName, resultSet.getTimestamp(columnName));
	                    } else {
	                        element.accumulate(columnName, resultSet.getObject(columnName));
	                    }
	                }
	                jsonArray.add(element);
	                totalLength++;
	            }
	            jsonObject.accumulate("result", "success");
	            jsonObject.accumulate("rows", totalLength);
	            jsonObject.accumulate("data", jsonArray);
	        } catch (SQLException e) {
	            jsonObject.accumulate("result", "failure");
	            jsonObject.accumulate("error", e.getMessage());
	            e.printStackTrace();
	            
	        }
	        return jsonObject;
	    }
	 
	 
	 
	 public static Object[][] executeQuery(ResultSet rs) throws SQLException{
		    
		    ResultSetMetaData rsMetaData = rs.getMetaData();
		    int columnCount = rsMetaData.getColumnCount();
		    ArrayList<Object[]> result = new ArrayList<Object[]>();
		    Object[] header = new Object[columnCount];
		    
		    
		    for (int i=1; i <= columnCount; ++i){
		        Object label = rsMetaData.getColumnLabel(i);
		        header[i-1] = label;
		    }
		    while (rs.next()){
		        Object[] str = new Object[columnCount];
		        for (int i=1; i <= columnCount; i++){
		            Object obj = rs.getObject(i);
		            str[i-1] = obj;
		        }
		        result.add(str);
		    }
		    int resultLength = result.size();
		    Object[][] finalResult = new Object[resultLength+1][columnCount];
		   // finalResult[0] = header;
		    for(int i=0;i<resultLength;i++){
		        Object[] row = result.get(i);
		        finalResult[i] = row;
		    }
		    finalResult[resultLength] = header;
		    return finalResult;                    
		}
	 
	 
	 public static JSONArray getHashMap(ResultSet rs_SubItemType) throws SQLException {
		 
		 ResultSetMetaData metaData = rs_SubItemType.getMetaData();
		 int colCount = metaData.getColumnCount();
		 List<Map<String, Object>> row =new ArrayList<Map<String, Object>>();
		 while (rs_SubItemType.next()) {
				 
		 Map<String, Object> columns = new HashMap<String, Object>();
		if(! rs_SubItemType.wasNull())
		 for (int i = 1; i <= colCount; i++) {
			 if(rs_SubItemType.getObject(i).equals("[]")){
				 String a = "[]";
				 Object b = a;
				 columns.put(metaData.getColumnLabel(i), b);
			 }
			 else 
			 columns.put(metaData.getColumnLabel(i), rs_SubItemType.getObject(i));
					 					 
		 }
		  
		 row.add(columns);
		 }
	 
		 JSONArray jsonA = JSONArray.fromObject(row); 
		System.out.println(jsonA.toString());
	 
		 return jsonA;
		  
		 }
	 
	 static void runInitializationQueries(Connection connection,Statement stmt) throws SQLException
	 {
	      
	    
	         //TODO Get the queries from a .hiverc file
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
