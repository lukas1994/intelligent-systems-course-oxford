package search;

public class AStarFunction implements NodeFunction {
  private final NodeFunction heuristicFunction;

  public AStarFunction(NodeFunction heuristicFunction) {
    this.heuristicFunction = heuristicFunction;
  }

  public int calculate(Node node) {
    return node.rootDistance + heuristicFunction.calculate(node);
  }
}
