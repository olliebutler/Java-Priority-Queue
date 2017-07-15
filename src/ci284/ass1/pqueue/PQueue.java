


package ci284.ass1.pqueue;

public class PQueue<T> {

	private PQueueItem<T> head;
	public static enum ORDER {
		ASC, DESC;
	}
	public static ORDER DEFAULT_ORDER;
	private ORDER order;
	
	/**
	 * The default constructor for a PQueue, with the default order for priorities
	 */
	public PQueue() {
		
		order = ORDER.DESC;
		
	}
	
	/**
	 * The constructor to make a new PQueue with a given order
	 * @param order
	 */
	public PQueue(ORDER order) {
		
		switch(order){
		
		case DESC:
			
			this.order = ORDER.DESC;
			
			break;
			
		case ASC:
			
			this.order = ORDER.ASC;
			
			break;
		}
		
	}

	/**
	 * Remove and return data from the item at the front of the queue
	 * @return
	 */
	public T pop() {
		
		T temp = head.getData();
		head = head.getNext();
		return temp;
		
	}

	/**
	 * Return the data from the item at the front of the queue, without removing the item itself
	 * @return
	 */
	public T peek() {
		
		return head.getData();
		
	}

	/**
	 * Remove and return the item at the front of the queue
	 * @return
	 */
	public PQueueItem<T> popItem() {
		
		PQueueItem<T> temp = head;
		head = head.getNext();
		return temp;
		
	}
	
	/**
	 * Return the item at the front of the queue without removing it
	 * @return
	 */
	public PQueueItem<T> peekItem() {
		
		return head;

	}

	/**
	 * Insert a new item into the queue, which should be put in the right place according to its priority.
	 * That is, is order == ASC, then the new item should appear in the queue before all items with a
	 * HIGHER priority. If order == DESC, then the new item should appear in the queue before all items
	 * with a LOWER priority.
	 * @param data
	 * @param priority
	 */
	public void insert(T data, int priority) {
		
		PQueueItem<T> newItem = new PQueueItem<T>(data, priority);
		PQueueItem<T> previous = null;
		PQueueItem<T> current = head;
		
		switch(order){
				
				case DESC:
					
					while(current != null && priority < current.getPriority()){
						previous = current;
						current = current.getNext();	
					}
					break;
					
				case ASC:
					
					while(current != null && priority > current.getPriority()){
						previous = current;
						current = current.getNext();
					}
					break;
				}
		
		
		if (previous == null){
			newItem.setNext(head);
			head = newItem;
		}
		else{
			previous.setNext(newItem);
			newItem.setNext(current);
		}
		
	}
	
	/**
	 * Return the length of the queue
	 * @return
	 */
	public int length() {
		
		if (head == null){
			return 0;
		}
		else{
			int i = 0;
			
			PQueueItem<T> previous = null;
			PQueueItem<T> current = head;
			
			while(current != null){
				previous = current;
				current = current.getNext();
				i++;
			}
			return i;
			
		}
		
	}

	public String toString() {
		int i = length();
		PQueueItem<T> current = head;
		StringBuffer sb = new StringBuffer();
		while (i > 0) {
			sb.append(current.toString());
			if (i > 1)
				sb.append(": ");
			current = current.getNext();
			i--;
		}
		return sb.toString();
	}

}

