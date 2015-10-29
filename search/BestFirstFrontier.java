package search;

import java.util.PriorityQueue;

// queue implementation
public class BestFirstFrontier implements Frontier {
  private static final class NodeWrapper implements Comparable<NodeWrapper> {
    public Node node;
    public int value;

    public NodeWrapper(Node node, int value) {
      this.node = node;
      this.value = value;
    }

    @Override
    public int compareTo(NodeWrapper other) {
      return Integer.compare(value, other.value);
    }
  }
  private final PriorityQueue<NodeWrapper> q = new PriorityQueue<NodeWrapper>();
  private final NodeFunction f;

  public BestFirstFrontier(NodeFunction f) {
    this.f = f;
  }

  public void addNode(Node node) {
    q.add(new NodeWrapper(node, f.calculate(node)));
    Analytics.getInstance().updateMaxFrontierSize(q.size());
  }
  public void clear() {
    q.clear();
  }
  public boolean isEmpty() {
    return q.isEmpty();
  }
  public Node nextNode() {
    return q.remove().node;
  }
}
