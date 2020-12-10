package assignment2_f20;

public class TreeMap_imp implements TreeMap { 
	TMCell root;
	TMCell root_put;
	TMCell root_get;
	TMCell root_rem;
	TMCell root_has;
	TMCell root_minkey;
	TMCell root_maxkey;
	TMCell root_getkeys;
	TMCell parent;
	TMCell parent_Rmin;
	int size;
	int i=0;
	// add fields as you need to in order to provide the required behavior
	// also you may add private methods to assist you as needed
	// in implementing

	//-------------------------------------------------------------

	TreeMap_imp () { 
		root=null;
		size = 0;
		// for added fields you can add appropriate initialization code here
	}

	//-------------------------------------------------------------

	// dont change, we need this for grading
	@Override
	public TMCell getRoot() { return this.root; }

	//-------------------------------------------------------------
	// "height" is a complete implementation 
	// of the public interface method height
	// it is here to illustrate fr you how the textbook sets up 
	// the method implementation as recursion
	// you may include this in your project directly

	public int height() { // public interface method signature
		// this method is the public interface method
		// it is not recursive, but it calls a recursive
		// private method and passes it access to the tree cells
		return height_r(this.getRoot());
	}
	private int height_r(TMCell c) { 
		// inner method with different signature
		// this helper method uses recursion to traverse
		// and process the recursive structure of the tree of cells
		if (c==null) return -1;
		int lht = height_r(c.getLeft());
		int rht = height_r(c.getRight());
		return Math.max(lht,rht) + 1;
	}

	@Override
	public Value put(String k, Value v) {
		// TODO Auto-generated method stub
		root_put = root;
		return (put_r(k,v));
	}
	private Value put_r(String k, Value v) {
		// TODO Auto-generated method stub

		if(k==null)
		{
			return null;
		}

		if(root_put == null)
		{
			TMCell temp = new TMCell_imp (k,v);
			root = temp;
			root_put = root;
			size=size+1;
			return null;

		}


		if(hasKey(k)==false)
		{
			if(k.compareTo(root_put.getKey())<0) {
				if (root_put.getLeft() == null)
				{
					TMCell  new_cell = new TMCell_imp(k,v);
					root_put.setLeft(new_cell);
					size = size+1;
					return null;
				}
				else {
					root_put = root_put.getLeft();
					return put_r(k,v);
				}

			}
			else{
				if (root_put.getRight() == null)
				{
					TMCell  new_cell = new TMCell_imp(k,v);
					root_put.setRight(new_cell);
					size = size+1;
					return null;
				}
				else {
					root_put = root_put.getRight();
					return put_r(k,v);
				}
			}

		}
		else
		{
			Value old_one = get(k);
			root_get.setValue(v);
			return old_one;
		}

	}

	@Override
	public Value get(String k) {
		// TODO Auto-generated method stub
		root_get = root;
		return (get_r(k));
	}

	private Value get_r(String k) {
		// root_get = null
		if(root_get == null) {
			return null;
		}


		if(k.compareTo(root_get.getKey())==0) {
			return root_get.getValue();
		} 
		else if(k.compareTo(root_get.getKey())<0){
			if (root_get.getLeft() == null)
			{
				return null;
			}
			else {
				root_get = root_get.getLeft();
				return get_r(k);
			}
		}
		else{
			if (root_get.getRight() == null)
			{
				return null;
			}
			else {
				root_get = root_get.getRight();
				return get_r(k);
			}
		}
	}   



	@Override
	public void remove(String k) {
		// TODO Auto-generated method stub
		root_rem = root;
		remove_r(k);
	}


