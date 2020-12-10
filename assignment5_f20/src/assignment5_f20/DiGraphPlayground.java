package assignment5_f20;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
      exTest();
    }
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(1, "p");
      d.addNode(2, "a");
      d.addNode(3, "g");
      d.addNode(4, "e");
      d.addEdge(0, "p", "a", 10, null);
      d.addEdge(1, "p", "g", 4, null);
      d.addEdge(2, "p", "e", 8, null);
      d.addEdge(3, "a", "p", 9, null);
      d.addEdge(4, "a", "g", 12, null);
      d.addEdge(5, "a", "e", 100, null);
      d.addEdge(6, "g", "p", 2, null);
      d.addEdge(7, "g", "a", 15, null);
      d.addEdge(8, "g", "e", 1, null);
      d.addEdge(9, "e", "p", 6, null);
      d.addEdge(10, "e", "a", 3, null);
      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
      System.out.println(d.shortestPath("p").toString());
    }
}