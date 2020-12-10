package assignment4_f20;

public class MinBinHeap implements Heap {
	private CacheFrame[] array; // load this array
	private int size; // how many items currently in the heap
	private int arraySize; // Everything in the array will initially
							// be null. This is ok! Just build out
							// from array[1]

	public MinBinHeap(int nelts) {
		this.array = new CacheFrame[nelts + 1]; // remember we dont use slot 0
		this.arraySize = nelts + 1;
		this.size = 0;
		this.array[0] = new CacheFrame(null, 0); // 0 not used, so this is arbitrary
	}

	// Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public CacheFrame[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(CacheFrame elt) {
		// TODO Auto-generated method stub
		if (size == 0) {
			int subcript = size + 1;
			array[subcript] = elt;
			array[subcript].setSlot(subcript);
			
			
			size = size + 1;
			return;
		}

		int subcript = size + 1;
		array[subcript] = elt;
		array[subcript].setSlot(subcript);
		

		int child_subscript = subcript;
		int parent_subscript = (int) Math.floor(child_subscript / 2);

		while (array[child_subscript].getPriority() < array[parent_subscript].getPriority()) {
			CacheFrame temp = array[parent_subscript];
			array[parent_subscript] = array[child_subscript];
			array[parent_subscript].setSlot(parent_subscript);
			array[child_subscript] = temp;
			array[child_subscript].setSlot(child_subscript);
			child_subscript = parent_subscript;
			parent_subscript = (int) Math.floor(child_subscript / 2);
			if (parent_subscript < 1) {
				break;
			}
		}

