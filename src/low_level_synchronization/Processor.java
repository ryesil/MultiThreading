package low_level_synchronization;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
	//This is the shared list
private LinkedList<Integer> list=new LinkedList<Integer>();
private final int LIMIT = 10;//list's limit.
private Object lock=new Object();
	
public void produce() throws InterruptedException {
	int value=0;//java will autobox it when adding it to the list.
	while(true) {
		synchronized (lock) {
			// we want to make sure list has 10 items and if the list is full, thread will wait.
			while(list.size()==LIMIT) {
				lock.wait(); //We need to call wait on the object that we are locking on.
				// this thread will wait when the list is full.
			// If the list is full this will go to sleep and relinquish the lock and hand it over to the other thread.
		// Then other method will start running, when it reaches notify. it will wake up the first thread. 
		// We have the whileloop becasue we want to keep checking whether the list is full.
		//If you use wait make sure to surround it with while loop.
			}
			list.add(value++);// shared data must be in the synchronized block.
			lock.notify();//after adding hand over the lock to consume.
		}
		
	}
}

public void cosume() throws InterruptedException {
Random random=new Random();
while(true) {
	
	synchronized (lock) {
		while (list.size()==0) {
			lock.wait();
		}
	System.out.print("List size is: "+list.size());
	int value=list.removeFirst();
	System.out.println("; value is: "+ value);
	lock.notifyAll();//After removing hand over the lock to produce
	}
	Thread.sleep(random.nextInt(1000));
}
}


	
}
