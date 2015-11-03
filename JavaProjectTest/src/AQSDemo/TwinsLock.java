package AQSDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 设计一个同步工具，该工具在同一时刻，只能有两个线程能够并行访问，超过限制的其他线程进入阻塞状态。对于这个需求，可以利用同步器完成一个这样的设定，
 * 定义一个初始状态，为2，一个线程进行获取那么减1，一个线程释放那么加1，状态正确的范围在[0，1，2]三个之间，当在0时，
 * 代表再有新的线程对资源进行获取时只能进入阻塞状态（注意在任何时候进行状态变更的时候均需要以CAS作为原子性保障）。
 * 由于资源的数量多于1个，同时可以有两个线程占有资源，因此需要实现tryAcquireShared和tryReleaseShared方法
 * @author weifu
 *
 */
public class TwinsLock implements Lock{
	private final Sync sync = new Sync(2);
	
	private final static class Sync extends AbstractQueuedSynchronizer{

		public Sync(int count) {
			if(count <= 0){
				throw new IllegalArgumentException("the count must be large than zero");
			}
			super.setState(count);
		}
		
		@Override
		public int tryAcquireShared(int reduceCount){
			for (;;) {
				if (hasQueuedPredecessors())	//这个判断是保证线程有序性的关键，判断是否有比当前线程等待时间更长的线程，链表最前面的节点是否是自己。
                    return -1;
				int current = getState();
				int newCount = current - reduceCount;
				if(newCount < 0 || compareAndSetState(current, newCount))
					return newCount;
			}
		}
		
		@Override
		 public boolean tryReleaseShared(int returnCount){
			for(;;){
				int current = getState();
				int newCount = current + returnCount;
				if(compareAndSetState(current, newCount))
				return true;
			}
		}
		
	}

	@Override
	public void lock() {
		sync.acquireShared(1);
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireSharedInterruptibly(1);
		
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquireShared(1) >= 0;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		 return sync.tryAcquireSharedNanos(1, unit.toNanos(time));

	}

	@Override
	public void unlock() {
		sync.releaseShared(1);

		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
