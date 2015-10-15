package search;

public class IterativeDeepeningTreeSearch implements Search {

  private static class DeepeningNode extends Node {
    public final int depth;

    public DeepeningNode(Node parent, Action action, State state, int depth) {
      super(parent, action, state);
      this.depth = depth;
    }
  }

  public Node runSearch(Node root, GoalTest goalTest) {
    if (root == null || goalTest == null) {
      throw new RuntimeException();
    }
    Frontier frontier = new DepthFirstFrontier();
    for (int maxDepth = 0; ; maxDepth++) {
      frontier.addNode(new DeepeningNode(root.parent, root.action, root.state, 0));
      Analytics.getInstance().newNodeGenerated();
  		while (!frontier.isEmpty()) {
  			DeepeningNode node = (DeepeningNode) frontier.nextNode();
  			if (goalTest.isGoal(node.state))
  				return node;
        else if (node.depth < maxDepth) {
  				for (Action action : node.state.getApplicableActions()) {
  					State newState = node.state.getActionResult(action);
  					frontier.addNode(new DeepeningNode(node, action, newState, node.depth + 1));
            Analytics.getInstance().newNodeGenerated();
  				}
  			}
  		}
    }
		//return null;
  }
}
