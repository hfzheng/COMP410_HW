package assignment5_f20;

public interface Vertex {
	public void setIdNum(long n);
	public long getIdNum();
	public void setLabel(String newLabel);
	public String getLabel();
	public void setLeft(Cell newleftCell);
	public Cell getLeft();
}
