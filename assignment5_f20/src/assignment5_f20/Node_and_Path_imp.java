package assignment5_f20;

public class Node_and_Path_imp implements Node_and_Path {
	String node;
	long path_distance; 
	
	Node_and_Path_imp(String l, long d) {
		node = l; path_distance= d;}

	@Override
	public void setNode(String newnode) { node = newnode;}

	@Override
	public String getNode() {return node;}

	@Override
	public void setPath(long newdistance) { path_distance = newdistance;}

	@Override
	public long getPath() { return path_distance;}

}
