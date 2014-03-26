package mojohive;

public interface IMojoHiveDriver {
	
	
	int TestConnection(String driver, String url, String queuename, String username, String password );
	String TestNamedPipeRead(String pipename);
	int TestNamedPipeWrite(String pipename, String dataFilePath);

	String QueryResultSetAsXML(String driver, String url, String queuename, String username, String password, String query);
	String QueryShowTableAsXML(String driver, String url, String queuename, String username, String password);
	int QueryResultSetToPipe(String driver, String url, String queuename, String username, String password, String query, String datapipeName);	
	
	String GetLastExceptionMessage();
	String GetLastExceptionStackTrace();
	int GetLastErrorCode();

}
