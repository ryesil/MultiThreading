package dead_lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
 Deadlock can occur when we lock our locks in different order, and it can occur not 
 only with reentrant lock but also nested synchronized blocks.
 There are two solutions:
 1) always lock your locks in the same order.
 2)
 */
public class Runner {
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	Random random = new Random();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
		while(true) {
			//Acquire locks
			boolean gotFirstLock = false;
			boolean gotSecondLock =false;
			try {//try to get the locks
				gotFirstLock=firstLock.tryLock();
				gotSecondLock=secondLock.tryLock();
			}finally {// this will always execute.
			//if we got the both locks, I return from the method
			//because it is now acquired the locks. isini bitirdi lock lari aldi.
				if(gotFirstLock&&gotSecondLock) {
					return;
				}
	//If we haven't gotton the both locks, We need to check each lock and unlock it.			
				if((gotFirstLock)) {
					firstLock.unlock();
				}
				if(gotSecondLock) {
					secondLock.unlock();
				}
			}
			
//If we don't acquire the locks, and it doesn't return, it will sleep for a bit
			//and try again.
			//Locks not acquired
			Thread.sleep(1);
		}
		
	}

	public void firstThread() throws InterruptedException {

		for (int i = 0; i < 10000; i++) {
			//lock1.lock();//If the orders are different this can cause a deadlock
			//lock2.lock();
			acquireLocks(lock1,lock2);
			
			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}

	}

	public void secondThread() throws InterruptedException {

		for (int i = 0; i < 10000; i++) {
			//lock2.lock();
			//lock1.lock();
			acquireLocks(lock2,lock1);
			try {
				Account.transfer(acc2, acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}

	}

	public void finished() {
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total Balance: " + (acc1.getBalance() + acc2.getBalance()));
	}

}
