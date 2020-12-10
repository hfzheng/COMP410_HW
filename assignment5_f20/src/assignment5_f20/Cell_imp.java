package assignment5_f20;

public class Cell_imp implements Cell{
	public Vertex linkedvertex;
	public long weightvalue;
	public Cell left;
	
	Cell_imp(Vertex v, long n) { linkedvertex = v;  weightvalue = n; left = null; }
	
	@Override
	public void setLinkedVertex(Vertex newlinkedvertex) {linkedvertex = newlinkedvertex;}
	public Vertex getLinkedVertex() { return linkedvertex;}
	public void setWeightValue(long newweightvalue) {weightvalue = newweightvalue;}
	public long getWeightValue() {return weightvalue;}
	public void setLeft(Cell newleftCell) {left = newleftCell;}
	public Cell getLeft() {return left;}
	
	
}