	private void remove_r(String k) {
		// TODO Auto-generated method stub

		if(root_rem == null) {
			return;
		}


		if(hasKey(k)==true) {
			if(k.compareTo(root_rem.getKey())==0) {
				if(root_rem.getLeft()==null && root_rem.getRight()==null) {
					if(parent == null) {
						root_rem.setKey(null);
						size=size-1;
						root = root_rem;
						return;
					}
					if(root_rem.getKey().compareTo(parent.getKey())==0) {
						root_rem.setKey(null);
						size=size-1;
						return;

					}
					else if(root_rem.getKey().compareTo(parent.getKey())<0)
					{
						parent.setLeft(null);
						size = size-1;
						return;
					}
					else {
						parent.setRight(null);
						size = size-1;
						return;
					}

				}
				else if(root_rem.getLeft()!=null && root_rem.getRight()!=null) {
                    if(parent == null)
                    {
    					if(root_rem.getRight().getLeft()==null) {
    						root_rem.getRight().setLeft(root_rem.getLeft());
    						root = root_rem.getRight();
    						size=size-1;
    						return;
    					}
                    	
    					else {
    						TMCell new_cell_connect = Rightfindmincell(root_rem.getRight());
    							new_cell_connect.setLeft(root_rem.getLeft());
    							new_cell_connect.setRight(root_rem.getRight());
    							root = new_cell_connect;
    							size=size-1;
    							
    							return;

    					}
                    }
					if(root_rem.getRight().getLeft()==null) {
						parent.setRight(root_rem.getRight());
						root_rem.getRight().setLeft(root_rem.getLeft());
						size=size-1;
						return;

					}
					else {
						TMCell new_cell_connect = Rightfindmincell(root_rem.getRight());
						if(new_cell_connect.getLeft().getKey().compareTo(parent.getKey())<0) {
							parent.setLeft(new_cell_connect);
							new_cell_connect.setLeft(root_rem.getLeft());
							new_cell_connect.setRight(root_rem.getRight());	
							size=size-1;
							return;
						}
						else {
							parent.setRight(new_cell_connect);
							new_cell_connect.setLeft(root_rem.getLeft());
							new_cell_connect.setRight(root_rem.getRight());
							size=size-1;
							return;

						}


					}

				}
				else {
					if(root_rem.getLeft()!=null) {
						if(parent == null) {
							root=root_rem.getLeft();
							size=size-1;
							return;
						}

						if(root_rem.getLeft().getKey().compareTo(parent.getKey())<0) {
							parent.setLeft(root_rem.getLeft());
							size = size-1;
							return;
						}
						else {
							parent.setRight(root_rem.getLeft());
							size = size-1;
							return;

						}

					}
					else if(root_rem.getRight()!=null){
						if(parent == null) {
							root=root_rem.getRight();
							size=size-1;
							return;
						}

						if(root_rem.getRight().getKey().compareTo(parent.getKey())<0) {
							parent.setLeft(root_rem.getRight());
							size = size-1;
							return;
						}
						else {
							parent.setRight(root_rem.getRight());
							size = size-1;
							return;

						}

					}
				}


			}
			else if(k.compareTo(root_rem.getKey())<0) {
				parent = root_rem;
				root_rem = root_rem.getLeft();
				remove_r(k);
			}
			else {
				parent = root_rem;
				root_rem = root_rem.getRight();
				remove_r(k);
			}

		}
		else {
			return;
		}


	}

	@Override
	public boolean hasKey(String k) {
		// TODO Auto-generated method stub
		root_has=root;
		return (hasKey_r(k));
	}
	private boolean hasKey_r(String k) {
		if(root_has == null)
		{
			return false;

		}


		if(k.compareTo(root_has.getKey())==0) {
			return true;
		} 
		else if(k.compareTo(root_has.getKey())<0){
			if (root_has.getLeft() == null)
			{
				return false;
			}
			else {
				root_has = root_has.getLeft();
				return hasKey_r(k);
			}
		}
		else{
			if (root_has.getRight() == null)
			{
				return false;
			}
			else {
				root_has = root_has.getRight();
				return hasKey_r(k);
			}
		}

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return  size;
	}


	@Override
	public String maxKey() {
		// TODO Auto-generated method stub
		root_maxkey = root;
		return(maxKey_r());
	}
	private String maxKey_r() {
		if (root_maxkey == null){
			return null;}
		if(root_maxkey.getRight()==null) {
			return(root_maxkey.getKey());

		}
		else {
			root_maxkey = root_maxkey.getRight();
			return(maxKey_r());
		}
	}

	@Override
	public String minKey() {
		// TODO Auto-generated method stub
		root_minkey = root;
		return(minKey_r());
	}
	private String minKey_r() {
		if (root_minkey == null){
			return null;}
		if(root_minkey.getLeft()==null) {
			return(root_minkey.getKey());
		}
		else {
			root_minkey = root_minkey.getLeft();
			return(minKey_r());
		}

	}

	@Override

	public String[] getKeys() {
		// TODO Auto-generated method stub
		i=0;
		root_getkeys = root;
		String[] allkeys = new String[size];
		getKeys_r(allkeys);
		return(allkeys);

	}

	public void getKeys_r(String[] allkeys) {

		if(root_getkeys == null)
		{
			return;
		}

		TMCell temp = root_getkeys;
		root_getkeys = temp.getLeft();
		getKeys_r(allkeys);

		allkeys[i] = temp.getKey();
		i = i+1;

		root_getkeys = temp.getRight();
		getKeys_r(allkeys);
	}

	public TMCell Rightfindmincell(TMCell root_Rightfindmincell) {
		if(root_Rightfindmincell==null)
		{
			return null;
		}

		if(root_Rightfindmincell.getLeft()==null) {
			parent_Rmin.setLeft(null);
			return(root_Rightfindmincell);
		}
		else {
			parent_Rmin =  root_Rightfindmincell;
			root_Rightfindmincell = root_Rightfindmincell.getLeft();
			return(Rightfindmincell(root_Rightfindmincell));
		}
	}


	//-------------------------------------------------------------
	// here down... you fill in the implementations for
	// the other interface methods
	//-------------------------------------------------------------
	//
	// remember to implement the required recursion as noted
	// in the interface definition
	//
	//-------------------------------------------------------------

}