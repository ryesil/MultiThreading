package waitandnotify;

import java.util.Scanner;

/*
 * 
 * Wait and Notify
 * 
 * 
 */


public class Processor {
	//Intrinsic lock of processor
	public void produce() throws InterruptedException {
synchronized (this) {//meaning of this: Intrinsic lock of Processor
	System.out.println("Producer thread is running...");
	wait();// wait is the method of ancestor of all objects, object class
	System.out.println("Resumed.");	
	//wait will cause this synchronized block to lose control and hand it over to 
	//consume
}
	}

	public void consume() throws InterruptedException {
		Scanner scanner=new Scanner(System.in);
		Thread.sleep(2000);
		synchronized (this) {//this: is locking the synchronized on the same object.
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify();
			Thread.sleep(5000);
			// notify will notify the other thread to take control of the lock
			//If the other thread is waiting, this will notify that thread.
			// It notifies all threads, so if we have one thread waiting, this is great
			// after we hit the start button, produce starts the first  thread,
			//Then it relases the key because of the wait() method.
			//Then second thread starts it prints "waiting for return key" Then waits for us to 
			//hit a key because of the scanner. after we hit a key, then second thread hands over the lock
			// to the first thread. Then first thread continues from wait block and prints "Resumed". 
			//Important: Notify will not release the lock, it will wait until the thread block is finished.
			//Notifying is one thing and finising the thread another thing before releasing the lock.
		}
	}

}
