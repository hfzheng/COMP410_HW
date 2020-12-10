package assignment5_f20;

public interface Cell {
	public void setLinkedVertex(Vertex newlinkedvertex);
	public Vertex getLinkedVertex();
	public void setWeightValue(long newweightvalue);
	public long getWeightValue();
	public void setLeft(Cell newleftCell);
	public Cell getLeft();

}
