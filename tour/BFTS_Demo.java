package tour;

import search.*;

public class BFTS_Demo {
	public static final boolean TREE = false;
	public static final boolean BFS = false;
	public static final boolean ITD = true;

	public static void main(String[] args) {
		System.out.println("This is a demonstration of breadth-first tree search on Romania tour");
		System.out.println();

		Frontier frontier = null;
		if (BFS) {
			frontier = new BreadthFirstFrontier();
		}
		else {
			frontier = new DepthFirstFrontier();
		}

		Search search = null;
		if (ITD) {
			search = new IterativeDeepeningTreeSearch();
		}
		else if (TREE) {
			search = new TreeSearch(frontier);
		}
		else {
			search = new GraphSearch(frontier);
		}

		Cities romania = SetUpRomania.getRomaniaMap();
		City startCity = romania.getState("Bucharest");

		GoalTest goalTest = new TourGoalTest(romania.getAllCities(), startCity);
		Node root = new Node(null, null, new TourState(startCity));
		Node solution = search.runSearch(root, goalTest);
		new TourPrinting().printSolution(solution);

		System.out.println(Analytics.getInstance().getNumNodes() + " nodes generated.");
		System.out.println("max frontier size: " + Analytics.getInstance().getMaxFrontierSize());
	}
}
