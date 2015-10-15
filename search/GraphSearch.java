package search;

import java.util.HashSet;

public class GraphSearch implements Search {
  private final Frontier frontier;
  private final HashSet<State> visited;

  public GraphSearch(Frontier frontier) {
    this.frontier = frontier;
    this.visited = new HashSet<State>();
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
          if (!visited.contains(newState)) {
					  frontier.addNode(new Node(node, action, newState));
            visited.add(newState);
            Analytics.getInstance().newNodeGenerated();
          }
				}
			}
		}
		return null;
  }
}
