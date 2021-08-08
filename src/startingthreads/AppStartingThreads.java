package startingthreads;
/*Start a thread
 *2 ways to start a thread
 *   a) first method, Extend thread class, thread class has a method run, we need to override it
 *   Thread has sleep method.
 *   In order to run this Runner class in its own thread, we need to declare an
 *   instance of this class. 
 *   Runner runner1=new Runner(), then we need to use start() method so that,
 *   We urge thread class to go and look for its thread.
 *   The point of multiThreading is running codes simultaneously.
 * 	b)second method, implement runnable interface and pass to the constructor of the thread class.
 * 
 */

class Runner extends Thread {

	public void run() {

		for( int i=0;i<10;i++) {
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
public class AppStartingThreads {
public static void main(String[] args) {
	Runner runner1=new Runner();
	runner1.start();
	
	Runner runner2=new Runner();
	runner2.start();

}
	
}
