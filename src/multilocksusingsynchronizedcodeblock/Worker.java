package multilocksusingsynchronizedcodeblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	/*
	 * When you call the synchronized method, that is gonna acquire intrinsic lock
	 * or monitor lock of the worked object here. So if one thread runs, and the another thread tries
	 * to run it, The other thread has to wait to acquire that lock until the first thread releases it.
	 * by exiting the method. this is fine but takes too long. The solution is to have different locks
	 * for each method. So that while one thread is running one method, another thread can run the second 
	 * method. Threads are synchronizing on different locks at the same time.
	 * 
	 */
	private Random random=new Random();
	private Object lock1=new Object();//objects have intrinsic lock
	private Object lock2=new Object();//We create two objects to have two locks.
	
	
	private List<Integer> list1=new ArrayList<>();
	private List<Integer> list2=new ArrayList<>();

	
	public void stageOne() {
		
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			list1.add(random.nextInt(100));
		}
		
	}
	
	public void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			list2.add(random.nextInt(100));
		}
		
	}
	
	public void process() {
		for(int i=0;i<1000;i++) {
			stageOne();
			stageTwo();
		}
	}
	public void main() {
		System.out.println("Starting....");
		long start=System.currentTimeMillis();
		
		Thread t1=new Thread(new Runnable() {

			public void run() {
			
				process();
			}
			
		});
		
		Thread t2=new Thread(new Runnable() {

			public void run() {
			
				process();
			}
			
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long  end=System.currentTimeMillis();
		System.out.println("Time taken: "+(end-start));
		System.out.println("List1: "+list1.size()+"; List2: "+list2.size());
	
	}
	
}
