package ThreadPool;

public class ThreadPoolMain {

	public static void main(String args[]){
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5);
		System.out.println("init thread pool size: " + threadPool.getWorkerCount());
		for(int i=0; i<16; i++){
			Runnable task = new Runnable() {
				
				@Override
				public void run() {
					System.out.println("task number "+ Thread.currentThread() );
					
				}
			};
			threadPool.execute(task);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			System.out.println("thread pool size: " + threadPool.getWorkerCount());	
		}
	}
	
}
