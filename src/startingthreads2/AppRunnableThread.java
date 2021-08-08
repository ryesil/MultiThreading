package startingthreads2;

/*
 * Runnable is an interface which has only one method in it "run".
 * To Run this we need to declare an instance of Thread class.
 * Then, we pass an instance of Runner class to the constructor of the thread class.
 * Thread t1=new Thread(new Runner()); then call t1.start().
 */


class Runner implements Runnable {

	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println("Hello "+ i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}

public class AppRunnableThread {
public static void main(String[] args) {
	
	Thread t1=new Thread(new Runner());
	Thread t2=new Thread(new Runner());
	t1.start();
	t2.start();
	
	
}
}
