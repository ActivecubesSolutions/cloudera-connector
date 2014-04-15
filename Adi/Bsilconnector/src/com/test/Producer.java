package com.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private BlockingQueue<Message> queue;
	ResultSet res;
    
    public Producer(BlockingQueue<Message> q,ResultSet res){
        this.queue=q;
        this.res=res;
    }
    @Override
    public void run() {
        //produce messages
    	
        try {
			while(res.next()){
				Message msg = new Message();
			    msg.setMsg(res.getCursorName());
			    try {
			         queue.put(msg);
			       // System.out.println("Produced "+msg.getMsg());
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
       
        Message msg1 = new Message();
        try {
        	msg1.setMsg("flase");
            queue.put(msg1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

}
