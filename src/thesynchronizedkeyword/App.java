package thesynchronizedkeyword;
/*
 * To wait for the thread to finish their execution we use join() method on on Thread instances
 * t1.join();
 * t2.join(); but this doesn't quite work. So these two thread might try to change the  shared value
 * at the same time. Since They both run in their own threads, one can increment a value and store it back
 * while other thread is still updating an old value. see the example below.
 * So instead of letting both thread to change the variable we wrap the variable inside a synchronized method 
 * then let the threads use this method. So here intrinsic lock must be required by a thread to use the object.
 * We don't need to use volatile keyword because the synchronized keyword will guarantee that the current state
 * of the variable is visible to all threads.This is what volatile does. It makes sure that the current state is visible
 * by all threads.
 * 
 */
public class App {
	private int count =0;
	
	public synchronized void increment() {
		count++;
	}

	public static void main(String[] args) {
	App app=new App();
	app.doWork();

	}
	public void doWork() {
		Thread t1=new Thread(new Runnable() {
			public void run() {
		for(int i=0;i<10000;i++) {
			increment();
			//count++;
			//count=count+1; Three steps here remember.
			
		}
				
			}
			
		});
		Thread t2=new Thread(new Runnable() {
			public void run() {
		for(int i=0;i<10000;i++) {
			//count++;
			increment();
			
		}	
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
		
		System.out.println("count is "+count);

}
}
