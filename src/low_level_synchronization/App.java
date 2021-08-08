package low_level_synchronization;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Processor processor=new Processor();
		
		Thread t1=new Thread(new Runnable() {

			@Override
			public void run() {
			try {
				processor.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		});
		Thread t2=new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.cosume();
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
}
