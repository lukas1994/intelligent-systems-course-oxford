package search;

import java.util.LinkedList;

// stack implementation
public class DepthFirstFrontier implements Frontier {
  private final LinkedList<Node> s = new LinkedList<Node>();

  public void addNode(Node node) {
    s.add(node);
    Analytics.getInstance().updateMaxFrontierSize(s.size());
  }
  public void clear() {
    s.clear();
  }
  public boolean isEmpty() {
    return s.isEmpty();
  }
  public Node nextNode() {
    return s.removeLast();
  }
}
