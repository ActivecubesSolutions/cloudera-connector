package mojopipes;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;

public class MojoPipeClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try { 
			// Connect to the pipe 
			String pipe_name = "\\\\.\\pipe\\mojotestpipe";
			RandomAccessFile pipe = new RandomAccessFile(pipe_name, "rw"); 
			
			
			
			//String echoText =  "[JAVA] Hello word from Java named pipes client.\n"; 
			// write to pipe 
			//pipe.write ( echoText.getBytes() ); 
			// read response 
			String echoResponse = pipe.readLine(); 
			System.out.println("[JAVA] Response received by Java client: " + echoResponse ); 
			
			
			pipe.close(); 
			 
			} catch (Exception e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
			}

	}

}
