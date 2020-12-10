package assignment4_f20;

import java.util.HashMap;

public class Cache_LFU implements Cache {

	HashMap<String, CacheFrame> map;
	// allocate from java collections lib
	// do it this way so we all start with default size and
	// default lambda and default hash function for string keys
	MinBinHeap heap; // your own heap code above
	int limit; // max num elts the cache can hold
	int size; // current number elts in the cache

	public Cache_LFU(int maxElts) {
		this.map = new HashMap<String, CacheFrame>();
		this.heap = new MinBinHeap(maxElts);
		this.limit = maxElts;
		this.size = 0;
	}

	// dont change this we need it for grading
	public MinBinHeap getHeap() {
		return this.heap;
	}

	public HashMap getHashMap() {
		return this.map;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return limit;
	}

	@Override
	public int numElts() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		if (size == limit) {
			return true;
		}
		return false;
	}

	@Override
	public boolean refer(String address) {
		// TODO Auto-generated method stub
		
		CacheFrame new_CacheFrame = new CacheFrame(address,0);
		new_CacheFrame.setPriority(1);

		if (map.containsKey(address) == true) {
			heap.incElt(map.get(address));
			return true;
		} else {
			if (isFull() == true) {
				map.remove(heap.getMin().getValue());
				heap.delMin();
				map.put(address, new_CacheFrame);
				
				
				
				heap.insert(map.get(address));
				
				
			} else {
				map.put(address, new_CacheFrame);
				heap.insert(map.get(address));
				
				size = size + 1;
				
			}
			return false;
		}

	}

	// =========================================================
	//
	// you fill in code for the other ops in the interface
	//
	// ==========================================================

}
