package search;

public class Node {
	public final Node parent;
	public final Action action;
	public final State state;
	public int rootDistance;

	public Node(Node parent, Action action, State state) {
		this.parent = parent;
		this.action = action;
		this.state = state;
		if (parent == null) { // root
			this.rootDistance = 0;
		}
		else {
			this.rootDistance = this.parent.rootDistance + action.getCost();
		}
	}
}
