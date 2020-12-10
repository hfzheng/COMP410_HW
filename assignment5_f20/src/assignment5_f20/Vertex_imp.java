package assignment5_f20;



public class Vertex_imp implements Vertex{
	public long IdNum;
	public String Label;
	public Vertex next;
	public Cell Left;

	Vertex_imp(long n, String l) { IdNum=n;  Label=l; next=null; }

	public void setIdNum(long newId) { IdNum = newId; }
	public long getIdNum() { return IdNum; }
	public void setLabel(String newLabel) { Label = newLabel;}
	public String getLabel() { return Label; }
	public void setLeft(Cell newleftCell) { Left = newleftCell;}
	public Cell getLeft() { return Left;}



}
