package search;

public class TreeSearch implements Search {
  private final Frontier frontier;

  public TreeSearch(Frontier frontier) {
    this.frontier = frontier;
  }

  public Node runSearch(Node root, GoalTest goalTest) {
    if (frontier == null || root == null || goalTest == null) {
      throw new RuntimeException();
    }
    frontier.clear();
    frontier.addNode(root);
    Analytics.getInstance().newNodeGenerated();
		while (!frontier.isEmpty()) {
			Node node = frontier.nextNode();
			if (goalTest.isGoal(node.state))
				return node;
			else {
				for (Action action : node.state.getApplicableActions()) {
					State newState = node.state.getActionResult(action);
					frontier.addNode(new Node(node, action, newState));
          Analytics.getInstance().newNodeGenerated();
				}
			}
		}
		return null;
  }
}
