package npuzzle;

import search.*;

public class BFTS_Demo {
	public static final boolean TREE = false;
	public static final boolean BFS = false;
	public static final boolean ITD = true;

	public static void main(String[] args) {
		System.out.println("This is a demonstration of breadth-first tree search on 8-puzzle");
		System.out.println();

		Tiles initialConfiguration = new Tiles(new int[][] {
			{ 7, 4, 2 },
			{ 8, 1, 3 },
			{ 5, 0, 6 }
		});

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

		GoalTest goalTest = new TilesGoalTest();
		Node root = new Node(null, null, initialConfiguration);
		Node solution = search.runSearch(root, goalTest);

		new NPuzzlePrinting().printSolution(solution);

		System.out.println(Analytics.getInstance().getNumNodes() + " nodes generated.");
		System.out.println("max frontier size: " + Analytics.getInstance().getMaxFrontierSize());
	}
}
