package assignment3_f20;

public interface HMCell { // these will be used by the grader to 
                          // examine your data structure contents
  public void setKey(String newKey);
  public String getKey();
  public void setValue(Value newValue);
  public Value getValue();
  public void setNext(HMCell nextCell);
  public HMCell getNext();
}
 