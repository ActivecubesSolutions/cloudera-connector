package mojohive;

import java.sql.SQLException;

public interface IMojoHiveDriver {
	
	
	int TestConnection(String driver, String url, String queuename, String username, String password );
	String TestNamedPipeRead(String pipename);
	int TestNamedPipeWrite(String pipename, String dataFilePath);

	boolean QueryResultSet(String driver, String url, String queuename, String username, String password, String query);	
	int QueryResultSetToPipe(String driver, String url, String queuename, String username, String password, String query, String datapipeName);	
	
	String GetLastExceptionMessage();
	String GetLastExceptionStackTrace();
	int GetLastErrorCode();
	String Chunkprocess() throws SQLException;
	String getcloumn(String ff) throws SQLException;

}
