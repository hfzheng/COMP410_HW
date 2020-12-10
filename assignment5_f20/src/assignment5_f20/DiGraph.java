package assignment5_f20;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;



public class DiGraph implements DiGraphInterface {
	long sizenodes;
	long sizeedges;
	LinkedList<Vertex> allvertices;   
	HashMap<String, Vertex> mapV;
	// in here go all your data and methods for the graph

	public DiGraph () { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
		this.mapV = new HashMap<String, Vertex>();
		this.allvertices = new LinkedList<Vertex>();

	}

	@Override
	public boolean addNode(long idNum, String label) {
		// TODO Auto-generated method stub

		Vertex newVertex = new Vertex_imp(idNum,label);

		if(mapV.containsKey(label)== true) {
			return false;
		}
		else if(idNum < 0) {
			return false;
		}
		else {
			allvertices.add(newVertex);
			mapV.put(label, newVertex);
			sizenodes = sizenodes + 1;
			return true;
		}
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
        if(Objects.isNull(weight)) {weight = 1;}
		if(mapV.containsKey(sLabel)==false) {
			return false;
		}
		else if(mapV.containsKey(dLabel)==false) {
			return false;
		}
		else if(weight < 0){
			return false;
		}
		else if(containsEdge(sLabel, dLabel) == true ) {
			return false;
		}
		else {
			Vertex startvertex = mapV.get(sLabel);
			Vertex endvertex = mapV.get(dLabel);
			Cell newcell = new Cell_imp(endvertex,weight);

			if (startvertex.getLeft()==null) {
				startvertex.setLeft(newcell);
				sizeedges = sizeedges+1;
				return true;
			}
			else {
				Cell cell = startvertex.getLeft();
				while(cell.getLeft() != null) {
					cell = cell.getLeft();
				}
				cell.setLeft(newcell);
				sizeedges = sizeedges+1;
				return true;
			}
		}
	}

	

	@Override
	public boolean delNode(String label) {
		if(mapV.containsKey(label)==false) {
			return false;
		}
		else {
			allvertices.remove(mapV.get(label));
			mapV.remove(label);
			sizenodes = sizenodes - 1;
			return true;
		}
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		// TODO Auto-generated method stub
		
		if(mapV.containsKey(sLabel)==false) {
			return false;
		}
		else if(mapV.containsKey(dLabel)==false) {
			return false;
		}
		else if(containsEdge(sLabel, dLabel) == false){
			return false;
		}
		else {
			Vertex startvertex = mapV.get(sLabel);
		    Cell cell = startvertex.getLeft();
		    
		    if(cell.getLinkedVertex().getLabel()==dLabel) {
		    	startvertex.setLeft(cell.getLeft());
		    	sizeedges = sizeedges-1;
		    	return true;
		    }
		    
		    Cell previous = null;
		    while(cell.getLeft() != null) {
		    	if(cell.getLeft().getLinkedVertex().getLabel()==dLabel) {
		    		cell.setLeft(cell.getLeft().getLeft());
		    		sizeedges = sizeedges-1;
		    		return true;
		    	}
		    	previous = cell;
		    	cell = cell.getLeft();
		    }
		    
		    previous.setLeft(null); 
		    sizeedges = sizeedges-1;
			return true;
		}
	}

	@Override
	public long numNodes() {
		// TODO Auto-generated method stub
		return sizenodes;
	}

	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		return sizeedges;
	}

	// rest of your code to implement the various operations
	public boolean containsEdge(String sLabel, String dLabel) {
		Vertex startvertex = mapV.get(sLabel);
		if(startvertex.getLeft()==null) {
			return false;
		}
		
		Cell newcell = startvertex.getLeft();

		while(newcell.getLeft() != null) {
			if (newcell.getLinkedVertex().getLabel()==dLabel) {
				return true;
			}
			newcell = newcell.getLeft();
		}
		
		if(newcell.getLinkedVertex().getLabel()==dLabel) {return true;}
		return false;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		// TODO Auto-generated method stub
		ShortestPathInfo[] SPInfo = new ShortestPathInfo[(int) sizenodes];
		Comparator<Node_and_Path> comparator = new PathComparator();
		PriorityQueue<Node_and_Path> pq = new PriorityQueue<Node_and_Path>((int) sizenodes,comparator);
		Boolean[] IFend = new Boolean[(int) sizenodes];
		Vertex startvertex = mapV.get(label);
		Arrays.fill(IFend, false);
		for(int i=0;i<sizenodes;i++) {
			int index = allvertices.indexOf(mapV.get(allvertices.get(i).getLabel()));
			SPInfo[index]= new ShortestPathInfo(mapV.get(allvertices.get(i).getLabel()).getLabel(),-1);
		}
		
		
		 
		long baseweight = 0;
		Node_and_Path start = new Node_and_Path_imp(startvertex.getLabel(),baseweight);
		pq.add(start);
		Node_and_Path head = pq.peek();
		int index_0 = allvertices.indexOf(mapV.get(head.getNode()));
		SPInfo[index_0]= new ShortestPathInfo(head.getNode(),head.getPath());
		IFend[index_0] = true;
		pq.remove();
		
		
		while(Arrays.asList(IFend).contains(false)){
			Cell cell;
			cell = startvertex.getLeft();
			if(cell==null) {
				break;
			}
			while(cell!=null) {
				Node_and_Path temp_node = new Node_and_Path_imp(cell.getLinkedVertex().getLabel(),
						cell.getWeightValue()+baseweight);
				pq.add(temp_node);
				cell = cell.getLeft();
			}
			head = pq.peek();
			int index = allvertices.indexOf(mapV.get(head.getNode()));
			if(SPInfo[index].getTotalWeight()!=-1) {
				if(SPInfo[index].getTotalWeight()>head.getPath())
				{
					SPInfo[index]= new ShortestPathInfo(head.getNode(),head.getPath());
				}
			}
			else {
				SPInfo[index]= new ShortestPathInfo(head.getNode(),head.getPath());
				IFend[index] = true;
			}
			
			startvertex = mapV.get(head.getNode());
			baseweight = head.getPath();
			pq.remove();
			Iterator<Node_and_Path> it = pq.iterator();
			while (it.hasNext()) {
				Node_and_Path temp;
				temp = it.next();
			   if (temp.getNode() == head.getNode()) {
			       it.remove();
			    }
			}
            
		}
		return SPInfo;
	}
	
	
	public class PathComparator implements Comparator<Node_and_Path> {
	    @Override
	    public int compare(Node_and_Path x, Node_and_Path y) {
	        // Assume neither string is null. Real code should
	        // probably be more robust
	        // You could also just return x.length() - y.length(),
	        // which would be more efficient.
	        if (x.getPath() < y.getPath()) {
	            return -1;
	        }
	        if (x.getPath() > y.getPath()) {
	            return 1;
	        }
	        return 0;
	    }
	}
}

