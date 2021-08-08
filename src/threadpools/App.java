package threadpools;
/*
 * Threadpool is;
 * Having a number of workers in a factory
 * ExecutorService executor=Executors.newFixedThreadPool(2);
 * In above case we have 2 workers.
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
private int id;
	
public Processor (int id) {
	this.id=id;
}
	
	public void run() {//put some code you want to run :)

		System.out.println("Starting; "+ id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed: "+id);
	}
	
	
	
	
}



public class App {
public static void main(String[] args) {
	
	ExecutorService executor=Executors.newFixedThreadPool(2);
	//submit the teasks to executer
	long start=System.currentTimeMillis();
	for(int i=0;i<5;i++) {
		executor.submit(new Processor(i));
	}

	
	executor.shutdown();
	System.out.println("All tasks submitted.");
	try {
		executor.awaitTermination(1, TimeUnit.DAYS);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("All tasks comleted");
	System.out.println("Time took to complete: "+(System.currentTimeMillis()-start));
}
}
