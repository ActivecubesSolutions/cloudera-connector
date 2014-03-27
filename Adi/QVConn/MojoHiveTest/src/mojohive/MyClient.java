package mojohive;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.hadoop.hive.jdbc.HiveConnection;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.service.HiveClient;
import org.apache.hadoop.hive.service.HiveServerException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/*import com.facebook.thrift.TException;
import com.facebook.thrift.protocol.TBinaryProtocol;
import com.facebook.thrift.protocol.TProtocol;
import com.facebook.thrift.transport.TSocket;
import com.facebook.thrift.transport.TTransport;
import com.facebook.thrift.transport.TTransportException;
*/

public class MyClient {
  public static final int THREADS_NUMBER = 10;
  
  public static class Worker implements Callable<String> {
    
    TTransport transport;
    TProtocol protocol;
    HiveClient client;

    public Worker() {
      transport = new TSocket("192.168.1.249", 10000);
      protocol = new TBinaryProtocol(transport);
      client = new HiveClient(protocol); 
    }

    public String call() throws Exception {
    	
      transport.open();
     
    /*  client.execute("add jar /home/zhoumin//hadoop/mapreduce/zhoumin/dist/zhoumin-0.00.1.jar");
      client.execute("CREATE TEMPORARY FUNCTION strlen AS 'hadoop.hive.udf.UdfStringLength'");*/
      
      
      client.execute("SELECT id FROM emp ");
      String row = client.fetchOne();
      System.out.println(row);
      transport.close();
      return row;
    }
    
  }
  
  public static void main(String[] args) throws TTransportException,
      TException, HiveServerException, MetaException {
    ExecutorService exec = Executors.newCachedThreadPool();
    
    ArrayList<Future<String>> results = new ArrayList<Future<String>>();
    for(int i = 0; i < THREADS_NUMBER; i++) {
      results.add(exec.submit(new Worker()));
    }
    
    for(Future<String> fs : results) {
      try {
        System.out.println(fs.get());
      }catch (InterruptedException e) {
        System.out.println(e);
      } catch(ExecutionException e) {
        System.out.println(e);
      } finally {
        exec.shutdown();
      }
    }
  }
}