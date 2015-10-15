package search;

import java.util.LinkedList;

// queue implementation
public class BreadthFirstFrontier implements Frontier {
  private final LinkedList<Node> q = new LinkedList<Node>();

  public void addNode(Node node) {
    q.add(node);
    Analytics.getInstance().updateMaxFrontierSize(q.size());
  }
  public void clear() {
    q.clear();
  }
  public boolean isEmpty() {
    return q.isEmpty();
  }
  public Node nextNode() {
    return q.removeFirst();
  }
}
