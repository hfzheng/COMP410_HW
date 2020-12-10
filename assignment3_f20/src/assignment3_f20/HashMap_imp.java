package assignment3_f20;

public class HashMap_imp implements HashMap { 
	HMCell[] tab;
	int nelts;
	int tabsize;
	int lambda;


	//-------------------------------------------------------------

	HashMap_imp (int num) { 
		this.tab = new HMCell[num];
		// for (int i=0; i<num; i++) { tab[i] = null; }
		// we can rely on the Java compiler to fill the table array with nulls
		// another way would be Array.fill()
		this.nelts = 0; 
		tabsize = num;
	}

	//-------------------------------------------------------------

	public int hash (String key, int tabSize) {
		int hval = 7;
		for (int i=0; i<key.length(); i++) {
			hval = (hval*31) + key.charAt(i);
		}
		hval = hval % tabSize;
		if (hval<0) { hval += tabSize; }
		return hval;
	}

	//-------------------------------------------------------------

	// dont change 
	@Override
	public HMCell[] getTable() { return this.tab; }

	@Override
	public Value put(String k, Value v) {
		if (lambda() >= 1) {
			extend();
			return null;
		}
		else {
			if (hasKey(k)==false) {
				int i = hash(k,tabsize);
				HMCell HMcelltemp = tab[i];
				if (HMcelltemp == null) {
					HMCell newslotrootHMCell = new HMCell_imp(k,v);
					tab[i] = newslotrootHMCell;
					nelts = nelts+1;
					if (lambda() >= 1) {
						extend();
					}
						return null;
				}
				else {
					HMCell preHMcelltemp = null;
					while(HMcelltemp!=null) {
						preHMcelltemp = HMcelltemp;
						HMcelltemp = HMcelltemp.getNext();
					}
					HMCell newslotHMCell = new HMCell_imp(k,v);
					preHMcelltemp.setNext(newslotHMCell);
					nelts = nelts+1;
					if (lambda() >= 1) {
						extend();
					}
						return null;

				}
			}
			else {
				int i = hash(k,tabsize);
				HMCell HMcelltemp = tab[i];
				while (HMcelltemp != null)
				{
					if (HMcelltemp.getKey().compareTo(k)==0) {
						Value valtemp = HMcelltemp.getValue();
						HMcelltemp.setValue(v);
						return valtemp;
					}
					else {
						HMcelltemp = HMcelltemp.getNext();
					}
				}

			}
		}
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public Value get(String k) {
		// TODO Auto-generated method stub
		if(hasKey(k)==true) {
			int i = hash(k,tabsize);
			HMCell HMcelltemp = tab[i];
			while (HMcelltemp != null)
			{
				if (HMcelltemp.getKey().compareTo(k)==0) {
					return HMcelltemp.getValue();
				}
				else {
					HMcelltemp = HMcelltemp.getNext();
				}
			}

		}
		else {
			return null;
		}
		return null;

	}

	@Override
	public void remove(String k) {
		// TODO Auto-generated method stub
		if(hasKey(k)==false) {
			return;
		}
		else {
			int i = hash(k,tabsize);
			HMCell HMcelltemp = tab[i];
			if(HMcelltemp.getKey().compareTo(k)==0) {
				tab[i] = HMcelltemp.getNext();
				nelts= nelts - 1;
			}
			else {
				HMCell preHMcelltemp = null;
				while(HMcelltemp.getKey().compareTo(k)!=0) {
					preHMcelltemp = HMcelltemp;
					HMcelltemp = HMcelltemp.getNext();

				}
				if(HMcelltemp.getNext()==null) {
					preHMcelltemp.setNext(null);
					nelts= nelts - 1;
					return;
				}
				else {
					preHMcelltemp.setNext(HMcelltemp.getNext());
					nelts= nelts - 1;
					return;
				}


			}
		}

	}

	@Override
	public boolean hasKey(String k) {
		// TODO Auto-generated method stub
		for (int i=0;i < tabsize-1;i++) {
			HMCell HMcelltemp = tab[i];
			while (HMcelltemp != null) {
				if(HMcelltemp.getKey()==k) {
					return true;
				}
				else {
					HMcelltemp=HMcelltemp.getNext();
				}	
			}
		}
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return nelts;
	}

	@Override
	public String maxKey() {
		// TODO Auto-generated method stub
		String maxKey;
		if(nelts==0) {
			return null;
		}
		else {
			String[] allkeys = getKeys();
			maxKey = allkeys[0];
			for(int i=0; i<nelts;i++) {
				if (maxKey.compareTo(allkeys[i])<=0) {
					maxKey = allkeys[i];
				}
			}
			return maxKey;
		}
	}

	@Override
	public String minKey() {
		// TODO Auto-generated method stub
		String minKey;
		if(nelts==0) {
			return null;
		}
		else {
			String[] allkeys = getKeys();
			minKey = allkeys[0];
			for(int i=0; i<nelts;i++) {
				if (minKey.compareTo(allkeys[i])>=0) {
					minKey = allkeys[i];
				}
			}
			return minKey;
		}
	}

	@Override
	public String[] getKeys() {
		// TODO Auto-generated method stub
			String[] allkeys = new String[nelts];
			int j=0;
			for(int i=0;i<tabsize;i++)
			{
				HMCell HMcelltemp = tab[i];
				while (HMcelltemp != null) {
					allkeys[j] = HMcelltemp.getKey();
					j=j+1;
					HMcelltemp = HMcelltemp.getNext();
				}
			}
			return allkeys;

		}

	@Override
	public double lambda() {
		// TODO Auto-generated method stub

		return (double) nelts / tabsize;
	}

	@Override
	public double extend() {
		// TODO Auto-generated method stub
		String[] allkeys = getKeys();
		Value[] allvalues = new Value[nelts];
		for(int i=0;i<nelts;i++) {
			allvalues[i] = get(allkeys[i]);
		}
		tabsize = tabsize*2;
		this.tab = new HMCell[tabsize];
		int temp = nelts;
		nelts = 0;
		for(int i=0;i<temp;i++) {
			put(allkeys[i],allvalues[i]);
		}

		return (double) nelts / tabsize;
	}





	//-------------------------------------------------------------


	//-------------------------------------------------------------
	// here down... you fill in the implementations for
	// the other interface methods
	//-------------------------------------------------------------

}