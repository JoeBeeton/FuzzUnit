package uk.org.freedonia.pciterator;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;


public class IteratorWrapper<T> implements Iterator<T>, Closeable {
	
	private Iterator<T> iterator;
	private ArrayBlockingQueue<T>  blockingQueue;
	private ThreadedIteratorProducer<T> threadedProducer;
	private Thread producerThread;

	public IteratorWrapper( Iterator<T> iterator, int bufferSize ) {
		this.iterator = iterator;
		this.blockingQueue = new ArrayBlockingQueue<T>( bufferSize );
		initialSizeProducer();
	}

	private void initialSizeProducer() {
		threadedProducer = new ThreadedIteratorProducer<T>( iterator, blockingQueue  );
		producerThread = new Thread( threadedProducer );
		producerThread.start();
	}

	@Override
	public boolean hasNext() {
		boolean hasNext = false;
		if ( !blockingQueue.isEmpty() ) {
			hasNext = true;
		} else {
			if ( producerThread.isAlive() ) {
				while ( producerThread.isAlive() && blockingQueue.isEmpty() ) {
					try {
						Thread.sleep( 10 );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} 
				if ( !blockingQueue.isEmpty() ) {
					hasNext = true;
				}
			}
		}
		return hasNext;
	}

	@Override
	public T next() {
		try {
			return blockingQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws IOException {
		try {
			producerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
