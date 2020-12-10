///**
// * COMP 410
// * See inline comment descriptions for methods we need that are
// * not described in the interface.
// *
//*/
//package assignment1;
//
//public class StackSet implements StackSet_Interface {
//  private Node head;  //this will be the entry point to your linked list 
//                      //( it points to the first data cell, the top for a stack )
//  private Node root;
//  private final int limit;  //defines the maximum size the stackset may contain
//  private int size;   //current count of elements in the stackset 
//  
//  public StackSet( int maxNumElts ){ //this constructor is needed for testing purposes. 
//    head = null;                 //Please don't modify what you see here!
//    limit = maxNumElts;          //you may add fields if you need to
//    size = 0;
//    root = null;
//  }
//  
//  //implement all methods of the interface, and 
//  //also include the getRoot method below that we made for testing purposes. 
//  //Feel free to implement private helper methods!
//  //you may add any fields you need to add to make it all work... 
//  
//  public Node getRoot(){ //leave this method as is, used by the grader to grab 
//    return head;         //your data list easily.
//  }
//
//@Override
//public boolean push(double elt) {
//	// TODO Auto-generated method stub
//	Node push_node = new NodeImpl(elt);
//	if (size == 0) {
//		head = push_node;
//		root = push_node;
//		return true;
//	}
//	
//	Node cur_node = root;
//	Node pre_node = null;
//	for (int i=0;i<size;i++) {
//		if(elt == cur_node.getValue()) {
//			if (i==size-1) {return true;}
//			else if(i==0) {
//				if (root.getNext()==null) {
//					return true;
//				}
//				else {
//					root = root.getNext(); // if root is the last element
//					head.setNext(push_node);
//					head = push_node;
//					return true;
//					
//				}
//			}
//			else { 
//				pre_node.setNext(cur_node.getNext());
//				head.setNext(push_node);
//				head = push_node;
//				return true;
//			}
//		}
//		
//		pre_node = cur_node;
//		cur_node = cur_node.getNext();
//	}
//	
//	if (size>=limit) {
//		return false;
//	}
//	else if (size<limit)
//	{
//		head.setNext(push_node);
//		head = push_node;
//		size = size + 1;
//		return true;
//	}
//
//	return true;
//}
//
//
//@Override
//public boolean pop() {
//	// TODO Auto-generated method stub
//	Node cur_node = root;
//	if (size == 0) {return false;}
//	else {
//		for (int j=0; j<size-1;j++)
//		{
//			cur_node = cur_node.getNext();
//		}
//		head = cur_node;
//		head.setNext(null);
//		size = size - 1;
//		return true;
//	}
//
//}
//
//@Override
//public double peek() {
//	// TODO Auto-generated method stub
//	if (size >= 1) {return head.getValue();}
//	if (size == 0) {return Double.NaN;}
//	return 0;
//}
//
//@Override
//public boolean contains(double elt) {
//	// TODO Auto-generated method stub
//	Node cur_node = root;
//	for (int k=0;k<size;k++) {
//		if(elt == cur_node.getValue()) {return true;}
//		}
//	return false;
//	
//}
//
//@Override
//public int size() {
//	// TODO Auto-generated method stub
//
//	return size;
//}
//
//@Override
//public int limit() {
//	// TODO Auto-generated method stub
//	
//	return limit-size;
//}
//
//@Override
//public boolean isEmpty() {
//	// TODO Auto-generated method stub
//	if (size == 0) return true;
//    return false;
//}
//
//@Override
//public boolean isFull() {
//	// TODO Auto-generated method stub
//	if (size == limit) return true;
//	return false;
//}
//
//}




/**
 * COMP 410
 * See inline comment descriptions for methods we need that are
 * not described in the interface.
 *
*/
package assignment1;

public class StackSet implements StackSet_Interface {
  private Node head;  //this will be the entry point to your linked list 
                      //( it points to the first data cell, the top for a stack )
  private final int limit;  //defines the maximum size the stackset may contain
  private int size;   //current count of elements in the stackset 
  
  public StackSet( int maxNumElts ){ //this constructor is needed for testing purposes. 
    head = null;                 //Please don't modify what you see here!
    limit = maxNumElts;          //you may add fields if you need to
    size = 0;
  }
  
  //implement all methods of the interface, and 
  //also include the getRoot method below that we made for testing purposes. 
  //Feel free to implement private helper methods!
  //you may add any fields you need to add to make it all work... 
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab 
    return head;         //your data list easily.
  }

@Override
public boolean push(double elt) {
	// TODO Auto-generated method stub
	
	Node push_node = new NodeImpl(elt);
	Node head_temp = head;

	if (size == 0) {
	head = push_node;
	size = size + 1;
	return true;
	}

   Node cur_node = head;
   Node pre_node = null;
	
	if(contains(elt)==true) {
		for(int i=0;i<size;i++) {
			if(elt == cur_node.getValue()) {
				pre_node.setNext(cur_node.getNext());
				head = push_node;
				head.setNext(head_temp);
				return true;
			}
			pre_node = cur_node;
			cur_node = cur_node.getNext();
		}

	}
	else {
		if(size >= limit){
			return false;
		}
		else {
			head = push_node;
			head.setNext(head_temp);
			size = size + 1;
			return true;
		}
		
	}
	return false;

}
	



@Override
public boolean pop() {
	// TODO Auto-generated method stub
	
	if (size == 0) {
		return false;
	}
	else {
		head = head.getNext();
		size = size - 1;
		return true;
	}

}


@Override
public double peek() {
	// TODO Auto-generated method stub
	if (size >= 1) {return head.getValue();}
	if (size == 0) {return Double.NaN;}
	return 0;
}

@Override
public boolean contains(double elt) {
	// TODO Auto-generated method stub
	Node cur_node = head;
	for (int k=0;k<size;k++) {
		if(elt == cur_node.getValue()) {return true;}
		cur_node = cur_node.getNext();
		}
	return false;
	
}

@Override
public int size() {
	// TODO Auto-generated method stub

	return size;
}

@Override
public int limit() {
	// TODO Auto-generated method stub
	
	return limit-size;
}

@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	if (size == 0) return true;
    return false;
}

@Override
public boolean isFull() {
	// TODO Auto-generated method stub
	if (size == limit) return true;
	return false;
}

}