		size = size + 1;
		return;
	}

	@Override
	public void delMin() {
		// TODO Auto-generated method stub
		
		if(size == 0) {
			return;
		}
		if(size == 1) {
			array[1]=null;
			return;
		}
		array[1] = array[size];
		array[size] = null;
		array[1].setSlot(1);
		int parent_subscript = 1;
		int left_child_subscript = 2 * parent_subscript;
		int right_child_subscript = 2 * parent_subscript + 1;

		if (left_child_subscript > arraySize-1) {
			size = size - 1;
			return;
		}

		while (array[left_child_subscript] != null) {
			if (right_child_subscript > arraySize-1) {
				if (array[left_child_subscript].getPriority() < array[parent_subscript].getPriority()) {
					CacheFrame temp = array[parent_subscript];
					array[parent_subscript] = array[left_child_subscript];
					array[parent_subscript].setSlot(parent_subscript);
					array[left_child_subscript] = temp;
					array[left_child_subscript].setSlot(left_child_subscript);
					break;
				} else {
					break;
				}
			}
			if (array[right_child_subscript] == null) {
				if (array[left_child_subscript].getPriority() < array[parent_subscript].getPriority()) {
					CacheFrame temp = array[parent_subscript];
					array[parent_subscript] = array[left_child_subscript];
					array[parent_subscript].setSlot(parent_subscript);
					array[left_child_subscript] = temp;
					array[left_child_subscript].setSlot(left_child_subscript);
					break;
				} else {
					break;
				}
			} else {
				if (array[left_child_subscript].getPriority() < array[parent_subscript].getPriority()
						|| array[right_child_subscript].getPriority() < array[parent_subscript].getPriority()) {
					if (array[left_child_subscript].getPriority() < array[right_child_subscript].getPriority()) {
						CacheFrame temp = array[parent_subscript];
						array[parent_subscript] = array[left_child_subscript];
						array[parent_subscript].setSlot(parent_subscript);
						array[left_child_subscript] = temp;
						array[left_child_subscript].setSlot(left_child_subscript);
						parent_subscript = left_child_subscript;
						left_child_subscript = 2 * parent_subscript;
						right_child_subscript = 2 * parent_subscript + 1;
						if (left_child_subscript > arraySize-1) {
							break;
						}
					} else {
						CacheFrame temp = array[parent_subscript];
						array[parent_subscript] = array[right_child_subscript];
						array[parent_subscript].setSlot(parent_subscript);
						array[right_child_subscript] = temp;
						array[right_child_subscript].setSlot(right_child_subscript);
						parent_subscript = right_child_subscript;
						left_child_subscript = 2 * parent_subscript;
						right_child_subscript = 2 * parent_subscript + 1;
						if (left_child_subscript > arraySize-1) {
							break;
						}
					}
				} else {
					break;
				}
			}
		}

		size = size - 1;
		return;
	}

	@Override
	public CacheFrame getMin() {
		// TODO Auto-generated method stub
		return array[1];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void incElt(CacheFrame elt) {
		// TODO Auto-generated method stub
		if (size == 1) {
			array[elt.getSlot()].setPriority(elt.getPriority() + 1);
			return;
		}
		
		
		array[elt.getSlot()].setPriority(elt.getPriority() + 1);
		int parent_subscript = elt.getSlot();
		int left_child_subscript = 2 * parent_subscript;
		int right_child_subscript = 2 * parent_subscript + 1;

		if (left_child_subscript > arraySize-1) {
			return;
		}

		while (array[left_child_subscript] != null) {
			if (right_child_subscript > arraySize-1) {
				if (array[left_child_subscript].getPriority() < array[parent_subscript].getPriority()) {
					CacheFrame temp = array[parent_subscript];
					array[parent_subscript] = array[left_child_subscript];
					array[parent_subscript].setSlot(parent_subscript);
					array[left_child_subscript] = temp;
					array[left_child_subscript].setSlot(left_child_subscript);
					break;
				} else {
					break;
				}
			}
			if (array[right_child_subscript] == null) {
				if (array[left_child_subscript].getPriority() < array[parent_subscript].getPriority()) {
					CacheFrame temp = array[parent_subscript];
					array[parent_subscript] = array[left_child_subscript];
					array[parent_subscript].setSlot(parent_subscript);
					array[left_child_subscript] = temp;
					array[left_child_subscript].setSlot(left_child_subscript);
					break;
				} else {
					break;
				}
			} else {
				if (array[left_child_subscript].getPriority() < array[parent_subscript].getPriority()
						|| array[right_child_subscript].getPriority() < array[parent_subscript].getPriority()) {
					if (array[left_child_subscript].getPriority() < array[right_child_subscript].getPriority()) {
						CacheFrame temp = array[parent_subscript];
						array[parent_subscript] = array[left_child_subscript];
						array[parent_subscript].setSlot(parent_subscript);
						array[left_child_subscript] = temp;
						array[left_child_subscript].setSlot(left_child_subscript);
						parent_subscript = left_child_subscript;
						left_child_subscript = 2 * parent_subscript;
						right_child_subscript = 2 * parent_subscript + 1;
						if (left_child_subscript > arraySize-1) {
							break;
						}
					} else {
						CacheFrame temp = array[parent_subscript];
						array[parent_subscript] = array[right_child_subscript];
						array[parent_subscript].setSlot(parent_subscript);
						array[right_child_subscript] = temp;
						array[right_child_subscript].setSlot(right_child_subscript);
						parent_subscript = right_child_subscript;
						left_child_subscript = 2 * parent_subscript;
						right_child_subscript = 2 * parent_subscript + 1;
						if (left_child_subscript > arraySize-1) {
							break;
						}
					}
				} else {
					break;
				}
			}
		}
		return;

	}

	@Override
	public void decElt(CacheFrame elt) {
		// TODO Auto-generated method stub
		if (elt.getPriority() - 1 < 1) {
			array[elt.getSlot()].setPriority(1);
		} else {
			array[elt.getSlot()].setPriority(elt.getPriority() - 1);

		}
		if (size == 1) {
			return;
		}

		int child_subscript = elt.getSlot();
		int parent_subscript = (int) Math.floor(child_subscript / 2);

		while (array[child_subscript].getPriority() < array[parent_subscript].getPriority()) {
			CacheFrame temp = array[parent_subscript];
			array[parent_subscript] = array[child_subscript];
			array[parent_subscript].setSlot(parent_subscript);
			array[child_subscript] = temp;
			array[child_subscript].setSlot(child_subscript);
			child_subscript = parent_subscript;
			parent_subscript = (int) Math.floor(child_subscript / 2);
			if (parent_subscript < 1) {
				break;
			}
		}

		return;

	}

	// ===============================================================
	//
	// here down you implement the ops in the interface
	//
	// ===============================================================

}