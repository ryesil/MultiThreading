package countdownlatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
 * We can get into a lot of problems with thread synchronization
 *  if we try to access even so much as an integer from multiple threads
 *  at the same time.
 */
class Processor implements Runnable {
private CountDownLatch latch;//We don't use synchronized keyword here
							//Because CountDownLatch is threadSafe.
public Processor(CountDownLatch latch) {
	this.latch=latch;
}

	public void run() {
	System.out.println("Started.");
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	latch.countDown();
	}
}
public class App {
public static void main(String[] args) {
	//Thread safe class CountDownLacth
	//Count down from a number specified in the constructor
	CountDownLatch latch=new CountDownLatch(3);
	
	//This will create 3 threads and each thread will get one processor
	
	ExecutorService executor=Executors.newFixedThreadPool(3);
	for(int i=0;i<3;i++) {
		executor.submit(new Processor(latch));
		
	}
	try {
		latch.await();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Completed.");
}
}
