package assignment5_f20;

public class Edge_imp implements Edge{
	
	public long IdNum;
	public String sourceLabel;
	public String destinLabel;
	public long weight;
	public String Label;
	
	Edge_imp(long n, String sLabel, String dLabel, long w, String eLabel) {
		IdNum = n;sourceLabel = sLabel; destinLabel = dLabel; weight = w; Label = eLabel;}
	
	
	public void setIdNum(long n) {IdNum = n;}
	public long getIdNum() { return IdNum;}
	public void setsourceLabel(String newsourceLabel) {sourceLabel = newsourceLabel;}
	public String getsourceLabel() { return sourceLabel;}
	public void setdestinLabel(String newdestinLabel) {destinLabel = newdestinLabel;}
	public String getdestinLabel() { return destinLabel; }
	public void setweight(long newweight) { weight = newweight;}
	public long getweight() { return weight;}
	public void setLabel(String newLabel) { Label = newLabel;}
	public String getLabel() { return Label;}


}
