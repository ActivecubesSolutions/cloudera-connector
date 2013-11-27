package mojomysql;

public interface ISimpleMySQLDriver {
	
	void RunStaticQuery(String query);
	 
	String QueryResultSetAsString(String driver, String url, String username, String password, String query);	

}
