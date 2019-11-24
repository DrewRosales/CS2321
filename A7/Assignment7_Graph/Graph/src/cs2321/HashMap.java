package cs2321;


import net.datastructures.*;

public class HashMap<K, V> extends AbstractMap<K,V> implements Map<K, V> {

	/* Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
	 * 
	 */
	private UnorderedMap<K,V>[]  table;
	int   size;  // number of mappings(entries) 
	int   capacity; // The size of the hash table. 
	int     DefaultCapacity = 17; //The default hash table size





	/* Maintain the load factor <= 0.75.
	 * If the load factor is greater than 0.75, 
	 * then double the table, rehash the entries, and put then into new places. 
	 */
	double  loadfactor = 0.75;  

	/* Constructor that takes a hash size
	 * @param hashtable size: the number of buckets to initialize 
	 */
	public HashMap(int hashtablesize) {
		capacity = hashtablesize;
		table = (UnorderedMap<K,V>[]) new UnorderedMap[hashtablesize];
	}

	protected void createTable() {
		table = (UnorderedMap<K,V>[]) new UnorderedMap[capacity];
	}
	/* Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() {
		capacity = DefaultCapacity;
		table = (UnorderedMap<K,V>[]) new UnorderedMap[DefaultCapacity];
	}

	/* This method should be called by map an integer to the index range of the hash table 
	 */
	private int hashValue(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}


	//Used to resize the table.
	private void resize() {
		int newCap = capacity*2;
		ArrayList<Entry<K,V>>  buffer = new ArrayList<>(); 
		for(Entry<K, V> i: entrySet()) {
			buffer.addLast(i);
		}
		capacity=newCap;
		table = (UnorderedMap<K,V>[]) new UnorderedMap[capacity];
		size=0;
		for(Entry<K, V> i:buffer) {
			put(i.getKey(),i.getValue());
		}
	}
	/*
	 * The purpose of this method is for testing if the table was doubled when rehashing is needed. 
	 * Return the the size of the hash table. 
	 * It should be 17 initially, after the load factor is more than 0.75, it should be doubled.
	 */
	public int tableSize() {
		return capacity;
	}

	protected V bucketPut(int h, K k, V v) {
		UnorderedMap<K,V> bucket = table[h];
		if(bucket == null)
			bucket = table[h] = new UnorderedMap<>();
		int oldSize = bucket.size();
		V answer = bucket.put(k, v);
		size += (bucket.size()- oldSize);
		return answer;
	}

	//returns value associated with key k in bucket with hash value h, or else null.
	protected V BucketGet(int h, K k) {
		UnorderedMap<K,V> bucket = table[h];
		if(bucket == null)
			return null;
		return bucket.get(k);
	}

	//Removes entry having key k from bucket with hash value h (if any)
	protected V bucketRemove(int h, K k) {
		UnorderedMap<K,V> bucket = table[h];
		if(bucket == null)
			return null;
		int oldSize = bucket.size();
		V answer = bucket.remove(k);
		size -= (oldSize - bucket.size());
		return answer;
	}


	//returns the size
	@TimeComplexity("O(1)")
	//Because it just returns the size
	@TimeComplexityExpected("O(1)")
	//Because it just returns the size
	@Override
	public int size() {
		return size;
	}

	//returns if it is empty or not
	@TimeComplexity("O(1)")
	//Because it just returns if it is empty or not
	@TimeComplexityExpected("O(1)")
	//Because it just returns if it is empty or not
	@Override
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}

	//gets an element from the hash map using bucket get
	@TimeComplexity("O(n)")
	//Because all elements could be in one bucket.
	@TimeComplexityExpected("O(1)")
	//Because it potentially it normally doesn't go through all elements.
	@Override
	public V get(K key) {
		return BucketGet(hashValue(key),key);
	}

			//Puts a new value in and potentially resizes.
			@TimeComplexity("O(n)")
	//All elements in one bucket and last element in the bucket.
	@TimeComplexityExpected("O(1)")
	//Only has to go into one bucket and its one element. 
	@Override
	public V put(K key, V value) {
		int h =hashValue(key);
		V out ;
		if(table[h]==null) {
			size++;
			table[h]=new UnorderedMap<K,V>();
			table[h].put(key,value);
			return null;
		}
		out=table[h].put(key,value);
		size++;
		if(size>(capacity*loadfactor)) {
			resize();
		}

		return out;
	}

	//calls the bucket remove and hash value to remove the key.
	//Puts a new value in and potentially resizes.
	@TimeComplexity("O(n)")
	//All elements in one bucket and last element in the bucket.
	@TimeComplexityExpected("O(1)")
	//Only has to go into one bucket and its one element. 
	@Override
	public V remove(K key) {
		return bucketRemove(hashValue(key), key);
	}

	//Returns an iterable collection of all key-value entries of the map.
	//Puts a new value in and potentially resizes.
	@TimeComplexity("O(n)")
	//All elements returns in an arraylist
	@TimeComplexityExpected("O(n)")
	//Returns all the elements in an iterable form
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		for(int h = 0; h < capacity;h++) {
			if(table[h] != null)
				for(Entry<K,V> entry : table[h].entrySet())
					buffer.addLast(entry);
		}
		return buffer;
	}


}