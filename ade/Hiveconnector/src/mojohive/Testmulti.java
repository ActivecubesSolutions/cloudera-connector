package mojohive;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Testmulti implements Runnable {
	static MojoHiveDriver hive_driver;
	private static String username = "hive";
	private static String password = "hive";
	private static String queuename = "";
	private static String url = "jdbc:hive2://192.168.1.249:10000/default";
	private static String drivername = "org.apache.hive.jdbc.HiveDriver";

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		
		try {
			
			String gg=hive_driver.Chunkprocess();
			if(gg.equalsIgnoreCase("false")){
				System.exit(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		hive_driver = new MojoHiveDriver();
		String sql_query = "select * from emp ";
		boolean test= hive_driver.QueryResultSet(drivername, url,  queuename,  username, password, sql_query);
		if(test){
			
			String ff=" ";
			System.out.println(hive_driver.getcloumn(ff));
			System.out.println(hive_driver.Chunkprocess());
			
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 2; i++) {
            Runnable worker = new Testmulti();
            executor.execute(worker);
          }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

	


	

}
