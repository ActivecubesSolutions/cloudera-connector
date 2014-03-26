package mojohive;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public class MojoHiveMultiThreadedDriver implements Runnable{
	String parentQuery=null;
	Statement stmt = null;
	static String result=null;
	Integer start;
	Integer end;
	final static Integer THREAD_POOL=1;
	final static Integer BATCH_SIZE=1000;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String query=parentQuery+" limit "+ start;
			ResultSet  res = stmt.executeQuery(query);
			String singleResult=getHashMap(res);
			synchronized (singleResult) {
				result+=singleResult;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public MojoHiveMultiThreadedDriver(String parentQuery, Statement stmt,Integer start,Integer end) throws SQLException {
		super();
		this.parentQuery = parentQuery;
		this.stmt = stmt;
		this.start= start;
		this.end=end;
	}
	public static String getMultiThreadedResults(String parentQuery, Statement stmt) throws SQLException, InterruptedException{
		Thread[] threads = new Thread[THREAD_POOL];
		for(int i = 0; i < threads.length; i++) {
			int start=i*BATCH_SIZE;
			int end=start+BATCH_SIZE;
		    threads[i] = (new Thread(new MojoHiveMultiThreadedDriver(parentQuery,stmt,start,end))); 
		    threads[i].start(); 
		}
		
		for (Thread thread : threads)
		    thread.join();
		return result;
	}
	private String getHashMap(ResultSet rs_SubItemType) throws SQLException {
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
		return jsonA.toString();
	}

}
