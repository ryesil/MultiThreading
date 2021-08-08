package semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
public static void main(String[] args) throws Exception {
	//Semaphore sem=new Semaphore(1);//Count goes into the constructor
//count: the number of available permits of the semaphore	
	//sem.release();//increments the permits.decrement the permits.
	//sem.acquire();//Semaphore with one lock is like locking and unlocking.
// only difference is that we can happily release from different threads to where you
// did the acquire.With a lock, we have to unlock from the same thread that we locked from.
//There is no requirement with Semaphore. Semaphore is used to control access to some resource.
	//System.out.println("Available permits: "+ sem.availablePermits());
	Connection.getInstance().connect();
	ExecutorService executor =Executors.newCachedThreadPool();
//Creates a thread pool that creates new threads as needed, 
//but will reuse previously constructed threads when they are available.
//These pools will typically improve the performance of programs that
//execute many short-lived asynchronous tasks
	for(int i=0;i<200;i++) {
		executor.submit(new Runnable() {

			@Override
			public void run() {
				Connection.getInstance().connect();
			}
	
		});
	}
	executor.shutdown();
	executor.awaitTermination(1, TimeUnit.DAYS);
}
	
}
