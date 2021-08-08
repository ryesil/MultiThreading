package callable_and_future;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {
public static void main(String[] args) {
/*
 Callable and Future are two classes that enable you to get return
 results from your threads. They also allow your thread code to throw
 Exceptions. 
 Callable is a parameterized class.
 Parameter is the type that you want to return from your running thread code.
 Future doesn't have to return a result.
  Future<?> future = executor.submit(new Callable<void>() 
  and return null.
 */
ExecutorService executor=Executors.newCachedThreadPool();
//How do you get a return from the below code;

    Future<Integer> future = executor.submit(new Callable<Integer>() {
//We assign the return duration to future. Then we use future.get to get the return duration
	@Override
	public Integer call() throws Exception {
		Random random=new Random();
		int duration=random.nextInt(4000);
		System.out.println("Starting...");
		
		if(duration> 1000) {
			throw new IOException("Sleeping for too long..."); 
		}
		
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finished.");
		return duration;
	}
	
});
executor.shutdown();
//executor.awaitTermination(1, TimeUnit.DAYS);
//managerial thread finishes when my thread finishes.
try {
	System.out.println("Result is "+future.get());
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (ExecutionException e) {
	IOException ex=(IOException) e.getCause();
	System.out.println(ex.getMessage());//Sleeping for too long...
}	
//future.get() will just block until the thread associated with this future has terminated.
}
	
	
}
