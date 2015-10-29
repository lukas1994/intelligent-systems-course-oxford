package npuzzle;

import search.*;

public class BFTS_Demo {
	public static final boolean TREE = false;
	public static final boolean BFS = false;
	public static final boolean ITD = true;

	public static void main(String[] args) {
		Tiles initialConfiguration = new Tiles(new int[][] {
			{ 7, 4, 2 },
			{ 8, 1, 3 },
			{ 5, 0, 6 }
		});

		NodeFunction nodeFunction = new AStarFunction(new MisplacedTilesHeuristicFunction());
		Frontier frontier = new BestFirstFrontier(nodeFunction);

		Search search = new GraphSearch(frontier);

		GoalTest goalTest = new TilesGoalTest();
		Node root = new Node(null, null, initialConfiguration);
		Node solution = search.runSearch(root, goalTest);

		new NPuzzlePrinting().printSolution(solution);

		System.out.println(Analytics.getInstance().getNumNodes() + " nodes generated.");
		System.out.println("max frontier size: " + Analytics.getInstance().getMaxFrontierSize());
	}
}
