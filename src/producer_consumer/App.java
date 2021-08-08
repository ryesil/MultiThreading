package producer_consumer;
/*
 * ArrayBlockingQueue is a data structure which can hold 
 * data items, the type od which you can choose as with arraylist
 * That queue works in the way that you can add items to it and you
 * can remove items from it.
 * the first the Items you remove will be the first ones that you added.
 * First in First out(Queue)
 * Good thing about these classes in the concurrent package are that
 * they are thread safe.
 * import java.util.concurrent.ArrayBlockingQueue;
 *	import java.util.concurrent.BlockingQueue;
 *We don't have to worry about thread synchronization
 *put and take in concurrent packages is great put waits until there is a spot
 *take will wait until there is an item in the queue.
 */

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	private static BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(10);//size of 10
public static void main(String[] args) throws InterruptedException {
	Thread t1=new Thread(new Runnable() {
		public void run() {
		try {
			producer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	});
	
Thread t2=new Thread(new Runnable() {
	public void run() {
		try {
			consumer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
});
	
t1.start();
t2.start();
t1.join();
t2.join();

}
private static void producer() throws InterruptedException {
	Random random=new Random();
	while(true) {
		queue.put(random.nextInt(100));
		
	}
}

private static void consumer() throws InterruptedException {
	Random random=new Random();
	while(true) {
		Thread.sleep(100);
		
		//if(random.nextInt(10)==0||random.nextInt(10)==9||random.nextInt(10)==5||random.nextInt(10)==1) {
			Integer value =queue.take();
			System.out.println("Taken value: "+ value+ ": Queue size is: "+ queue.size());
		//}
		
		
	}
}

}
