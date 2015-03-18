package nl.jchmb.utils;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class OverflowQueue<E> extends AbstractQueue<E> {
	private Queue<E> queue;
	private int overflowLimit;
	
	public OverflowQueue(int overflowLimit) {
		queue = new LinkedList<E>();
		this.overflowLimit = overflowLimit;
	}
	
	@Override
	public boolean offer(E e) {
		if (size() >= overflowLimit) {
			poll();
		}
		
		return queue.offer(e);
	}

	@Override
	public E peek() {
		return queue.peek();
	}

	@Override
	public E poll() {
		return queue.poll();
	}

	@Override
	public Iterator<E> iterator() {
		return queue.iterator();
	}

	@Override
	public int size() {
		return queue.size();
	}
	
	public void clear() {
		queue.clear();
	}
}
