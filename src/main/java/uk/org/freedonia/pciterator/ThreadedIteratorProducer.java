package uk.org.freedonia.pciterator;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class ThreadedIteratorProducer<T> implements Runnable {
	
	private Iterator<T> iterator;
	private ArrayBlockingQueue<T> blockingQueue;

	public ThreadedIteratorProducer( Iterator<T> iterator, ArrayBlockingQueue<T> blockingQueue ) {
		this.iterator = iterator;
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		while ( iterator.hasNext() ) {
			try {
				blockingQueue.put( iterator.next() );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
