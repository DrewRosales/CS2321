package cs2321;


import net.datastructures.Entry;
import net.datastructures.Map;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedMap<K,V> extends AbstractMap<K,V> {
	
	/* Use ArrayList or DoublyLinked list for the Underlying storage for the map of entries.
	 * Uncomment one of these two lines;
	 * private ArrayList<Entry<K,V>> table; 
	 * private DoublyLinkedList<Entry<K,V>> table;
	 */
	private ArrayList<mapEntry<K,V>> table = new ArrayList<>();
	public UnorderedMap() {}
		

	@Override
	public int size() {
		return table.size();
	}

	@Override
	public boolean isEmpty() {
		return table.size() == 0;
	}
	private int findIndex(K key) {
		for(int i=0; i<size(); i++) {
			if(table.get(i).getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}
	@Override
	public V get(K key) {
		int i = findIndex(key);
		if(i == -1) {
			return null;
		}
		return table.get(i).getValue();
	}

	@Override
	public V put(K key, V value) {
		int i = findIndex(key);
		mapEntry<K,V> e = new mapEntry<>(key, value);
		if(i==-1) {
			table.addLast(e);
			return null;
		}else {
			V oldValue = table.get(i).getValue();
			table.get(i).setValue(value);
			return oldValue;
		}
	}

	@Override
	public V remove(K key) {
		int i = findIndex(key);
		
		if(i==-1) {
			return null;
		}
		V value = table.get(i).getValue();
		if(i != size() -1) {
			table.set(i, table.get(size() - 1));
		}
		table.remove(size()-1);
		return value;
	}
	
	private class EntryIterator implements Iterator<Entry<K,V>>{
		int cur = 0;
		@Override
		public boolean hasNext() {
			return cur < table.size();
		}

		@Override
		public Entry<K, V> next() {
			if(cur == table.size()) { 
				throw new NoSuchElementException();
			}
			return table.get(cur++);
		}
		
	}
	public class EntryIterable implements Iterable<Entry<K,V>>{
		public Iterator<Entry<K,V>> iterator(){
			return new EntryIterator();
		}
	}
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}

}
