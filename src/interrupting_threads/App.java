package interrupting_threads;

import java.util.Random;

public class App {

	public static void main(String[] args) throws InterruptedException {
	
		
		System.out.println("starting.");
		Thread t1=new Thread(new Runnable() {

			@Override
			public void run() {
			
				Random ran=new Random();
				for(int i=0;i<1E8;i++) {
//			if(Thread.currentThread().isInterrupted()) {
//				System.out.println("interrupted!");
//				break;
//			}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println("interrupted!");
						break;
					}
					Math.sin(ran.nextDouble());
				}		
			}
		});

		t1.start();
		Thread.sleep(500);
		t1.interrupt();
//sets a flag that tells the thread that it has been interrupted and there
		t1.join();
		
		System.out.println("Finished.");
		
	}

}
