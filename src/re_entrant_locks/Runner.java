package re_entrant_locks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 Re-entrant lock
 alternative to using synchronized keyword.
 Implements lock interface
 once a thread acquired this lock and once
 thread is locked, it can lock again if it want too.
 And the lock below keeps counting the number of times it was used.
 we need to unlock it by the same number of times.
 we can lock this one thread at a time
 
 */

public class Runner {
	
private int count=0;
private Lock lock=new ReentrantLock();
private Condition cond=lock.newCondition();
//remember every object in java has a wait and notify because
//they are methods of the object class which all objects are derived from.
//However, lock has different wait and lock which come from Condition class.
// lock uses await and signal equivalent of wait and notify.
//await and signal are methods fo Condition.
//So we are getting the condition object from the lock that we are locking on.
//What happens down in the code. Works like Synchronized wait and notify
//First thread locks the lock and prints waiting then hand over the lock to second thread
//because of cond.await() and it waits or sleeps; Second thread locks on the lock, then prints 
//press the return key. Then waits for us to press a key. Then releases the lock because of cond.await()
//then sleeps and waits for the lock to be unlocked again.
//Then first thread gets the lock then prints woken up. It increases count to 10000; Then unlocks the lock
//Then second thread acquires the lock and increases the count to 20000.
private void increment() {
	for(int i=0;i<10000;i++) {
		count++;
	}
}
	
	public void firstThread() throws InterruptedException {
		lock.lock();
		System.out.println("Waiting....");
		cond.await();//same as wait of synchronized.
		System.out.println("woken up!");
		try {
		increment();//If this throws an exception on the method.
		} finally {// finally will always be called. guaranteed.
		//below code lock.unluck will never be called.That's why we need to
		//surround the method with try catch.
		lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException {
		Thread.sleep(1000);
		lock.lock();
		System.out.println("press the return key");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key");
		//wait for a key pressed.
		cond.signal();//this has to go with lock.unlock(). it doesn't really releases the 
		//lock without lock.unlock.
		
		try {
		increment();//This is the code we want to synchronized.
		} finally {
		lock.unlock();
	}
	}

	
	public void finished() {
		System.out.println("count is: "+count);
	}
}
