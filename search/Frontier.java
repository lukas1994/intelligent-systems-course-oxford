package search;

public interface Frontier {
  void addNode(Node node);
  void clear();
  boolean isEmpty();
  Node nextNode(); // removes Node; pre-condition: isEmpty == false
}
