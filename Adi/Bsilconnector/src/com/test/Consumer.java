package com.test;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
	 
private BlockingQueue<Message> queue;
     
    public Consumer(BlockingQueue<Message> q){
        this.queue=q;
    }
    long lStartTime = new Date().getTime();
    @Override
    public void run() {
        try{
            Message msg;
            //consuming messages until exit message is received
            while((msg = queue.take()).getMsg() !="flase"){
              //System.out.println("Consumed "+msg.getMsg());
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        long lEndTime = new Date().getTime();
        double difference = lEndTime - lStartTime;
        System.out.println("Elapsed milliseconds: " + difference/1000);
    }
}
