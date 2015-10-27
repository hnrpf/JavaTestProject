package ThreadPool;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExecutor {
	
	private final LinkedList queue;
	private final HashSet<Worker> workers = new HashSet<Worker>();
	private final ReentrantLock mainLock = new ReentrantLock();
	private int coreNum;
	private int maxNum;
	
	public ThreadPoolExecutor(int coreNum){
		this.coreNum = coreNum;
//		this.maxNum = maxNum;
		queue = new LinkedList();
		for (int i = 0; i < coreNum; i++) {
			Worker worker = new Worker();
			worker.start();
			workers.add(worker);
		}
	}
	
	public  void execute(Runnable r){
		addWorker();
		synchronized(queue) {
            queue.addLast(r);
            queue.notifyAll();
        }
	}
	
	private void addWorker(){
		final ReentrantLock mainLock = this.mainLock;
		Worker worker = new Worker();
        mainLock.lock();
        try {
			int size = workers.size();
			if(size == this.coreNum){
				worker = null;
				return;
			}
			workers.add(worker);
			worker.start();
		} catch (Exception e) {
		}finally{
			mainLock.unlock();
		}
	}
	
	public int getWorkerCount(){
		int ret = this.coreNum;
		synchronized(workers){
			ret = workers.size();
		}
		return ret;
	}
	
	public void shutDown(){
		
	}
	
	private class Worker extends Thread{
		
		public void run(){
			Runnable r;
			while(true){
				synchronized(queue){
					while(queue.isEmpty()){
						try {
							queue.wait(20);
						} catch (InterruptedException e) {
						}
					}
					r = (Runnable)queue.removeFirst();
					try {
						r.run();
					} catch (Exception e) {
					}
				}
			}
		}
	}

}
